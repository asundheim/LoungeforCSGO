package com.apps.anders.loungeforcsgo;

/**
 * Created by Anders on 6/10/2016.
 */

public class ItemObject {
    String name;
    String wear;
    String src;
    String rarity;
    public String getName() {
        return name;
    }

    public String getWear() {
        return wear;
    }

    public String getPrice() {
        return price;
    }

    public String getRarity() {
        return rarity;
    }

    public String getSrc() {
        return src;
    }

    String price;
    public ItemObject(String name,String wear,String price,String src,String rarity){
        this.name = name;
        this.wear = wear;
        this.price = price;
        this.src = src;
        this.rarity = rarity;
    }
}
