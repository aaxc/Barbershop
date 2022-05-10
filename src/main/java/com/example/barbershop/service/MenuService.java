package com.example.barbershop.service;

import com.example.barbershop.model.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Menu model class
 *
 * @author  Dainis Abols <dainis@dainisabols.lv>
 * @since   09.05.2022
 */
public class MenuService
{
    // @TODO Make this a service!
    public List<MenuItem> getMenu(String activeUrl)
    {
        return new ArrayList<>(Arrays.asList(
                new MenuItem("Home", "", Objects.equals(activeUrl, "home")),
                new MenuItem("About", "about", Objects.equals(activeUrl, "about")),
                new MenuItem("Reservations", "reservations", Objects.equals(activeUrl, "reservations")),
                new MenuItem("<i class=\"fa-solid fa-lock text-warning\"></i>", "admin")
        ));
    }
}