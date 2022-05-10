package com.barbershop.repository;

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
    long timestamp_from;
    long timestamp_till;
    boolean cancelled;
    boolean finished;

    public OrderDAO(int service_id, int client_id, long timestamp_from, long timestamp_till, boolean cancelled, boolean finished)
    {
        this.service_id = service_id;
        this.client_id = client_id;
        this.timestamp_from = timestamp_from;
        this.timestamp_till = timestamp_till;
        this.cancelled = cancelled;
        this.finished = finished;
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

    public long getTimestampFrom()
    {
        return timestamp_from;
    }

    public void setTimestampFrom(long timestamp_from)
    {
        this.timestamp_from = timestamp_from;
    }

    public long getTimestampTill()
    {
        return timestamp_till;
    }

    public void setTimestampTill(long timestamp_till)
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
