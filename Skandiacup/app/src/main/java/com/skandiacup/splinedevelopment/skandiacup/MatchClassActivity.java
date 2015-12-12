package com.skandiacup.splinedevelopment.skandiacup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;

import java.util.ArrayList;

public class MatchClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView lv = (ListView) findViewById(R.id.matchGroupsList);
        MatchClass selectedClass = (MatchClass) getIntent().getSerializableExtra("MatchClass");
        setTitle(selectedClass.getName());

        ArrayList<MatchGroup> mgs = new ArrayList<>();
        for(MatchGroup mg : selectedClass.getMatchGroups()){
            mgs.add(mg);
        }

        lv.setAdapter(new MatchGroupsAdapter(getApplicationContext(), mgs));
    }
}
