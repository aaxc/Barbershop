package com.barbershop.repository.dto;

/**
 * Reservation order model DTO
 */
public class OrderDTO
{
    String name;
    String service_id;
    String date;

    /**
     * Full constructor
     */
    public OrderDTO(String name, String service_id, String date)
    {
        this.name = name;
        this.service_id = service_id;
        this.date = date;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getServiceId()
    {
        return service_id;
    }

    public void setServiceId(String service_id)
    {
        this.service_id = service_id;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
}
