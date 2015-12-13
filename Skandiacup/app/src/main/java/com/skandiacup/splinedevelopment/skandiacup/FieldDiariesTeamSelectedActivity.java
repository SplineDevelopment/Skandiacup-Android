package com.skandiacup.splinedevelopment.skandiacup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;

import java.util.ArrayList;

public class FieldDiariesTeamSelectedActivity extends AppCompatActivity {
    ArrayList<TournamentMatch> matches = null;
    ListView lv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_diaries_team_selected);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        matches = (ArrayList<TournamentMatch>) getIntent().getSerializableExtra("Match");
        System.out.println("Matches.size(): " + matches.size());
        lv = (ListView) findViewById(R.id.FDMatchesList);
        lv.setAdapter(new FieldDiariesSelectedTeamAdapter(getApplicationContext(), matches));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
