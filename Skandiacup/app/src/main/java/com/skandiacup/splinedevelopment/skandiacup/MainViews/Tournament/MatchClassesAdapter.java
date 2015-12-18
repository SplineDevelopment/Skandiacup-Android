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
        // TODO Auto-generated constructor stub
        this.context = context;
        this.matchClasses = mcs;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return matchClasses.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return matchClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = inflater.inflate(R.layout.groups_table_view_item, null);
        TextView text = (TextView) vi.findViewById(R.id.groupsListItem);
        text.setText(matchClasses.get(position).getName());
        return vi;
    }
}
