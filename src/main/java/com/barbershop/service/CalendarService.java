package com.barbershop.service;

import com.barbershop.mapper.OrderMapper;
import com.barbershop.model.OrderItem;
import com.barbershop.repository.OrderDAO;
import com.barbershop.repository.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Calendar data service
 * Fetches orders and creates calendar events
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 10.05.2022
 */
public class CalendarService
{
    public List<OrderItem> getEvents()
    {
        Jdbc jdbc = new Jdbc();

        ResultSet rs = jdbc.query("SELECT * FROM orders");
        List<OrderItem> result = new ArrayList<>();

        try {
            while (rs.next()) {
                result.add(OrderMapper.mapFromDAO(new OrderDAO(
                        rs.getInt("service_id"),
                        rs.getInt("client_id"),
                        rs.getLong("timestamp_from"),
                        rs.getLong("timestamp_till"),
                        rs.getBoolean("cancelled"),
                        rs.getBoolean("finished")
                )));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
