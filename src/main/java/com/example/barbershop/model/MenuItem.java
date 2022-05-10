package com.example.barbershop.model;

/**
 * MenuItem
 *
 * @author  Dainis Abols <dainis@dainisabols.lv>
 * @since   10.05.2022
 */
public class MenuItem
{
    String name;
    String url;
    Boolean active;

    public MenuItem(String name, String url)
    {
        this.name = name;
        this.url = url;
        this.active = false;
    }

    public MenuItem(String name, String url, Boolean active)
    {
        this.name = name;
        this.url = url;
        this.active = active;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }
}
