package com.skandiacup.splinedevelopment.skandiacup.MainViews.NewsAndInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.domain.RSSObject;

import java.util.ArrayList;

/**
 * Created by hoxmark on 28/10/15.
 */
class NewsAdapter extends BaseAdapter {

    Context context;
    String[] data;
    ArrayList<RSSObject> rssObjects;
    private static LayoutInflater inflater = null;



    public NewsAdapter(Context context, ArrayList<RSSObject> rssObjects) {
        this.context = context;
        this.rssObjects = rssObjects;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return rssObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null){
            vi = inflater.inflate(R.layout.news_item, null);
        }

        if (rssObjects!=null){
            TextView header = (TextView) vi.findViewById(R.id.firstLine);
            header.setText(rssObjects.get(position).getTitle());

            TextView body = (TextView) vi.findViewById(R.id.secondLine);
            body.setText(rssObjects.get(position).getItemDescription());
        } else {
            System.out.println("rssObjects = null");
        }

        return vi;
    }






}