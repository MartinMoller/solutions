/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.webtrade.recipesolution.logik.entity;

/**
 *
 * @author thomas
 */
public class RecipeIngredient {
    Ingredient ingredient;
    String amount;
    String measure;

    public RecipeIngredient(Ingredient ingredient, String amount, String measure) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.measure = measure;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
    
}
