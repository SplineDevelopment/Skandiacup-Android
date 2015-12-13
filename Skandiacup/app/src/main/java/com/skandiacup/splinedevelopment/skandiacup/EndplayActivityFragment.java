package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.Arena;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

/**
 * Created by Jorgen on 27/10/15.
 */
public class EndplayActivityFragment extends Fragment {
    private static final String NAMESPACE = "http://www.w3schools.com/webservices/";
    private static final String MAIN_REQUEST_URL = "http:/2/www.w3schools.com/webservices/tempconvert.asmx";
    private static final String SOAP_ACTION = "http://www.w3schools.com/webservices/FahrenheitToCelsius";
    private String endGameLevel;
    private String matchClassId;
    private ArrayList<TournamentMatch> matches;
    private ListView lv = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        this.endGameLevel = args.getString("endGameLevel");
        this.matchClassId = args.getString("matchClassId");

        View rootView = inflater.inflate(R.layout.fragment_endplay, container, false);
        lv = (ListView)rootView.findViewById(R.id.endPlayMatches);

        DataManager.getInstance().getTournamentMatches(matchClassId, null, null, "1", null, null, null, new SoapCallback<ArrayList<TournamentMatch>>() {
            @Override
            public void successCallback(ArrayList<TournamentMatch> data) {
                addAndFilterEndPlayMatches(data);
                lv.setAdapter(new EndPlayMatchAdapter(getContext(), matches));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), MatchViewActivity.class);
                        intent.putExtra("match", matches.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void errorCallback() {

            }
        });

        return rootView;
    }

    private void addAndFilterEndPlayMatches(ArrayList<TournamentMatch> data) {
        matches = new ArrayList<>();
        for(TournamentMatch match : data){
            if(match.getEndgamelevel().equals(endGameLevel)){
                matches.add(match);
            }
        }
    }
}
