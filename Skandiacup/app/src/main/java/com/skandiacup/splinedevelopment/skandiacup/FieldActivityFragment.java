package com.skandiacup.splinedevelopment.skandiacup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jorgen on 27/10/15.
 */
public class FieldActivityFragment extends Fragment {
    public FieldActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("Inside fieldactivity fragment (map)");

        return inflater.inflate(R.layout.fragment_field, container, false);
    }
}
