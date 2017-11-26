package com.example.admin.volleywithheaders.PojoClass;

/**
 * Created by Pankaj on 11/25/2017.
 */

public class MyOrder {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_count() {
        return order_count;
    }

    public void setOrder_count(String order_count) {
        this.order_count = order_count;
    }

    private String id;

    private String order_count;

    @Override
    public String toString() {
        return "ClassOrder [id = " + id + ", order_count = " + order_count + "]";
    }
}
