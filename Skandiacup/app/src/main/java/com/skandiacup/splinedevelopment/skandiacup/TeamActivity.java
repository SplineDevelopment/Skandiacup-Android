package com.skandiacup.splinedevelopment.skandiacup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchTable;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

public class TeamActivity extends AppCompatActivity {
    ListView lv = null;
    TournamentTeam team = null;
    MatchTable table = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        team = (TournamentTeam) getIntent().getSerializableExtra("TeamName");
        System.out.println(team.getName());
        setTitle(team.getName());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        lv = (ListView) findViewById(R.id.matchList);
        DataManager.getInstance().getTournamentMatches(null, team.getMatchGroupId(), null, null, null, null, null, new SoapCallback<ArrayList<TournamentMatch>>() {
            @Override
            public void successCallback(ArrayList<TournamentMatch> data) {
                final ArrayList<TournamentMatch> matches = new ArrayList<TournamentMatch>();
                for (TournamentMatch m : data) {
                    if (m.getHometeamname().equals(team.getName()) || m.getAwayteamname().equals(team.getName())) {
                        matches.add(m);
                    }
                }
                DataManager.getInstance().getMatchTables(team.getMatchGroupId(), null, null, null, null, new SoapCallback<ArrayList<MatchTable>>() {
                    @Override
                    public void successCallback(ArrayList<MatchTable> data) {
                        ArrayList<MatchTable> tables = new ArrayList<MatchTable>();
                        for (MatchTable table : data) {
                            tables.add(table);
                        }
                        System.out.println("inside teamactivity SUCCESSCALLBACK");
                        lv.setAdapter(new MatchAdapter(getApplicationContext(), matches, tables));
                    }
                    @Override
                    public void errorCallback() {
                    }
                });
            }

            @Override
            public void errorCallback() {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
