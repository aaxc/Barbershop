package com.barbershop.repository.dao;

import com.barbershop.repository.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Gender items from/for database
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 11.05.2022
 */
public class GenderDAO
{
    int id;
    String name;

    /**
     * Clean constructor
     */
    public GenderDAO()
    {
    }

    /**
     * Full constructor
     */
    public GenderDAO(int id, String name)
    {
        this.id = id;
        this.name = name;
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

    /**
     * Fetch Gender object by id
     */
    public static GenderDAO getById(int id)
    {
        Jdbc jdbc = new Jdbc();
        ResultSet rs = jdbc.query("SELECT * FROM genders WHERE id = " + id);

        try {
            while (rs.next()) {
                return new GenderDAO(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new GenderDAO();
    }
}
