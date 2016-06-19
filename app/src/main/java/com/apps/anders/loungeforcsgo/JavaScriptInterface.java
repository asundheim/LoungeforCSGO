package com.apps.anders.loungeforcsgo;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Anders on 6/2/2016.
 */
public class JavaScriptInterface {
    private Activity activity;

    //public static ArrayList<MatchObject> matches = new ArrayList<MatchObject>();
    public JavaScriptInterface(Activity activiy) {
        this.activity = activiy;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JavascriptInterface
    public void printAddress(String address) throws IOException {
        //Toast.makeText(activity,address, Toast.LENGTH_SHORT).show();
        String[] info = address.split("---");
        System.out.println(address);
        //System.out.println("test: "+info[8]);
        Globals g = (Globals) activity.getApplicationContext();
        g.addMatch(new MatchObject(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9],info[10]));
        //System.out.println(info[4]);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JavascriptInterface
    public void addWon(String s) {
        String[] item_info = s.split("---");
        System.out.println(s);
        Globals g = (Globals) activity.getApplicationContext();
        g.addWon(new ItemObject(item_info[0], item_info[1], item_info[2], item_info[3], item_info[4]));
    }

    @JavascriptInterface
    public void clearWon(){
        Globals g = (Globals)activity.getApplicationContext();
        g.clearWon();
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JavascriptInterface
    public void addReturned(String s) {
        String[] item_info = s.split("---");
        System.out.println(s);
        Globals g = (Globals) activity.getApplicationContext();
        g.addReturned(new ItemObject(item_info[0], item_info[1], item_info[2], item_info[3], item_info[4]));
    }

    @JavascriptInterface
    public void clearReturned(){
        Globals g = (Globals)activity.getApplicationContext();
        g.clearReturned();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JavascriptInterface
    public void addBettedMatch(String s) {
        String match_info[] = s.split("---");
        Globals g = (Globals) activity.getApplicationContext();
        System.out.println(s);
        g.addBetted_Match(new MatchObject(match_info[0], match_info[1], match_info[2], match_info[3], match_info[4],match_info[5]));
    }


    @JavascriptInterface
    public void addItems(String s, int i) {
        String item_info[] = s.split("---");
        System.out.println(s);
        Globals g = (Globals)activity.getApplicationContext();
        g.getBetted_Matches().get(i).addBet_items(new ItemObject(item_info[0],item_info[1],item_info[2],item_info[3],item_info[4]));
    }

    @JavascriptInterface
    public void clearBettedMatches(){
        Globals g = (Globals)activity.getApplicationContext();
        g.clearBetted_Matches();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JavascriptInterface
    public void clearReturns(){
        Globals g = (Globals)activity.getApplicationContext();
        g.clearReturns();
    }

    @JavascriptInterface
    public void addReturns(String s){
        String r[] = s.split("---");
        System.out.println(s);
        Globals g = (Globals)activity.getApplicationContext();
        //g.doShowBackpack();
        g.addReturns(new ItemObject(r[0],r[1],r[2],r[3],r[4]));
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JavascriptInterface
    public void clearBackpack(){
        Globals g = (Globals)activity.getApplicationContext();
        g.clearBackpack();
    }

    @JavascriptInterface
    public void addBackpack(String s){
        String r[] = s.split("---");
        System.out.println(s);
        Globals g = (Globals)activity.getApplicationContext();
        g.doShowBackpack();
        g.addBackpack(new ItemObject(r[0],r[1],r[2],r[3],r[4],r[5]));
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @JavascriptInterface
    public void clearBetpageItems(){
        Globals g = (Globals)activity.getApplicationContext();
        g.clearBetpage_items();
    }

    @JavascriptInterface
    public void addBetpageItem(String s){
        String[] e = s.split("---");
        Globals g = (Globals)activity.getApplicationContext();
        g.addBetpage_item(new ItemObject(e[0],e[1],e[2],e[3],e[4]));
        g.dontShowBackpack();
    }

    @JavascriptInterface
    public void updateA(String s){
        Globals g = (Globals)activity.getApplicationContext();
        g.setValA(s);
    }

    @JavascriptInterface
    public void updateB(String s){
        Globals g = (Globals)activity.getApplicationContext();
        g.setValB(s);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}