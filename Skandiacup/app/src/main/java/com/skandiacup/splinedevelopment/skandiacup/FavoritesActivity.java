package com.skandiacup.splinedevelopment.skandiacup;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class FavoritesActivity extends AppCompatActivity {
    SharedPreferences preferences;
    ArrayList<String> favoriteTeamsID;
    ArrayList<TournamentTeam> teams = new ArrayList<>();
    ListView lv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar a = getSupportActionBar();
        if (a != null) {
            a.setDisplayHomeAsUpEnabled(true);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        favoriteTeamsID = getFavoritedTeamsId();
            DataManager.getInstance().getTournamentTeams(null, null, null, null, new SoapCallback<ArrayList<TournamentTeam>>() {
                @Override
                public void successCallback(ArrayList<TournamentTeam> data) {
                    for (String s : favoriteTeamsID) {
                        for (TournamentTeam t : data) {
                            if (t.getId().equals(s)) {
                                teams.add(t);
                            }
                        }
                    }
                    lv = (ListView) findViewById(R.id.favoritesListView);
                    lv.setAdapter(new FavoritesAdapter(getApplicationContext(), teams));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), TeamActivity.class);
                            intent.putExtra("TeamName", teams.get(position));
                            startActivity(intent);
                        }
                    });
                }

                @Override
                public void errorCallback() {

                }
            });
        }

    public ArrayList<String> getFavoritedTeamsId(){
        ArrayList<String> teams = new ArrayList<>();
        Map<String, ?> favoritedteams = preferences.getAll();
        Set s = favoritedteams.keySet();
        if(s != null) {
            for (Iterator<String> it = s.iterator(); it.hasNext(); ) {
                String id = it.next();
                System.out.println("lag ID ===== " + id);
                teams.add(id);
            }
        }
        return teams;
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
