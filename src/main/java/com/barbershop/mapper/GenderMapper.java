package com.barbershop.mapper;

import com.barbershop.model.Gender;
import com.barbershop.repository.dao.GenderDAO;

/**
 * Gender item mapper
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 11.05.2022
 */
public class GenderMapper
{
    /**
     * Convert Gender Database Access object to Gender object
     */
    public static Gender mapFromDAO(GenderDAO genderDAO)
    {
        Gender myItem = new Gender();

        myItem.setId(genderDAO.getId());
        myItem.setName(genderDAO.getName());

        return myItem;
    }
}
