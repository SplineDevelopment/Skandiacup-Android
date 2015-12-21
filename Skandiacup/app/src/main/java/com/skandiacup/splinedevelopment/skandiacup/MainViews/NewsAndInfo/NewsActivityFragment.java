package com.skandiacup.splinedevelopment.skandiacup.MainViews.NewsAndInfo;

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

/**
 * Created by hoxmark on 27/10/15.
 */
public class NewsActivityFragment extends Fragment {
    ListView lv = null;
    private ProgressBar spinner;

    ArrayList<RSSObject> rssObject;

    public NewsActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        lv = (ListView)rootView.findViewById(R.id.newsListView);

        spinner = (ProgressBar) rootView.findViewById(R.id.progressBar1);

        DataManager.getInstance().getRSSFeed(new SoapCallback<ArrayList<RSSObject>>() {
            @Override
            public void successCallback(ArrayList<RSSObject> data) {
                System.out.println("Datamanager RSS OK: " + data.size());
                rssObject = data;
                spinner.setVisibility(View.GONE);
                if (getContext() != null) {
                    NewsAdapter newsAdapter = new NewsAdapter(getContext().getApplicationContext(), data);
                    lv.setAdapter(newsAdapter);
                }
            }

            @Override
            public void errorCallback() {
                TextView tv = (TextView) rootView.findViewById(R.id.onErrorMessage);
                spinner.setVisibility(View.GONE);
                String errorMessage = getErrorMessage(getContext());
                tv.setText(errorMessage);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent detailIntent = new Intent(getContext(), NewsItemActivity.class);
                detailIntent.putExtra("NewItem",rssObject.get(position));
                startActivity(detailIntent);
            }
        });

        return rootView;
    }
}