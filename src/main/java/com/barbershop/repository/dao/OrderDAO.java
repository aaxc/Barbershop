package com.barbershop.repository.dao;

import com.barbershop.repository.Jdbc;

import java.sql.PreparedStatement;

/**
 * Order items from/for database
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 10.05.2022
 */
public class OrderDAO
{
    int id;
    int service_id;
    int client_id;
    long timestamp;

    /**
     * Full constructor
     */
    public OrderDAO(int id, int service_id, int client_id, long timestamp)
    {
        this.id = id;
        this.service_id = service_id;
        this.client_id = client_id;
        this.timestamp = timestamp;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getServiceId()
    {
        return service_id;
    }

    public void setServiceId(int service_id)
    {
        this.service_id = service_id;
    }

    public int getClientId()
    {
        return client_id;
    }

    public void setClientId(int client_id)
    {
        this.client_id = client_id;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * Destroy order from database
     */
    public static void destroy(int id)
    {
        try {
            Jdbc jdbc = new Jdbc();
            String query = "DELETE FROM orders WHERE id = ?";

            PreparedStatement preparedStmt = jdbc.conn.prepareStatement(query);
            preparedStmt.setInt(1, id);

            preparedStmt.execute();
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
