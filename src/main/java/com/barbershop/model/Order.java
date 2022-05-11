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
    int service_id;
    int client_id;
    Timestamp timestamp_from;
    Timestamp timestamp_till;
    boolean cancelled;
    boolean finished;

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

    public Timestamp getTimestampFrom()
    {
        return timestamp_from;
    }

    public void setTimestampFrom(Timestamp timestamp_from)
    {
        this.timestamp_from = timestamp_from;
    }

    public Timestamp getTimestampTill()
    {
        return timestamp_till;
    }

    public void setTimestampTill(Timestamp timestamp_till)
    {
        this.timestamp_till = timestamp_till;
    }

    public boolean isCancelled()
    {
        return cancelled;
    }

    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }
}
