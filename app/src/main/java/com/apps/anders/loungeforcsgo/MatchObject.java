package com.apps.anders.loungeforcsgo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.ExecutionException;

/**
 * Created by Anders on 6/2/2016.
 */
public class MatchObject{
    String team_1;
    String team_2;
    String odds_team_1;
    String odds_team_2;
    String time;
    String team_1_won;
    String team_2_won;
    String value;
    String team_1_url;
    String team_2_url;
    String match_url;
    String format;
    String extra_info;
    String team_1_picked;
    String team_2_picked;
    ArrayList<ItemObject> bet_items = new ArrayList<ItemObject>();

    public MatchObject(String team_1,String team_2,String odds_team_1,String odds_team_2,String team_1_url,String team_2_url,String time, String format, String team_1_won, String team_2_won, String match_url) throws IOException {
        this.team_1 = team_1.replace("&amp;","&");
        this.team_2 = team_2.replace("&amp;","&");
        this.odds_team_1 = odds_team_1;
        this.odds_team_2 = odds_team_2;
        this.time = time.indexOf("ago")==-1?time.substring(0,time.indexOf("now")+3):time.substring(0,time.indexOf("ago")+3);
        this.format = format;
        extra_info = time.indexOf("ago")==-1?time.substring(time.indexOf("now") + 3):time.substring(time.indexOf("ago")+3);
        this.team_1_won = team_1_won.equals("TAG")?"":"won";
        this.team_2_won = team_2_won.equals("TAG")?"":"won";
        this.team_1_url = "https://" + team_1_url.split("\"")[1].substring(2, team_1_url.split("\"")[1].length() - 11);
        this.team_2_url = "https://"+team_2_url.split("\"")[1].substring(2,team_2_url.split("\"")[1].length()-11);
        this.match_url = match_url;
    }
    public MatchObject(String team_1, String team_2, String odds_team_1, String odds_team_2, String time,String value,String team_1_picked, String team_2_picked){
        this.team_1 = team_1.replace("&amp;","&");
        this.team_2 = team_2.replace("&amp;","&");
        this.odds_team_1 = odds_team_1;
        this.odds_team_2 = odds_team_2;
        this.time = time;
        this.value = value;
        this.team_1_picked = team_1_picked;
        this.team_2_picked = team_2_picked;
    }
//////////////////////////////////////////////////////////////////////

    public String getExtra_info() {
        return extra_info;
    }

//////////////////////////////////////////////////////////////////////

    public String getMatch_url() {
        return match_url;
    }

//////////////////////////////////////////////////////////////////////

    public ArrayList<ItemObject> getBet_items() {
        return bet_items;
    }

//////////////////////////////////////////////////////////////////////

    public String getTeam_1_url() {
        return team_1_url;
    }

//////////////////////////////////////////////////////////////////////

    public String getTeam_2_url() {
        return team_2_url;
    }

//////////////////////////////////////////////////////////////////////

    public String getTime() {
        return time;
    }

//////////////////////////////////////////////////////////////////////

    public String getFormat() {
        return format;
    }

//////////////////////////////////////////////////////////////////////

    public String getTeam_1_won() {
        return team_1_won;
    }

//////////////////////////////////////////////////////////////////////

    public String getTeam_2_won() {
        return team_2_won;
    }

//////////////////////////////////////////////////////////////////////

    public String getOdds_team_2() {
        return odds_team_2;
    }

//////////////////////////////////////////////////////////////////////

    public String getOdds_team_1() {
        return odds_team_1;
    }

//////////////////////////////////////////////////////////////////////

    public String getTeam_2() {
        return team_2;
    }

//////////////////////////////////////////////////////////////////////

    public String getTeam_1() {
        return team_1;
    }

//////////////////////////////////////////////////////////////////////

    public void addBet_items(ItemObject i){
        bet_items.add(i);
    }

//////////////////////////////////////////////////////////////////////

    public String getValue(){
        return value;
    }

//////////////////////////////////////////////////////////////////////

    public String getTeam_1_picked() {
        return team_1_picked;
    }

//////////////////////////////////////////////////////////////////////

    public String getTeam_2_picked() {
        return team_2_picked;
    }

//////////////////////////////////////////////////////////////////////
}
