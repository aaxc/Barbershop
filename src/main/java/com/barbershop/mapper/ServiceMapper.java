package com.barbershop.mapper;

import com.barbershop.model.Service;
import com.barbershop.repository.ServiceDAO;

/**
 * Service item mapper
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 11.05.2022
 */
public class ServiceMapper
{
    public static Service mapFromDAO(ServiceDAO serviceDAO)
    {
        Service myItem = new Service();

        myItem.setId(serviceDAO.getId());
        myItem.setName(serviceDAO.getName());
        myItem.setPrice((long) serviceDAO.getPrice() / 100);

        return myItem;
    }
}
