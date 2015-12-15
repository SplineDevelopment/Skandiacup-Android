package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchTable;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchTableRow;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eiriksandberg on 05.11.2015.
 */
public class MatchAdapter extends BaseAdapter{
    Context context;
    private ArrayList<TournamentMatch> matches;
    private ArrayList<TournamentMatch> matchesNotYetPlayed;
    private ArrayList<TournamentMatch> matchesPlayed;
    private MatchTable table;
    private ArrayList<MatchTableRow> matchTableRows;
    private static LayoutInflater inflater = null;
    private int sections = 4;
    private String matchClassName;
    Map<String, Integer> map = new HashMap<String, Integer>();

    public MatchAdapter(Context context, ArrayList<TournamentMatch> matches, ArrayList<MatchTable> tables, String matchClassName) {
        this.context = context;
        this.matches = matches;
        this.matchesNotYetPlayed = matchesNotYetPlayed(matches);
        this.matchesPlayed = matchesPlayed(matches);
        if(!tables.isEmpty()){
            this.table = tables.get(0);
            this.matchTableRows = table.getRows();
        }else{
            this.matchTableRows = new ArrayList<>();
        }
        this.matchClassName = matchClassName;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<TournamentMatch> matchesNotYetPlayed(ArrayList<TournamentMatch> m){
        ArrayList<TournamentMatch> matchesNotYetPlayed = new ArrayList<>();
        for (TournamentMatch match : m){
            if (match.getHomegoal().equals("") || match.getAwaygoal().equals("")){
                matchesNotYetPlayed.add(match);
            }
        }
        return matchesNotYetPlayed;
    }

    public ArrayList<TournamentMatch> matchesPlayed(ArrayList<TournamentMatch> m){
        ArrayList<TournamentMatch> matchesPlayed = new ArrayList<>();
        for (TournamentMatch match : m){
            if (match.getHomegoal() != null || match.getAwaygoal() != null){
                matchesPlayed.add(match);
            }
        }
        return matchesPlayed;
    }

    @Override
    public int getCount() {
        int count = matchTableRows.size() + matches.size() + sections; // + matches.size() plus 1 beacuse og tableinformationheader
        return count;
    }

    @Override
    public Object getItem(int position) {
        positionResolver(position);
        String s = map.keySet().toString();
        s = s.replace("]", "");
        s = s.replace("[", "");
        int index = -1;
        switch (s) {

            case "matchesnotplayeditem": {
                String indexString = map.get("matchesnotplayeditem").toString();
                index = Integer.parseInt(indexString);
                break;
            }
            case "matchesplayeditem": {
                String indexString = map.get("matchesplayeditem").toString();
                index = Integer.parseInt(indexString);
                break;
            }
        }
        return matches.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View setHeader(View convertView, String title, boolean isTableHeader){
        View vi = convertView;
        vi = inflater.inflate(R.layout.list_header, null);
        TextView text = (TextView) vi.findViewById(R.id.list_header_title);
        if(isTableHeader && matchClassName != null) {
            title += " - " + matchClassName;
        }
        text.setText(title);
        return text;
    }

    public View setInfosection(View convertView, String title){
        View vi = convertView;
        vi = inflater.inflate(R.layout.infosection, null);
        TextView text = (TextView) vi.findViewById(R.id.info_header_title);
        text.setText(title);
        return text;
    }

    public View setTableHeader(View convertView){
        View vi = convertView;
        vi = inflater.inflate(R.layout.tableheader, null);
        return vi;
    }

    public View setTableRow(View convertView, MatchTableRow row){
        View vi = convertView;
        vi = inflater.inflate(R.layout.table_row, null);
        TextView position = (TextView) vi.findViewById(R.id.position);
        position.setText(row.getPosition());
        TextView teamname = (TextView) vi.findViewById(R.id.teamname);
        teamname.setText(row.getA());
        TextView matchesPlayed = (TextView) vi.findViewById(R.id.matchesPlayed);
        matchesPlayed.setText(""+gamesPlayed(row));
        TextView wins = (TextView) vi.findViewById(R.id.wins);
        wins.setText(row.getC());
        TextView draws = (TextView) vi.findViewById(R.id.draws);
        draws.setText(row.getD());
        TextView losses = (TextView) vi.findViewById(R.id.losses);
        losses.setText(row.getE());
        TextView plusMinus = (TextView) vi.findViewById(R.id.plusminus);
        plusMinus.setText(""+calculatePlusMinus(row));
        TextView points = (TextView) vi.findViewById(R.id.points);
        points.setText(row.getG());
        return vi;
    }

    public int gamesPlayed(MatchTableRow row){
        int gp = 0;
        gp += Integer.parseInt(row.getE());
        gp += Integer.parseInt(row.getD());
        gp += Integer.parseInt(row.getC());
        return gp;
    }

    public int calculatePlusMinus(MatchTableRow row){
        String string = row.getF();
        String[] parts = string.split(" ");
        int goalsFor = Integer.parseInt(parts[0]);
        int goalsAgainst = Integer.parseInt(parts[2]);
        return goalsFor - goalsAgainst;
    }

    public View setMatchesNotPlayed(int position, View vi){
        vi = inflater.inflate(R.layout.match_row, null);
        TextView dateLabel = (TextView) vi.findViewById(R.id.dateLabel);
        TextView timeLabel = (TextView) vi.findViewById(R.id.timeLabel);
        String dateText = matchesNotYetPlayed.get(position).getMatchdate();
        String[] tempDate = dateText.split("T");
        String finalDate = tempDate[0];
        String time = tempDate[1];
        StringBuilder sb = new StringBuilder(time);
        sb.delete(5, 8);
        time = sb.toString();
        dateLabel.setText(finalDate);
        timeLabel.setText(time);
        TextView homescore = (TextView) vi.findViewById(R.id.homeScoreLabel);
        homescore.setText(context.getResources().getString(R.string.adapter_match_no));
        homescore.setTextSize(10);
        homescore.setPadding(60, 0, 0, 0);
        TextView awayscore = (TextView) vi.findViewById(R.id.awayScoreLabel);
        awayscore.setText(context.getResources().getString(R.string.adapter_match_result));
        TextView hometeam = (TextView) vi.findViewById(R.id.homeTeamLabel);
        awayscore.setTextSize(10);
        awayscore.setPadding(50, 0, 0, 0);
        hometeam.setText(matchesNotYetPlayed.get(position).getHometeamname());
        TextView awayteam = (TextView) vi.findViewById(R.id.awayTeamLabel);
        awayteam.setText(matchesNotYetPlayed.get(position).getAwayteamname());
        return vi;
    }

    public View setMatchesPlayed(int position, View vi){
        vi = inflater.inflate(R.layout.match_row, null);
        TextView dateLabel = (TextView) vi.findViewById(R.id.dateLabel);
        TextView timeLabel = (TextView) vi.findViewById(R.id.timeLabel);
        String dateText = matchesPlayed.get(position).getMatchdate();
        String[] tempDate = dateText.split("T");
        String finalDate = tempDate[0];
        String time = tempDate[1];
        StringBuilder sb = new StringBuilder(time);
        sb.delete(5,8);
        time = sb.toString();
        dateLabel.setText(finalDate);
        timeLabel.setText(time);
        TextView homescore = (TextView) vi.findViewById(R.id.homeScoreLabel);
        homescore.setText(matchesPlayed.get(position).getHomegoal());
        TextView awayscore = (TextView) vi.findViewById(R.id.awayScoreLabel);
        awayscore.setText(matchesPlayed.get(position).getAwaygoal());
        TextView hometeam = (TextView) vi.findViewById(R.id.homeTeamLabel);
        hometeam.setText(matchesPlayed.get(position).getHometeamname());
        TextView awayteam = (TextView) vi.findViewById(R.id.awayTeamLabel);
        awayteam.setText(matchesPlayed.get(position).getAwayteamname());
        return vi;
    }

    public void positionResolver(int position){
        map = new HashMap<String, Integer>();
        final int TABLECOUNTER = matchTableRows.size() + 2; // plus 2 because of the header and the tableinformation
        final int UPCOMMINGMATCHES = TABLECOUNTER + matchesNotYetPlayed.size() + 1; // plus 1 because of the header
        final int MATCHESPLAYED = UPCOMMINGMATCHES + matchesPlayed.size() + 1; // plus 1 because of the header
        if (position >= 0 && position < TABLECOUNTER) {
            if (position == 0) {
                map.put("tableheader", 0);
            } else if (position == 1){
                map.put("tableinformation", 0);
            } else{
                int newPosition = position - 2; // resets position to fill in correct tableitems
                map.put("tableitem", newPosition); // Minus 1 because of the header
            }
        }
        if (position >= TABLECOUNTER && position < UPCOMMINGMATCHES) {
            int newPosition = position - TABLECOUNTER;
            if (newPosition == 0) {
                map.put("matchesnotplayedheader", 0);
            }
            else{
                newPosition = newPosition-1; // minus 1 because of header
                map.put("matchesnotplayeditem", newPosition);
            }
        }
        if (position >= UPCOMMINGMATCHES && position < MATCHESPLAYED) {
            int newPosition = position - UPCOMMINGMATCHES;
            if (newPosition == 0) {
                map.put("matchesplayedheader", 0);
            } else{
                newPosition = newPosition-1; // minus 1 because of header
                map.put("matchesplayeditem", newPosition);
            }
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        positionResolver(position);
        String s = map.keySet().toString();
        s = s.replace("]", "");
        s = s.replace("[", "");
        switch (s){
            case "tableheader": {
                String adapter_match_table = context.getResources().getString(R.string.adapter_match_table);
                return setHeader(vi, adapter_match_table, true); //
            }
            case "tableinformation": {
                return setTableHeader(vi);
            }
            case "tableitem": {
                String indexString = map.get("tableitem").toString();
                int index = Integer.parseInt(indexString);
                return setTableRow(vi, matchTableRows.get(index));
            }
            case "matchesnotplayedheader": {
                String adapter_match_upcomingMatches = context.getResources().getString(R.string.adapter_match_upcomingMatches);
                return setHeader(vi, adapter_match_upcomingMatches, false);
            }
            case "noupcomming": {
                String adapter_match_noUpcomingMatches = context.getResources().getString(R.string.adapter_match_noUpcomingMatches);
                return setInfosection(vi, adapter_match_noUpcomingMatches);
            }
            case "matchesnotplayeditem": {
                String indexString = map.get("matchesnotplayeditem").toString();
                int index = Integer.parseInt(indexString);
                return setMatchesNotPlayed(index, vi);
            }
            case "matchesplayedheader": {
                String adapter_match_gamesPlayed = context.getResources().getString(R.string.adapter_match_gamesPlayed);
                return setHeader(vi, adapter_match_gamesPlayed, false);
            }
            case "nomatchesplayed": {
                String adapter_match_noGamesPlayed = context.getResources().getString(R.string.adapter_match_noGamesPlayed);
                return setInfosection(vi, adapter_match_noGamesPlayed);
            }
            case "matchesplayeditem": {
                String indexString = map.get("matchesplayeditem").toString();
                int index = Integer.parseInt(indexString);
                return setMatchesPlayed(index, vi);
            }
            default:{
                String adapter_match_errorOccurred = context.getResources().getString(R.string.adapter_match_errorOccurred);
                return setInfosection(vi, adapter_match_errorOccurred);
            }

        }
    }

    public ArrayList<TournamentMatch> getMatches(){
        return matches;
    }
}


