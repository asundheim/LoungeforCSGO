package com.apps.anders.loungeforcsgo;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Anders on 6/2/2016.
 */
public class Globals extends Application {
    public static ArrayList<MatchObject> matches_ = new ArrayList<MatchObject>();
    public static ArrayList<ItemObject> won_items = new ArrayList<ItemObject>();
    public static ArrayList<ItemObject> returned_items = new ArrayList<ItemObject>();

    public static ArrayList<ItemObject> getReturned_items() {
        return returned_items;
    }

    public ArrayList<MatchObject> getMatches() {
        return matches_;
    }
    public boolean pass = false;
    public boolean passItems = false;
    public void addMatch(MatchObject m){
        matches_.add(m);
    }
    public void addWon(ItemObject i){won_items.add(i);}
    public void addReturned(ItemObject i){returned_items.add(i);}
    public ArrayList<ItemObject> getWon_items(){return won_items;}
    public void printMatch(){
        System.out.println("Matches "+matches_.get(1));
    }
    public void change(){
        pass = true;
    }
    public boolean passState(){
        return pass;
    }
    public boolean passItemState(){
        return passItems;
    }
    public void changeItems(){
        passItems = true;
    }


}
