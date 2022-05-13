package com.barbershop.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

    public Connection conn;

    /**
     * Clean constructor
     * Initializes connection
     */
    public Jdbc()
    {
        try {
            conn = DriverManager.getConnection(this.url + this.database, this.user, this.password);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    /**
     * Basic query execution
     */
    public ResultSet query(String query)
    {
        ResultSet result = null;

        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return result;
    }
}
