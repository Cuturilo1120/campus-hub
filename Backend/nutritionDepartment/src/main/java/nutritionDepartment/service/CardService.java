package nutritionDepartment.service;

import jakarta.persistence.EntityNotFoundException;
import nutritionDepartment.model.Card;
import nutritionDepartment.model.Meal;
import nutritionDepartment.model.MealType;
import nutritionDepartment.model.Menu;
import nutritionDepartment.model.dto.BuyMealRequest;
import nutritionDepartment.model.dto.CardRequest;
import nutritionDepartment.model.dto.CardResponse;
import nutritionDepartment.model.dto.FundsRequest;
import nutritionDepartment.model.dto.MealResponse;
import nutritionDepartment.repository.CardRepository;
import nutritionDepartment.repository.MealRepository;
import nutritionDepartment.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private MenuRepository menuRepository;

    public CardResponse createCard(CardRequest request) {
        if (cardRepository.existsByStudentId(request.studentId())) {
            throw new IllegalArgumentException("Student already has a card");
        }
        Card card = new Card();
        card.setStudentId(request.studentId());
        card.setBalance(request.balance());
        card.setExpirationDate(request.expirationDate());
        Card saved = cardRepository.save(card);

        Arrays.stream(MealType.values()).forEach(type -> {
            Meal meal = new Meal();
            meal.setMealType(type);
            meal.setAmount(0);
            meal.setCard(saved);
            mealRepository.save(meal);
        });

        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<CardResponse> getAllCards() {
        return cardRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CardResponse getCardById(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
        return toResponse(card);
    }

    public CardResponse renewExpiration(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
        Date renewed = Date.from(
                Instant.ofEpochMilli(card.getExpirationDate().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                        .plusYears(1)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()
        );
        card.setExpirationDate(renewed);
        return toResponse(cardRepository.save(card));
    }

    public CardResponse addFunds(Long id, FundsRequest request) {
        if (request.amount() == null || request.amount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
        card.setBalance(card.getBalance() + request.amount());
        return toResponse(cardRepository.save(card));
    }

    public void deleteCard(Long id) {
        if (!cardRepository.existsById(id)) {
            throw new EntityNotFoundException("Card not found");
        }
        cardRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CardResponse getCardByStudentId(Long studentId) {
        Card card = cardRepository.findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found for student"));
        return toResponse(card);
    }

    @Transactional
    public CardResponse buyMeal(Long studentId, BuyMealRequest request) {
        Card card = cardRepository.findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found for student"));

        Menu menu = menuRepository.findById(1L).orElseThrow();
        double price = switch (request.mealType()) {
            case BREAKFAST -> menu.getBreakfastPrice();
            case LUNCH -> menu.getLunchPrice();
            case DINNER -> menu.getDinnerPrice();
        };

        if (card.getBalance() < price) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        card.setBalance(card.getBalance() - price);
        cardRepository.save(card);

        Meal meal = mealRepository.findByCardIdAndMealType(card.getId(), request.mealType())
                .stream().findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Meal not found on card"));
        meal.setAmount(meal.getAmount() + 1);
        mealRepository.save(meal);

        return toResponse(card);
    }

    @Transactional
    public CardResponse consumeMeal(Long cardId, BuyMealRequest request) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));

        Meal meal = mealRepository.findByCardIdAndMealType(cardId, request.mealType())
                .stream().findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Meal not found on card"));

        if (meal.getAmount() <= 0) {
            throw new IllegalArgumentException("No remaining " + request.mealType().name().toLowerCase() + " meals on this card");
        }

        meal.setAmount(meal.getAmount() - 1);
        mealRepository.save(meal);

        return toResponse(card);
    }

    private CardResponse toResponse(Card card) {
        List<MealResponse> meals = card.getMeals().stream()
                .map(m -> new MealResponse(m.getMealType(), m.getAmount()))
                .toList();
        return new CardResponse(card.getId(), card.getBalance(), card.getExpirationDate(), card.getStudentId(), meals);
    }
}
