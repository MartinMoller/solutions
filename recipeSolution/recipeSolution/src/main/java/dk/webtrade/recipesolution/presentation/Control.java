/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.webtrade.recipesolution.presentation;

import dk.webtrade.recipesolution.data.DataMapper;
import dk.webtrade.recipesolution.logik.entity.Recipe;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thomas
 */
@WebServlet(name = "Control", urlPatterns = {"/Control"})
public class Control extends HttpServlet {

    DataMapper dm = new DataMapper();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. return table;
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String origin = request.getParameter("origin");
            if (origin != null) {
                switch (origin) {
                    case "login":
                        request.setAttribute("message", "Login method is not yet implemented");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        break;
                    case "register":
                        request.setAttribute("message", "Register method is not yet implemented");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        break;
                    case "addRecipe":
                        request.setAttribute("message", "AddRecipe method is not yet implemented");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        break;
                    case "showRecipes":
                        List<Recipe> recipes = dm.getAllRecipes();
                        String table = HTMLgenerator.recipes2Table(recipes);
                        request.setAttribute("table", table);
                        request.getRequestDispatcher("showRecipes.jsp").forward(request, response);
                        break;
                    case "showRecipe":
                        //                    request.setAttribute("message", "showRecipe method is not yet implemented");
                        //                    request.getRequestDispatcher("error.jsp").forward(request, response);
                        String recipeStr = request.getParameter("recipeId");
                        if (recipeStr != null) {
                            int recipeId = Integer.parseInt(recipeStr);
                            String recipeHTML = HTMLgenerator.recipe2HTML(dm.getRecipe(recipeId));
                            request.setAttribute("recipe", recipeHTML);
                            request.getRequestDispatcher("showRecipe.jsp").forward(request, response);
                        } else {
                            request.setAttribute("message", "That recipe is not available");
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                        }
                        break;
                    default:
                        throw new AssertionError("The request is not supported by the server");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        request.setAttribute("message", "This service requires you to come from a known origin");
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
