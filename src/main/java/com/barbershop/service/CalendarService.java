package com.barbershop.service;

import com.barbershop.mapper.OrderMapper;
import com.barbershop.model.Order;
import com.barbershop.repository.dao.ClientDAO;
import com.barbershop.repository.dao.OrderDAO;
import com.barbershop.repository.Jdbc;
import com.barbershop.repository.dto.AdminDTO;
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

        ResultSet rs = jdbc.query("SELECT * FROM orders ORDER BY timestamp DESC");
        List<Order> result = new ArrayList<>();

        try {
            while (rs.next()) {
                result.add(OrderMapper.mapFromDAO(new OrderDAO(
                        rs.getInt("id"),
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
     * Fetch events based on DTO options
     * Ideally, this should re-map to validated DAO objects, before adding to query select
     */
    public List<Order> getEvents(AdminDTO adminDTO)
    {
        Jdbc jdbc = new Jdbc();

        // Start builder
        String query = "SELECT * FROM orders o";
        String where = "";
        String join = "";

        try {

            // Check ID
            if (adminDTO.getId() > 0) {
                where += " AND o.id = " + adminDTO.getId();
            }

            // Check client
            if (!adminDTO.getClient().equals("")) {
                join += " JOIN clients c ON c.id = o.client_id";
                where += " AND c.first_name = '" + adminDTO.getClient() + "'";
            }

            // Check service
            if (!adminDTO.getService().equals("")) {
                join += " JOIN services s ON s.id = s.service_id";
                where += " AND s.name = '" + adminDTO.getClient() + "'";
            }

            // Check price
            if (adminDTO.getPrice() > 0) {
                join += " JOIN services s ON s.id = o.service_id";
                where += " AND s.price = " + adminDTO.getPrice() * 100;
            }

            // Check date
            if (adminDTO.getDate() != null) {
                where += " AND o.timestamp = " + adminDTO.getDate().getTime();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Combine query
        if (!where.equals("")) {
            where = " WHERE 1=1" + where;
        }
        query += join + where + " ORDER BY o.timestamp DESC";

        System.out.println(query);

        ResultSet rs = jdbc.query(query);
        List<Order> result = new ArrayList<>();

        try {
            if (rs != null) {
                while (rs.next()) {
                    result.add(OrderMapper.mapFromDAO(new OrderDAO(
                            rs.getInt("id"),
                            rs.getInt("service_id"),
                            rs.getInt("client_id"),
                            rs.getLong("timestamp")
                    )));
                }
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
            item.put("title", t.getClient().getFirstName());
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
