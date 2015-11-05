package com.skandiacup.splinedevelopment.skandiacup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;

import com.skandiacup.splinedevelopment.skandiacup.mappers.InstagramItem;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity {
    ArrayList<InstagramItem> list;
    GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setList();

        setContentView(R.layout.activity_social);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        grid = (GridLayout) findViewById(R.id.grid);
        for(int x=0;x<20;x++) {
            ImageButton image = new ImageButton(SocialActivity.this);
            image.setPadding(3, 3, 3, 3);
            image.setBackgroundResource(R.drawable.logosmal);
            DownloadImageTask a = new DownloadImageTask(image);
            //a.doInBackground("http://skandiacup.no/wp-content/uploads/2012/03/diplomis.jpg");
            grid.addView(image);
        }
    }





    void setList(){
        DataManager.getInstance().getInstagramPhotos(new SoapCallback<ArrayList<InstagramItem>>() {
            @Override
            public void successCallback(ArrayList<InstagramItem> data) {
                list = data;

            }

            @Override
            public void errorCallback() {
                //TODO fix this
            }
        });
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}



class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageButton bmImage;
    String url = "http://skandiacup.no/wp-content/uploads/2012/03/diplomis.jpg";

    public DownloadImageTask(ImageButton bmImage) {
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        System.out.println("qweqweqweqweqweqweqwe");
        //String urldisplay = url;
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        System.out.println("fjklsdklafjaklsdøfjkladsjfjklas");
        bmImage.setImageBitmap(result);
    }
}



