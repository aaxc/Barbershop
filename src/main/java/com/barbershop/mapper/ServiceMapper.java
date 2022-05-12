package com.barbershop.mapper;

import com.barbershop.model.Service;
import com.barbershop.repository.dao.ServiceDAO;

/**
 * Service item mapper
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 11.05.2022
 */
public class ServiceMapper
{
    /**
     * Convert Service Database Access object to Service object
     */
    public static Service mapFromDAO(ServiceDAO serviceDAO)
    {
        Service myItem = new Service();

        myItem.setId(serviceDAO.getId());
        myItem.setName(serviceDAO.getName());
        myItem.setPrice((long) serviceDAO.getPrice() / 100);

        return myItem;
    }
}
