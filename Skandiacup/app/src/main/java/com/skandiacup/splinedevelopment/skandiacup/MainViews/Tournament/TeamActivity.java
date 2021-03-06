/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.MainViews.Tournament;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchTable;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchTableRow;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static com.skandiacup.splinedevelopment.skandiacup.utils.ErrorMessageGenerator.getErrorMessage;

public class TeamActivity extends AppCompatActivity {
    SharedPreferences preferences;
    ArrayList<TournamentTeam> favoriteTeams;
    private ArrayList<TournamentMatch> matches;
    ListView lv = null;
    TournamentTeam team = null;
    MatchGroup matchgroup = null;
    MatchTable table = null;
    String matchGroupId = null;
    private String matchClassName;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        ActionBar a = getSupportActionBar();
        if (a != null) {
            a.setDisplayHomeAsUpEnabled(true);
        }
        //Apparently no need to try/catch this - Android checks if the data exist, and if it doesnt, it returns null.
        //Therefore check for null when using team /matchgroup in other places.
        team = (TournamentTeam) getIntent().getSerializableExtra("TeamName");
        matchgroup = (MatchGroup) getIntent().getSerializableExtra("matchGroup");
        matchClassName = (String)getIntent().getSerializableExtra("matchClassName");

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
                matches = new ArrayList<>();
                for (TournamentMatch m : data) {
                    if (matchgroup == null && (m.getHometeamname().equals(team.getName()) || m.getAwayteamname().equals(team.getName()))) {
                        matches.add(m);
                    } else if(matchgroup != null){
                        matches.add(m);
                    }
                }

                DataManager.getInstance().getMatchTables(matchGroupId, null, null, null, null, new SoapCallback<ArrayList<MatchTable>>() {
                    @Override
                    public void successCallback(ArrayList<MatchTable> data) {
                        ArrayList<MatchTable> tables = new ArrayList<>();
                        if(data.isEmpty()){
                            tables.add(createEmptyTable());
                        }
                        else{
                            for (MatchTable table : data) {
                                tables.add(table);
                            }
                        }
                        System.out.println("inside teamactivity SUCCESSCALLBACK");
                        spinner.setVisibility(View.GONE);
                        lv.setAdapter(new MatchAdapter(getApplicationContext(), matches, tables, matchClassName));
                    }

                    @Override
                    public void errorCallback() {
                        // errors are handled on outer error
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

    public ArrayList<TournamentTeam> getFavoritedTeams(ImageButton button){
        ArrayList<TournamentTeam> teams = new ArrayList<>();
        Map<String, ?> favoritedteams = preferences.getAll();
        if (favoritedteams != null && team != null){
            if(favoritedteams.containsValue(team.getId())){
                button.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.favoritefilled));
                System.out.println(team + " Finnes i favoritter");
            }
        } else{
            System.out.println(team + " Finnes ikke i favoritter");
            button.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.favoriteunfilled));
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
            button.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.favoritefilled));
            System.out.println(team + " ble lagt til i favoritter");
            String text = getResources().getString(R.string.activity_Team_Toast_teamAdded);
            makeFavoriteToast(text);
        } else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(team.getId());
            editor.commit();
            button.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.favoriteunfilled));
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

    private MatchTable createEmptyTable(){
        MatchTable emptyTable = new MatchTable();
        ArrayList<MatchTableRow> rows = new ArrayList<>();
        MatchTableRow header = new MatchTableRow();

        ArrayList<String> teamNames = new ArrayList<>();
        for(TournamentMatch match : matches){
            if(!teamNames.contains(match.getHometeamname())){
                teamNames.add(match.getHometeamname());
            }
            if(!teamNames.contains(match.getAwayteamname())){
                teamNames.add(match.getAwayteamname());
            }
        }
        Collections.sort(teamNames);

        for(int i = 0; i<teamNames.size(); i++){
            MatchTableRow row = new MatchTableRow();
            row.setPosition("" + (i+1));
            row.setA(teamNames.get(i));
            row.setB("0");
            row.setC("0");
            row.setD("0");
            row.setE("0");
            row.setF("0 0 0");
            row.setG("0");
            row.setH("0");
            row.setI("0");
            row.setJ("0");
            row.setK("0");
            row.setL("0");
            rows.add(row);
        }
        emptyTable.setHeader(header);
        emptyTable.setRows(rows);
        return emptyTable;
    }
}