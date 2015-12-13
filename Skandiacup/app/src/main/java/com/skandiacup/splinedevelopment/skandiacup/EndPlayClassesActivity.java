package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    private void addAndFilterEndplayMatchClasses(ArrayList<MatchClass> data){
        for(MatchClass mc : data){
            for(MatchGroup mg : mc.getMatchGroups()){
                System.out.println("************************************ WE ARE CHECKING MATHGROUP************************************");
                System.out.println("current id is " + mc.getId() + " and group class id is " + mg.getMatchClassId() + " and group id is " + mg.getId() + " BUT IS IT FUCKING ENDPLAY " + mg.getIsPlayOff());
                if(mc.getId() == mg.getMatchClassId() && mg.getIsPlayOff().equals("true")){
                    System.out.println("************************************ WE ARE ADDING A MATCHCLASS ************************************");
                    matchClasses.add(mc);
                    break;
                }
            }
        }
    }
}
