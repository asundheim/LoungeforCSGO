package com.apps.anders.loungeforcsgo;

import android.app.Application;
import android.view.View;

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
    public void clearBetted_Matches(){betted_matches.clear(); }

    //////////////////////////////////////////////////////////////////////////

    public static ArrayList<ItemObject> won_items = new ArrayList<ItemObject>();
    public ArrayList<ItemObject> getWon_items(){
        return won_items;
    }
    public void addWon(ItemObject i){
        won_items.add(i);
    }
    public void clearWon(){ won_items.clear(); }

    ///////////////////////////////////////////////////////////////////////////

    public static ArrayList<ItemObject> returned_items = new ArrayList<ItemObject>();
    public static ArrayList<ItemObject> getReturned_items() {
        return returned_items;
    }
    public void addReturned(ItemObject i){
        returned_items.add(i);
    }
    public void clearReturned(){ returned_items.clear(); }

    ////////////////////////////////////////////////////////////////////////////

    public static ArrayList<ItemObject> returns = new ArrayList<ItemObject>();
    public static ArrayList<ItemObject> getReturns(){ return returns;}
    public void addReturns(ItemObject i){ returns.add(i); }
    public void clearReturns(){ returns.clear();}

    ////////////////////////////////////////////////////////////////////////////

    public static ArrayList<ItemObject> backpack = new ArrayList<ItemObject>();
    public static ArrayList<ItemObject> getBackpack(){ return backpack;}
    public void addBackpack(ItemObject i){ backpack.add(i); }
    public void clearBackpack(){ backpack.clear();}

    ////////////////////////////////////////////////////////////////////////////

    public static int matchNum = 0;
    public void setMatchNum(String s){matchNum = Integer.parseInt(s); }
    public static int getMatchNum(){return matchNum;}

    ////////////////////////////////////////////////////////////////////////////

    public static ArrayList<ItemObject> betpage_items = new ArrayList<ItemObject>();
    public static ArrayList<ItemObject> getBetpage_items() {
        return betpage_items;
    }
    public void addBetpage_item(ItemObject i) {
        betpage_items.add(i);
    }
    public void clearBetpage_items(){betpage_items.clear();}
    public boolean showBackpack = true;
    public boolean getShowBackpack(){ return showBackpack; }
    public void dontShowBackpack(){ showBackpack = false; }
    public void doShowBackpack(){ showBackpack = true; }
    public String valA;
    public void setValA(String s){valA=s;}
    public String getValA(){return valA; }
    public String valB;
    public void setValB(String s){valB=s;}
    public String getValB(){return valB; }
    public View item1;
    public View item2;
    public View item3;
    public View item4;
    public View item5;
    public View item6;
    public View getItem1() {
        return item1;
    }
    public View getItem2() {
        return item2;
    }
    public View getItem3() {
        return item3;
    }
    public View getItem4() {
        return item4;
    }
    public View getItem5() {
        return item5;
    }
    public View getItem6() {
        return item6;
    }
    public void setItem1(View item1) {
        this.item1 = item1;
    }
    public void setItem2(View item2) {
        this.item2 = item2;
    }
    public void setItem3(View item3) {
        this.item3 = item3;
    }
    public void setItem4(View item4) {
        this.item4 = item4;
    }
    public void setItem5(View item5) {
        this.item5 = item5;
    }
    public void setItem6(View item6) {
        this.item6 = item6;
    }


    ////////////////////////////////////////////////////////////////////////////

    public String invState = "returns";
    public void setStateReturns(){invState="returns";}
    public void setStateBackpack(){invState="backpack";}
    public String getInvState(){return invState;}

    ////////////////////////////////////////////////////////////////////////////
    public boolean pass = false;
    public boolean passItems = false;
    public void change(){
        pass = true;
    }
    public void changeItems(){
        passItems = true;
    }

    ////////////////////////////////////////////////////////////////////////////

    public int homescreens = 0;
    public int getHomescreens(){return homescreens; }
    public void addToHomescreens(){homescreens++;}

    /////////////////////////////////////////////////////////////////////////////

}
