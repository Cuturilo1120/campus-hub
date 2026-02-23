package users.model.dto;

public record MenuResponse(
        Double breakfastPriceBudget,
        Double lunchPriceBudget,
        Double dinnerPriceBudget,
        Double breakfastPriceSelfFinance,
        Double lunchPriceSelfFinance,
        Double dinnerPriceSelfFinance
) {}
