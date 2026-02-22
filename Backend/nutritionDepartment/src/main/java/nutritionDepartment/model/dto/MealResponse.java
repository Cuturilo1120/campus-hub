package nutritionDepartment.model.dto;

import nutritionDepartment.model.MealType;

public record MealResponse(MealType mealType, int amount) {}
