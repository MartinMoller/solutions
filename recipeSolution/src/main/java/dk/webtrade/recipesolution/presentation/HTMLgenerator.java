/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.webtrade.recipesolution.presentation;

import dk.webtrade.recipesolution.data.DataMapper;
import dk.webtrade.recipesolution.logik.entity.Recipe;
import java.util.List;

/**
 *
 * @author thomas
 */
public class HTMLgenerator {
    public static String recipes2Table(List<Recipe> recipes){
        String table = "<table class=\"table table-striped\"><thead><tr>THROW</tr></thead><tbody>TBROW</tbody></table>";
        String headerRow = "<th>ID</th><th>RECIPE NAME</th>";
        String dataRow = recipes.stream().map((r)->{return "<tr><td>"+r.getId()+"</td><td><a href=\"Control?origin=showRecipe&recipeId="+r.getId()+"\">"+r.getName()+"</a></td></tr>";}).reduce("",(el, acc)->{return el+acc;});
        return table.replace("THROW", headerRow).replace("TBROW", dataRow);
    }
    public static String recipe2HTML(Recipe recipe){
        String container = "<div id=\"container\"><img src=\"img/IMG\"/><div><h3>NAME</h3> by <span>USER</span></div><div>Tilberedelsestid: TIME</div><div>INGREDIENTS</div><div>TODO</div></div>";
        final String NAME = recipe.getName();
        final String USER = recipe.getUser().getUsername();
        final String TIME = ""+recipe.getCookingTime();
        final String TODO = ""+recipe.getTodo();
        final String IMG  = recipe.getImg();
        final String INGREDIENTS = "<ul>"+recipe.getIngredients().stream().map(ing->"<li>"+ing.getIngredient().getName()+" - " + ing.getAmount()+" - "+ing.getMeasure()).reduce("", (acc,combiner)->acc+combiner);
        return container.replace("NAME", NAME).replace("USER", USER).replace("TIME", TIME).replace("TODO", TODO).replace("IMG", IMG).replace("INGREDIENTS", INGREDIENTS);
    }
    
    public static void main(String[] args) {
        try {
            String res = recipes2Table(new DataMapper().getAllRecipes());
            System.out.println(res);
            System.out.println("-----------------------------------------------------------");
            String res1 = recipe2HTML(new DataMapper().getRecipe(1));
            System.out.println(res1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
