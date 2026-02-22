package users.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import users.model.dto.BuyMealRequest;
import users.model.dto.CardResponse;
import users.repository.StudentRepository;

import java.util.Map;

@Service
public class MealService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @Value("${nutrition.service.url}")
    private String nutritionServiceUrl;

    public CardResponse consumeMeal(Long cardId, BuyMealRequest request) {
        try {
            return restTemplate.exchange(
                    nutritionServiceUrl + "/cards/" + cardId + "/meals/consume",
                    org.springframework.http.HttpMethod.PATCH,
                    new org.springframework.http.HttpEntity<>(request),
                    CardResponse.class
            ).getBody();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new EntityNotFoundException("Card not found");
            }
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new IllegalArgumentException("No remaining meals of that type on this card");
            }
            throw ex;
        }
    }

    public CardResponse buyMeal(BuyMealRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long studentId = studentRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"))
                .getId();

        Map<String, String> body = Map.of("mealType", request.mealType());
        try {
            return restTemplate.postForObject(
                    nutritionServiceUrl + "/cards/student/" + studentId + "/buy",
                    body,
                    CardResponse.class
            );
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new EntityNotFoundException("Card not found");
            }
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new IllegalArgumentException("Insufficient balance");
            }
            throw ex;
        }
    }
}