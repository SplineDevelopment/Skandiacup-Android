package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skandiacup.splinedevelopment.skandiacup.mappers.InstagramItem;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

public class InstagramPopoverActivity extends AppCompatActivity {
    String username = "";
    String mainImageURL = "";
    String profileImageURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_popover);

        Intent intent = getIntent();

        try{
            username = intent.getStringExtra("username");
            mainImageURL = intent.getStringExtra("mainImage");
            profileImageURL = intent.getStringExtra("profileImage");
            setInstagram();

        }catch (Exception e){
            System.out.println(e);
            finish();
        }


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
                        int bestProfileImageSize = (getWindowSizeWidth()-10)/3;
                        ImageView profileImageImageView = (ImageView) findViewById(R.id.profileIamge);
                        /*profileImageImageView.setMinimumHeight(bestProfileImageSize);
                        profileImageImageView.setMinimumWidth(bestProfileImageSize);
                        profileImageImageView.setMaxHeight(bestProfileImageSize);
                        profileImageImageView.setMaxWidth(bestProfileImageSize);*/

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instagram_popover, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
