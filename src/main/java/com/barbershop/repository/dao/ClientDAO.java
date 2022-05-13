package com.barbershop.repository.dao;

import com.barbershop.repository.Jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Client items from/for database
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 11.05.2022
 */
public class ClientDAO
{
    int id;
    String first_name;
    String last_name;
    String email;
    String phone;
    int gender;

    /**
     * Clean constructor
     */
    public ClientDAO()
    {
    }

    /**
     * Full constructor
     */
    public ClientDAO(int id, String first_name, String last_name, String email, String phone, int gender)
    {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return first_name;
    }

    public void setFirstName(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLastName()
    {
        return last_name;
    }

    public void setLastName(String last_name)
    {
        this.last_name = last_name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public int getGender()
    {
        return gender;
    }

    public void setGender(int gender)
    {
        this.gender = gender;
    }

    /**
     * Fetch Client object by id
     */
    public static ClientDAO getById(int id)
    {
        Jdbc jdbc = new Jdbc();
        ResultSet rs = jdbc.query("SELECT * FROM clients WHERE id = " + id);

        try {
            while (rs.next()) {
                return new ClientDAO(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("gender_id")
                );
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return new ClientDAO();
    }

    /**
     * Fetch clients ID by Name
     */
    public static int getIdByName(String name)
    {
        Jdbc jdbc = new Jdbc();
        ResultSet rs = jdbc.query("SELECT * FROM clients WHERE first_name = '" + name + "'");

        // Return id if client already in database
        try {
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Fetch clients ID by name or create a new one, if none exists
     */
    public static int getIdOrSave(String name)
    {
        // Check if client exists
        int id = getIdByName(name);
        if (id > 0) {
            return id;
        }

        try {
            Jdbc jdbc = new Jdbc();

            // Create new client
            String query = "INSERT INTO clients (first_name, last_name, email, phone, gender_id) values (?, ?, ?, ? ,?)";
            PreparedStatement preparedStmt = jdbc.conn.prepareStatement(query);

            // Prepare
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, "-");
            preparedStmt.setString(3, String.format("%s@%s", (int) (Math.random() * 100000), "faker.com"));
            preparedStmt.setString(4, "555-000-000");
            preparedStmt.setInt(5, (int) (Math.random() * 2 + 1));

            // Store
            preparedStmt.execute();

            // Fetch newly created entry id
            id = getIdByName(name);
        } catch (Exception e) {
//            e.printStackTrace();
        }

        // Return client id
        return id;
    }
}
