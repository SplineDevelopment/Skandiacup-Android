package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;

import java.util.ArrayList;

/**
 * Created by eiriksandberg on 09.12.2015.
 */
public class FavoritesAdapter extends BaseAdapter{
        ArrayList<TournamentTeam> favoriteTeams;
        Context context;
        ArrayList<TournamentTeam> teams;
        ArrayList<MatchClass> matchClasses;
        private static LayoutInflater inflater = null;


        public FavoritesAdapter(Context context, ArrayList<TournamentTeam> teams, ArrayList<MatchClass> matchClasses) {
            // TODO Auto-generated constructor stub
            this.context = context;
            this.teams = teams;
            this.matchClasses = matchClasses;
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
            TournamentTeam t = (TournamentTeam) getItem(position);
            String nameText = (t.getName());
            for(MatchClass mc : matchClasses){
                if(mc.getId().equals(t.getMatchClassId())) {
                    for (MatchGroup mg : mc.getMatchGroups()) {
                        if (mg.getId().equals(t.getMatchGroupId())) {
                            nameText += " - " + context.getResources().getString(R.string.adapter_teams_class)
                                    + " " + mc.getCode() +
                                    " - " + context.getResources().getString(R.string.adapter_teams_group)
                                    + " " + mg.getName();
                        }
                    }
                }
            }
            text.setText(nameText);
            return vi;
        }
}
