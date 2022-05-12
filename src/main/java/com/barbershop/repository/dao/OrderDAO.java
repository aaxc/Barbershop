package com.barbershop.repository.dao;

/**
 * Order items from/for database
 *
 * @author Dainis Abols <dainis@dainisabols.lv>
 * @since 10.05.2022
 */
public class OrderDAO
{
    int service_id;
    int client_id;
    long timestamp;

    /**
     * Full constructor
     */
    public OrderDAO(int service_id, int client_id, long timestamp)
    {
        this.service_id = service_id;
        this.client_id = client_id;
        this.timestamp = timestamp;
    }

    public int getServiceId()
    {
        return service_id;
    }

    public void setServiceId(int service_id)
    {
        this.service_id = service_id;
    }

    public int getClientId()
    {
        return client_id;
    }

    public void setClientId(int client_id)
    {
        this.client_id = client_id;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }
}
