package com.barbershop.repository.dto;

/**
 * Reservation order model DTO
 *
 * @author Dainis Abols<dainis@dainisabols.lv>
 * @since 12.05.2022
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
        // Validate entries
        try {
            // Trim spaces and remove possible tags
            this.name = name.trim().replaceAll("\\<.*?\\>", "").replaceAll(";", "");
            this.service_id = service_id.trim().replaceAll("\\<.*?\\>", "").replaceAll(";", "");
            this.date = date.trim().replaceAll("\\<.*?\\>", "").replaceAll(";", "");
        } catch (Exception e) {
//            e.printStackTrace();
        }
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
