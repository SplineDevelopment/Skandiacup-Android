package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jorgen on 27/10/15.
 */
public class FavoritesActivityFragment extends Fragment {
    SharedPreferences preferences;
    ArrayList<String> favoriteTeamsID;
    ArrayList<TournamentTeam> teams;
    ArrayList<MatchClass> matchClasses;
    ListView lv = null;

    public FavoritesActivityFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataManager.getInstance().getMatchClasses(new SoapCallback<ArrayList<MatchClass>>() {
            @Override
            public void successCallback(ArrayList<MatchClass> data) {
                matchClasses = data;
            }

            @Override
            public void errorCallback() {

            }
        });
        preferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        favoriteTeamsID = getFavoritedTeamsId();
        for (String s : favoriteTeamsID){
            DataManager.getInstance().getTournamentTeams(s, null, null, null, new SoapCallback<ArrayList<TournamentTeam>>() {
                @Override
                public void successCallback(ArrayList<TournamentTeam> data) {
                    for(TournamentTeam t : data){
                        teams.add(t);
                    }
                }
                @Override
                public void errorCallback() {

                }
            });
        }
        lv = (ListView)rootView.findViewById(R.id.favoritesList);
        lv.setAdapter(new FavoritesAdapter(getContext(), teams,matchClasses));
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    public ArrayList<String> getFavoritedTeamsId(){
        ArrayList<String> teams = new ArrayList<>();
        Map<String, ?> favoritedteams = preferences.getAll();
        Set s = favoritedteams.keySet();
        if(s != null) {
            for (Iterator<String> it = s.iterator(); it.hasNext(); ) {
                String clubId = it.next();
                System.out.println("Klubbid ===== " + clubId);
                teams.add(clubId);
            }
        }
        return teams;
    }
}
