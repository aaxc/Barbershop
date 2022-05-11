package com.barbershop.controller;

import com.barbershop.model.Order;
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

@WebServlet(name = "ReservationsController", value = "/reservations")
public class ReservationsController extends HttpServlet
{
    // Load services
    private final MenuService menuService = new MenuService();
    private final CalendarService calendarService = new CalendarService();

    /**
     * Main data processor
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Load menu
        request.setAttribute("menuList", menuService.getMenu("reservations"));

        // Set calendar
        request.setAttribute("calendarEvents", calendarService.getEventsAsJson());

        // Make dispatcher and redirect to view
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reservations.jsp");
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
        // @TODO Make a DTO for this!
        String name = request.getParameter("name");
        String date = request.getParameter("date");

        System.out.println(name + " " + date);

        processRequest(request, response);
    }
}