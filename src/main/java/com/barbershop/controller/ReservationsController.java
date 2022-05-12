package com.barbershop.controller;

import com.barbershop.repository.dto.OrderDTO;
import com.barbershop.service.CalendarService;
import com.barbershop.service.MenuService;
import com.barbershop.service.ServicesService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ReservationsController", value = "/reservations")
public class ReservationsController extends HttpServlet
{
    // Load services
    private final MenuService menuService = new MenuService();
    private final CalendarService calendarService = new CalendarService();
    private final ServicesService servicesService = new ServicesService();

    /**
     * Main data processor
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Load menu
        request.setAttribute("menuList", menuService.getMenu("reservations"));

        // Set available services
        request.setAttribute("servicesList", servicesService.getServices());

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
        calendarService.saveEvent(new OrderDTO(
                request.getParameter("name"),
                request.getParameter("service"),
                request.getParameter("date")
        ));

        processRequest(request, response);
    }
}