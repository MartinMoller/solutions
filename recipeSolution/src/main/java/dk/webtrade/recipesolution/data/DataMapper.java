/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.webtrade.recipesolution.data;

import dk.webtrade.recipesolution.logik.entity.Ingredient;
import dk.webtrade.recipesolution.logik.entity.Recipe;
import dk.webtrade.recipesolution.logik.entity.RecipeIngredient;
import dk.webtrade.recipesolution.logik.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thomas
 */
public class DataMapper {

    /*
    Methods:
    getUsers()
    getUser(int id)
    getAllRecipes()
    getAllRecipes(User user)
    getRecipe(int id)
    getAllIngredients()
    getIngredientByName(String name)
    ----------------
    addUser(User user)
    addRecipe(Recipe recipe)
    addIngredient(Ingredient i)
    
     */
    private final String GET_ALL_USERS = "SELECT id, username, password FROM recipesDB.User";
    private final String GET_USER_BY_ID = "SELECT id, username, password FROM recipesDB.User WHERE id = ?";
    private final String GET_ALL_RECIPES = "SELECT id, name, description, todo, cookingtime, image FROM recipesDB.Recipe;";
    private final String GET_RECIPES_BY_USER = "SELECT id, name, description, todo, cookingtime, image FROM recipesDB.Recipe WHERE User_id = ? ORDER BY id ASC";
    private final String GET_RECIPE_BY_ID = "SELECT User_id, id, name, description, todo, cookingtime, image FROM recipesDB.Recipe WHERE id = ?";
    private final String GET_ALL_INGREDIENTS = "SELECT id, name FROM recipesDB.Ingredient";
    private final String GET_INGREDIENT_BY_NAME = "SELECT id, name FROM recipesDB.Ingredient WHERE name = ?";
    private final String GET_INGREDIENTS_BY_RECIPE_ID = "SELECT Ingredient_id, name, amount, measure FROM Recipe_has_Ingredient,Ingredient WHERE Recipe_has_Ingredient.Ingredient_id = Ingredient.id AND Recipe_has_Ingredient.Recipe_id = ?";
    private final String ADD_NEW_USER = "INSERT INTO User(username,password) VALUES (?,?);";
    private final String ADD_NEW_RECIPE = "INSERT INTO Recipe (name,description,todo,cookingtime,image,User_id)VALUES(?,?,?,?,?,?)";
    private final String ADD_INGREDIENT_TO_RECIPE = "INSERT INTO Recipe_has_Ingredient(Recipe_id,Ingredient_id,amount,measure)VALUES(?,?,?,?);";
    private final String ADD_NEW_INGREDIENT = "INSERT INTO Ingredient(name)VALUES(?)";

