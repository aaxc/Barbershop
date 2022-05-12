package com.barbershop.repository.dto;

import com.barbershop.repository.Jdbc;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Reservation order model DTO
 *
 * @author Dainis Abols<dainis@dainisabols.lv>
 * @since 12.05.2022
 */
public class AdminDTO
{
    int id;
    String client_name;
    String service_name;
    long price;
    Timestamp date;

    /**
     * Full constructor
     */
    public AdminDTO(String id, String client, String service, String price, String date)
    {
        // Validate entries
        try {
            // Accept only positive ID
            int newId = Integer.parseInt(id);
            if (newId > 0) {
                this.id = newId;
            } else {
                this.id = 0;
            }

            // Trim spaces and remove possible tags
            this.client_name = client.trim().replaceAll("\\<.*?\\>", "").replaceAll(";", "");
            this.service_name = service.trim().replaceAll("\\<.*?\\>", "").replaceAll(";", "");

            // Accept only positive price
            long newPrice = Long.parseLong(price);
            if (newPrice > 0) {
                this.price = newPrice;
            } else {
                this.price = 0;
            }

            // Parse date or die in general. We got all right or nothing!
            Date myDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            this.date = new Timestamp(myDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getClient()
    {
        return client_name;
    }

    public void setClient(String client_name)
    {
        this.client_name = client_name;
    }

    public String getService()
    {
        return service_name;
    }

    public void setService(String service_name)
    {
        this.service_name = service_name;
    }

    public long getPrice()
    {
        return price;
    }

    public void setPrice(long price)
    {
        this.price = price;
    }

    public Timestamp getDate()
    {
        return date;
    }

    public void setDate(Timestamp date)
    {
        this.date = date;
    }
}
