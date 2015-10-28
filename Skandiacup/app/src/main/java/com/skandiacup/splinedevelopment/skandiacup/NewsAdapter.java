package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hoxmark on 28/10/15.
 */
class NewsAdapter extends BaseAdapter {

    Context context;
    String[] data;
    private static LayoutInflater inflater = null;


    public NewsAdapter(Context context, String[] data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
       // if (vi == null){
            vi = inflater.inflate(R.layout.news_item, null);
   // }


        TextView header = (TextView) vi.findViewById(R.id.firstLine);
        header.setText(data[position]);

        TextView body = (TextView) vi.findViewById(R.id.secondLine);
        body.setText("body");

        return vi;
    }






}
