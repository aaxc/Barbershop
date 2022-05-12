package com.barbershop.controller;

import com.barbershop.repository.dao.OrderDAO;
import com.barbershop.repository.dto.AdminDTO;
import com.barbershop.repository.dto.OrderDTO;
import com.barbershop.service.CalendarService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminController", value = "/admin")
public class AdminController extends HttpServlet
{
    private final CalendarService calendarService = new CalendarService();

    /**
     * Main data processor
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response, Boolean clean) throws ServletException, IOException
    {
        if (clean) {
            request.setAttribute("formId", "");
            request.setAttribute("formClient", "");
            request.setAttribute("formService", "");
            request.setAttribute("formPrice", "");
            request.setAttribute("formDate", "");

            request.setAttribute("calendarEvents", calendarService.getEvents());
        }

        // Make dispatcher and redirect to view
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * GET method
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Set default form values
        processRequest(request, response, true);
    }

    /**
     * POST method
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Make form DTO
        if (request.getParameter("delete") != null && !request.getParameter("delete").equals("")) {
            // Delete object
            OrderDAO.destroy(Integer.parseInt(request.getParameter("delete")));

            processRequest(request, response, true);
        } else {
            AdminDTO adminDTO = new AdminDTO(
                    request.getParameter("id"),
                    request.getParameter("client"),
                    request.getParameter("service"),
                    request.getParameter("price"),
                    request.getParameter("date")
            );

            // Set default form values
            request.setAttribute("formId", adminDTO.getId() > 0 ? adminDTO.getId() : "");
            request.setAttribute("formClient", adminDTO.getClient() != null ? adminDTO.getClient() : "");
            request.setAttribute("formService", adminDTO.getService() != null ? adminDTO.getService() : "");
            request.setAttribute("formPrice", adminDTO.getPrice() > 0 ? adminDTO.getPrice() : "");
            request.setAttribute("formDate", adminDTO.getDate() != null ? adminDTO.getDate() : "");

            request.setAttribute("calendarEvents", calendarService.getEvents(adminDTO));

            processRequest(request, response, false);
        }
    }
}