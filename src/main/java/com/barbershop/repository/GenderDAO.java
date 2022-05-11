package com.barbershop.repository;

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

    public GenderDAO()
    {
    }

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

    public static GenderDAO getById(int id)
    {
        Jdbc jdbc = new Jdbc();
        ResultSet rs = jdbc.query("SELECT * FROM genders WHERE id = " + id);

        try {
            rs.next();
            return new GenderDAO(rs.getInt("id"), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new GenderDAO();
    }
}
