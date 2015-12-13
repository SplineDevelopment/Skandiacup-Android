package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.skandiacup.splinedevelopment.skandiacup.mappers.InstagramItem;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;
import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity {
    ArrayList<InstagramItem> list;
    GridLayout grid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_social);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        setList(width, height);
    }

    void setList(final int width, int height){
        DataManager.getInstance().getInstagramPhotos(new SoapCallback<ArrayList<InstagramItem>>() {
            @Override
            public void successCallback(ArrayList<InstagramItem> data) {
                list = data;
                grid = (GridLayout) findViewById(R.id.grid);
                for(int x=0;x<list.size();x++) {
                    final ImageButton image = new ImageButton(SocialActivity.this);
                    image.setPadding(10, 10, 10, 10); //perfect padding.
                    final int bestSize = ((width - 10) / 3); //Best size of images.
                    image.setMinimumWidth(bestSize);
                    image.setMinimumHeight(bestSize);
                    image.setMaxWidth(bestSize);
                    image.setMaxHeight(bestSize);
                    grid.addView(image);

                    image.setId(x);
                    image.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Toast.makeText(view.getContext(),
                                    "Button clicked index = " + image.getId() , Toast.LENGTH_SHORT)
                                    .show();
                            Intent intent = new Intent(getApplicationContext(), InstagramPopoverActivity.class);
                            intent.putExtra(("profileImage"), list.get(image.getId()).getUserPhotoURL());
                            intent.putExtra(("username"), list.get(image.getId()).getUser());
                            intent.putExtra(("mainImage"), list.get(image.getId()).getUrl());
                            startActivity(intent);
                        }
                    });





                    DataManager.getInstance().getInstagramItem(list.get(x).getThumbnailUrl(), new SoapCallback<byte[]>() {
                        @Override
                        public void successCallback(byte[] data) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                            image.setImageBitmap(Bitmap.createScaledBitmap(bmp, bestSize, bestSize, false));
                        }

                        @Override
                        public void errorCallback() {
                            //TODO Error ?
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
}


