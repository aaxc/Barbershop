package com.barbershop.mapper;

import com.barbershop.model.Order;
import com.barbershop.repository.dao.ClientDAO;
import com.barbershop.repository.dao.OrderDAO;
import com.barbershop.repository.dao.ServiceDAO;

import java.sql.Timestamp;

/**
 * Order item mapper
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 10.05.2022
 */
public class OrderMapper
{
    /**
     * Convert Order Database Access object to Order object
     */
    public static Order mapFromDAO(OrderDAO orderDAO)
    {
        Order myItem = new Order();

        myItem.setService(ServiceMapper.mapFromDAO(ServiceDAO.getById(orderDAO.getServiceId())));
        myItem.setClient(ClientMapper.mapFromDAO(ClientDAO.getById(orderDAO.getClientId())));
        myItem.setTimestamp(new Timestamp(orderDAO.getTimestamp()));

        return myItem;
    }
}
