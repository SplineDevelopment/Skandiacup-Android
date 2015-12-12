package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;

import java.util.ArrayList;

class MatchGroupsAdapter extends BaseAdapter {

    Context context;
    ArrayList<MatchGroup> mgs;
    private static LayoutInflater inflater = null;


    public MatchGroupsAdapter(Context context, ArrayList<MatchGroup> mgs) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.mgs = mgs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mgs.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = inflater.inflate(R.layout.matchgroup_list_item, null);
        TextView text = (TextView) vi.findViewById(R.id.matchgroupListItem);
        text.setText(mgs.get(position).getName());
        return vi;
    }
}
