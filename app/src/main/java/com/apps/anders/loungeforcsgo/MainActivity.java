    package com.apps.anders.loungeforcsgo;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
//import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Ion;

import java.io.File;

    public class MainActivity extends AppCompatActivity{
    WebView webview;
    Toast t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        t= Toast.makeText(this,"",Toast.LENGTH_SHORT);

        webview = new WebView(this);
        //webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        JavaScriptInterface jsInterface = new JavaScriptInterface(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(jsInterface, "JSInterface");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                System.out.println(url);
                if(url.equals("https://csgolounge.com/")) {
                    webview.loadUrl("javascript:(function() { " +
                            "var content = document.getElementsByClassName('match').length; " +
                            "for(i = 0;i<document.getElementsByClassName('match').length;i++){ " +
                            "window.JSInterface.printAddress(document.getElementsByClassName('match')[i].getElementsByClassName('matchleft')[0].getElementsByClassName('teamtext')[0].getElementsByTagName('B')[0].innerHTML + \"---\" + document.getElementsByClassName('match')[i].getElementsByClassName('matchleft')[0].getElementsByClassName('teamtext')[1].getElementsByTagName('B')[0].innerHTML + \"---\" + document.getElementsByClassName('match')[i].getElementsByClassName('matchleft')[0].getElementsByClassName('teamtext')[0].getElementsByTagName('I')[0].innerHTML + \"---\" + document.getElementsByClassName('match')[i].getElementsByClassName('matchleft')[0].getElementsByClassName('teamtext')[1].getElementsByTagName('I')[0].innerHTML + \"---\" + document.getElementsByClassName('match')[i].getElementsByClassName('matchleft')[0].getElementsByClassName('team')[0].style.background + \"---\" + document.getElementsByClassName('match')[i].getElementsByClassName('matchleft')[0].getElementsByClassName('team')[1].style.background + \"---\" + document.getElementsByClassName('whenm')[i].textContent + \"---\" + document.getElementsByClassName('match')[i].getElementsByClassName('matchleft')[0].getElementsByClassName('format')[0].textContent + \"---\" + document.getElementsByClassName('match')[i].getElementsByClassName('matchleft')[0].getElementsByClassName('team')[0].innerHTML + \"TAG\"+ \"---\" + document.getElementsByClassName('match')[i].getElementsByClassName('matchleft')[0].getElementsByClassName('team')[1].innerHTML + \"TAG\"); " +
                            "}" +
                            "window.JSInterface.moveon(); " +
                            "})()");
                    Globals globals = (Globals) getApplicationContext();
                    //globals.printMatch();
                    System.out.println("timing");
                    /*while (globals.passState() == false) {
                    }*/
                    SystemClock.sleep(2000);
                    System.out.println("released");
                    for (int i = 0; i < globals.getMatches().size(); i++) {
                        MatchObject m = globals.getMatches().get(i);
                        //System.out.println(m.getTeam_1() + "(" + m.getOdds_team_1() + ") vs " + m.getTeam_2() + "(" + m.getOdds_team_2() + ")");
                    }
                    setContentView(R.layout.container);
                    LinearLayout container = (LinearLayout) findViewById(R.id.linear_container);
                    for (int i = 0; i < 21; i++) {
                        View newView = LayoutInflater.from(MainActivity.this).inflate(R.layout.new_match, null);
                        ImageButton button1 = (ImageButton) newView.findViewById(R.id.betted_team_button_1);
                        ImageButton button2 = (ImageButton) newView.findViewById(R.id.betted_team_button_2);
                        TextView team_1 = (TextView) newView.findViewById(R.id.betted_team_1);
                        TextView team_2 = (TextView) newView.findViewById(R.id.betted_team_2);

                        TextView team_1_odds = (TextView) newView.findViewById(R.id.betted_odds_1);
                        TextView team_2_odds = (TextView) newView.findViewById(R.id.betted_odds_2);
                        if (globals.getMatches().get(i).getTeam_1_won().equals("won")) {
                            team_1_odds.setTextColor(Color.GREEN);
                            team_2_odds.setTextColor(Color.RED);
                        } else if (globals.getMatches().get(i).getTeam_2_won().equals("won")) {
                            team_2_odds.setTextColor(Color.GREEN);
                            team_1_odds.setTextColor(Color.RED);

                        }
                        TextView time = (TextView) newView.findViewById(R.id.betted_time);
                        TextView format = (TextView) newView.findViewById(R.id.textViewBestOf);
                        TextView extra_info = (TextView) newView.findViewById(R.id.textViewExtra);
                        team_1.setText(globals.getMatches().get(i).getTeam_1());
                        team_2.setText(globals.getMatches().get(i).getTeam_2());
                        team_1_odds.setText(globals.getMatches().get(i).getOdds_team_1());
                        team_2_odds.setText(globals.getMatches().get(i).getOdds_team_2());
                        time.setText(globals.getMatches().get(i).getTime());
                        format.setText(globals.getMatches().get(i).getFormat());
                        extra_info.setText(globals.getMatches().get(i).getExtra_info());
                        //System.out.println(globals.getMatches().get(i).getTeam_1().toLowerCase().replace("\'","").replace("&",""));
                        //int imageResource1 = getResources().getIdentifier(globals.getMatches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", ""), "drawable", getPackageName());
                        //System.out.println(imageResource);
                        File im_1 = new File(Environment.getExternalStorageDirectory()
                                + "/LoungeForCSGO/" + globals.getMatches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", "") + ".jpg");

                        if (!im_1.exists()) {
                            downloadFile("http://www.csgolounge.com/img/teams/" + globals.getMatches().get(i).getTeam_1() + ".jpg", globals.getMatches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", ""));
                            button1.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/LoungeForCSGO/" + globals.getMatches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        } else {
                            button1.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/LoungeForCSGO/" + globals.getMatches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        }

                        File im_2 = new File(Environment.getExternalStorageDirectory()
                                + "/LoungeForCSGO/" + globals.getMatches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", "") + ".jpg");

                        if (!im_2.exists()) {
                            System.out.println("fail");
                            downloadFile("http://www.csgolounge.com/img/teams/" + globals.getMatches().get(i).getTeam_2() + ".jpg", globals.getMatches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", ""));
                            button2.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/LoungeForCSGO/" + globals.getMatches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        } else {
                            button2.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/LoungeForCSGO/" + globals.getMatches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        }

                        container.addView(newView);
                    }
                }else if(url.equals("https://steamcommunity.com/openid/login?openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.mode=checkid_setup&openid.return_to=https%3A%2F%2Fcsgolounge.com%2Flogin&openid.realm=https%3A%2F%2Fcsgolounge.com&openid.ns.sreg=http%3A%2F%2Fopenid.net%2Fextensions%2Fsreg%2F1.1&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select")){
                    setContentView(webview);
                    /*Button clickButton = (Button) findViewById(R.id.submit);
                    clickButton.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            webview.loadUrl("javascript:(function() { " +
                                    "document.getElementById('steamAccountName').value = window.JSInterface.getUsername();" +
                                    "document.getElementById('steamPassword').value = window.JSInterface.getPassword();" +
                                    "document.getElementById('imageLogin').click();" +
                                    "})()");
                        }

                    });
                    Button loginButton = (Button) findViewById(R.id.login);
                    loginButton.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            webview.loadUrl("javascript:(function() { " +
                                    "document.getElementById('twofactorcode_entry').value = window.JSInterface.getAuth();" +
                                    "document.getElementsByClassName('auth_button leftbtn')[0].click();" +
                                    "})()");

                        }

                    });*/

                }else if(url.equals("https://csgolounge.com/mybets")){
                    webview.loadUrl("javascript:(function() { " +
                            "var it = document.getElementsByClassName('full')[document.getElementsByClassName('match').length]; " +
                            "for(i=1;i<it.childNodes.length;i+=2){ " +
                                "window.JSInterface.addWon(it.childNodes[i].childNodes[1].childNodes[1].innerHTML + \"---\" + \"NA\" + \"---\" + it.childNodes[i].childNodes[3].getElementsByClassName('value')[0].innerHTML + \"---\" + it.childNodes[i].childNodes[3].getElementsByClassName('smallimg')[0].src + \"---\" + it.childNodes[i].childNodes[1].childNodes[4].innerHTML);" +
                            "}" +
                            "var its = document.getElementsByClassName('full')[1+(document.getElementsByClassName('match').length)]; " +
                            "for(i=1;i<its.childNodes.length;i+=2){ " +
                                "window.JSInterface.addReturned(its.childNodes[i].childNodes[1].childNodes[1].innerHTML + \"---\" + \"NA\" + \"---\" + its.childNodes[i].childNodes[3].getElementsByClassName('value')[0].innerHTML + \"---\" + its.childNodes[i].childNodes[3].getElementsByClassName('smallimg')[0].src + \"---\" + its.childNodes[i].childNodes[1].childNodes[4].innerHTML);" +
                            "}" +
                            "var fm = document.getElementsByClassName('match'); " +
                            "for(i=0;i<fm.length;i++){ " +
                                "window.JSInterface.addBettedMatch(fm[i].childNodes[1].childNodes[1].childNodes[1].childNodes[1].innerText + \"---\" + fm[i].childNodes[1].childNodes[1].childNodes[5].childNodes[1].innerText + \"---\" + fm[i].childNodes[1].childNodes[1].childNodes[1].childNodes[3].innerText + \"---\" + fm[i].childNodes[1].childNodes[1].childNodes[5].childNodes[3].innerText + \"---\" + document.getElementsByClassName('whenm')[i].innerText + \"---\" + fm[0].getElementsByClassName('full')[0].getElementsByClassName('potwin Value')[0].innerText); " +
                                "var ky = document.getElementsByClassName('match')[i].getElementsByClassName('winsorloses')[1].getElementsByClassName('oitm');" +
                                "for(j=0;j<ky.length;j++){" +
                                    "window.JSInterface.addItems(ky[j].childNodes[1].childNodes[1].innerText + \"---\" + ky[j].childNodes[1].childNodes[1].innerText + \"---\" + ky[j].childNodes[3].getElementsByClassName('value')[0].innerText + \"---\" + ky[j].childNodes[3].getElementsByClassName('smallimg')[0].src + \"---\" + ky[j].childNodes[1].childNodes[4].innerText,i);" +
                                "}" +
                            "}" +
                            "})()");
                    final Globals gl = (Globals) getApplicationContext();
                    System.out.println("held");
                    SystemClock.sleep(4000);
                    System.out.println("continue");

                    setContentView(R.layout.mybets);
                    //setContentView(webview);
                    final GridLayout won = (GridLayout)findViewById(R.id.won);

                    for(int i = 0; i<gl.getWon_items().size(); i++){
                        View newItem = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                        TextView name = (TextView)newItem.findViewById(R.id.name);
                        name.setText(gl.getWon_items().get(i).getName());
                        TextView wear = (TextView)newItem.findViewById(R.id.wear);
                        wear.setText(gl.getWon_items().get(i).getWear());
                        TextView price = (TextView)newItem.findViewById(R.id.price);
                        price.setText(gl.getWon_items().get(i).getPrice());
                        ImageView image = (ImageView)newItem.findViewById(R.id.picture);
                        Ion.with(image).load(gl.getWon_items().get(i).getSrc());
                        final int finalI = i;
                        image.setOnTouchListener(new View.OnTouchListener(){
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                               t.setText(gl.getWon_items().get(finalI).getName());
                               t.show();
                               return true;
                            }
                        });
                        String rarity = gl.getWon_items().get(i).getRarity();
                        price.setBackgroundColor(itemColor(rarity));
                        won.addView(newItem);
                    }
                    GridLayout returned = (GridLayout)findViewById(R.id.returned);
                    for(int i=0;i<gl.getReturned_items().size();i++){
                        View newItem = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                        TextView name = (TextView)newItem.findViewById(R.id.name);
                        name.setText(gl.getReturned_items().get(i).getName());
                        TextView wear = (TextView)newItem.findViewById(R.id.wear);
                        wear.setText(gl.getReturned_items().get(i).getWear());
                        TextView price = (TextView)newItem.findViewById(R.id.price);
                        price.setText(gl.getReturned_items().get(i).getPrice());
                        ImageView image = (ImageView)newItem.findViewById(R.id.picture);
                        Ion.with(image).load(gl.getReturned_items().get(i).getSrc());
                        final int finalK = i;
                        image.setOnTouchListener(new View.OnTouchListener(){
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                t.setText(gl.getReturned_items().get(finalK).getName());
                                t.show();
                                return true;
                            }
                        });
                        String rarity = gl.getReturned_items().get(i).getRarity();
                        price.setBackgroundColor(itemColor(rarity));
                        returned.addView(newItem);
                    }
                    LinearLayout mybets = (LinearLayout)findViewById(R.id.betted_container);
                    for(int i=0;i<gl.getBetted_Matches().size();i++){
                        View bettedMatchView = LayoutInflater.from(MainActivity.this).inflate(R.layout.betted_match, null);
                        TextView team1 = (TextView) bettedMatchView.findViewById(R.id.betted_team_1);
                        team1.setText(gl.getBetted_Matches().get(i).getTeam_1());
                        TextView team2 = (TextView) bettedMatchView.findViewById(R.id.betted_team_2);
                        team2.setText(gl.getBetted_Matches().get(i).getTeam_2());
                        TextView team1_odds = (TextView) bettedMatchView.findViewById(R.id.betted_odds_1);
                        team1_odds.setText(gl.getBetted_Matches().get(i).getOdds_team_1());
                        TextView team2_odds = (TextView) bettedMatchView.findViewById(R.id.betted_odds_2);
                        team2_odds.setText(gl.getBetted_Matches().get(i).getOdds_team_2());
                        TextView time = (TextView)bettedMatchView.findViewById(R.id.betted_time);
                        time.setText(gl.getBetted_Matches().get(i).getTime());
                        TextView val = (TextView)bettedMatchView.findViewById(R.id.betted_expected);
                        val.setText("Expected Return: $" + gl.getBetted_Matches().get(i).getValue().split(" ")[0]);
                        for(int j=1;j<=gl.getBetted_Matches().get(i).getBet_items().size();j++){
                            ImageView imv = (ImageView)bettedMatchView.findViewById(getResources().getIdentifier("betted_item_"+j,"id",getApplicationContext().getPackageName())).findViewById(R.id.picture);
                            Ion.with(imv).load(gl.getBetted_Matches().get(i).getBet_items().get(j-1).getSrc());
                            final int finalI = i;
                            final int finalJ = j;
                            imv.setOnTouchListener(new View.OnTouchListener(){
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    t.setText(gl.getBetted_Matches().get(finalI).getBet_items().get(finalJ -1).getName());
                                    t.show();
                                    return true;
                                }
                            });
                            TextView prc = (TextView)bettedMatchView.findViewById(getResources().getIdentifier("betted_item_"+j,"id",getApplicationContext().getPackageName())).findViewById((R.id.price));
                            prc.setText(gl.getBetted_Matches().get(i).getBet_items().get(j-1).getPrice());
                            String rarity = gl.getBetted_Matches().get(i).getBet_items().get(j-1).getRarity();
                            prc.setBackgroundColor(itemColor(rarity));
                        }
                        ImageButton b1 = (ImageButton)bettedMatchView.findViewById(R.id.betted_team_button_1);
                        ImageButton b2 = (ImageButton)bettedMatchView.findViewById(R.id.betted_team_button_2);
                        File im_1 = new File(Environment.getExternalStorageDirectory()
                                + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", "") + ".jpg");

                        if (!im_1.exists()) {
                            downloadFile("http://www.csgolounge.com/img/teams/" + gl.getBetted_Matches().get(i).getTeam_1() + ".jpg", gl.getBetted_Matches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", ""));
                            b1.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        } else {
                            b1.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        }

                        File im_2 = new File(Environment.getExternalStorageDirectory()
                                + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", "") + ".jpg");

                        if (!im_2.exists()) {
                            System.out.println("fail");
                            downloadFile("http://www.csgolounge.com/img/teams/" + gl.getBetted_Matches().get(i).getTeam_2() + ".jpg", gl.getBetted_Matches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", ""));
                            b2.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        } else {
                            b2.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        }
                        mybets.addView(bettedMatchView);
                    }
                }else{
                    SystemClock.sleep(4000);
                    webview.loadUrl("javascript:(function() { " +
                            "console.log(\"b\");" +
                            "var iw = document.getElementById('backpack').getElementsByClassName('oitm');" +
                            "console.log(iw);" +
                            "window.JSInterface.clearReturns();" +
                            "for(i=0;i<iw.length;i++){" +
                                "window.JSInterface.addReturns(iw[i].childNodes[1].childNodes[1].innerText + \"---\" + iw[i].childNodes[1].childNodes[1].innerText + \"---\" + iw[i].childNodes[3].getElementsByClassName('value')[0].innerText + \"---\" + iw[i].childNodes[3].getElementsByClassName('smallimg')[0].src + \"---\" + iw[i].childNodes[1].childNodes[4].innerText)" +
                            "}" +
                            "})()");
                    final Globals gl = (Globals)getApplicationContext();
                    System.out.println("held");
                    SystemClock.sleep(4000);
                    System.out.println("continue");
                    setContentView(R.layout.beton_match);
                    GridLayout returns = (GridLayout)findViewById(R.id.returns);
                    for(int i=0;i<gl.getReturns().size();i++){
                        View newItem = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                        TextView name = (TextView)newItem.findViewById(R.id.name);
                        name.setText(gl.getReturns().get(i).getName());
                        TextView wear = (TextView)newItem.findViewById(R.id.wear);
                        wear.setText(gl.getReturns().get(i).getWear());
                        TextView price = (TextView)newItem.findViewById(R.id.price);
                        price.setText(gl.getReturns().get(i).getPrice());
                        ImageView image = (ImageView)newItem.findViewById(R.id.picture);
                        Ion.with(image).load(gl.getReturns().get(i).getSrc());
                        final int finalL = i;
                        image.setOnTouchListener(new View.OnTouchListener(){
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                t.setText(gl.getReturns().get(finalL).getName());
                                t.show();
                                return true;
                            }
                        });
                        String rarity = gl.getReturns().get(i).getRarity();
                        price.setBackgroundColor(itemColor(rarity));
                        returns.addView(newItem);
                    }
                }
            }
        });




        webview.loadUrl("http://csgolounge.com/");


        //setContentView(webview);
    }
        int itemColor(String color){
            switch(color){
                case " Industrial":
                    return getResources().getColor(R.color.industrial);
                case " Restricted":
                    return getResources().getColor(R.color.restricted);
                case " Classified":
                    return getResources().getColor(R.color.classified);
                case " Mil-Spec":
                    return getResources().getColor(R.color.milspec);
                case " Consumer":
                    return getResources().getColor(R.color.consumer);
                case " Covert":
                    return getResources().getColor(R.color.covert);
                default:
                    return getResources().getColor(R.color.consumer);
            }
        }
        public void downloadFile(String uRl, String name) {
            File direct = new File(Environment.getExternalStorageDirectory()
                    + "/LoungeForCSGO");

            if (!direct.exists()) {
                direct.mkdirs();
            }

            DownloadManager mgr = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);

            Uri downloadUri = Uri.parse(uRl);
            DownloadManager.Request request = new DownloadManager.Request(
                    downloadUri);

            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI
                            | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false).setTitle("Running")
                    .setDescription("Updating Team Images")
                    .setDestinationInExternalPublicDir("/LoungeForCSGO", name+".jpg");

            mgr.enqueue(request);

        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login) {
            webview.loadUrl("javascript:(function() { " +
                    "document.getElementById('login').click(); " +
                    "})()");
            return true;
        }
        if (id==R.id.bets){
            webview.loadUrl("javascript:(function() { " +
                    "document.getElementById('menu').childNodes[7].click(); " +
                    "})()");
            return true;
        }
        if (id==R.id.logout){
            webview.loadUrl("javascript:(function() { " +
                    "document.getElementById('logout').click(); " +
                    "})()");
        }
        if (id==R.id.test){
            webview.loadUrl("javascript:(function() { " +
                    "document.getElementsByClassName('matchmain')[9].getElementsByClassName('match')[0].getElementsByClassName('matchleft')[0].childNodes[1].click(); " +
                    "})()");
        }
        return super.onOptionsItemSelected(item);
    }

}
