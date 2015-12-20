package com.skandiacup.splinedevelopment.skandiacup.MainViews.Favorite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.FavoritesAdapter;
import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.MainViews.Tournament.TeamActivity;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.skandiacup.splinedevelopment.skandiacup.utils.ErrorMessageGenerator.getErrorMessage;


public class FavoritesActivity extends AppCompatActivity {
    private ProgressBar spinner;
    SharedPreferences preferences;
    ArrayList<String> favoriteTeamsID;
    ArrayList<TournamentTeam> teams = new ArrayList<>();
    ArrayList<MatchClass> matchClasses;
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
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        DataManager.getInstance().getMatchClasses(new SoapCallback<ArrayList<MatchClass>>() {
            @Override
            public void successCallback(ArrayList<MatchClass> data) {
                matchClasses = data;
            }

            @Override
            public void errorCallback() {
                System.out.println("Error getting matchClasses in favorites view");
                TextView tv = (TextView) findViewById(R.id.onErrorMessage);
                spinner.setVisibility(View.GONE);
                if(tv.getText().length() == 0) {
                    String errorMessage = getErrorMessage(getApplicationContext());
                    tv.setText(errorMessage);
                }
            }
        });
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
                    lv.setAdapter(new FavoritesAdapter(getApplicationContext(), teams, matchClasses));
                    spinner.setVisibility(View.GONE);
                    if (teams.size() == 0){
                        TextView tv = (TextView) findViewById(R.id.noFavoritesAdded);
                        tv.setText(getApplicationContext().getResources().getString(R.string.no_favorites_added));
                    }
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
                    System.out.println("Error getting teams in favorite view");
                    TextView tv = (TextView) findViewById(R.id.onErrorMessage);
                    spinner.setVisibility(View.GONE);
                    if(tv.getText().length() == 0) {
                        String errorMessage = getErrorMessage(getApplicationContext());
                        tv.setText(errorMessage);
                    }
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
