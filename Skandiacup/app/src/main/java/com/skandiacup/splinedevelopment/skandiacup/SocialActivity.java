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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Space;

import com.skandiacup.splinedevelopment.skandiacup.domain.RSSObject;
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


        ViewHolderSosialView viewHolderSosialView = new ViewHolderSosialView();
    }

    void setList(){
        DataManager.getInstance().getInstagramPhotos(new SoapCallback<ArrayList<InstagramItem>>() {
            @Override
            public void successCallback(ArrayList<InstagramItem> data) {
                list = data;
                grid = (GridLayout) findViewById(R.id.grid);
                for(int x=0;x<list.size();x++) {
                    final ImageButton image = new ImageButton(SocialActivity.this);
                    image.setPadding(10, 10, 10, 10);
                    grid.addView(image);
                    //"http://skandiacup.no/wp-content/uploads/2012/03/diplomis.jpg"
                    System.out.println(list.get(x).getThumbnailUrl());
                    //"https://finncdn.no/mmo/2015/6/cms/18/g/fin/n-d/evi/ces/_op/t.p/ng_960825654.jpg"

                    DataManager.getInstance().getInstagramItem(list.get(x).getThumbnailUrl(), new SoapCallback<byte[]>() {
                        @Override
                        public void successCallback(byte[] data) {

                            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                            //ImageView image = (ImageView) findViewById(R.id.imageView1);
                            image.setImageBitmap(bmp);

                        }

                        @Override
                        public void errorCallback() {
                            System.out.println("ERROR CALLBACK i INSTAGRAM");

                        }
                    });

                }
            }

            @Override
            public void errorCallback() {
                //TODO fix this
            }
        });
    }

    public static Drawable loadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }



}

class ViewHolderSosialView {
    /*TextView text;
    TextView timestamp;
    ImageView icon;
    ProgressBar progress;
    int position;*/
}

