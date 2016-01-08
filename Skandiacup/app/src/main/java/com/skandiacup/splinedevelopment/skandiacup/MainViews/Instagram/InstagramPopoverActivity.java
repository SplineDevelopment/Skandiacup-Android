/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.MainViews.Instagram;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

public class InstagramPopoverActivity extends AppCompatActivity {
    String username = "";
    String mainImageURL = "";
    String profileImageURL = "";
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_popover);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar a = getSupportActionBar();
        if (a != null) {
            a.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        try{
            username = intent.getStringExtra("username");
            mainImageURL = intent.getStringExtra("mainImage");
            id = intent.getStringExtra("id");
            profileImageURL = intent.getStringExtra("profileImage");
            setInstagram();

        }catch (Exception e){
            System.out.println(e);
            finish();
        }


        Button takeMeToInstagram = (Button) findViewById(R.id.takeMeToInstagram);
        takeMeToInstagram.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/"+username);
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/"+username)));
                }
            }
        });


    }

            public void setInstagram(){

                //Set username:
                TextView usernameTexField = (TextView) findViewById(R.id.userName);
                usernameTexField.setText(username);

                //Set mainImage image
                DataManager.getInstance().getInstagramItem(mainImageURL, new SoapCallback<byte[]>() {
                    @Override
                    public void successCallback(byte[] data) {
                        ImageView mainImageImageView = (ImageView) findViewById(R.id.mainImage);
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        mainImageImageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 300, 300, false));
                    }

                    @Override
                    public void errorCallback() {
                        //TODO Error ?
                    }
                });

                //Set profile image
                DataManager.getInstance().getInstagramItem(profileImageURL, new SoapCallback<byte[]>() {
                    @Override
                    public void successCallback(byte[] data) {
                        int bestProfileImageSize = (getWindowSizeWidth() - 10) / 3;
                        ImageView profileImageImageView = (ImageView) findViewById(R.id.profileIamge);


                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        profileImageImageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, bestProfileImageSize, bestProfileImageSize, false));

                    }

                    @Override
                    public void errorCallback() {
                        //TODO Error ?
                    }
                });
            }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    int getWindowSizeWidth(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        return width;
    }





}
