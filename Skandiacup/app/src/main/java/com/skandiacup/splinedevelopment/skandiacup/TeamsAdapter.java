package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;

import java.util.ArrayList;

class TeamsAdapter extends ArrayAdapter<TournamentTeam> {
    private static LayoutInflater inflater = null;

    public TeamsAdapter(Context context, ArrayList<TournamentTeam> teams) {
        super(context, 0, teams);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.teams_table_view_item, null);
        }
        TextView text = (TextView) convertView.findViewById(R.id.teamsListItem);
        text.setText(getItem(position).getName());
        return convertView;
    }
}