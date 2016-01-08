/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.MainViews.Tournament;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;

import java.util.ArrayList;

public class MatchClassesAdapter extends BaseAdapter {

    Context context;
    ArrayList<MatchClass> matchClasses;
    private static LayoutInflater inflater = null;


    public MatchClassesAdapter(Context context, ArrayList<MatchClass> mcs) {
        this.context = context;
        this.matchClasses = mcs;
        if (context != null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @Override
    public int getCount() {
        return matchClasses.size();
    }

    @Override
    public Object getItem(int position) {
        return matchClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = inflater.inflate(R.layout.groups_table_view_item, null);
        TextView text = (TextView) vi.findViewById(R.id.groupsListItem);
        text.setText(matchClasses.get(position).getName());
        return vi;
    }
}