    public static void main(String[] args) {
        try {
            DataMapper dm = new DataMapper();
            System.out.println("---------------------------------------------TEST: getUsers");
            dm.getUsers().forEach((user) -> {System.out.println(user.getUsername());});
            System.out.println("---------------------------------------------TEST: getIngredientByName()");
            String ingredientName = dm.getIngredientByName("mel").getName();
            System.out.println("Ingredient found: "+ingredientName);
            System.out.println("---------------------------------------------TEST: getUser()");
            User u = dm.getUser(1);
            String username = u.getUsername();
            System.out.println("USER found: "+username);
            System.out.println("---------------------------------------------TEST: addRecipe");
            Recipe recipe = new Recipe("Naan brød", "Indisk brød bagt på pande", "Bland ingredienserne sammen og ælt dejen grundigt. Rul små kugler af dejen. Rul kuglerne til flade brød og steg dem på panden", 20, "naan.jpg");
            recipe.addIngredient(new RecipeIngredient(dm.getIngredientByName("mel"), "500", "ml"));
            recipe.addIngredient(new RecipeIngredient(dm.getIngredientByName("salt"), "1", "tskf"));
            recipe.addIngredient(new RecipeIngredient(dm.getIngredientByName("mælk"), "200", "ml"));
            recipe.setUser(u);
//            dm.addRecipe(recipe); //THIS WILL ADD ANOTHER NAAN BRØD
            System.out.println("----------------------------------------------TEST: getAllRecipes");
            dm.getAllRecipes().forEach((r) -> {
                System.out.println(r.getName() + " from " + r.getUser().getUsername());
            });
            System.out.println("----------------------------------------------TEST: getRecipe(int id)");
            Recipe r = dm.getRecipe(1);
            System.out.println("Recipe id: 1: "+r.getName());
            System.out.println("----------------------------------------------TEST: getAllIngredients");
            dm.getAllIngredients().forEach((ingredient) -> {
                System.out.println(ingredient.getId() + " : " + ingredient.getName());
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList();
        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(GET_ALL_USERS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(id, username, password);
                List<Recipe> recipes = getAllRecipesByUser(user);
                user.setRecipes(recipes);
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public List<Recipe> getAllRecipesByUser(User user) {
        List<Recipe> recipes = new ArrayList();
        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(GET_RECIPES_BY_USER);
            pstmt.setInt(1, user.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String todo = rs.getString("todo");
                int cookingtime = rs.getInt("cookingtime");
                String image = rs.getString("image");
                Recipe recipe = new Recipe(id, name, description, todo, cookingtime, image);
                recipe.setUser(user);
                recipes.add(recipe);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recipes;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList();
        getUsers().forEach((user) -> {
            user.getRecipes().forEach((recipe) -> {
                recipes.add(recipe);
            });
        });
        return recipes;
    }

    //This one is complex because to persist a recipe we must also persist each individual ingredient.
    public Recipe addRecipe(Recipe recipe) throws Exception {
        List<RecipeIngredient> ingredientItems = recipe.getIngredients();
        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(ADD_NEW_RECIPE, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, recipe.getName());
            pstmt.setString(2, recipe.getDescription());
            pstmt.setString(3, recipe.getTodo());
            pstmt.setInt(4, recipe.getCookingTime());
            pstmt.setString(5, null);
            pstmt.setInt(6, recipe.getUser().getId());
            int result = pstmt.executeUpdate();
            if (result == 0) {
                throw new Exception("Recipe could not be added to database");
            } else {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    recipe.setId(id);
                }
                rs.close();
            }
            pstmt.close();

            for (RecipeIngredient ingredientItem : ingredientItems) {
                PreparedStatement pstmt2 = con.prepareStatement(ADD_INGREDIENT_TO_RECIPE);
                pstmt2.setInt(1, recipe.getId());
                pstmt2.setInt(2, ingredientItem.getIngredient().getId());
                pstmt2.setString(3, ingredientItem.getAmount());
                pstmt2.setString(4, ingredientItem.getMeasure());
                int result2 = pstmt2.executeUpdate();
                if (result2 == 0) {
                    throw new Exception("Ingredient " + ingredientItem.getIngredient().getName() + " could not be persisted");
                }
                pstmt2.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recipe;
    }
    public Recipe getRecipe(int id) throws Exception{
        
        Recipe recipe = null;
        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(GET_RECIPE_BY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("User_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String todo = rs.getString("todo");
                int cookingtime = rs.getInt("cookingtime");
                String image = rs.getString("image");
                recipe = new Recipe(id, name, description, todo, cookingtime, image);
                recipe.setUser(getUser(userId));
                
            } else {
                throw new Exception("No such User found");
            }
            pstmt.close();
            rs.close();
            PreparedStatement pstmt2 = con.prepareStatement(GET_INGREDIENTS_BY_RECIPE_ID);
            pstmt2.setInt(1, id);
            ResultSet rs2 = pstmt2.executeQuery();
            while(rs2.next()){
                int ingId = rs2.getInt("Ingredient_id");
                String name = rs2.getString("name");
                String amount = rs2.getString("amount");
                String measure = rs2.getString("measure");
                Ingredient in = new Ingredient(ingId, name);
                RecipeIngredient rIng = new RecipeIngredient(in, amount, measure);
                recipe.addIngredient(rIng);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recipe;
    
    }

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = new ArrayList();
        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(GET_ALL_INGREDIENTS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("name");
                Ingredient ingredient = new Ingredient(id, username);
                ingredients.add(ingredient);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ingredients;
    }

    public Ingredient getIngredientByName(String name) throws Exception {
        Ingredient ingredient = null;
        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(GET_INGREDIENT_BY_NAME);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String ingredientName = rs.getString("name");
                ingredient = new Ingredient(id, ingredientName);
            } else {
                throw new Exception("No such ingredient found");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ingredient;
    }
    public User getUser(int id) throws Exception{
        User user = null;
        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement(GET_USER_BY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                user = new User(id, username);
            } else {
                throw new Exception("No such User found");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
