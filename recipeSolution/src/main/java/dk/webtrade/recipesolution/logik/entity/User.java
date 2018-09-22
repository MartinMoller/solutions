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
public class User {
    int id;
    String username;
    String password;
    List<Recipe> recipes = new ArrayList();

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipes(Recipe e) {
        this.recipes.add(e);
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
