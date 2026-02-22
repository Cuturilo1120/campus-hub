package nutritionDepartment.service;

import jakarta.persistence.EntityNotFoundException;
import nutritionDepartment.model.Card;
import nutritionDepartment.model.dto.CardRequest;
import nutritionDepartment.model.dto.CardResponse;
import nutritionDepartment.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public CardResponse createCard(CardRequest request) {
        if (cardRepository.existsByStudentId(request.studentId())) {
            throw new IllegalArgumentException("Student already has a card");
        }
        Card card = new Card();
        card.setStudentId(request.studentId());
        card.setBalance(request.balance());
        card.setExpirationDate(request.expirationDate());
        Card saved = cardRepository.save(card);
        return toResponse(saved);
    }

    public List<CardResponse> getAllCards() {
        return cardRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public CardResponse getCardById(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
        return toResponse(card);
    }

    public void deleteCard(Long id) {
        if (!cardRepository.existsById(id)) {
            throw new EntityNotFoundException("Card not found");
        }
        cardRepository.deleteById(id);
    }

    private CardResponse toResponse(Card card) {
        return new CardResponse(card.getId(), card.getBalance(), card.getExpirationDate(), card.getStudentId());
    }
}
