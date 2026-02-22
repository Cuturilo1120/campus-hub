package users.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import users.model.dto.CardRequest;
import users.model.dto.CardResponse;
import users.repository.StudentRepository;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @Value("${nutrition.service.url}")
    private String nutritionServiceUrl;

    public CardResponse createCard(CardRequest request) {
        if (!studentRepository.existsById(request.studentId())) {
            throw new EntityNotFoundException("Student not found");
        }
        try {
            return restTemplate.postForObject(nutritionServiceUrl + "/cards", request, CardResponse.class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new IllegalArgumentException("Student already has a card");
            }
            throw ex;
        }
    }

    public List<CardResponse> getAllCards() {
        return restTemplate.exchange(
                nutritionServiceUrl + "/cards",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CardResponse>>() {}
        ).getBody();
    }

    public CardResponse getCardById(Long id) {
        try {
            return restTemplate.getForObject(nutritionServiceUrl + "/cards/" + id, CardResponse.class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new EntityNotFoundException("Card not found");
            }
            throw ex;
        }
    }

    public void deleteCard(Long id) {
        try {
            restTemplate.delete(nutritionServiceUrl + "/cards/" + id);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new EntityNotFoundException("Card not found");
            }
            throw ex;
        }
    }
}
