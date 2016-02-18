/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.MainViews.Field;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.skandiacup.splinedevelopment.skandiacup.Extention.ScaleImageView;
import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;

public class FieldActivityFragment extends Fragment {
    boolean rotated = false;

    public FieldActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("Inside fieldactivity fragment (map)");

        final View view = inflater.inflate(R.layout.fragment_field, container, false);

        DataManager dm = DataManager.getInstance();

        dm.getFieldImage(new SoapCallback<byte[]>() {
            @Override
            public void successCallback(byte[] data) {
                final ScaleImageView img = (ScaleImageView) view.findViewById(R.id.mapimagefield);
                Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                img.setImageBitmap(bm);
            }

            @Override
            public void errorCallback() {

            }
        } );

        ImageButton mapRotateButton = (ImageButton) view.findViewById(R.id.mapRotateButton);
        mapRotateButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleImageView img = (ScaleImageView) view.findViewById(R.id.mapimagefield);
                if (rotated) {
                    img.setRotation(0);
                    img.setScaleX(1f);
                    img.setScaleY(1f);
                } else {
                    img.setRotation(270);
                    img.setScaleX(1.2f);
                    img.setScaleY(1.2f);
                }
                rotated = !rotated;
            }
        });

        return view;
    }
}
