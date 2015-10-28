package com.skandiacup.splinedevelopment.skandiacup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by hoxmark on 27/10/15.
 */
public class NewsActivityFragment extends Fragment {
    ListView lv = null;
    String[] header = {"Nyhetoverskrift 1", "Nyhetoverskrift 2"};

    public NewsActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        lv = (ListView)rootView.findViewById(R.id.newsListView);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext().getApplicationContext(), R.layout.news_item, teams);

        //lv.setAdapter(adapter);

        lv.setAdapter(new NewsAdapter(this.getContext().getApplicationContext(), header));

        return rootView;
    }
}
