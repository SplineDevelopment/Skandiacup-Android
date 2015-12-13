package com.skandiacup.splinedevelopment.skandiacup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by borgarlie on 13/12/15.
 */
public class FieldActivityFragmentDiaries extends Fragment {
    public FieldActivityFragmentDiaries() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("Inside fieldactivity fragment (diaries)");

        return inflater.inflate(R.layout.fragment_field_diary, container, false);
    }
}
