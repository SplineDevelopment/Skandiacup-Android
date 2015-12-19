package com.skandiacup.splinedevelopment.skandiacup.MainViews.Tournament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;


import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TournamentGroupsActivityFragment extends Fragment {
    ListView lv = null;
    private ProgressBar spinner;

    public TournamentGroupsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tournament_groups, container, false);
        lv = (ListView)rootView.findViewById(R.id.groupsList);
        spinner = (ProgressBar)rootView.findViewById(R.id.progressBar1);
        DataManager.getInstance().getMatchClasses(new SoapCallback<ArrayList<MatchClass>>() {
            @Override
            public void successCallback(ArrayList<MatchClass> data) {
                final ArrayList<MatchClass> mcs = new ArrayList<MatchClass>();
                for(MatchClass mc : data){
                    mcs.add(mc);
                }
                spinner.setVisibility(View.GONE);
                lv.setAdapter(new MatchClassesAdapter(getContext(), mcs));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), MatchClassActivity.class);
                        intent.putExtra("MatchClass", mcs.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void errorCallback() {
//                Omg error wat do
            }
        });
        return rootView;
    }
}
