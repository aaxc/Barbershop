package com.barbershop.model;

import java.sql.Timestamp;

/**
 * Order items
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 10.05.2022
 */
public class Order
{
    int id;
    Service service;
    Client client;
    Timestamp timestamp;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp)
    {
        this.timestamp = timestamp;
    }
}
