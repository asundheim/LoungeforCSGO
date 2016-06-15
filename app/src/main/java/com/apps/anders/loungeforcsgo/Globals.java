package com.apps.anders.loungeforcsgo;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Anders on 6/2/2016.
 */
public class Globals extends Application {

    /////////////////////////////////////////////////////////////////////////

    public static ArrayList<MatchObject> matches_ = new ArrayList<MatchObject>();
    public ArrayList<MatchObject> getMatches() {
        return matches_;
    }
    public void addMatch(MatchObject m){
        matches_.add(m);
    }

    //////////////////////////////////////////////////////////////////////////

    public static ArrayList<MatchObject> betted_matches = new ArrayList<MatchObject>();
    public ArrayList<MatchObject> getBetted_Matches() {
        return betted_matches;
    }
    public void addBetted_Match(MatchObject m){
        betted_matches.add(m);
    }

    //////////////////////////////////////////////////////////////////////////

    public static ArrayList<ItemObject> won_items = new ArrayList<ItemObject>();
    public ArrayList<ItemObject> getWon_items(){
        return won_items;
    }
    public void addWon(ItemObject i){
        won_items.add(i);
    }

    ///////////////////////////////////////////////////////////////////////////

    public static ArrayList<ItemObject> returned_items = new ArrayList<ItemObject>();
    public static ArrayList<ItemObject> getReturned_items() {
        return returned_items;
    }
    public void addReturned(ItemObject i){
        returned_items.add(i);
    }

    ////////////////////////////////////////////////////////////////////////////

    public static ArrayList<ItemObject> returns = new ArrayList<ItemObject>();
    public static ArrayList<ItemObject> getReturns(){ return returns;}
    public void addReturns(ItemObject i){ returns.add(i); }
    public void clearReturns(){ returns.clear();}

    ////////////////////////////////////////////////////////////////////////////
    public boolean pass = false;
    public boolean passItems = false;
    public void change(){
        pass = true;
    }
    public void changeItems(){
        passItems = true;
    }


}
