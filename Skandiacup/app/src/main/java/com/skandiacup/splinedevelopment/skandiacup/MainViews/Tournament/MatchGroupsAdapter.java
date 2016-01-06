package com.skandiacup.splinedevelopment.skandiacup.MainViews.Tournament;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;

import java.util.ArrayList;

public class MatchGroupsAdapter extends BaseAdapter {
    Context context;
    ArrayList<MatchGroup> mgs;
    private static LayoutInflater inflater = null;


    public MatchGroupsAdapter(Context context, ArrayList<MatchGroup> mgs) {
        this.context = context;
        this.mgs = mgs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mgs.size();
    }

    @Override
    public Object getItem(int position) {
        return mgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = inflater.inflate(R.layout.matchgroup_list_item, null);
        TextView text = (TextView) vi.findViewById(R.id.matchgroupListItem);
        text.setText(context.getResources().getString(R.string.adapter_teams_group) + " " + mgs.get(position).getName());
        return vi;
    }
}
