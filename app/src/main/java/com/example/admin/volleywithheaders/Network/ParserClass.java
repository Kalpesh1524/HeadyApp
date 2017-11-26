package com.example.admin.volleywithheaders.Network;

import android.util.SparseArray;

import com.example.admin.volleywithheaders.PojoClass.MyOrder;
import com.example.admin.volleywithheaders.PojoClass.MyRankings;
import com.example.admin.volleywithheaders.PojoClass.MyShareCount;
import com.example.admin.volleywithheaders.PojoClass.MyViewCount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 6/13/17.
 */

public class ParserClass {

    static List<MyViewCount> viewList;
    static List<MyRankings> rankList;
    static List<MyOrder> orderList;
    static List<MyShareCount> shareList;

    public static List<MyRankings> parseRank(String ranking) {

        JSONArray games_arry = null;
        JSONArray productArry = null;

        MyRankings rank = null;
        try {

            //games_arry = new JSONArray(content);
            rankList = new ArrayList<>();

            games_arry = new JSONObject(ranking).getJSONArray("rankings");
            //games_arry = new JSONArray(jsonString).getJSONArray("");;
            for (int i = 0; i < games_arry.length(); i++) {

                JSONObject obj = games_arry.getJSONObject(i);
                String chk1 = obj.getString("ranking");
                rank = new MyRankings();
                rank.setRanking(obj.getString("ranking"));

                rankList.add(rank);

            }

            return rankList;
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<MyViewCount> parseData(String content) {

        JSONArray games_arry = null;
        JSONArray productArry = null;

        MyViewCount games = null;
        try {
            viewList = new ArrayList<>();
            games_arry = new JSONObject(content).getJSONArray("rankings");
            JSONObject obj = games_arry.getJSONObject(0);
            String chk1 = obj.getString("ranking");
            productArry = obj.getJSONArray("products");
            for (int j = 0; j < productArry.length(); j++) {
                JSONObject objProduct = productArry.getJSONObject(j);
                String id = objProduct.getString("id");
                games = new MyViewCount();
                games.setId(objProduct.getString("id"));
                games.setView_count(objProduct.getString("view_count"));
                viewList.add(games);
            }
            return viewList;

        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static List<MyOrder> parseOrderData(String content) {
        JSONArray games_arry = null;
        JSONArray productArry = null;
        MyOrder games = null;
        try {
            orderList = new ArrayList<>();
            games_arry = new JSONObject(content).getJSONArray("rankings");

            JSONObject obj = games_arry.getJSONObject(1);
            String chk1 = obj.getString("ranking");

            productArry = obj.getJSONArray("products");
            for (int j = 0; j < productArry.length(); j++) {
                JSONObject objProduct = productArry.getJSONObject(j);
                String id = objProduct.getString("id");
                games = new MyOrder();
                games.setId(objProduct.getString("id"));
                games.setOrder_count(objProduct.getString("order_count"));
                orderList.add(games);
            }
            return orderList;

        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<MyShareCount> parseShareData(String content) {
        JSONArray games_arry = null;
        JSONArray productArry = null;
        MyShareCount games = null;
        try {
            shareList = new ArrayList<>();
            games_arry = new JSONObject(content).getJSONArray("rankings");

            JSONObject obj = games_arry.getJSONObject(2);
            String chk1 = obj.getString("ranking");

            productArry = obj.getJSONArray("products");
            for (int j = 0; j < productArry.length(); j++) {
                JSONObject objProduct = productArry.getJSONObject(j);
                String id = objProduct.getString("id");
                games = new MyShareCount();
                games.setId(objProduct.getString("id"));
                games.setShares(objProduct.getString("shares"));
                shareList.add(games);
            }
            return shareList;

        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static SparseArray<String> parseDetailsData(String content, String id) {
        Integer fetchedID;
        JSONArray games_arry = null;
        JSONArray productArry = null;
        JSONArray varientArray = null;
        String prName, prID;
        int k = 1;
        fetchedID = Integer.parseInt(id);
        SparseArray<String> sArray = new SparseArray<>();
        try {
            games_arry = new JSONObject(content).getJSONArray("categories");


            for (int i = 0; i < games_arry.length(); i++) {
                JSONObject obj = games_arry.getJSONObject(i);
                prName = obj.getString("name");
                prID = obj.getString("id");
                productArry = obj.getJSONArray("products");
                if (productArry.length() > 0){
                    for (int j = 0; j < productArry.length(); j++) {
                        JSONObject objProduct = productArry.getJSONObject(j);
                        Integer JsonId = Integer.parseInt(objProduct.getString("id"));
                        if (JsonId == fetchedID){
                            sArray.put(k,prID);
                            k++;
                            sArray.put(k,prName);
                            k++;
                            String subName = objProduct.getString("name");
                            sArray.put(k,subName);
                            k++;
                            varientArray = objProduct.getJSONArray("variants");
                            if (varientArray.length() > 0){
                                for (int v = 0; v < varientArray.length(); v++) {
                                    JSONObject varObject = varientArray.getJSONObject(v);
                                    String color = varObject.getString("color");
                                    sArray.put(k,color);
                                    k++;
                                    String size = varObject.getString("size");
                                    sArray.put(k,size);
                                    k++;
                                    String price = varObject.getString("price");
                                    sArray.put(k,price);
                                    k++;
                                }
                                break;
                            }

                        }else {
                            sArray.put(k,"No Data");
                        }
                    }
                }else {
                    sArray.put(k,"No Data");
                }

            }
        return sArray;

        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
