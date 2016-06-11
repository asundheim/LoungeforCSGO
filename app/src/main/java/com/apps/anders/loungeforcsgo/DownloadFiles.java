package com.apps.anders.loungeforcsgo;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Anders on 6/4/2016.
 */
public class DownloadFiles extends AsyncTask<String,Integer,Drawable> {
    @Override
    protected Drawable doInBackground(String... strings) {
        try {
            InputStream is = (InputStream) new URL(strings[0]).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
