package users.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import users.model.dto.CardRequest;
import users.model.dto.CardResponse;
import users.model.dto.CardStatusResponse;
import users.model.dto.FundsRequest;
import users.repository.StudentRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;

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
        Date expirationDate = Date.from(
                LocalDate.now().plusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
        Map<String, Object> payload = Map.of(
                "studentId", request.studentId(),
                "balance", 0.0,
                "expirationDate", expirationDate
        );
        try {
            return restTemplate.postForObject(nutritionServiceUrl + "/cards", payload, CardResponse.class);
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

    public CardResponse renewCard(Long id) {
        try {
            return restTemplate.exchange(
                    nutritionServiceUrl + "/cards/" + id + "/renew",
                    HttpMethod.PATCH,
                    null,
                    CardResponse.class
            ).getBody();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new EntityNotFoundException("Card not found");
            }
            throw ex;
        }
    }

    public CardResponse addFunds(Long id, FundsRequest request) {
        try {
            return restTemplate.exchange(
                    nutritionServiceUrl + "/cards/" + id + "/funds",
                    HttpMethod.PATCH,
                    new HttpEntity<>(request),
                    CardResponse.class
            ).getBody();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new EntityNotFoundException("Card not found");
            }
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new IllegalArgumentException("Amount must be greater than zero");
            }
            throw ex;
        }
    }

    public CardStatusResponse getMyCard() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long studentId = studentRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"))
                .getId();
        try {
            CardResponse card = restTemplate.getForObject(
                    nutritionServiceUrl + "/cards/student/" + studentId, CardResponse.class);
            return new CardStatusResponse(card, null);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                return new CardStatusResponse(null,
                        "You don't have a card yet. Please visit the student service office to get one.");
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
