package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

public class EndPlayClassesActivity extends AppCompatActivity {
    private ArrayList<MatchClass> matchClasses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_play_classes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar a = getSupportActionBar();
        if (a != null) {
            a.setDisplayHomeAsUpEnabled(true);
        }

        matchClasses = new ArrayList<>();
        DataManager.getInstance().getMatchClasses(new SoapCallback<ArrayList<MatchClass>>() {
            @Override
            public void successCallback(ArrayList<MatchClass> data) {
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
                if (mc.getId() == mg.getMatchClassId() && mg.getIsPlayOff().equals("true")) {
                    matchClasses.add(mc);
                    break;
                }
            }
        }
    }
}
