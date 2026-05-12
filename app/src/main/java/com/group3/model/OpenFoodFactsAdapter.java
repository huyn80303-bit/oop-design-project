package com.group3.model;

public class OpenFoodFactsAdapter implements INutrition {
 private final OpenFoodFactsResponse response;

    public OpenFoodFactsAdapter(OpenFoodFactsResponse response) {
        this.response = response;
    }

    @Override
    public String getFoodName() {
        return response.getProduct_name() != null
                ? response.getProduct_name() : "Unknown";
    }

    @Override
    public double getCalories() {
        return response.getNutriments() != null
                ? response.getNutriments().getEnergy_kcal_100g() : 0;
    }

    @Override
    public double getProtein() {
        return response.getNutriments() != null
                ? response.getNutriments().getProteins_100g() : 0;
    }

    @Override
    public double getCarbs() {
        return response.getNutriments() != null
                ? response.getNutriments().getCarbohydrates_100g() : 0;
    }

    @Override
    public double getFat() {
        return response.getNutriments() != null
                ? response.getNutriments().getFat_100g() : 0;
    }
}
