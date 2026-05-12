package com.group3.api;
import com.group3.model.Nutrition;
import com.group3.model.OpenFoodFactsAdapter;
import com.group3.model.OpenFoodFactsResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
public class OpenFoodFactsAPI {
 private static final String BASE_URL =
        "https://world.openfoodfacts.org/cgi/search.pl?search_terms=%s&json=true";

    public Nutrition fetchNutritionData(String productName) throws Exception {
        String urlStr = String.format(BASE_URL, productName.replace(" ", "+"));
        
        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "GymTrackingApp/1.0");

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream())
        );
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        reader.close();

      
        JSONObject json = new JSONObject(sb.toString());
        JSONObject product = json.getJSONArray("products").getJSONObject(0);

     
        OpenFoodFactsResponse raw = new OpenFoodFactsResponse();
        raw.setProduct_name(product.optString("product_name", productName));

        OpenFoodFactsResponse.Nutriments n = new OpenFoodFactsResponse.Nutriments();
        if (product.has("nutriments")) {
            JSONObject nm = product.getJSONObject("nutriments");
            n.setEnergy_kcal_100g(nm.optDouble("energy-kcal_100g", 0));
            n.setProteins_100g(nm.optDouble("proteins_100g", 0));
            n.setCarbohydrates_100g(nm.optDouble("carbohydrates_100g", 0));
            n.setFat_100g(nm.optDouble("fat_100g", 0));
        }
        raw.setNutriments(n);

      
        return new OpenFoodFactsAdapter(raw);
    }
}
