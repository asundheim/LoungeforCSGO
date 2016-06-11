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
    @JavascriptInterface
    public void printAddress(String address) throws IOException {
        //Toast.makeText(activity,address, Toast.LENGTH_SHORT).show();
        String[] info = address.split("---");
        //System.out.println(address);
        //System.out.println("test: "+info[8]);
        Globals g = (Globals)activity.getApplicationContext();
        g.addMatch(new MatchObject(info[0], info[1], info[2], info[3],info[4],info[5],info[6],info[7],info[8],info[9]));
        //System.out.println(info[4]);
    }
    @JavascriptInterface
    public void moveon(){
        Globals g = (Globals)activity.getApplicationContext();
        g.change();
    }
    @JavascriptInterface
    public String getUsername(){
        EditText t = (EditText)activity.findViewById(R.id.username);
        return t.getText().toString();
    }
    @JavascriptInterface
    public String getPassword(){
        EditText t = (EditText)activity.findViewById(R.id.password);
        return t.getText().toString();
    }
    @JavascriptInterface
    public String getAuth(){
        EditText t = (EditText)activity.findViewById(R.id.authcode);
        return t.getText().toString();
    }
    @JavascriptInterface
    public void addWon(String s){
        String[] item_info = s.split("---");
        System.out.println(s);
        Globals g = (Globals)activity.getApplicationContext();
        g.addWon(new ItemObject(item_info[0],item_info[1],item_info[2]));
    }
    @JavascriptInterface
    public void passIt(){
        Globals g = (Globals)activity.getApplicationContext();
        g.changeItems();
    }
}