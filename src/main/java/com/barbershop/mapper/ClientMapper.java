package com.barbershop.mapper;

import com.barbershop.model.Client;
import com.barbershop.repository.dao.ClientDAO;
import com.barbershop.repository.dao.GenderDAO;

/**
 * Client item mapper
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 11.05.2022
 */
public class ClientMapper
{
    /**
     * Convert Client Database Access object to Client object
     */
    public static Client mapFromDAO(ClientDAO clientDAO)
    {
        Client myItem = new Client();

        myItem.setId(clientDAO.getId());
        myItem.setFirstName(clientDAO.getFirstName());
        myItem.setLastName(clientDAO.getLastName());
        myItem.setEmail(clientDAO.getEmail());
        myItem.setPhone(clientDAO.getPhone());
        myItem.setGender(GenderMapper.mapFromDAO(GenderDAO.getById(clientDAO.getGender())));

        return myItem;
    }
}
