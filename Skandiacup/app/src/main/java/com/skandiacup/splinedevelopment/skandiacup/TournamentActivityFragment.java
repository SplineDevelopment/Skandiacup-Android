package com.skandiacup.splinedevelopment.skandiacup;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentClub;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TournamentActivityFragment extends Fragment {
    ListView lv = null;

    public TournamentActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tournament, container, false);
        lv = (ListView)rootView.findViewById(R.id.teamsList);
        DataManager.getInstance().getTournamentTeams(null, null, null, null, new SoapCallback<ArrayList<TournamentTeam>>() {
            @Override
            public void successCallback(ArrayList<TournamentTeam> data) {
                ArrayList<TournamentTeam> teamNames = new ArrayList<TournamentTeam>();
                for(TournamentTeam t : data){
                    teamNames.add(t);
                }
                lv.setAdapter(new TeamsAdapter(getContext(), teamNames));
            }

            @Override
            public void errorCallback() {

            }
        });
        return rootView;
    }
}
