package com.apps.anders.loungeforcsgo;

/**
 * Created by Anders on 6/10/2016.
 */

public class ItemObject {
    String name;
    String wear;

    public String getName() {
        return name;
    }

    public String getWear() {
        return wear;
    }

    public String getPrice() {
        return price;
    }

    String price;
    public ItemObject(String name,String wear,String price){
        this.name = name;
        this.wear = wear;
        this.price = price;
    }
}
