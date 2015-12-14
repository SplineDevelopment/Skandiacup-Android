package com.skandiacup.splinedevelopment.skandiacup;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;


public class MatchViewActivity extends AppCompatActivity {
    TournamentMatch match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        match = (TournamentMatch) getIntent().getSerializableExtra("match");

        if (match != null){
            if(match.getHometeamname() != null && !match.getHometeamname().equals("")){
                ((TextView)this.findViewById(R.id.homeTeamNameLabel)).setText(match.getHometeamname());
            }

            if(match.getAwayteamname() != null && !match.getAwayteamname().equals("")){
                ((TextView)this.findViewById(R.id.awayTeamNameLabel)).setText(match.getAwayteamname());
            }

            if(match.getMatchdate() != null && !match.getMatchdate().equals("")){
                ((TextView)this.findViewById(R.id.dateLabel)).setText(match.getMatchdate());
            }

            if(match.getHomegoal() != null && !match.getHomegoal().equals("")){
                ((TextView)this.findViewById(R.id.homeTeamGoalLabel)).setText(match.getHomegoal());
            }

            if(match.getAwaygoal() != null && !match.getAwaygoal().equals("")){
                ((TextView)this.findViewById(R.id.awayTeamGoalLabel)).setText(match.getAwaygoal());
            }
        }
        ActionBar a = getSupportActionBar();
        if (a != null) {
            a.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
