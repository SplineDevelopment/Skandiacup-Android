/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.MainViews.NewsAndInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.RSSObject;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

import static com.skandiacup.splinedevelopment.skandiacup.utils.ErrorMessageGenerator.getErrorMessage;

public class NewsActivityFragmentInfo extends Fragment{
    ListView lv = null;
    private ProgressBar spinner;

    ArrayList<RSSObject> rssObject;

    public NewsActivityFragmentInfo() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        lv = (ListView) rootView.findViewById(R.id.newsListView);

        spinner = (ProgressBar)rootView.findViewById(R.id.progressBar1);

        DataManager.getInstance().getRSSFeedInfo(new SoapCallback<ArrayList<RSSObject>>() {
            @Override
            public void successCallback(ArrayList<RSSObject> data) {
                System.out.println("Datamanager RSS OK: " + data.size());
                rssObject = data;
                if (getContext() != null) {
                    spinner.setVisibility(View.GONE);
                    InfoAdapter newsAdapter = new InfoAdapter(getContext().getApplicationContext(), data);
                    lv.setAdapter(newsAdapter);
                }
            }

            @Override
            public void errorCallback() {
                System.out.println("Datamanager RSS IKKE-OK");
                TextView tv = (TextView) rootView.findViewById(R.id.onErrorMessage);
                spinner.setVisibility(View.GONE);
                Context c = getContext();
                if (c != null) {
                    String errorMessage = getErrorMessage(c);
                    tv.setText(errorMessage);
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent detailIntent = new Intent(getContext(), InfoItemActivity.class);
                detailIntent.putExtra("NewItem",rssObject.get(position));
                startActivity(detailIntent);
            }
        });

        return rootView;
    }
}