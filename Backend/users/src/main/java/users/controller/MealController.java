package users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import users.model.dto.BuyMealRequest;
import users.model.dto.CardResponse;
import users.service.MealService;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping("/buy")
    @PreAuthorize("hasRole('STUDENT')")
    public CardResponse buy(@RequestBody BuyMealRequest request) {
        return mealService.buyMeal(request);
    }

    @PatchMapping("/{cardId}/consume")
    @PreAuthorize("hasRole('COOK')")
    public CardResponse consume(@PathVariable Long cardId, @RequestBody BuyMealRequest request) {
        return mealService.consumeMeal(cardId, request);
    }

}