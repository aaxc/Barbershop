package com.barbershop.service;

import com.barbershop.mapper.OrderMapper;
import com.barbershop.model.Order;
import com.barbershop.repository.OrderDAO;
import com.barbershop.repository.Jdbc;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

    /**
     * [
     *    {
     *        title: 'BOOKED',
     *        start: '2022-05-10'
     *     },
     *     {
     *         title: 'BOOKED',
     *         start: '2022-05-13'
     *     },
     *     {
     *         title: 'BOOKED',
     *         start: '2022-05-14'
     *     }
     * ]
     */
    public String getEventsAsJson()
    {
        JSONArray json = new JSONArray();
        JSONObject item = new JSONObject();

        List<Order> test = this.getEvents();
        for (Order t : test) {
            item.put("title", "BOOKED");
            item.put("start", new SimpleDateFormat("yyyy-MM-dd").format(t.getTimestampFrom().getTime()));
            json.add(item);
        }

        return json.toString();
    }
}
