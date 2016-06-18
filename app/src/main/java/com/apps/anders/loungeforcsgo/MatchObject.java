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
import java.util.concurrent.ExecutionException;

/**
 * Created by Anders on 6/2/2016.
 */
public class MatchObject {
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
    ArrayList<ItemObject> bet_items = new ArrayList<ItemObject>();
    public String getExtra_info() {
        return extra_info;
    }

    public ArrayList<ItemObject> getBet_items() {
        return bet_items;
    }

    public String getTeam_1_url() {
        return team_1_url;
    }

    public String getTeam_2_url() {
        return team_2_url;
    }

    String format;
    String extra_info;
    public String getTime() {
        return time;
    }

    public String getFormat() {
        return format;
    }

    Drawable team_1_image;
    Drawable team_2_image;

    public String getTeam_1_won() {
        return team_1_won;
    }

    public String getTeam_2_won() {
        return team_2_won;
    }

    public MatchObject(String team_1,String team_2,String odds_team_1,String odds_team_2,String team_1_url,String team_2_url,String time, String format, String team_1_won, String team_2_won) throws IOException {
        this.team_1 = team_1.replace("&amp;","&");
        this.team_2 = team_2.replace("&amp;","&");
        this.odds_team_1 = odds_team_1;
        this.odds_team_2 = odds_team_2;
        this.time = time.indexOf("ago")==-1?time.substring(0,time.indexOf("now")+3):time.substring(0,time.indexOf("ago")+3);
        this.format = format;
        extra_info = time.indexOf("ago")==-1?time.substring(time.indexOf("now") + 3):time.substring(time.indexOf("ago")+3);
        this.team_1_won = team_1_won.equals("TAG")?"":"won";
        this.team_2_won = team_2_won.equals("TAG")?"":"won";
        //int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        //System.out.println(team_1_url.split("\"")[1].substring(2,team_1_url.split("\"")[1].length()-11));
        this.team_1_url = "https://" + team_1_url.split("\"")[1].substring(2, team_1_url.split("\"")[1].length() - 11);
        this.team_2_url = "https://"+team_2_url.split("\"")[1].substring(2,team_2_url.split("\"")[1].length()-11);
    }
    public MatchObject(String team_1, String team_2, String odds_team_1, String odds_team_2, String time,String value){
        this.team_1 = team_1.replace("&amp;","&");
        this.team_2 = team_2.replace("&amp;","&");
        this.odds_team_1 = odds_team_1;
        this.odds_team_2 = odds_team_2;
        this.time = time;
        this.value = value;
    }
    public static Drawable fromURL(String url){
        try {
            return new DownloadFiles().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getOdds_team_2() {
        return odds_team_2;
    }

    public String getOdds_team_1() {
        return odds_team_1;
    }

    public String getTeam_2() {
        return team_2;
    }

    public String getTeam_1() {
        return team_1;
    }

    public Drawable getTeam_1_image() {
        return team_1_image;
    }

    public Drawable getTeam_2_image() {
        return team_2_image;
    }
    public void addBet_items(ItemObject i){
        bet_items.add(i);
    }
    public String getValue(){
        return value;
    }
}
