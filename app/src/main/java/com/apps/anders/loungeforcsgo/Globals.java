package com.apps.anders.loungeforcsgo;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Anders on 6/2/2016.
 */
public class Globals extends Application {
    public static ArrayList<MatchObject> matches_ = new ArrayList<MatchObject>();
    public ArrayList<MatchObject> getMatches() {
        return matches_;
    }
    public boolean pass = false;
    public void addMatch(MatchObject m){
        matches_.add(m);
    }
    public void printMatch(){
        System.out.println("Matches "+matches_.get(1));
    }
    public void change(){
        pass = true;
    }
    public boolean passState(){
        return pass;
    }


}
