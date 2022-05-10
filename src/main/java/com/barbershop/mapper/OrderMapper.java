package com.barbershop.mapper;

import com.barbershop.model.OrderItem;
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
    public static OrderItem mapFromDAO(OrderDAO orderDAO)
    {
        OrderItem myOrderItem = new OrderItem();

        myOrderItem.setServiceId(orderDAO.getServiceId());
        myOrderItem.setClientId(orderDAO.getClientId());
        myOrderItem.setTimestampFrom(new Timestamp(orderDAO.getTimestampFrom()));
        myOrderItem.setTimestampTill(new Timestamp(orderDAO.getTimestampFrom()));
        myOrderItem.setCancelled(orderDAO.isCancelled());
        myOrderItem.setFinished(orderDAO.isFinished());

        return myOrderItem;
    }
}
