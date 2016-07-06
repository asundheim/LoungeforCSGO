    package com.apps.anders.loungeforcsgo;

import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.android.vending.billing.IInAppBillingService;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.koushikdutta.ion.Ion;

import java.io.File ;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
/* TODO
     - My bets
        - Get rid of waiting x seconds for load and instead intelligently load data from JS
     - Front page
        - Get rid of waiting x seconds for load and instead intelligently load data from JS
     - Match pages
        - Get rid of waiting x seconds for load and instead intelligently load data from JS
        - Loading notification for loading backpack
        https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=QRP5HUMTSXE3U&lc=US&item_name=CSGOLounge%20for%20Android&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted
 */

    public class MainActivity extends AppCompatActivity {
    WebView webview;
    Toast t;
    GoBetween go;
    InterstitialAd mInterstitialAd;
    boolean loadmatches = false;
       /* IInAppBillingService mService;
        ServiceConnection mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }

            @Override
            public void onServiceConnected(ComponentName name,
                                           IBinder service) {
                mService = IInAppBillingService.Stub.asInterface(service);
            }
        };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Intent serviceIntent =
                new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);*/
        /*mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });*/
        /*requestNewInterstitial();*/
        setContentView(R.layout.loading);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8215399930412333~7017340401");
        loadAnimation();
        t = Toast.makeText(this,"",Toast.LENGTH_SHORT);

        webview = new WebView(this);
        webview.loadUrl("http://csgolounge.com/");
        //webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        JavaScriptInterface jsInterface = new JavaScriptInterface(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(jsInterface, "JSInterface");
        //document.getElementsByClassName('matchleft')[i].getElementsByClassName('format')[0].textContent
        WebSettings settings = webview.getSettings();
        settings.setDomStorageEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                System.out.println(url);
                if(url.equals("https://csgolounge.com/")) {
                    setContentView(R.layout.loading);
                    loadAnimation();
                    webview.loadUrl("javascript:(function() { " +
                            "var content = document.getElementsByClassName('match').length; " +
                            "for(i = 0;i<21;i++){ " +
                                "try{" +
                                    "f = document.getElementsByClassName('matchleft')[i].getElementsByClassName('format')[0].textContent;" +
                                    "window.JSInterface.printAddress(document.getElementsByClassName('matchleft')[i].getElementsByClassName('teamtext')[0].getElementsByTagName('B')[0].innerHTML + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('teamtext')[1].getElementsByTagName('B')[0].innerHTML + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('teamtext')[0].getElementsByTagName('I')[0].innerHTML + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('teamtext')[1].getElementsByTagName('I')[0].innerHTML + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('team')[0].style.background + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('team')[1].style.background + \"---\" + document.getElementsByClassName('matchleft')[i].parentElement.parentElement.getElementsByClassName('matchheader')[0].getElementsByClassName('whenm')[0].textContent + \"---\" + f + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('team')[0].innerHTML + \"TAG\"+ \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('team')[1].innerHTML + \"TAG---\" + i); " +
                                "}catch(err){" +
                                    "f=\"--\";" +
                            //document.getElementsByClassName('matchleft')[0].parentElement.parentElement.getElementsByClassName('matchheader')[0].getElementsByClassName('whenm')[0].textContent

                                    "window.JSInterface.printAddress(document.getElementsByClassName('matchleft')[i].getElementsByClassName('teamtext')[0].getElementsByTagName('B')[0].innerHTML + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('teamtext')[1].getElementsByTagName('B')[0].innerHTML + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('teamtext')[0].getElementsByTagName('I')[0].innerHTML + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('teamtext')[1].getElementsByTagName('I')[0].innerHTML + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('team')[0].style.background + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('team')[1].style.background + \"---\" + document.getElementsByClassName('matchleft')[i].parentElement.parentElement.getElementsByClassName('matchheader')[0].getElementsByClassName('whenm')[0].textContent + \"---\" + f + \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('team')[0].innerHTML + \"TAG\"+ \"---\" + document.getElementsByClassName('matchleft')[i].getElementsByClassName('team')[1].innerHTML + \"TAG---\" + i); " +
                                "}" +
                            "}" +
                            "window.JSInterface.moveon(); " +
                            "})()");
                    final Globals globals = (Globals) getApplicationContext();
                    //globals.printMatch();
                    System.out.println("timing");
                    /*while (loadmatches == false) {
                    }*/

                    SystemClock.sleep(100);
                    System.out.println("released");
                    for (int i = 0; i < globals.getMatches().size(); i++) {
                        MatchObject m = globals.getMatches().get(i);
                        //System.out.println(m.getTeam_1() + "(" + m.getOdds_team_1() + ") vs " + m.getTeam_2() + "(" + m.getOdds_team_2() + ")");
                    }
                   /* globals.addToHomescreens();
                    if (mInterstitialAd.isLoaded()&&globals.getHomescreens()%2!=0) {
                        mInterstitialAd.show();
                    }*/
                    setContentView(R.layout.container);
                    AdView mAdView = (AdView) findViewById(R.id.adView);
                    AdRequest adRequest = new AdRequest.Builder().setGender(AdRequest.GENDER_MALE).build();
                    mAdView.loadAd(adRequest);
                    LinearLayout container = (LinearLayout) findViewById(R.id.linear_container);
                    for (int i = 0; i < 21; i++) {
                        View newView = LayoutInflater.from(MainActivity.this).inflate(R.layout.new_match, null);
                        ImageView button1 = (ImageView) newView.findViewById(R.id.team_1_image);
                        ImageView button2 = (ImageView) newView.findViewById(R.id.team_2_image);
                        final TextView matchlink = (TextView)newView.findViewById(R.id.matchlink);
                        matchlink.setText(globals.getMatches().get(i).getMatch_url());
                        Button ma = (Button)newView.findViewById(R.id.matchButton);
                        final Button team1button = (Button)newView.findViewById(R.id.team_1_button);
                        final Button team2button = (Button)newView.findViewById(R.id.team_2_button);
                        team1button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Globals g = (Globals)getApplicationContext();
                                g.setMatchNum(matchlink.getText().toString());
                                setContentView(R.layout.loading);
                                loadAnimation();
                                webview.loadUrl("javascript:(function() { " +
                                        "document.getElementsByClassName('matchleft')["+ matchlink.getText().toString()+"].childNodes[1].click(); " +
                                        "})()");
                            }
                        });
                        team2button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Globals g = (Globals)getApplicationContext();
                                g.setMatchNum(matchlink.getText().toString());
                                setContentView(R.layout.loading);
                                loadAnimation();
                                webview.loadUrl("javascript:(function() { " +
                                        "document.getElementsByClassName('matchleft')["+ matchlink.getText().toString()+"].childNodes[1].click(); " +
                                        "})()");
                            }
                        });
                        TextView team_1 = (TextView) newView.findViewById(R.id.betted_team_1);
                        TextView team_2 = (TextView) newView.findViewById(R.id.betted_team_2);
                        Ion.with(button1).load(globals.getMatches().get(i).getTeam_1_url());
                        Ion.with(button2).load(globals.getMatches().get(i).getTeam_2_url());
                        TextView team_1_odds = (TextView) newView.findViewById(R.id.betted_odds_1);
                        TextView team_2_odds = (TextView) newView.findViewById(R.id.betted_odds_2);

                        ma.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Globals g = (Globals)getApplicationContext();
                                g.setMatchNum(matchlink.getText().toString());
                                setContentView(R.layout.loading);
                                loadAnimation();
                                webview.loadUrl("javascript:(function() { " +
                                        "document.getElementsByClassName('matchleft')["+ matchlink.getText().toString()+"].childNodes[1].click(); " +
                                        "})()");
                            }
                        });
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
                        System.out.println(globals.getMatches().get(i).getFormat());
                        format.setText(globals.getMatches().get(i).getFormat());
                        extra_info.setText(globals.getMatches().get(i).getExtra_info());
                        //System.out.println(globals.getMatches().get(i).getTeam_1().toLowerCase().replace("\'","").replace("&",""));
                        //int imageResource1 = getResources().getIdentifier(globals.getMatches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", ""), "drawable", getPackageName());
                        //System.out.println(imageResource);

                        /*File im_1 = new File(Environment.getExternalStorageDirectory()
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
                        }*/

                        container.addView(newView);
                    }
                    final SwipeRefreshLayout swipe = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
                    swipe.setOnRefreshListener(
                            new SwipeRefreshLayout.OnRefreshListener() {
                                @Override
                                public void onRefresh() {
                                    globals.clearMatches();
                                    t.setText("Reloading, Please Wait");
                                    t.show();
                                    webview.reload();
                                    // This method performs the actual data-refresh operation.
                                    // The method calls setRefreshing(false) when it's finished
                                    swipe.setRefreshing(false);
                                }
                            }
                    );
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
                            "window.JSInterface.clearWon(); " +
                            "var it = document.getElementsByClassName('full')[document.getElementsByClassName('match').length]; " +
                            "for(i=1;i<it.childNodes.length;i+=2){ " +
                                "window.JSInterface.addWon(it.childNodes[i].childNodes[1].childNodes[1].innerHTML + \"---\" + \"NA\" + \"---\" + it.childNodes[i].childNodes[3].getElementsByClassName('value')[0].innerHTML + \"---\" + it.childNodes[i].childNodes[3].getElementsByClassName('smallimg')[0].src + \"---\" + it.childNodes[i].childNodes[1].childNodes[4].innerHTML + \"---\" + it.childNodes[i].childNodes[3].childNodes[1].innerText);" +
                            "}" +
                            "window.JSInterface.clearReturned();" +
                            "var its = document.getElementsByClassName('full')[1+(document.getElementsByClassName('match').length)]; " +
                            "for(i=1;i<its.childNodes.length;i+=2){ " +
                                "window.JSInterface.addReturned(its.childNodes[i].childNodes[1].childNodes[1].innerHTML + \"---\" + \"NA\" + \"---\" + its.childNodes[i].childNodes[3].getElementsByClassName('value')[0].innerHTML + \"---\" + its.childNodes[i].childNodes[3].getElementsByClassName('smallimg')[0].src + \"---\" + its.childNodes[i].childNodes[1].childNodes[4].innerHTML + \"---\" + its.childNodes[i].childNodes[3].childNodes[1].innerText);" +
                            "}" +
                            "var fm = document.getElementsByClassName('match'); " +
                            "window.JSInterface.clearBettedMatches();" +
                            "try{" +
                            "for(i=0;i<fm.length;i++){ " +

                                    "var ky = document.getElementsByClassName('match')[i].getElementsByClassName('winsorloses')[1].getElementsByClassName('oitm');" +
                                    "window.JSInterface.addBettedMatch(fm[i].childNodes[1].childNodes[1].childNodes[1].childNodes[1].innerText + \"---\" + fm[i].childNodes[1].childNodes[1].childNodes[5].childNodes[1].innerText + \"---\" + fm[i].childNodes[1].childNodes[1].childNodes[1].childNodes[3].innerText + \"---\" + fm[i].childNodes[1].childNodes[1].childNodes[5].childNodes[3].innerText + \"---\" + document.getElementsByClassName('whenm')[i].innerText + \"---\" + fm[i].getElementsByClassName('full')[0].getElementsByClassName('potwin Value')[0].innerText + \"---\" + fm[i].childNodes[1].childNodes[1].childNodes[1].childNodes.length + \"---\" + fm[i].childNodes[1].childNodes[1].childNodes[5].childNodes.length); " +
                                    "for(j=0;j<ky.length;j++){" +
                                        "window.JSInterface.addItems(ky[j].childNodes[1].childNodes[1].innerText + \"---\" + ky[j].childNodes[1].childNodes[1].innerText + \"---\" + ky[j].childNodes[3].getElementsByClassName('value')[0].innerText + \"---\" + ky[j].childNodes[3].getElementsByClassName('smallimg')[0].src + \"---\" + ky[j].childNodes[1].childNodes[4].innerText + \"---\" + ky[j].childNodes[1].innerText,i);" +
                                    "}" +

                            "}" +
                            "}catch(err){console.log(err);}" +
                            "})()");
                    final Globals gl = (Globals) getApplicationContext();
                    System.out.println("held");
                    SystemClock.sleep(1000);

                    System.out.println("continue");

                    setContentView(R.layout.mybets);
                    //setContentView(webview);
                    final GridLayout won = (GridLayout)findViewById(R.id.won);
                    double totalVal = 0;
                    double classified = 0;
                    double covert = 0;
                    double restricted = 0;
                    double milspec = 0;
                    double consumer = 0;
                    double industrial = 0;
                    double other = 0;
                    for(int i = 0; i<gl.getWon_items().size(); i++){
                        View newItem = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                        final TextView name = (TextView)newItem.findViewById(R.id.name);
                        name.setText(gl.getWon_items().get(i).getName());
                        TextView wear = (TextView)newItem.findViewById(R.id.wear);
                        wear.setText(gl.getWon_items().get(i).getWear());
                        TextView price = (TextView)newItem.findViewById(R.id.price);
                        price.setText(gl.getWon_items().get(i).getPrice());
                        totalVal+=Double.parseDouble(gl.getWon_items().get(i).getPrice().substring(2));
                        switch(gl.getWon_items().get(i).getRarity()){
                            case " Industrial":
                                industrial+=Double.parseDouble(gl.getWon_items().get(i).getPrice().substring(2));
                                break;
                            case " Restricted":
                                restricted += Double.parseDouble(gl.getWon_items().get(i).getPrice().substring(2));
                                break;
                            case " Classified":
                                classified += Double.parseDouble(gl.getWon_items().get(i).getPrice().substring(2));
                                break;
                            case " Mil-Spec":
                                milspec += Double.parseDouble(gl.getWon_items().get(i).getPrice().substring(2));
                                break;
                            case " Consumer":
                                consumer += Double.parseDouble(gl.getWon_items().get(i).getPrice().substring(2));
                                break;
                            case " Covert":
                                covert += Double.parseDouble(gl.getWon_items().get(i).getPrice().substring(2));
                                break;
                            default:
                                other += Double.parseDouble(gl.getWon_items().get(i).getPrice().substring(2));
                                break;
                        }
                        ImageView image = (ImageView)newItem.findViewById(R.id.picture);
                        Ion.with(image).load(gl.getWon_items().get(i).getSrc());
                        ImageView st = (ImageView)newItem.findViewById(R.id.imageView10);
                        if(gl.getWon_items().get(i).getST().equals("ST")){
                            st.setBackgroundColor(getResources().getColor(R.color.StatTrak));
                        }
                        Button b = (Button)newItem.findViewById(R.id.selectButton);
                        b.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                t.setText(name.getText().toString());
                                t.show();
                                return true;
                            }
                        });
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
                        final TextView name = (TextView)newItem.findViewById(R.id.name);
                        name.setText(gl.getReturned_items().get(i).getName());
                        TextView wear = (TextView)newItem.findViewById(R.id.wear);
                        wear.setText(gl.getReturned_items().get(i).getWear());
                        TextView price = (TextView)newItem.findViewById(R.id.price);
                        price.setText(gl.getReturned_items().get(i).getPrice());
                        totalVal+=Double.parseDouble(gl.getReturned_items().get(i).getPrice().substring(2));
                        switch(gl.getReturned_items().get(i).getRarity()){
                            case " Industrial":
                                industrial += Double.parseDouble(gl.getReturned_items().get(i).getPrice().substring(2));
                                break;
                            case " Restricted":
                                restricted += Double.parseDouble(gl.getReturned_items().get(i).getPrice().substring(2));
                                break;
                            case " Classified":
                                classified += Double.parseDouble(gl.getReturned_items().get(i).getPrice().substring(2));
                                break;
                            case " Mil-Spec":
                                milspec += Double.parseDouble(gl.getReturned_items().get(i).getPrice().substring(2));
                                break;
                            case " Consumer":
                                consumer += Double.parseDouble(gl.getReturned_items().get(i).getPrice().substring(2));
                                break;
                            case " Covert":
                                covert += Double.parseDouble(gl.getReturned_items().get(i).getPrice().substring(2));
                                break;
                            default:
                                other += Double.parseDouble(gl.getReturned_items().get(i).getPrice().substring(2));
                                break;
                        }
                        ImageView image = (ImageView)newItem.findViewById(R.id.picture);
                        Ion.with(image).load(gl.getReturned_items().get(i).getSrc());
                        ImageView st = (ImageView)newItem.findViewById(R.id.imageView10);
                        if(gl.getReturned_items().get(i).getST().equals("ST")){
                            st.setBackgroundColor(getResources().getColor(R.color.StatTrak));
                        }
                        Button b = (Button)newItem.findViewById(R.id.selectButton);
                        b.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                t.setText(name.getText().toString());
                                t.show();
                                return true;
                            }
                        });
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
                    TextView totl = (TextView)findViewById(R.id.totalValue);
                    totl.setText("Total Value $"+(double)Math.round(totalVal * 100d) / 100d);
                    TextView cov = (TextView)findViewById(R.id.betted_covert);
                    cov.setText("Covert: $"+(double)Math.round(covert * 100d) / 100d);
                    TextView clas = (TextView)findViewById(R.id.betted_classified);
                    clas.setText("Classified: $"+(double)Math.round(classified * 100d) / 100d);
                    TextView res = (TextView)findViewById(R.id.betted_restricted);
                    res.setText("Restricted: $"+(double)Math.round(restricted * 100d) / 100d);
                    TextView mil = (TextView)findViewById(R.id.betted_milspec);
                    mil.setText("Mil-Spec: $"+(double)Math.round(milspec * 100d) / 100d);
                    TextView ind = (TextView)findViewById(R.id.betted_industrial);
                    ind.setText("Industrial: $"+(double)Math.round(industrial * 100d) / 100d);
                    TextView con = (TextView)findViewById(R.id.betted_consumer);
                    con.setText("Consumer: $"+(double)Math.round(consumer * 100d) / 100d);
                    TextView oth = (TextView)findViewById(R.id.betted_default);
                    oth.setText("Other: $"+(double)Math.round(other * 100d) / 100d);
                    LinearLayout mybets = (LinearLayout)findViewById(R.id.betted_container);
                    for(int i=0;i<gl.getBetted_Matches().size();i++){
                        final View bettedMatchView = LayoutInflater.from(MainActivity.this).inflate(R.layout.betted_match, null);
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
                        final ImageView bt1 = (ImageView)bettedMatchView.findViewById(R.id.imageView3);
                        final ImageView bt2 = (ImageView)bettedMatchView.findViewById(R.id.imageView4);
                        if(gl.getBetted_Matches().get(i).getTeam_1_picked().equals("7")){
                            bt1.setBackgroundColor(getResources().getColor(R.color.selectedteam));
                            bt2.setBackgroundColor(getResources().getColor(R.color.behindimage));
                        }else{
                            bt2.setBackgroundColor(getResources().getColor(R.color.selectedteam));
                            bt1.setBackgroundColor(getResources().getColor(R.color.behindimage));
                        }
                        TextView val = (TextView)bettedMatchView.findViewById(R.id.betted_expected);
                        final Button change = (Button)bettedMatchView.findViewById(R.id.changeteam);
                        final int finalI1 = i;
                        change.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                change.setVisibility(View.GONE);
                                final Button teamLeft = (Button)bettedMatchView.findViewById(R.id.teamLeft);
                                final Button teamRight = (Button)bettedMatchView.findViewById(R.id.teamRight);
                                teamLeft.setVisibility(View.VISIBLE);
                                teamRight.setVisibility(View.VISIBLE);
                                webview.loadUrl("javascript:(function() { " +
                                        "document.getElementsByClassName('buttonright')["+ finalI1 +"].click();" +
                                        "})()");
                                teamLeft.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        change.setVisibility(View.VISIBLE);
                                        teamLeft.setVisibility(View.GONE);
                                        teamRight.setVisibility(View.GONE);
                                        webview.loadUrl("javascript:(function() { " +
                                                "document.getElementsByClassName('matchheader')["+ finalI1 +"].getElementsByClassName('button changeteam')[0].click();" +
                                                "})()");

                                        bt1.setBackgroundColor(getResources().getColor(R.color.selectedteam));
                                        bt2.setBackgroundColor(getResources().getColor(R.color.behindimage));
                                    }
                                });
                                teamRight.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        change.setVisibility(View.VISIBLE);
                                        teamLeft.setVisibility(View.GONE);
                                        teamRight.setVisibility(View.GONE);
                                        webview.loadUrl("javascript:(function() { " +
                                                "document.getElementsByClassName('matchheader')["+ finalI1 +"].getElementsByClassName('button changeteam')[1].click();" +
                                                "})()");
                                        ImageView bt1 = (ImageView)bettedMatchView.findViewById(R.id.imageView3);
                                        ImageView bt2 = (ImageView)bettedMatchView.findViewById(R.id.imageView4);
                                        bt2.setBackgroundColor(getResources().getColor(R.color.selectedteam));
                                        bt1.setBackgroundColor(getResources().getColor(R.color.behindimage));
                                    }
                                });
                            }
                        });
                        /*final TextView matchnum = (TextView)bettedMatchView.findViewById(R.id.matchnum);
                        matchnum.setText(gl.getBetted_Matches().get(i).getMatch_url());
                        Button matchbutton = (Button)bettedMatchView.findViewById(R.id.matchButton);
                        matchbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setContentView(R.layout.loading);
                                webview.loadUrl("javascript:(function() { " +
                                        "document.getElementsByClassName('match')["+ matchnum.getText().toString()+"].getElementsByClassName('winsorloses')[0].childNodes[1].click(); " +
                                        "})()");
                            }
                        });*/
                        double total = 0;
                        val.setText("Expected Return: $" + gl.getBetted_Matches().get(i).getValue().split(" ")[0]);
                        for(int j=1;j<=gl.getBetted_Matches().get(i).getBet_items().size();j++){
                            total+=Double.parseDouble(gl.getBetted_Matches().get(i).getBet_items().get(j-1).getPrice().substring(2));
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
                        TextView valueBet = (TextView)bettedMatchView.findViewById(R.id.betted_valuebet);
                        valueBet.setText((double)Math.round(total * 100d) / 100d==0.0?"Item Draft/Load Error":"Value Bet $"+(double)Math.round(total * 100d) / 100d);
                        ImageView b1 = (ImageView)bettedMatchView.findViewById(R.id.team_1_image);
                        ImageView b2 = (ImageView)bettedMatchView.findViewById(R.id.team_2_image);
                        Ion.with(b1).load("https://csgolounge.com/img/teams/"+gl.getBetted_Matches().get(i).getTeam_1()+".jpg");
                        Ion.with(b2).load("https://csgolounge.com/img/teams/"+gl.getBetted_Matches().get(i).getTeam_2()+".jpg");
                        /*File im_1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", "") + ".jpg");

                        if (!im_1.exists()) {
                            downloadFile("http://www.csgolounge.com/img/teams/" + gl.getBetted_Matches().get(i).getTeam_1() + ".jpg", gl.getBetted_Matches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", ""));
                            b1.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        } else {
                            b1.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_1().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        }

                        File im_2 = new File(Environment.getExternalStorageDirectory()
                                + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", "") + ".jpg");

                        if (!im_2.exists()) {
                            System.out.println("fail");
                            downloadFile("http://www.csgolounge.com/img/teams/" + gl.getBetted_Matches().get(i).getTeam_2() + ".jpg", gl.getBetted_Matches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", ""));
                            b2.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        } else {
                            b2.setImageDrawable(Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/LoungeForCSGO/" + gl.getBetted_Matches().get(i).getTeam_2().toLowerCase().replace("\'", "").replace("&", "") + ".jpg"));
                        }*/
                        mybets.addView(bettedMatchView);
                    }
                    final SwipeRefreshLayout swipe = (SwipeRefreshLayout)findViewById(R.id.reloadMyBets);
                    swipe.setOnRefreshListener(
                            new SwipeRefreshLayout.OnRefreshListener() {
                                @Override
                                public void onRefresh() {
                                    gl.clearReturned();
                                    gl.clearBetted_Matches();
                                    gl.clearWon();
                                    t.setText("Reloading, Please Wait");
                                    t.show();
                                    webview.reload();
                                    // This method performs the actual data-refresh operation.
                                    // The method calls setRefreshing(false) when it's finished
                                    swipe.setRefreshing(false);
                                }
                            }
                    );
                }else{

                    webview.loadUrl("javascript:(function() { " +
                            "try{" +
                                "var lg = document.getElementsByClassName('betpoll')[0].getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                "window.JSInterface.clearBetpageItems();" +
                                "for(i=0;i<lg.length;i++){" +
                                    "window.JSInterface.addBetpageItem(lg[i].childNodes[1].childNodes[1].innerText + \"---\" + lg[i].childNodes[1].childNodes[1].innerText + \"---\" + lg[i].childNodes[3].getElementsByClassName('value')[0].innerText + \"---\" + lg[i].childNodes[3].getElementsByClassName('smallimg')[0].src + \"---\" + lg[i].childNodes[1].childNodes[4].innerText + \"---\" + lg[i].childNodes[1].innerText)" +
                                "}" +
                            "}catch(err){" +
                                "" +
                            "}" +
                            "})()");
                    setContentView(R.layout.beton_match);
                    loadReturns();
                    final Globals gl = (Globals)getApplicationContext();
                    System.out.println("held");
                    System.out.println("continue");
                    gl.setStateReturns();
                    int mN = gl.getMatchNum();
                    ImageView button1 = (ImageView)findViewById(R.id.team_1_image);
                    ImageView button2 = (ImageView)findViewById(R.id.team_2_image);
                    Button selectTeam1 = (Button)findViewById(R.id.team1_button);
                    selectTeam1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            webview.loadUrl("javascript:(function() { " +
                                    "document.getElementsByClassName('team')[0].click(); " +
                                    "})()");
                            ImageView t1 = (ImageView)findViewById(R.id.imageView8);
                            ImageView t2 = (ImageView)findViewById(R.id.imageView9);
                            t1.setBackgroundColor(getResources().getColor(R.color.selectedteam));
                            t2.setBackgroundColor(getResources().getColor(R.color.behindimage));
                            gl.setBetTrue();
                        }
                    });
                    Button selectTeam2 = (Button)findViewById(R.id.team2_button);
                    selectTeam2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            webview.loadUrl("javascript:(function() { " +
                                    "document.getElementsByClassName('team')[1].click(); " +
                                    "})()");
                            ImageView t1 = (ImageView)findViewById(R.id.imageView8);
                            ImageView t2 = (ImageView)findViewById(R.id.imageView9);
                            t2.setBackgroundColor(getResources().getColor(R.color.selectedteam));
                            t1.setBackgroundColor(getResources().getColor(R.color.behindimage));
                            gl.setBetTrue();
                        }
                    });
                    Button placebet = (Button)findViewById(R.id.placebet);
                    placebet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                System.out.println("ffffffuuuu");
                            t.setText("Place Bet");
                            t.show();
                                //setContentView(R.layout.loading);
                                //loadAnimation();
                                webview.loadUrl("javascript:(function() { " +
                                        "document.getElementById('placebut').click(); " +
                                        "})()");
                                gl.setBetFalse();

                        }
                    });
                    TextView team_1 = (TextView)findViewById(R.id.betted_team_1);
                    TextView team_2 = (TextView)findViewById(R.id.betted_team_2);
                    TextView time = (TextView)findViewById(R.id.betted_time);
                    Ion.with(button1).load(gl.getMatches().get(mN).getTeam_1_url());
                    Ion.with(button2).load(gl.getMatches().get(mN).getTeam_2_url());
                    TextView team_1_odds = (TextView)findViewById(R.id.betted_odds_1);
                    TextView team_2_odds = (TextView)findViewById(R.id.betted_odds_2);
                    team_1.setText(gl.getMatches().get(mN).getTeam_1());
                    team_2.setText(gl.getMatches().get(mN).getTeam_2());
                    team_1_odds.setText(gl.getMatches().get(mN).getOdds_team_1());
                    team_2_odds.setText(gl.getMatches().get(mN).getOdds_team_2());
                    time.setText(gl.getMatches().get(mN).getTime());
                    TextView valA = (TextView)findViewById(R.id.return_team1);
                    TextView valB = (TextView)findViewById(R.id.return_team2);
                    webview.loadUrl("javascript:(function() { " +
                            "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                            "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                            "})()");
                    SystemClock.sleep(100);
                    valA.setText(gl.getValA());
                    valB.setText(gl.getValB());
                    for(int j=1;j<=gl.getBetpage_items().size();j++){
                        ImageView imv = (ImageView)findViewById(getResources().getIdentifier("betted_item_"+j,"id",getApplicationContext().getPackageName())).findViewById(R.id.picture);
                        Ion.with(imv).load(gl.getBetpage_items().get(j-1).getSrc());
                        final int finalJ = j;
                        imv.setOnTouchListener(new View.OnTouchListener(){
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                t.setText(gl.getBetpage_items().get(finalJ -1).getName());
                                t.show();
                                return true;
                            }
                        });
                        TextView prc = (TextView)findViewById(getResources().getIdentifier("betted_item_"+j,"id",getApplicationContext().getPackageName())).findViewById((R.id.price));
                        prc.setText(gl.getBetpage_items().get(j-1).getPrice());
                        String rarity = gl.getBetpage_items().get(j-1).getRarity();
                        prc.setBackgroundColor(itemColor(rarity));
                    }
                    Button switchreturns = (Button)findViewById(R.id.switch_returns);
                    switchreturns.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (gl.getInvState().equals("backpack")) {
                            webview.loadUrl("javascript:(function() { " +
                                    "document.getElementsByClassName('tab')[1].click(); " +
                                    "})()");

                                loadReturns();
                                TextView t1 = (TextView) findViewById(R.id.betted_item_1).findViewById(R.id.price);
                                TextView t2 = (TextView) findViewById(R.id.betted_item_2).findViewById(R.id.price);
                                TextView t3 = (TextView) findViewById(R.id.betted_item_3).findViewById(R.id.price);
                                TextView t4 = (TextView) findViewById(R.id.betted_item_4).findViewById(R.id.price);
                                t1.setText("");
                                t2.setText("");
                                t3.setText("");
                                t4.setText("");
                                t1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                t2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                t3.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                t4.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
                                ImageView i1 = (ImageView) findViewById(R.id.betted_item_1).findViewById(R.id.picture);
                                ImageView i2 = (ImageView) findViewById(R.id.betted_item_2).findViewById(R.id.picture);
                                ImageView i3 = (ImageView) findViewById(R.id.betted_item_3).findViewById(R.id.picture);
                                ImageView i4 = (ImageView) findViewById(R.id.betted_item_4).findViewById(R.id.picture);
                                i1.setImageResource(android.R.color.transparent);
                                i2.setImageResource(android.R.color.transparent);
                                i3.setImageResource(android.R.color.transparent);
                                i4.setImageResource(android.R.color.transparent);
                                TextView valA = (TextView) findViewById(R.id.return_team1);
                                TextView valB = (TextView) findViewById(R.id.return_team2);
                                webview.loadUrl("javascript:(function() { " +
                                        "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                        "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                        "})()");
                                SystemClock.sleep(100);
                                valA.setText(gl.getValA());
                                valB.setText(gl.getValB());
                                viewSwitcher.showPrevious();
                                gl.setStateReturns();
                            }
                        }
                    });
                    Button switchbackpack = (Button)findViewById(R.id.switch_backpack);
                    /*if(!gl.getShowBackpack()){
                        switchbackpack.setVisibility(View.GONE);
                        switchreturns.setVisibility(View.GONE);
                    }*/
                    Button b = (Button)findViewById(R.id.reloadBackpack);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            webview.loadUrl("javascript:(function() { " +
                                    "document.getElementsByClassName('srch')[0].childNodes[1].click(); " +
                                    "})()");
                            loadBackpack();
                        }
                    });
                    switchbackpack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (gl.getInvState().equals("returns")) {
                                webview.loadUrl("javascript:(function() { " +
                                        "document.getElementsByClassName('tab')[0].click(); " +
                                        "})()");
                                t.setText("Loading");
                                t.show();
                                loadBackpack();
                                TextView t1 = (TextView) findViewById(R.id.betted_item_1).findViewById(R.id.price);
                                TextView t2 = (TextView) findViewById(R.id.betted_item_2).findViewById(R.id.price);
                                TextView t3 = (TextView) findViewById(R.id.betted_item_3).findViewById(R.id.price);
                                TextView t4 = (TextView) findViewById(R.id.betted_item_4).findViewById(R.id.price);
                                t1.setText("");
                                t2.setText("");
                                t3.setText("");
                                t4.setText("");
                                t1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                t2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                t3.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                t4.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                ImageView i1 = (ImageView) findViewById(R.id.betted_item_1).findViewById(R.id.picture);
                                ImageView i2 = (ImageView) findViewById(R.id.betted_item_2).findViewById(R.id.picture);
                                ImageView i3 = (ImageView) findViewById(R.id.betted_item_3).findViewById(R.id.picture);
                                ImageView i4 = (ImageView) findViewById(R.id.betted_item_4).findViewById(R.id.picture);
                                i1.setImageResource(android.R.color.transparent);
                                i2.setImageResource(android.R.color.transparent);
                                i3.setImageResource(android.R.color.transparent);
                                i4.setImageResource(android.R.color.transparent);
                                TextView valA = (TextView) findViewById(R.id.return_team1);
                                TextView valB = (TextView) findViewById(R.id.return_team2);
                                webview.loadUrl("javascript:(function() { " +
                                        "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                        "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                        "})()");
                                SystemClock.sleep(100);
                                valA.setText(gl.getValA());
                                valB.setText(gl.getValB());
                                ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
                                viewSwitcher.showNext();
                                gl.setStateBackpack();
                            }
                        }
                    });

                }
            }
        });
        //setContentView(webview);
    }
        void loadReturns(){
            Button b = (Button)findViewById(R.id.reloadBackpack);
            b.setVisibility(View.INVISIBLE);
            SystemClock.sleep(4000);
            webview.loadUrl("javascript:(function() { " +
                    "var iw = document.getElementById('backpack').getElementsByClassName('oitm');" +
                    "console.log(iw);" +
                    "window.JSInterface.clearReturns();" +
                    "for(i=0;i<iw.length;i++){" +
                        "window.JSInterface.addReturns(iw[i].childNodes[1].childNodes[1].innerText + \"---\" + iw[i].childNodes[1].childNodes[1].innerText + \"---\" + iw[i].childNodes[3].getElementsByClassName('value')[0].innerText + \"---\" + iw[i].childNodes[3].getElementsByClassName('smallimg')[0].src + \"---\" + iw[i].childNodes[1].childNodes[4].innerText + \"---\" + iw[i].childNodes[3].childNodes[1].innerText)" +
                    "}" +
                    "})()");
            SystemClock.sleep(500);
            final Globals gl = (Globals)getApplicationContext();
            final GridLayout returns = (GridLayout)findViewById(R.id.returns);
            returns.removeAllViews();
            double totalVal = 0;
            double classified = 0;
            double covert = 0;
            double restricted = 0;
            double milspec = 0;
            double consumer = 0;
            double industrial = 0;
            double other = 0;
            for(int i=0;i<gl.getReturns().size();i++){
                final View newItem = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                TextView name = (TextView)newItem.findViewById(R.id.name);
                name.setText(gl.getReturns().get(i).getName());
                TextView wear = (TextView)newItem.findViewById(R.id.wear);
                wear.setText(gl.getReturns().get(i).getWear());
                TextView price = (TextView)newItem.findViewById(R.id.price);
                price.setText(gl.getReturns().get(i).getPrice());
                ImageView image = (ImageView)newItem.findViewById(R.id.picture);
                Ion.with(image).load(gl.getReturns().get(i).getSrc());
                ImageView st = (ImageView)newItem.findViewById(R.id.imageView10);
                if(gl.getReturns().get(i).getST().equals("ST")){
                    st.setBackgroundColor(getResources().getColor(R.color.StatTrak));
                }
                totalVal+=Double.parseDouble(gl.getReturns().get(i).getPrice().substring(2));
                switch(gl.getReturns().get(i).getRarity()){
                    case " Industrial":
                        industrial += Double.parseDouble(gl.getReturns().get(i).getPrice().substring(2));
                        break;
                    case " Restricted":
                        restricted += Double.parseDouble(gl.getReturns().get(i).getPrice().substring(2));
                        break;
                    case " Classified":
                        classified += Double.parseDouble(gl.getReturns().get(i).getPrice().substring(2));
                        break;
                    case " Mil-Spec":
                        milspec += Double.parseDouble(gl.getReturns().get(i).getPrice().substring(2));
                        break;
                    case " Consumer":
                        consumer += Double.parseDouble(gl.getReturns().get(i).getPrice().substring(2));
                        break;
                    case " Covert":
                        covert += Double.parseDouble(gl.getReturns().get(i).getPrice().substring(2));
                        break;
                    default:
                        other += Double.parseDouble(gl.getReturns().get(i).getPrice().substring(2));
                        break;
                }

                final int finalL = i;
                image.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        t.setText(gl.getReturns().get(finalL).getName());
                        t.show();
                        return true;
                    }
                });
                final Button addButton = (Button)newItem.findViewById(R.id.selectButton);
                addButton.setText(""+i);
                addButton.setTextColor(Color.TRANSPARENT);
                addButton.setOnLongClickListener(new View.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View v) {
                        t.setText(gl.getReturns().get(finalL).getName());
                        t.show();
                        return true;
                    }
                });
                final int finalI = i;
                addButton.setOnClickListener(new View.OnClickListener() {
                    final String s = gl.getReturns().get(finalI).getName();
                    @Override
                    public void onClick(View v) {
                        webview.loadUrl("javascript:(function() { " +
                                "var f = document.getElementById('backpack').getElementsByClassName('oitm');" +
                                "for(x=0;x<f.length;x++){" +
                                    "if(f[x].getElementsByClassName('name')[0].childNodes[1].innerText==\""+s+"\"){" +
                                        "f[x].getElementsByClassName('item')[0].click();" +
                                    "}" +
                                "}" +
                                "})()");
                        TextView t1 = (TextView)findViewById(R.id.betted_item_1).findViewById(R.id.price);
                        TextView t2 = (TextView)findViewById(R.id.betted_item_2).findViewById(R.id.price);
                        TextView t3 = (TextView)findViewById(R.id.betted_item_3).findViewById(R.id.price);
                        TextView t4 = (TextView)findViewById(R.id.betted_item_4).findViewById(R.id.price);
                        TextView t5 = (TextView)findViewById(R.id.betted_item_5).findViewById(R.id.price);
                        TextView t6 = (TextView)findViewById(R.id.betted_item_6).findViewById(R.id.price);
                        if(t1.getText().toString().equals("")){
                            gl.setItem1(newItem);
                            ImageView i1 = (ImageView)findViewById(R.id.betted_item_1).findViewById(R.id.picture);
                            Ion.with(i1).load(gl.getReturns().get(finalI).getSrc());
                            t1.setText(gl.getReturns().get(finalI).getPrice());
                            String rarity = gl.getReturns().get(finalI).getRarity();
                            t1.setBackgroundColor(itemColor(rarity));
                            Button b1 = (Button)findViewById(R.id.betted_item_1).findViewById(R.id.selectButton);
                            b1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        returns.addView(gl.getItem1());
                                        ImageView ii1 = (ImageView) findViewById(R.id.betted_item_1).findViewById(R.id.picture);
                                        TextView tt1 = (TextView) findViewById(R.id.betted_item_1).findViewById(R.id.price);
                                        ii1.setImageResource(android.R.color.transparent);
                                        tt1.setText("");
                                        tt1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                        webview.loadUrl("javascript:(function() { " +
                                                "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                                "for(x=0;x<g.length;x++){" +
                                                "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\"" + s + "\"){" +
                                                "g[x].getElementsByClassName('item tobet')[0].click();" +
                                                "}" +
                                                "}" +
                                                "})()");
                                        TextView valA = (TextView) findViewById(R.id.return_team1);
                                        TextView valB = (TextView) findViewById(R.id.return_team2);
                                        webview.loadUrl("javascript:(function() { " +
                                                "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                                "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                                "})()");
                                        SystemClock.sleep(100);
                                        valA.setText(gl.getValA());
                                        valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            returns.removeView(newItem);
                        }else if(t2.getText().toString().equals("")){
                            gl.setItem2(newItem);
                            ImageView i2 = (ImageView)findViewById(R.id.betted_item_2).findViewById(R.id.picture);
                            Ion.with(i2).load(gl.getReturns().get(finalI).getSrc());
                            t2.setText(gl.getReturns().get(finalI).getPrice());
                            String rarity = gl.getReturns().get(finalI).getRarity();
                            t2.setBackgroundColor(itemColor(rarity));
                            Button b2 = (Button)findViewById(R.id.betted_item_2).findViewById(R.id.selectButton);
                            b2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        returns.addView(gl.getItem2());
                                        ImageView ii2 = (ImageView) findViewById(R.id.betted_item_2).findViewById(R.id.picture);
                                        TextView tt2 = (TextView) findViewById(R.id.betted_item_2).findViewById(R.id.price);
                                        ii2.setImageResource(android.R.color.transparent);
                                        tt2.setText("");
                                        tt2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                        webview.loadUrl("javascript:(function() { " +
                                                "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                                "for(x=0;x<g.length;x++){" +
                                                "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\"" + s + "\"){" +
                                                "g[x].getElementsByClassName('item tobet')[0].click();" +
                                                "}" +
                                                "}" +
                                                "})()");
                                        TextView valA = (TextView) findViewById(R.id.return_team1);
                                        TextView valB = (TextView) findViewById(R.id.return_team2);
                                        webview.loadUrl("javascript:(function() { " +
                                                "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                                "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                                "})()");
                                        SystemClock.sleep(100);
                                        valA.setText(gl.getValA());
                                        valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            returns.removeView(newItem);
                            //returns.removeViewAt(Integer.parseInt(addButton.getText().toString()));
                        }else if(t3.getText().toString().equals("")){
                            gl.setItem3(newItem);
                            ImageView i3 = (ImageView)findViewById(R.id.betted_item_3).findViewById(R.id.picture);
                            Ion.with(i3).load(gl.getReturns().get(finalI).getSrc());
                            t3.setText(gl.getReturns().get(finalI).getPrice());
                            String rarity = gl.getReturns().get(finalI).getRarity();
                            t3.setBackgroundColor(itemColor(rarity));
                            Button b3 = (Button)findViewById(R.id.betted_item_3).findViewById(R.id.selectButton);
                            b3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        returns.addView(gl.getItem3());
                                        ImageView ii3 = (ImageView) findViewById(R.id.betted_item_3).findViewById(R.id.picture);
                                        TextView tt3 = (TextView) findViewById(R.id.betted_item_3).findViewById(R.id.price);
                                        ii3.setImageResource(android.R.color.transparent);
                                        tt3.setText("");
                                        tt3.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                        webview.loadUrl("javascript:(function() { " +
                                                "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                                "for(x=0;x<g.length;x++){" +
                                                "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\"" + s + "\"){" +
                                                "g[x].getElementsByClassName('item tobet')[0].click();" +
                                                "}" +
                                                "}" +
                                                "})()");
                                        TextView valA = (TextView) findViewById(R.id.return_team1);
                                        TextView valB = (TextView) findViewById(R.id.return_team2);
                                        webview.loadUrl("javascript:(function() { " +
                                                "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                                "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                                "})()");
                                        SystemClock.sleep(100);
                                        valA.setText(gl.getValA());
                                        valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            //returns.removeViewAt(Integer.parseInt(addButton.getText().toString()));
                            returns.removeView(newItem);
                        }else if(t4.getText().toString().equals("")){
                            gl.setItem4(newItem);
                            ImageView i4 = (ImageView)findViewById(R.id.betted_item_4).findViewById(R.id.picture);
                            Ion.with(i4).load(gl.getReturns().get(finalI).getSrc());
                            t4.setText(gl.getReturns().get(finalI).getPrice());
                            String rarity = gl.getReturns().get(finalI).getRarity();
                            t4.setBackgroundColor(itemColor(rarity));
                            Button b4 = (Button)findViewById(R.id.betted_item_4).findViewById(R.id.selectButton);
                            b4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        returns.addView(gl.getItem4());
                                        ImageView ii4 = (ImageView) findViewById(R.id.betted_item_4).findViewById(R.id.picture);
                                        TextView tt4 = (TextView) findViewById(R.id.betted_item_4).findViewById(R.id.price);
                                        ii4.setImageResource(android.R.color.transparent);
                                        tt4.setText("");
                                        tt4.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                        webview.loadUrl("javascript:(function() { " +
                                                "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                                "for(x=0;x<g.length;x++){" +
                                                "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\"" + s + "\"){" +
                                                "g[x].getElementsByClassName('item tobet')[0].click();" +
                                                "}" +
                                                "}" +
                                                "})()");
                                        TextView valA = (TextView) findViewById(R.id.return_team1);
                                        TextView valB = (TextView) findViewById(R.id.return_team2);
                                        webview.loadUrl("javascript:(function() { " +
                                                "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                                "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                                "})()");
                                        SystemClock.sleep(100);
                                        valA.setText(gl.getValA());
                                        valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            //returns.removeViewAt(Integer.parseInt(addButton.getText().toString()));
                            returns.removeView(newItem);
                        }else if(t5.getText().toString().equals("")){
                            gl.setItem5(newItem);
                            ImageView i5 = (ImageView)findViewById(R.id.betted_item_5).findViewById(R.id.picture);
                            Ion.with(i5).load(gl.getReturns().get(finalI).getSrc());
                            t5.setText(gl.getReturns().get(finalI).getPrice());
                            String rarity = gl.getReturns().get(finalI).getRarity();
                            t5.setBackgroundColor(itemColor(rarity));
                            Button b5 = (Button)findViewById(R.id.betted_item_5).findViewById(R.id.selectButton);
                            b5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        returns.addView(gl.getItem5());
                                        ImageView ii5 = (ImageView) findViewById(R.id.betted_item_5).findViewById(R.id.picture);
                                        TextView tt5 = (TextView) findViewById(R.id.betted_item_5).findViewById(R.id.price);
                                        ii5.setImageResource(android.R.color.transparent);
                                        tt5.setText("");
                                        tt5.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                        webview.loadUrl("javascript:(function() { " +
                                                "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                                "for(x=0;x<g.length;x++){" +
                                                "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\"" + s + "\"){" +
                                                "g[x].getElementsByClassName('item tobet')[0].click();" +
                                                "}" +
                                                "}" +
                                                "})()");
                                        TextView valA = (TextView) findViewById(R.id.return_team1);
                                        TextView valB = (TextView) findViewById(R.id.return_team2);
                                        webview.loadUrl("javascript:(function() { " +
                                                "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                                "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                                "})()");
                                        SystemClock.sleep(100);
                                        valA.setText(gl.getValA());
                                        valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            //returns.removeViewAt(Integer.parseInt(addButton.getText().toString()));
                            returns.removeView(newItem);
                        }else if(t6.getText().toString().equals("")){
                            gl.setItem6(newItem);
                            returns.removeView(newItem);
                            ImageView i6 = (ImageView)findViewById(R.id.betted_item_6).findViewById(R.id.picture);
                            Ion.with(i6).load(gl.getReturns().get(finalI).getSrc());
                            t6.setText(gl.getReturns().get(finalI).getPrice());
                            String rarity = gl.getReturns().get(finalI).getRarity();
                            t6.setBackgroundColor(itemColor(rarity));
                            Button b6 = (Button)findViewById(R.id.betted_item_6).findViewById(R.id.selectButton);
                            b6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        returns.addView(gl.getItem6());
                                        gl.setItem6(null);
                                        ImageView ii6 = (ImageView) findViewById(R.id.betted_item_6).findViewById(R.id.picture);
                                        TextView tt6 = (TextView) findViewById(R.id.betted_item_6).findViewById(R.id.price);
                                        ii6.setImageResource(android.R.color.transparent);
                                        tt6.setText("");
                                        tt6.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                        webview.loadUrl("javascript:(function() { " +
                                                "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                                "for(x=0;x<g.length;x++){" +
                                                "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\"" + s + "\"){" +
                                                "g[x].getElementsByClassName('item tobet')[0].click();" +
                                                "}" +
                                                "}" +
                                                "})()");
                                        TextView valA = (TextView) findViewById(R.id.return_team1);
                                        TextView valB = (TextView) findViewById(R.id.return_team2);
                                        webview.loadUrl("javascript:(function() { " +
                                                "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                                "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                                "})()");
                                        SystemClock.sleep(100);
                                        valA.setText(gl.getValA());
                                        valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            //returns.removeViewAt(Integer.parseInt(addButton.getText().toString()));

                        }
                        TextView valA = (TextView)findViewById(R.id.return_team1);
                        TextView valB = (TextView)findViewById(R.id.return_team2);
                        webview.loadUrl("javascript:(function() { " +
                                "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                "})()");
                        SystemClock.sleep(100);
                        valA.setText(gl.getValA());
                        valB.setText(gl.getValB());

                    }
                });
                String rarity = gl.getReturns().get(i).getRarity();
                price.setBackgroundColor(itemColor(rarity));
                returns.addView(newItem);
            }
            TextView totl = (TextView)findViewById(R.id.totalValue);
            totl.setText("Total Value $"+(double)Math.round(totalVal * 100d) / 100d);
            TextView cov = (TextView)findViewById(R.id.betted_covert);
            cov.setText("Covert: $"+(double)Math.round(covert * 100d) / 100d);
            TextView clas = (TextView)findViewById(R.id.betted_classified);
            clas.setText("Classified: $"+(double)Math.round(classified * 100d) / 100d);
            TextView res = (TextView)findViewById(R.id.betted_restricted);
            res.setText("Restricted: $"+(double)Math.round(restricted * 100d) / 100d);
            TextView mil = (TextView)findViewById(R.id.betted_milspec);
            mil.setText("Mil-Spec: $"+(double)Math.round(milspec * 100d) / 100d);
            TextView ind = (TextView)findViewById(R.id.betted_industrial);
            ind.setText("Industrial: $"+(double)Math.round(industrial * 100d) / 100d);
            TextView con = (TextView)findViewById(R.id.betted_consumer);
            con.setText("Consumer: $"+(double)Math.round(consumer * 100d) / 100d);
            TextView oth = (TextView)findViewById(R.id.betted_default);
            oth.setText("Other: $"+(double)Math.round(other * 100d) / 100d);
            TextView smallBet = (TextView)findViewById(R.id.smallBet);
            smallBet.setText("5%: $"+(double)Math.round((totalVal*.05) * 100d) / 100d);
            TextView mediumBet = (TextView)findViewById(R.id.mediumBet);
            mediumBet.setText("10%: $"+(double)Math.round((totalVal*.1) * 100d) / 100d);
            TextView largeBet = (TextView)findViewById(R.id.largeBet);
            largeBet.setText("20%: $"+(double)Math.round((totalVal*.2) * 100d) / 100d);
        }
        void loadBackpack(){
            final Globals gl = (Globals)getApplicationContext();
            final GridLayout backpack = (GridLayout)findViewById(R.id.backpack);
            backpack.addView(LayoutInflater.from(MainActivity.this).inflate(R.layout.loading, null));
            Button b = (Button)findViewById(R.id.reloadBackpack);
            b.setVisibility(View.VISIBLE);
            SystemClock.sleep(4000);
            webview.loadUrl("javascript:(function() { " +
                    "var iw = document.getElementById('backpack').getElementsByClassName('oitm');" +
                    "console.log(iw);" +
                    "window.JSInterface.clearBackpack();" +
                    "for(i=0;i<iw.length;i++){" +
                        "window.JSInterface.addBackpack(iw[i].childNodes[1].childNodes[1].innerText + \"---\" + iw[i].childNodes[1].childNodes[1].innerText + \"---\" + iw[i].childNodes[3].getElementsByClassName('value')[0].innerText + \"---\" + iw[i].childNodes[3].getElementsByClassName('smallimg')[0].src + \"---\" + iw[i].getElementsByClassName('name')[0].getElementsByTagName('I')[0].innerText + \"---\" + iw[i].getElementsByClassName('name')[0].childNodes[3].textContent + \"---\" + iw[i].childNodes[3].childNodes[1].innerText)" +
                    "}" +
                    "})()");
            SystemClock.sleep(500);
            double totalVal = 0;
            double classified = 0;
            double covert = 0;
            double restricted = 0;
            double milspec = 0;
            double consumer = 0;
            double industrial = 0;
            double other = 0;
            backpack.removeAllViews();
            for(int i=0;i<gl.getBackpack().size();i++){
                final View newItem = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
                TextView name = (TextView)newItem.findViewById(R.id.name);
                name.setText(gl.getBackpack().get(i).getName());
                TextView wear = (TextView)newItem.findViewById(R.id.wear);
                wear.setText(gl.getBackpack().get(i).getWear());
                TextView price = (TextView)newItem.findViewById(R.id.price);
                price.setText(gl.getBackpack().get(i).getPrice());
                ImageView image = (ImageView)newItem.findViewById(R.id.picture);
                Ion.with(image).load(gl.getBackpack().get(i).getSrc());
                ImageView st = (ImageView)newItem.findViewById(R.id.imageView10);
                if(gl.getBackpack().get(i).getST().equals("ST")){
                    st.setBackgroundColor(getResources().getColor(R.color.StatTrak));
                }
                totalVal+=Double.parseDouble(gl.getBackpack().get(i).getPrice().substring(2));

                switch(gl.getBackpack().get(i).getRarity()){
                    case " Industrial":
                        industrial += Double.parseDouble(gl.getBackpack().get(i).getPrice().substring(2));
                        break;
                    case " Restricted":
                        restricted += Double.parseDouble(gl.getBackpack().get(i).getPrice().substring(2));
                        break;
                    case " Classified":
                        classified += Double.parseDouble(gl.getBackpack().get(i).getPrice().substring(2));
                        break;
                    case " Mil-Spec":
                        milspec += Double.parseDouble(gl.getBackpack().get(i).getPrice().substring(2));
                        break;
                    case " Consumer":
                        consumer += Double.parseDouble(gl.getBackpack().get(i).getPrice().substring(2));
                        break;
                    case " Covert":
                        covert += Double.parseDouble(gl.getBackpack().get(i).getPrice().substring(2));
                        break;
                    default:
                        other += Double.parseDouble(gl.getBackpack().get(i).getPrice().substring(2));
                        break;
                }
                final int finalL = i;

                final Button addButton = (Button)newItem.findViewById(R.id.selectButton);
                addButton.setText(""+i);
                addButton.setTextColor(Color.TRANSPARENT);
                final int finalI = i;
                addButton.setOnLongClickListener(new View.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View v) {
                        t.setText(gl.getBackpack().get(finalL).getName());
                        t.show();
                        return true;
                    }
                });
                addButton.setOnClickListener(new View.OnClickListener() {

                    final String s = gl.getBackpack().get(finalI).getName();
                    @Override
                    public void onClick(View v) {
                        webview.loadUrl("javascript:(function() { " +
                                "var f = document.getElementById('backpack').getElementsByClassName('oitm');" +
                                "for(x=0;x<f.length;x++){" +
                                "if(f[x].getElementsByClassName('name')[0].childNodes[1].innerText==\""+s+"\"){" +
                                "f[x].getElementsByClassName('item')[0].click();" +
                                "}" +
                                "}" +
                                "})()");
                        TextView valA = (TextView)findViewById(R.id.return_team1);
                        TextView valB = (TextView)findViewById(R.id.return_team2);
                        webview.loadUrl("javascript:(function() { " +
                                "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                "})()");
                        SystemClock.sleep(100);
                        valA.setText(gl.getValA());
                        valB.setText(gl.getValB());
                        TextView t1 = (TextView)findViewById(R.id.betted_item_1).findViewById(R.id.price);
                        TextView t2 = (TextView)findViewById(R.id.betted_item_2).findViewById(R.id.price);
                        TextView t3 = (TextView)findViewById(R.id.betted_item_3).findViewById(R.id.price);
                        TextView t4 = (TextView)findViewById(R.id.betted_item_4).findViewById(R.id.price);
                        TextView t5 = (TextView)findViewById(R.id.betted_item_5).findViewById(R.id.price);
                        TextView t6 = (TextView)findViewById(R.id.betted_item_6).findViewById(R.id.price);
                        if(t1.getText().toString().equals("")){
                            gl.setItem1(newItem);
                            ImageView i1 = (ImageView)findViewById(R.id.betted_item_1).findViewById(R.id.picture);
                            Ion.with(i1).load(gl.getBackpack().get(finalI).getSrc());
                            t1.setText(gl.getBackpack().get(finalI).getPrice());
                            String rarity = gl.getBackpack().get(finalI).getRarity();
                            t1.setBackgroundColor(itemColor(rarity));
                            Button b1 = (Button)findViewById(R.id.betted_item_1).findViewById(R.id.selectButton);
                            b1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try{
                                    backpack.addView(gl.getItem1());
                                    ImageView ii1 = (ImageView)findViewById(R.id.betted_item_1).findViewById(R.id.picture);
                                    TextView tt1 = (TextView)findViewById(R.id.betted_item_1).findViewById(R.id.price);
                                    ii1.setImageResource(android.R.color.transparent);
                                    tt1.setText("");
                                    tt1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                    webview.loadUrl("javascript:(function() { " +
                                            "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                            "for(x=0;x<g.length;x++){" +
                                            "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\""+s+"\"){" +
                                            "g[x].getElementsByClassName('item tobet')[0].click();" +
                                            "}" +
                                            "}" +
                                            "})()");
                                    TextView valA = (TextView)findViewById(R.id.return_team1);
                                    TextView valB = (TextView)findViewById(R.id.return_team2);
                                    webview.loadUrl("javascript:(function() { " +
                                            "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                            "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                            "})()");
                                    SystemClock.sleep(100);
                                    valA.setText(gl.getValA());
                                    valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            backpack.removeView(newItem);
                        }else if(t2.getText().toString().equals("")){
                            gl.setItem2(newItem);
                            ImageView i2 = (ImageView)findViewById(R.id.betted_item_2).findViewById(R.id.picture);
                            Ion.with(i2).load(gl.getBackpack().get(finalI).getSrc());
                            t2.setText(gl.getBackpack().get(finalI).getPrice());
                            String rarity = gl.getBackpack().get(finalI).getRarity();
                            t2.setBackgroundColor(itemColor(rarity));
                            Button b2 = (Button)findViewById(R.id.betted_item_2).findViewById(R.id.selectButton);
                            b2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try{
                                    backpack.addView(gl.getItem2());
                                    ImageView ii2 = (ImageView)findViewById(R.id.betted_item_2).findViewById(R.id.picture);
                                    TextView tt2 = (TextView)findViewById(R.id.betted_item_2).findViewById(R.id.price);
                                    ii2.setImageResource(android.R.color.transparent);
                                    tt2.setText("");
                                    tt2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                    webview.loadUrl("javascript:(function() { " +
                                            "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                            "for(x=0;x<g.length;x++){" +
                                            "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\""+s+"\"){" +
                                            "g[x].getElementsByClassName('item tobet')[0].click();" +
                                            "}" +
                                            "}" +
                                            "})()");
                                    TextView valA = (TextView)findViewById(R.id.return_team1);
                                    TextView valB = (TextView)findViewById(R.id.return_team2);
                                    webview.loadUrl("javascript:(function() { " +
                                            "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                            "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                            "})()");
                                    SystemClock.sleep(100);
                                    valA.setText(gl.getValA());
                                    valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            backpack.removeView(newItem);
                            //returns.removeViewAt(Integer.parseInt(addButton.getText().toString()));
                        }else if(t3.getText().toString().equals("")){
                            gl.setItem3(newItem);
                            ImageView i3 = (ImageView)findViewById(R.id.betted_item_3).findViewById(R.id.picture);
                            Ion.with(i3).load(gl.getBackpack().get(finalI).getSrc());
                            t3.setText(gl.getBackpack().get(finalI).getPrice());
                            String rarity = gl.getBackpack().get(finalI).getRarity();
                            t3.setBackgroundColor(itemColor(rarity));
                            Button b3 = (Button)findViewById(R.id.betted_item_3).findViewById(R.id.selectButton);
                            b3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try{
                                    backpack.addView(gl.getItem3());
                                    ImageView ii3 = (ImageView)findViewById(R.id.betted_item_3).findViewById(R.id.picture);
                                    TextView tt3 = (TextView)findViewById(R.id.betted_item_3).findViewById(R.id.price);
                                    ii3.setImageResource(android.R.color.transparent);
                                    tt3.setText("");
                                    tt3.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                    webview.loadUrl("javascript:(function() { " +
                                            "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                            "for(x=0;x<g.length;x++){" +
                                            "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\""+s+"\"){" +
                                            "g[x].getElementsByClassName('item tobet')[0].click();" +
                                            "}" +
                                            "}" +
                                            "})()");
                                    TextView valA = (TextView)findViewById(R.id.return_team1);
                                    TextView valB = (TextView)findViewById(R.id.return_team2);
                                    webview.loadUrl("javascript:(function() { " +
                                            "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                            "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                            "})()");
                                    SystemClock.sleep(100);
                                    valA.setText(gl.getValA());
                                    valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            //returns.removeViewAt(Integer.parseInt(addButton.getText().toString()));
                             backpack.removeView(newItem);
                        }else if(t4.getText().toString().equals("")){
                            gl.setItem4(newItem);
                            ImageView i4 = (ImageView)findViewById(R.id.betted_item_4).findViewById(R.id.picture);
                            Ion.with(i4).load(gl.getBackpack().get(finalI).getSrc());
                            t4.setText(gl.getBackpack().get(finalI).getPrice());
                            String rarity = gl.getBackpack().get(finalI).getRarity();
                            t4.setBackgroundColor(itemColor(rarity));
                            Button b4 = (Button)findViewById(R.id.betted_item_4).findViewById(R.id.selectButton);
                            b4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try{
                                    backpack.addView(gl.getItem4());
                                    ImageView ii4 = (ImageView)findViewById(R.id.betted_item_4).findViewById(R.id.picture);
                                    TextView tt4 = (TextView)findViewById(R.id.betted_item_4).findViewById(R.id.price);
                                    ii4.setImageResource(android.R.color.transparent);
                                    tt4.setText("");
                                    tt4.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                    webview.loadUrl("javascript:(function() { " +
                                            "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                            "for(x=0;x<g.length;x++){" +
                                            "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\""+s+"\"){" +
                                            "g[x].getElementsByClassName('item tobet')[0].click();" +
                                            "}" +
                                            "}" +
                                            "})()");
                                    TextView valA = (TextView)findViewById(R.id.return_team1);
                                    TextView valB = (TextView)findViewById(R.id.return_team2);
                                    webview.loadUrl("javascript:(function() { " +
                                            "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                            "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                            "})()");
                                    SystemClock.sleep(100);
                                    valA.setText(gl.getValA());
                                    valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            //returns.removeViewAt(Integer.parseInt(addButton.getText().toString()));
                            backpack.removeView(newItem);
                        }else if(t5.getText().toString().equals("")){
                            gl.setItem5(newItem);
                            ImageView i5 = (ImageView)findViewById(R.id.betted_item_5).findViewById(R.id.picture);
                            Ion.with(i5).load(gl.getBackpack().get(finalI).getSrc());
                            t5.setText(gl.getBackpack().get(finalI).getPrice());
                            String rarity = gl.getBackpack().get(finalI).getRarity();
                            t5.setBackgroundColor(itemColor(rarity));
                            Button b5 = (Button)findViewById(R.id.betted_item_5).findViewById(R.id.selectButton);
                            b5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try{
                                    backpack.addView(gl.getItem5());
                                    ImageView ii5 = (ImageView)findViewById(R.id.betted_item_5).findViewById(R.id.picture);
                                    TextView tt5 = (TextView)findViewById(R.id.betted_item_5).findViewById(R.id.price);
                                    ii5.setImageResource(android.R.color.transparent);
                                    tt5.setText("");
                                    tt5.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                    webview.loadUrl("javascript:(function() { " +
                                            "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                            "for(x=0;x<g.length;x++){" +
                                            "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\""+s+"\"){" +
                                            "g[x].getElementsByClassName('item tobet')[0].click();" +
                                            "}" +
                                            "}" +
                                            "})()");
                                    TextView valA = (TextView)findViewById(R.id.return_team1);
                                    TextView valB = (TextView)findViewById(R.id.return_team2);
                                    webview.loadUrl("javascript:(function() { " +
                                            "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                            "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                            "})()");
                                    SystemClock.sleep(100);
                                    valA.setText(gl.getValA());
                                    valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            //returns.removeViewAt(Integer.parseInt(addButton.getText().toString()));
                            backpack.removeView(newItem);
                        }else if(t6.getText().toString().equals("")){
                            gl.setItem6(newItem);
                            ImageView i6 = (ImageView)findViewById(R.id.betted_item_6).findViewById(R.id.picture);
                            Ion.with(i6).load(gl.getBackpack().get(finalI).getSrc());
                            t6.setText(gl.getBackpack().get(finalI).getPrice());
                            String rarity = gl.getBackpack().get(finalI).getRarity();
                            t6.setBackgroundColor(itemColor(rarity));
                            Button b6 = (Button)findViewById(R.id.betted_item_6).findViewById(R.id.selectButton);
                            b6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try{
                                    backpack.addView(gl.getItem6());
                                    ImageView ii6 = (ImageView)findViewById(R.id.betted_item_6).findViewById(R.id.picture);
                                    TextView tt6 = (TextView)findViewById(R.id.betted_item_6).findViewById(R.id.price);
                                    ii6.setImageResource(android.R.color.transparent);
                                    tt6.setText("");
                                    tt6.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                                    webview.loadUrl("javascript:(function() { " +
                                            "var g = document.getElementById('betpoll').getElementsByClassName('left')[0].getElementsByClassName('oitm');" +
                                            "for(x=0;x<g.length;x++){" +
                                            "if(g[x].getElementsByClassName('name')[0].childNodes[1].innerText==\""+s+"\"){" +
                                            "g[x].getElementsByClassName('item tobet')[0].click();" +
                                            "}" +
                                            "}" +
                                            "})()");
                                    TextView valA = (TextView)findViewById(R.id.return_team1);
                                    TextView valB = (TextView)findViewById(R.id.return_team2);
                                    webview.loadUrl("javascript:(function() { " +
                                            "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                            "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                            "})()");
                                    SystemClock.sleep(100);
                                    valA.setText(gl.getValA());
                                    valB.setText(gl.getValB());
                                    }catch(IllegalStateException e){}
                                }
                            });
                            //returns.removeViewAt(Integer.parseInt(addButton.getText().toString()));
                            backpack.removeView(newItem);
                        }
                        valA = (TextView)findViewById(R.id.return_team1);
                        valB = (TextView)findViewById(R.id.return_team2);
                        webview.loadUrl("javascript:(function() { " +
                                "window.JSInterface.updateA(document.getElementById('teamA').innerText + \" for \" + document.getElementsByClassName('yourVal')[0].innerText);" +
                                "window.JSInterface.updateB(document.getElementById('teamB').innerText + \" for \" + document.getElementsByClassName('yourVal')[1].innerText);" +
                                "})()");
                        SystemClock.sleep(100);
                        valA.setText(gl.getValA());
                        valB.setText(gl.getValB());

                    }
                });
                String rarity = gl.getBackpack().get(i).getRarity();
                price.setBackgroundColor(itemColor(rarity));
                backpack.addView(newItem);
            }
            TextView totl = (TextView)findViewById(R.id.totalValue);
            totl.setText("Total Value $"+(double)Math.round(totalVal * 100d) / 100d);
            TextView cov = (TextView)findViewById(R.id.betted_covert);
            cov.setText("Covert: $"+(double)Math.round(covert * 100d) / 100d);
            TextView clas = (TextView)findViewById(R.id.betted_classified);
            clas.setText("Classified: $"+(double)Math.round(classified * 100d) / 100d);
            TextView res = (TextView)findViewById(R.id.betted_restricted);
            res.setText("Restricted: $"+(double)Math.round(restricted * 100d) / 100d);
            TextView mil = (TextView)findViewById(R.id.betted_milspec);
            mil.setText("Mil-Spec: $"+(double)Math.round(milspec * 100d) / 100d);
            TextView ind = (TextView)findViewById(R.id.betted_industrial);
            ind.setText("Industrial: $"+(double)Math.round(industrial * 100d) / 100d);
            TextView con = (TextView)findViewById(R.id.betted_consumer);
            con.setText("Consumer: $"+(double)Math.round(consumer * 100d) / 100d);
            TextView oth = (TextView)findViewById(R.id.betted_default);
            oth.setText("Other: $"+(double)Math.round(other * 100d) / 100d);
            TextView smallBet = (TextView)findViewById(R.id.smallBet);
            smallBet.setText("5%: $"+(double)Math.round((totalVal*.05) * 100d) / 100d);
            TextView mediumBet = (TextView)findViewById(R.id.mediumBet);
            mediumBet.setText("10%: $"+(double)Math.round((totalVal*.1) * 100d) / 100d);
            TextView largeBet = (TextView)findViewById(R.id.largeBet);
            largeBet.setText("20%: $"+(double)Math.round((totalVal*.2) * 100d) / 100d);
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
        private void requestNewInterstitial() {
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("174203EA08217D1FE42E2DD650CAE3D3")
                    .build();

            mInterstitialAd.loadAd(adRequest);
        }
        public void press(View view){
            System.out.println("push");
        }
        public void downloadFile(String uRl, String name) {
            File direct = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
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
                    .setDestinationInExternalFilesDir(getApplicationContext(),"/LoungeForCSGO", name+".jpg");

            mgr.enqueue(request);

        }
        public void loadAnimation(){

        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            // Check if the key event was the Back button and if there's history
            if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
                webview.goBack();
                setContentView(R.layout.loading);
                loadAnimation();

                return true;
            }
            // If it wasn't the Back key or there's no web page history, bubble up to the default
            // system behavior (probably exit the activity)
            return super.onKeyDown(keyCode, event);
        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.home){
            setContentView(R.layout.loading);
            loadAnimation();
            webview.loadUrl("javascript:(function() { " +
                    "document.body.childNodes[1].childNodes[1].click(); " +
                    "})()");
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.login) {
            webview.loadUrl("javascript:(function() { " +
                    "document.getElementById('login').click(); " +
                    "})()");
            t.setText("Login");
            t.show();
            return true;
        }
        if (id==R.id.bets){
            setContentView(R.layout.loading);
            loadAnimation();
            webview.loadUrl("javascript:(function() { " +
                    "document.getElementById('menu').childNodes[7].click(); " +
                    "})()");
            return true;
        }
        if (id==R.id.logout){
            setContentView(R.layout.loading);
            loadAnimation();
            webview.loadUrl("javascript:(function() { " +
                    "document.getElementById('logout').click(); " +
                    "})()");
        }
        if (id==R.id.test){
            setContentView(webview);
        }
        if (id==R.id.about){
            setContentView(R.layout.about);
            LinearLayout main = (LinearLayout)findViewById(R.id.main);
            Element versionElement = new Element();
            versionElement.setTitle("Version 1.4");
            Element donateElement = new Element();
            donateElement.setTitle("I'm poor, please donate here");
            donateElement.setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=QRP5HUMTSXE3U&lc=US&item_name=CSGOLounge%20for%20Android&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted")));
            View aboutPage = new AboutPage(this)
                    .setDescription("An Android application for CSGOLounge")
                    .addItem(versionElement)
                    .setImage(R.mipmap.ic_launcher)
                    .addEmail("andersundheim@gmail.com")
                    .addItem(donateElement)
                    .create();
            main.addView(aboutPage);
    }
        return super.onOptionsItemSelected(item);
    }

        public void pressedMatch(View view) {
            System.out.println("Pushed");
        }
        /*@Override
        public void onDestroy() {
            super.onDestroy();
            if (mService != null) {
                unbindService(mServiceConn);
            }
        }*/
    }
