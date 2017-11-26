package com.example.admin.volleywithheaders.PojoClass;

/**
 * Created by Pankaj on 11/25/2017.
 */

public class MyRankings {
    private String ranking;


    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString()
    {
        return "ClassRanking [ranking = "+ranking+"]";
    }


}
