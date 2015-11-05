package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
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

/**
 * Created by eiriksandberg on 05.11.2015.
 */
public class MatchAdapter extends BaseAdapter{
    Context context;
    private ArrayList<TournamentMatch> matchesNotYetPlayed;
    private ArrayList<TournamentMatch> matchesPlayed;
    private ArrayList<TournamentMatch> matches;
    private MatchTable table;
    private ArrayList<MatchTableRow> matchTableRows;
    private static LayoutInflater inflater = null;
    private final int sections = 4;
    private int matchesSet = 0;
    private int matchesPlayedSet = 0;
    private int numberOfTableRowsIncludingHeader;
    private int numberOfTableRowsSet = 0;
    private int newPosition = 0;


    public MatchAdapter(Context context, ArrayList<TournamentMatch> matches, ArrayList<MatchTable> tables) {
        this.context = context;
        this.matches = matches;
        this.matchesNotYetPlayed = matchesNotYetPlayed(matches);
        this.matchesPlayed = matchesPlayed(matches);
        this.table = tables.get(0);
        this.matchTableRows = table.getRows();
        this.numberOfTableRowsIncludingHeader = matchTableRows.size()+1;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<TournamentMatch> matchesNotYetPlayed(ArrayList<TournamentMatch> m){
        ArrayList<TournamentMatch> matchesNotYetPlayed = new ArrayList<>();
        for (TournamentMatch match : m){
            if (match.getHomegoal().equals(null) || match.getAwaygoal().equals(null)){
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
        int count = matches.size() + sections + numberOfTableRowsIncludingHeader;
        System.out.println("Antall celler som skal fylles ut " +count);
        newPosition = 0;
        return count;
    }

    @Override
    public Object getItem(int position) {
        return matches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View setHeader(View convertView, String title){
        View vi = convertView;
        vi = inflater.inflate(R.layout.list_header, null);
        TextView text = (TextView) vi.findViewById(R.id.list_header_title);
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


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        System.out.println("position: " + position);
        return setHeader(vi, "test");
        /*
        if(position == 0){
            System.out.println("celle 0 satt. (header 1)");
            return setHeader(vi, "Tabell");
        }
        if (position > 0 && position <= numberOfTableRowsIncludingHeader && numberOfTableRowsIncludingHeader > 1){
            if(numberOfTableRowsSet < numberOfTableRowsIncludingHeader){
                if(numberOfTableRowsSet == 0){
                    numberOfTableRowsSet++;
                    System.out.println("celle 1 satt.(tabellheader)");
                    return setTableHeader(convertView); // Tableheader is being set
                }

                View v = setTableRow(convertView, matchTableRows.get(numberOfTableRowsSet-1));
                numberOfTableRowsSet++;
                System.out.println("celle: " + numberOfTableRowsSet + " er satt. (lag)");
                return v;
            }
        }
        newPosition = numberOfTableRowsIncludingHeader+1; // defining new position where header will be put
        if(position == newPosition){
            System.out.println("celle " + newPosition + " satt. (Header 3. Kommende kamper)");
            return setHeader(vi, "Kommende kamper");
        }
        if(position > newPosition && position <= (matchesNotYetPlayed.size() + newPosition)) {
            if (matchesSet < matchesNotYetPlayed.size()) {
                vi = inflater.inflate(R.layout.match_row, null);
                TextView dateLabel = (TextView) vi.findViewById(R.id.dateLabel);
                dateLabel.setText(matchesNotYetPlayed.get(matchesSet).getMatchdate());
                TextView homescore = (TextView) vi.findViewById(R.id.homeScoreLabel);
                homescore.setText(matchesNotYetPlayed.get(matchesSet).getHomegoal());
                TextView awayscore = (TextView) vi.findViewById(R.id.awayScoreLabel);
                awayscore.setText(matchesNotYetPlayed.get(matchesSet).getAwaygoal());
                TextView hometeam = (TextView) vi.findViewById(R.id.homeTeamLabel);
                hometeam.setText(matchesNotYetPlayed.get(matchesSet).getHometeamname());
                TextView awayteam = (TextView) vi.findViewById(R.id.awayTeamLabel);
                awayteam.setText(matchesNotYetPlayed.get(matchesSet).getAwayteamname());
                System.out.println("Celle " + (newPosition + matchesSet) + " er satt. (Lag er satt i kamper ikke spilt)");
                matchesSet++;
                return vi;
            }
        }
        newPosition = newPosition + matchesNotYetPlayed.size() +1;
        System.out.println("Newposition ====== " + newPosition);
       // if(position == (matchesNotYetPlayed.size() + 3)){ gammel
        if(position == newPosition){
            System.out.println("Celle " + newPosition + " er satt (kamper spilt header)");
            return setHeader(vi, "Kamper spilt");
        }
        //if (position > (matchesNotYetPlayed.size() + 3) && position <= (matchesNotYetPlayed.size() + matchesPlayed.size() + 3)){
        if (position > newPosition && position <= (newPosition+matchesPlayed.size())){
            if (matchesSet < matchesPlayed.size()){
                vi = inflater.inflate(R.layout.match_row, null);
                TextView dateLabel = (TextView) vi.findViewById(R.id.dateLabel);
                dateLabel.setText(matchesPlayed.get(matchesPlayedSet).getMatchdate());
                TextView homescore = (TextView) vi.findViewById(R.id.homeScoreLabel);
                homescore.setText(matchesPlayed.get(matchesPlayedSet).getHomegoal());
                TextView awayscore = (TextView) vi.findViewById(R.id.awayScoreLabel);
                awayscore.setText(matchesPlayed.get(matchesPlayedSet).getAwaygoal());
                TextView hometeam = (TextView) vi.findViewById(R.id.homeTeamLabel);
                hometeam.setText(matchesPlayed.get(matchesPlayedSet).getHometeamname());
                TextView awayteam = (TextView) vi.findViewById(R.id.awayTeamLabel);
                awayteam.setText(matchesPlayed.get(matchesPlayedSet).getAwayteamname());
                matchesPlayedSet++;
                System.out.println("Celle " + (newPosition+matchesPlayedSet) + " er satt. (Lag satt i kamper spilt)");
                return vi;
            }
        }
        System.out.println("Hallllllaaaa");
        return null;
        */
    }
}


