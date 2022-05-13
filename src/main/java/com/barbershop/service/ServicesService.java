package com.barbershop.service;

import com.barbershop.mapper.ServiceMapper;
import com.barbershop.model.Service;
import com.barbershop.repository.Jdbc;
import com.barbershop.repository.dao.ServiceDAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Barbershop services data service
 * Fetches different services and prices from database
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 12.05.2022
 */
public class ServicesService
{
    /**
     * Fetch list of services
     */
    public List<Service> getServices()
    {
        Jdbc jdbc = new Jdbc();

        ResultSet rs = jdbc.query("SELECT * FROM services");
        List<Service> result = new ArrayList<>();

        try {
            while (rs.next()) {
                result.add(ServiceMapper.mapFromDAO(new ServiceDAO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price")
                )));
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return result;
    }
}
