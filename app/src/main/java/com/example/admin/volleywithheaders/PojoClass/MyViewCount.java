package com.example.admin.volleywithheaders.PojoClass;

/**
 * Created by Pankaj on 11/25/2017.
 */

public class MyViewCount {
    private String id;

    private String view_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    @Override
    public String toString()
    {
        return "ClassView [id = "+id+", view_count = "+view_count+"]";
    }

}
