package com.barbershop.service;

import com.barbershop.mapper.OrderMapper;
import com.barbershop.model.Order;
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
        List<Order> test = this.getEvents();
        for (Order t : test) {
            System.out.println(t.getTimestampFrom());
        }

        return "[{title: 'BOOKED',start: '2022-05-14'}]";
    }
}
