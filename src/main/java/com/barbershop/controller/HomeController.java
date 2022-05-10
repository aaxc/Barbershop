package com.barbershop.controller;

import com.barbershop.model.OrderItem;
import com.barbershop.service.CalendarService;
import com.barbershop.service.MenuService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/")
public class HomeController extends HttpServlet
{
    // Load services
    private final MenuService menuService = new MenuService();
    private final CalendarService calendarService = new CalendarService();

    /**
     * Main data processor
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // @TODO Remove test
        List<OrderItem> test = calendarService.getEvents();
        for (OrderItem t : test) {
            System.out.println(t.getTimestampFrom());
        }

        // Load menu
        request.setAttribute("menuList", menuService.getMenu("home"));

        // Make dispatcher and redirect to view
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
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