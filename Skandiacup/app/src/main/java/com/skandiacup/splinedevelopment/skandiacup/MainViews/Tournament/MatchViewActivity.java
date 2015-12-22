package com.skandiacup.splinedevelopment.skandiacup.MainViews.Tournament;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.domain.Field;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;

import java.util.ArrayList;


public class MatchViewActivity extends AppCompatActivity {
    TournamentMatch match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        match = (TournamentMatch) getIntent().getSerializableExtra("match");

        DataManager.getInstance().getFields(null, null, null, new SoapCallback<ArrayList<Field>>() {
            @Override
            public void successCallback(ArrayList<Field> data) {
                if(match != null) {
                    for (Field f : data) {
                        if (f.getFieldID().equals(match.getFieldid())) {
                            ((TextView)findViewById(R.id.matchFieldNameLabel)).setText(f.getFieldName());
                            break;
                        }
                    }
                }
            }

            @Override
            public void errorCallback() {

            }
        });

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

            if(match.getReason() != null && !match.getReason().equals("")){
                String winner = "";
                if(match.getWinner().equals("H")){
                    winner = match.getHometeamtext();
                } else{
                    winner = match.getAwayteamtext();
                }

                if(match.getReason().equals("ST")){
                    ((TextView)this.findViewById(R.id.matchReason)).setText(winner + " " + getResources().getString(R.string.activity_match_penaltyreason));

                } else if(match.getReason().equals("WO")){
                    ((TextView)this.findViewById(R.id.matchReason)).setText(winner + " " + getResources().getString(R.string.activity_match_walkoverreason));
                } else{
                    ((TextView)this.findViewById(R.id.matchReason)).setText("");
                }
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
