package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchTable;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class TeamActivity extends AppCompatActivity {
    SharedPreferences preferences;
    ArrayList<TournamentTeam> favoriteTeams;
    ListView lv = null;
    TournamentTeam team = null;
    MatchGroup matchgroup = null;
    MatchTable table = null;
    String matchGroupId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar a = getSupportActionBar();
        if (a != null) {
            a.setDisplayHomeAsUpEnabled(true);
        }
        //Apparently no need to try/catch this - Android checks if the data exist, and if it doesnt, it returns null.
        //Therefore check for null when using team /matchgroup in other places.
        team = (TournamentTeam) getIntent().getSerializableExtra("TeamName");
        matchgroup = (MatchGroup) getIntent().getSerializableExtra("matchGroup");

        if(team != null){
            matchGroupId = team.getMatchGroupId();
        } else if (matchgroup != null){
            this.findViewById(R.id.favoritebutton).setVisibility(View.INVISIBLE);
            matchGroupId = matchgroup.getId();
        }

        setTitle(team != null ? team.getName() : matchgroup != null ? "Gruppe " + matchgroup.getName() : "");
        final ImageButton button = (ImageButton) findViewById(R.id.favoritebutton);
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v){
                                          setFavoriteTeam(button,team);
                                          favoriteTeams = getFavoritedTeams(button);
                                          if (favoriteTeams != null){
                                              for (TournamentTeam t : favoriteTeams){
                                                  System.out.println(t.getName());
                                              }
                                          } else{
                                              // TODO:: remove sout and handle this
                                              System.out.println("Tomt i favoriteslista");
                                          }
                                      }
                                  });
        favoriteTeams = getFavoritedTeams(button);
        lv = (ListView) findViewById(R.id.matchList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TournamentMatch match = (TournamentMatch) lv.getAdapter().getItem(position);
                Intent intent = new Intent(getApplicationContext(), MatchViewActivity.class);
                intent.putExtra("match", match);
                startActivity(intent);
            }
        });
        DataManager.getInstance().getTournamentMatches(null, matchGroupId, null, null, null, null, null, new SoapCallback<ArrayList<TournamentMatch>>() {
            @Override
            public void successCallback(ArrayList<TournamentMatch> data) {
                final ArrayList<TournamentMatch> matches = new ArrayList<TournamentMatch>();
                for (TournamentMatch m : data) {
                    if (matchgroup == null && (m.getHometeamname().equals(team.getName()) || m.getAwayteamname().equals(team.getName()))) {
                        matches.add(m);
                    } else {
                        matches.add(m);
                    }
                }

                DataManager.getInstance().getMatchTables(matchGroupId, null, null, null, null, new SoapCallback<ArrayList<MatchTable>>() {
                    @Override
                    public void successCallback(ArrayList<MatchTable> data) {
                        ArrayList<MatchTable> tables = new ArrayList<MatchTable>();
                        for (MatchTable table : data) {
                            tables.add(table);
                        }
                        System.out.println("inside teamactivity SUCCESSCALLBACK");
                        lv.setAdapter(new MatchAdapter(getApplicationContext(), matches, tables));
                    }

                    @Override
                    public void errorCallback() {
                    }
                });
            }

            @Override
            public void errorCallback() {

            }
        });

    }

    public ArrayList<TournamentTeam> getFavoritedTeams(ImageButton button){
        ArrayList<TournamentTeam> teams = new ArrayList<>();
        Map<String, ?> favoritedteams = preferences.getAll();
        if (favoritedteams != null && team != null){
            if(favoritedteams.containsValue(team.getId())){
                button.setImageDrawable(getResources().getDrawable(R.drawable.favoritefilled, getTheme()));
                System.out.println(team + " Finnes i favoritter");
            }
        } else{
            System.out.println(team + " Finnes ikke i favoritter");
            //TODO Change .getDrawable? This will not work for Android API 15.
            button.setImageDrawable(getResources().getDrawable(R.drawable.favoriteunfilled, getTheme()));
            return null;
        }
        return teams;
    }

    public boolean checkIfAlreadyFavorited(String team){
        Map<String, ?> favoritedteams = preferences.getAll();
        if (team != null){
            if(favoritedteams.containsValue(team)){
                return true;
            }
        }
        return false;
    }

    public void makeFavoriteToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void setFavoriteTeam(ImageButton button, TournamentTeam team){
        if (!checkIfAlreadyFavorited(team.getId())){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(team.getId(), team.getId());
            editor.commit();
            button.setImageDrawable(getResources().getDrawable(R.drawable.favoritefilled, getTheme()));
            System.out.println(team + " ble lagt til i favoritter");
            String text = getResources().getString(R.string.activity_Team_Toast_teamAdded);
            makeFavoriteToast(text);
        } else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(team.getId());
            editor.commit();
            button.setImageDrawable(getResources().getDrawable(R.drawable.favoriteunfilled, getTheme()));
            System.out.println(team + " ble fjernet fra favoritter");
            String text = getResources().getString(R.string.activity_Team_Toast_teamRemoved);
            makeFavoriteToast(text);
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
