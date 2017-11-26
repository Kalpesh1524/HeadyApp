package com.example.admin.volleywithheaders.PojoClass;

/**
 * Created by Pankaj on 11/25/2017.
 */

public class MyShareCount {
    private String id;
    private String shares;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    @Override
    public String toString() {
        return "ClassShare [id = " + id + ", shares = " + shares + "]";
    }

}
