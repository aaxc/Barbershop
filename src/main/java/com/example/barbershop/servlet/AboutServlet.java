package com.example.barbershop.servlet;

import com.example.barbershop.service.MenuService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AboutServlet", value = "/about")
public class AboutServlet extends HttpServlet
{
    // Load services
    private final MenuService menuService = new MenuService();

    /**
     * Main data processor
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Load menu
        request.setAttribute("menuList", menuService.getMenu("about"));

        // Make dispatcher and redirect to view
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/about.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * GET method
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * POST method
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }
}