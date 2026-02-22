package nutritionDepartment.controller;

import nutritionDepartment.model.dto.BuyMealRequest;
import nutritionDepartment.model.dto.CardRequest;
import nutritionDepartment.model.dto.CardResponse;
import nutritionDepartment.model.dto.FundsRequest;
import nutritionDepartment.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse create(@RequestBody CardRequest request) {
        return cardService.createCard(request);
    }

    @GetMapping
    public List<CardResponse> getAll() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public CardResponse getOne(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @PatchMapping("/{id}/renew")
    public CardResponse renew(@PathVariable Long id) {
        return cardService.renewExpiration(id);
    }

    @PatchMapping("/{id}/funds")
    public CardResponse addFunds(@PathVariable Long id, @RequestBody FundsRequest request) {
        return cardService.addFunds(id, request);
    }

    @GetMapping("/student/{studentId}")
    public CardResponse getByStudentId(@PathVariable Long studentId) {
        return cardService.getCardByStudentId(studentId);
    }

    @PostMapping("/student/{studentId}/buy")
    public CardResponse buyMeal(@PathVariable Long studentId, @RequestBody BuyMealRequest request) {
        return cardService.buyMeal(studentId, request);
    }

    @PatchMapping("/{id}/meals/consume")
    public CardResponse consumeMeal(@PathVariable Long id, @RequestBody BuyMealRequest request) {
        return cardService.consumeMeal(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
