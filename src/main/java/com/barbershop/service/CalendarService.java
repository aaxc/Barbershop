package com.barbershop.service;

import com.barbershop.mapper.OrderMapper;
import com.barbershop.model.Order;
import com.barbershop.repository.dao.ClientDAO;
import com.barbershop.repository.dao.OrderDAO;
import com.barbershop.repository.Jdbc;
import com.barbershop.repository.dto.OrderDTO;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Calendar data service
 * Fetches orders and creates calendar events
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 10.05.2022
 */
public class CalendarService
{
    /**
     * Fetch event list
     */
    public List<Order> getEvents()
    {
        Jdbc jdbc = new Jdbc();

        ResultSet rs = jdbc.query("SELECT * FROM orders");
        List<Order> result = new ArrayList<>();

        try {
            while (rs.next()) {
                result.add(OrderMapper.mapFromDAO(new OrderDAO(
                        rs.getInt("service_id"),
                        rs.getInt("client_id"),
                        rs.getLong("timestamp")
                )));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Fetch events and convert to JSON format for calendar
     */
    public String getEventsAsJson()
    {
        JSONArray json = new JSONArray();

        List<Order> test = this.getEvents();
        for (Order t : test) {
            JSONObject item = new JSONObject();
            item.put("title", "BOOKED");
            item.put("start", new SimpleDateFormat("yyyy-MM-dd").format(t.getTimestamp().getTime()));
            json.add(item);
        }

        return json.toString();
    }

    /**
     * Store new event
     */
    public void saveEvent(OrderDTO orderDTO)
    {
        try {
            Jdbc jdbc = new Jdbc();

            String query = "INSERT INTO orders (service_id, client_id, timestamp) values (?, ?, ?)";
            PreparedStatement preparedStmt = jdbc.conn.prepareStatement(query);

            // Set service id
            preparedStmt.setInt(1, Integer.parseInt(orderDTO.getServiceId()));

            // Fetch client id, if exists
            int clientId = ClientDAO.getIdOrSave(orderDTO.getName());
            preparedStmt.setInt(2, clientId);

            // Create timestamp from received date
            Date date = new SimpleDateFormat("yyyy-MM-ddd").parse(orderDTO.getDate());
            preparedStmt.setLong(3, date.getTime());

            // execute the prepared statement
            preparedStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
