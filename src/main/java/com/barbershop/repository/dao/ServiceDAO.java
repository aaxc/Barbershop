package com.barbershop.repository.dao;

import com.barbershop.repository.Jdbc;

import java.sql.ResultSet;

/**
 * Service items from/for database
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 11.05.2022
 */
public class ServiceDAO
{
    int id;
    String name;
    int price;

    /**
     * Clean constructor
     */
    public ServiceDAO()
    {
    }

    /**
     * Full constructor
     */
    public ServiceDAO(int id, String name, int price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    /**
     * Fetch Service object by id
     */
    public static ServiceDAO getById(int id)
    {
        Jdbc jdbc = new Jdbc();
        ResultSet rs = jdbc.query("SELECT * FROM services WHERE id = " + id);

        try {
            rs.next();
            return new ServiceDAO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price")
            );
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return new ServiceDAO();
    }
}
