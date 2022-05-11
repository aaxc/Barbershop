package com.barbershop.mapper;

import com.barbershop.model.Order;
import com.barbershop.repository.OrderDAO;

import java.sql.Timestamp;

/**
 * Order item mapper
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 10.05.2022
 */
public class OrderMapper
{
    public static Order mapFromDAO(OrderDAO orderDAO)
    {
        Order myItem = new Order();

        myItem.setServiceId(orderDAO.getServiceId()); // @TODO setService()
        myItem.setClientId(orderDAO.getClientId());   // @TODO setClient()
        myItem.setTimestampFrom(new Timestamp(orderDAO.getTimestampFrom()));
        myItem.setTimestampTill(new Timestamp(orderDAO.getTimestampFrom()));
        myItem.setCancelled(orderDAO.isCancelled());
        myItem.setFinished(orderDAO.isFinished());

        return myItem;
    }
}
