package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;

import java.util.ArrayList;

class TeamsAdapter extends ArrayAdapter<TournamentTeam> {
    private static LayoutInflater inflater = null;
    private ArrayList<MatchClass> matchClasses;

    public TeamsAdapter(Context context, ArrayList<TournamentTeam> teams, ArrayList<MatchClass> matchClasses) {
        super(context, 0, teams);
        this.matchClasses = matchClasses;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.teams_table_view_item, null);
        }
        for(MatchClass mc : matchClasses){
            if(mc.getId().equals(getItem(position).getMatchClassId())) {
                for (MatchGroup mg : mc.getMatchGroups()) {
                    if (mg.getId().equals(getItem(position).getMatchGroupId())) {
                        ((TextView) convertView.findViewById(R.id.matchClassNameLabel))
                                .setText(getContext().getResources().getString(R.string.adapter_teams_class)
                                        + " " + mc.getCode() +
                                        " - " + getContext().getResources().getString(R.string.adapter_teams_group)
                                        + " " + mg.getName());
                    }
                }
            }
        }
        TextView text = (TextView) convertView.findViewById(R.id.teamsListItem);
        text.setText(getItem(position).getName());
        return convertView;
    }
}