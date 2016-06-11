    package com.apps.anders.loungeforcsgo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity{
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                    while (globals.passState() == false) {
                    }
                    System.out.println("released");
                    for (int i = 0; i < globals.getMatches().size(); i++) {
                        MatchObject m = globals.getMatches().get(i);
                        //System.out.println(m.getTeam_1() + "(" + m.getOdds_team_1() + ") vs " + m.getTeam_2() + "(" + m.getOdds_team_2() + ")");
                    }
                    setContentView(R.layout.container);
                    LinearLayout container = (LinearLayout) findViewById(R.id.linear_container);
                    for (int i = 0; i < 21; i++) {
                        View newView = LayoutInflater.from(MainActivity.this).inflate(R.layout.new_match, null);
                        ImageButton button1 = (ImageButton) newView.findViewById(R.id.button1);
                        ImageButton button2 = (ImageButton) newView.findViewById(R.id.button2);
                        TextView team_1 = (TextView) newView.findViewById(R.id.textView1);
                        TextView team_2 = (TextView) newView.findViewById(R.id.textView2);

                        TextView team_1_odds = (TextView) newView.findViewById(R.id.textView1_odds);
                        TextView team_2_odds = (TextView) newView.findViewById(R.id.textView2_odds);
                        if (globals.getMatches().get(i).getTeam_1_won().equals("won")) {
                            team_1_odds.setTextColor(Color.GREEN);
                            team_2_odds.setTextColor(Color.RED);
                        } else if (globals.getMatches().get(i).getTeam_2_won().equals("won")) {
                            team_2_odds.setTextColor(Color.GREEN);
                            team_1_odds.setTextColor(Color.RED);

                        }
                        TextView time = (TextView) newView.findViewById(R.id.textViewTime);
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
                    setContentView(R.layout.mybets);
                    GridView won = (GridView)findViewById(R.id.won);


                }
            }
        });




        webview.loadUrl("http://csgolounge.com/");


        //setContentView(webview);
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
        }

        return super.onOptionsItemSelected(item);
    }

}
