/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.MainViews.Field;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.MainViews.Tournament.MatchViewActivity;
import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;

import java.util.ArrayList;

import static com.skandiacup.splinedevelopment.skandiacup.utils.ErrorMessageGenerator.getErrorMessage;

public class FieldDiariesTeamSelectedActivity extends AppCompatActivity {
    ArrayList<TournamentMatch> matches = null;
    ListView lv = null;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_diaries_team_selected);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);

        ActionBar a = getSupportActionBar();
        if (a != null) {
            a.setDisplayHomeAsUpEnabled(true);
        }

        final String fieldId = (String) getIntent().getSerializableExtra("fieldId");

        lv = (ListView) findViewById(R.id.FDMatchesList);

        DataManager.getInstance().getTournamentMatches(null, null, null, null, null, null, null, new SoapCallback<ArrayList<TournamentMatch>>() {
            @Override
            public void successCallback(ArrayList<TournamentMatch> data) {
                ArrayList<TournamentMatch> matches = new ArrayList<TournamentMatch>();
                for (TournamentMatch m : data) {
                    if (m.getHomegoal().equals("") && m.getFieldid().equals(fieldId)) {
                        matches.add(m);
                    }
                }
                lv.setAdapter(new FieldDiariesSelectedTeamAdapter(getApplicationContext(), matches));
                spinner.setVisibility(View.GONE);
                if (matches.size() == 0){
                    TextView tv = (TextView) findViewById(R.id.noTableMessage);
                    tv.setText(getApplicationContext().getResources().getString(R.string.activity_field_no_matches_available));
                }
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TournamentMatch match = (TournamentMatch) lv.getAdapter().getItem(position);
                        Intent intent = new Intent(getApplicationContext(), MatchViewActivity.class);
                        intent.putExtra("match", match);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void errorCallback() {
                TextView tv = (TextView) findViewById(R.id.onErrorMessage);
                spinner.setVisibility(View.GONE);
                if(tv.getText().length() == 0) {
                    String errorMessage = getErrorMessage(getApplicationContext());
                    tv.setText(errorMessage);
                }
            }
        });

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
