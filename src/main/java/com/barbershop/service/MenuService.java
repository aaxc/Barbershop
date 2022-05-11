package com.barbershop.service;

import com.barbershop.model.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Menu model class
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 09.05.2022
 */
public class MenuService
{
    // @TODO Make this a service!
    public List<Menu> getMenu(String activeUrl)
    {
        return new ArrayList<>(Arrays.asList(
                new Menu("Home", "", Objects.equals(activeUrl, "home")),
                new Menu("About", "about", Objects.equals(activeUrl, "about")),
                new Menu("Reservations", "reservations", Objects.equals(activeUrl, "reservations")),
                new Menu("<i class=\"fa-solid fa-lock text-warning\"></i>", "admin")
        ));
    }
}