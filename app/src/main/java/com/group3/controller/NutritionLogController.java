package com.group3.controller;

import com.group3.model.Nutrition;
import com.group3.model.OpenFoodFactsAPI;

public class NutritionLogController {
private final OpenFoodFactsAPI openFoodFactsAPI = new OpenFoodFactsAPI();

   
    public Nutrition lookupNutrition(String productName) throws Exception {
        return openFoodFactsAPI.fetchNutritionData(productName);
    }

    public void saveNutritionLog(Nutrition nutrition) {
    
        System.out.println("Saved: " + nutrition.getFoodName()
            + " | Cal: " + nutrition.getCalories());
    }
}
