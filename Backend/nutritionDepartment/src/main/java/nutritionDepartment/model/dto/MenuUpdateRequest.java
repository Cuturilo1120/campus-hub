package nutritionDepartment.model.dto;

public record MenuUpdateRequest(
        Double breakfastPriceBudget,
        Double lunchPriceBudget,
        Double dinnerPriceBudget,
        Double breakfastPriceSelfFinance,
        Double lunchPriceSelfFinance,
        Double dinnerPriceSelfFinance
) {}
