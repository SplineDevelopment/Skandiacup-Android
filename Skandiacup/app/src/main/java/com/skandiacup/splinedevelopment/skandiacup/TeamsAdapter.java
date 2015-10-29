package com.skandiacup.splinedevelopment.skandiacup;

/**
 * Created by eiriksandberg on 29.10.2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;

import java.util.ArrayList;

class TeamsAdapter extends BaseAdapter {

    Context context;
    ArrayList<TournamentTeam> teams;
    private static LayoutInflater inflater = null;


    public TeamsAdapter(Context context, ArrayList<TournamentTeam> teams) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.teams = teams;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return teams.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return teams.get(position);
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
        vi = inflater.inflate(R.layout.teams_table_view_item, null);
        // }
        TextView text = (TextView) vi.findViewById(R.id.teamsListItem);
        text.setText(teams.get(position).getName());
        return vi;
    }






}
