package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skandiacup.splinedevelopment.skandiacup.domain.RSSObject;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

/**
 * Created by hoxmark on 27/10/15.
 */
public class NewsActivityFragment extends Fragment {
    ListView lv = null;

    ArrayList<RSSObject> rssObject;

    public NewsActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        lv = (ListView)rootView.findViewById(R.id.newsListView);

        DataManager.getInstance().getRSSFeed(new SoapCallback<ArrayList<RSSObject>>() {
            @Override
            public void successCallback(ArrayList<RSSObject> data) {
                System.out.println("Datamanager RSS OK: " + data.size());
                rssObject = data;
                if (getContext() != null) {
                    System.out.println("View ikke tilgjengelig!");
                    NewsAdapter newsAdapter = new NewsAdapter(getContext().getApplicationContext(), data);
                    lv.setAdapter(newsAdapter);
                }
            }

            @Override
            public void errorCallback() {
                //TODO Legg inn feilmelding
                System.out.println("Datamanager RSS IKKE-OK");
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