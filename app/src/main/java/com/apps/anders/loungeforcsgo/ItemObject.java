package com.apps.anders.loungeforcsgo;

/**
 * Created by Anders on 6/10/2016.
 */

public class ItemObject {
    String floatValue;
    String name;
    String wear;
    String src;
    String rarity;
    String price;
    String[] stickers;

    public String getFloatValue() {
        return floatValue;
    }

    public String[] getStickers() {
        return stickers;
    }

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

    public ItemObject(String name,String wear,String price,String src,String rarity){
        this.name = name;
        this.wear = wear;
        this.price = price;
        this.src = src;
        this.rarity = rarity;
    }
    public ItemObject(String name,String wear,String price,String src,String rarity, String floatValue){
        this.name = name;
        this.wear = wear;
        this.price = price;
        this.src = src;
        this.rarity = rarity;
        this.floatValue = floatValue.substring(24);
    }
    public ItemObject(String name,String wear,String price,String src,String rarity, String floatValue, String[] stickers){
        this.name = name;
        this.wear = wear;
        this.price = price;
        this.src = src;
        this.rarity = rarity;
        this.floatValue = floatValue;
        this.stickers = stickers;
    }

}
