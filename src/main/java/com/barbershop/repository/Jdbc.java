package com.barbershop.repository;

import java.sql.*;

/**
 * DB connector
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 10.05.2022
 */
public class Jdbc
{
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "password";
    String database = "barbershop";

    Connection conn;

    public Jdbc()
    {
        try {
            conn = DriverManager.getConnection(this.url + this.database, this.user, this.password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet query(String query)
    {
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (Exception ignored) {
            System.out.println("query fail");
        }

        return result;
    }
}
