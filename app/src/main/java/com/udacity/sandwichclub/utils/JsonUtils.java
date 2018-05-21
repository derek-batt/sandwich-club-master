package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    //JSON keys
    private static final String SAND_IMAGE = "image";
    private static final String SAND_NAME = "name";
    private static final String SAND_MAIN_NAME = "mainName";
    private static final String SAND_AKA = "alsoKnownAs";
    private static final String SAND_ORIGIN = "placeOfOrigin";
    private static final String SAND_DESCRIPTION = "description";
    private static final String SAND_INGREDIENTS = "ingredients";

    /**
     * This method parses JSON for a specific sandwich and structures the data to return a Sandwich object
     *
     * @param json JSON string for clicked sandwich
     *
     * @return Sandwich Object describing sandwich data or null if JSON data cannot be properly parsed
     */
    public static Sandwich parseSandwichJson(String json) {

        //Sandwich object data to be populated
        String sandwichMainName;
        List<String> sandwichAlsoKnownAs = new ArrayList<>();
        String sandwichOrigin;
        String sandwichDescription;
        String sandwichImage;
        List<String> sandwichIngredients = new ArrayList<>();

        try {
            //root is { JSONObject
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameJson = sandwichJson.getJSONObject(SAND_NAME);
            sandwichMainName = nameJson.optString(SAND_MAIN_NAME);//use opt instead of get because some values are blank
            JSONArray alsoKnownAsArray = nameJson.getJSONArray(SAND_AKA);
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {//could abstract as a function for aka and ingredients if repeated more
                sandwichAlsoKnownAs.add(alsoKnownAsArray.optString(i));
            }
            sandwichOrigin = sandwichJson.getString(SAND_ORIGIN);
            sandwichDescription = sandwichJson.getString(SAND_DESCRIPTION);
            sandwichImage = sandwichJson.getString(SAND_IMAGE);
            JSONArray ingredientsArray = sandwichJson.getJSONArray(SAND_INGREDIENTS);
            for (int j = 0; j < ingredientsArray.length(); j++) {
                sandwichIngredients.add(ingredientsArray.getString(j));
            }

        } catch (JSONException e) {
            return null;
        }
        return new Sandwich(sandwichMainName, sandwichAlsoKnownAs, sandwichOrigin, sandwichDescription, sandwichImage, sandwichIngredients);
    }
}

