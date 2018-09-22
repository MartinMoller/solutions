/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.webtrade.recipesolution.logik.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thomas
 */
public class Recipe {
    int id;
    String name;
    String description;
    String todo;
    int cookingTime;
    String img;
    List<RecipeIngredient> ingredients = new ArrayList();
    User user;

    public Recipe(int id, String name, String description, String todo, int cookingTime, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.todo = todo;
        this.cookingTime = cookingTime;
        this.img = img;
    }
    public Recipe(String name, String description, String todo, int cookingTime, String img) {
        this.name = name;
        this.description = description;
        this.todo = todo;
        this.cookingTime = cookingTime;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(RecipeIngredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
