package com.skandiacup.splinedevelopment.skandiacup;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.skandiacup.splinedevelopment.skandiacup.domain.Field;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by borgarlie on 13/12/15.
 */
public class FieldActivityFragmentDiaries extends Fragment {
    ListView lv = null;
    public FieldActivityFragmentDiaries() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_field_diary, container, false);
        lv = (ListView)rootView.findViewById(R.id.fieldDiariesList);
        DataManager.getInstance().getFields(null, null, null, new SoapCallback<ArrayList<Field>>() {
            @Override
            public void successCallback(ArrayList<Field> data) {
                final ArrayList<Field> fieldnames = new ArrayList<Field>();
                for(Field f: data){
                    fieldnames.add(f);
                }
                lv.setAdapter(new DiariesAdapter(getContext(), fieldnames));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            final int position, long id) {
                       final Intent intent = new Intent(getContext(), FieldDiariesTeamSelectedActivity.class);
                        DataManager.getInstance().getTournamentMatches(null, null, null, null, null, null, null, new SoapCallback<ArrayList<TournamentMatch>>() {
                            @Override
                            public void successCallback(ArrayList<TournamentMatch> data) {
                                ArrayList<TournamentMatch> matches = new ArrayList<TournamentMatch>();
                                for (TournamentMatch m : data) {

                                    if ((m.getHomegoal() == "" && m.getFieldid().equals(fieldnames.get(position).getFieldID())) || (m.getHomegoal() == null)){ // m.getHomegoal() == null &&
                                        matches.add(m);
                                    }
                                }
                                intent.putExtra("Match", matches);
                                startActivity(intent);
                            }

                            @Override
                            public void errorCallback() {

                            }
                        });
                    }
                });
            }

            @Override
            public void errorCallback() {

            }
        });
        return rootView;
    }
}
