package com.skandiacup.splinedevelopment.skandiacup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Jorgen on 27/10/15.
 */
public class FieldActivityFragment extends Fragment {
    boolean rotated = false;

    public FieldActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("Inside fieldactivity fragment (map)");

        final View view = inflater.inflate(R.layout.fragment_field, container, false);

        Button mapRotateButton = (Button) view.findViewById(R.id.mapRotateButton);
        mapRotateButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaleimageview.ScaleImageView img = (scaleimageview.ScaleImageView) view.findViewById(R.id.mapimagefield);
                if (rotated) {
                    img.setRotation(0);
                } else {
                    img.setRotation(270);
                }
                rotated = !rotated;
                System.out.println("clicked the button");
            }
        });

        return view;
    }
}
