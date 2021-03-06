/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.MainViews.Endplay;

import android.content.Context;
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

import com.skandiacup.splinedevelopment.skandiacup.MainViews.Tournament.MatchClassesAdapter;
import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

import static com.skandiacup.splinedevelopment.skandiacup.utils.ErrorMessageGenerator.getErrorMessage;

public class EndPlayClassesActivity extends AppCompatActivity {
    private ArrayList<MatchClass> matchClasses;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_play_classes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = (ProgressBar)findViewById(R.id.progressBar1);

        ActionBar a = getSupportActionBar();
        if (a != null) {
            a.setDisplayHomeAsUpEnabled(true);
        }

        matchClasses = new ArrayList<>();
        DataManager.getInstance().getMatchClasses(new SoapCallback<ArrayList<MatchClass>>() {
            @Override
            public void successCallback(ArrayList<MatchClass> data) {
                spinner.setVisibility(View.GONE);
                addAndFilterEndplayMatchClasses(data);
                ListView lv = (ListView)findViewById(R.id.endPlayClasses);
                lv.setAdapter(new MatchClassesAdapter(getApplicationContext(), matchClasses));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), EndplayActivity.class);
                        intent.putExtra("matchClass", matchClasses.get(position));
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void errorCallback() {
                TextView tv = (TextView) findViewById(R.id.onErrorMessage);
                spinner.setVisibility(View.GONE);
                if(tv.getText().length() == 0) {
                    Context c = getApplicationContext();
                    if (c != null) {
                        String errorMessage = getErrorMessage(c);
                        tv.setText(errorMessage);
                    }
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

    private void addAndFilterEndplayMatchClasses(ArrayList<MatchClass> data) {
        for (MatchClass mc : data) {
            for (MatchGroup mg : mc.getMatchGroups()) {
                if (mc.getId().equals(mg.getMatchClassId()) && mg.getIsPlayOff().equals("true")) {
                    matchClasses.add(mc);
                    break;
                }
            }
        }
    }
}
