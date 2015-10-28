package com.skandiacup.splinedevelopment.skandiacup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skandiacup.splinedevelopment.skandiacup.domain.RSSObject;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

/**
 * Created by Jorgen on 27/10/15.
 */
public class FieldActivityFragment extends Fragment {
    public FieldActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DataManager.getInstance().getRSSFeed(new SoapCallback<ArrayList<RSSObject>>() {
            @Override
            public void successCallback(ArrayList<RSSObject> data) {
                System.out.println(data);
            }

            @Override
            public void errorCallback() {
                System.out.println("ERROR in getting RSS feed");
            }
        });

        return inflater.inflate(R.layout.fragment_field, container, false);
    }
}
