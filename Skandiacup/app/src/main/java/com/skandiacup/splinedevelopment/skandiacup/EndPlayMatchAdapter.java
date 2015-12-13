package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class EndPlayMatchAdapter extends BaseAdapter{

    private static final Map<String, String> HEADER_TITLES = new HashMap<>();
    static {
        HEADER_TITLES.put("1.0000", "Finale");
        HEADER_TITLES.put("2.0000", "Semifinaler");
        HEADER_TITLES.put("4.0000", "Kvartfinaler");
        HEADER_TITLES.put("8.0000", "Åttendedelsfinaler");
        HEADER_TITLES.put("16.0000", "Sekstendelsfinaler");
        HEADER_TITLES.put("32.0000", "32-delsfinaler");
        HEADER_TITLES.put("64.0000", "64-delsfinaler");
        HEADER_TITLES.put("128.0000", "128-delsfinaler");
    }


    private Context context;
    private ArrayList<TournamentMatch> matches;
    private static LayoutInflater inflater = null;

    public EndPlayMatchAdapter(Context context, ArrayList<TournamentMatch> matches){
        this.context = context;
        this.matches = matches;
        Collections.sort(this.matches);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println(matches);
    }

    @Override
    public int getCount() {
        return matches.size() + getNumberOfHeaders();
    }

    @Override
    public Object getItem(int position) {
        int subtract = (int)Math.floor(Math.log(position) / Math.log(2));
        return matches.get(position-subtract);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        double i = 0;
        int pow = 0;
        int index = position;
        while(i<256){
            i = i+ Math.pow(2, pow) +1;
            if(index == i || index == 0){
                if(index != 0){
                    int subtract = (int)Math.floor(Math.log(index) / Math.log(2));
                    index = index - subtract + 1;
                } else {
                    index = index + 1;
                }
                View vi = convertView;
                vi = inflater.inflate(R.layout.list_header, null);
                TextView text = (TextView) vi.findViewById(R.id.list_header_title);
                text.setText(HEADER_TITLES.get(index + ".0000"));
                return vi;
            }
            pow++;
        }

//        int subtract = (int)Math.floor(Math.log(position) / Math.log(2));
//        position -= subtract;

        //@TODO - find a generalized mathematical formula for this.
        if(position<2){        // add 3
            position -= 1;
        } else if(position<5){ // add 5
            position -= 2;
        }else if(position<10){ // add 9
            position -= 3;
        }else if(position<19){ // add 17
            position -= 4;
        }else if(position<36){ // add 33
            position -= 5;
        }else if(position<69){ // add 65
            position -= 6;
        }else if(position<134){ // add 129
            position -= 7;
        }else if(position<263){ // add 257
            position -= 8;
        }else if(position<520){ // add 513
            position -= 9;
        }else if(position<1033){ // add 1025
            position -= 10;
        } else if(position<2058){ // add 2049
            position -= 11;
        }else if(position < 4107){ // lol
            position -= 12;
        }

        View vi = inflater.inflate(R.layout.match_row, null);
        ((TextView) vi.findViewById(R.id.homeTeamLabel)).setText(matches.get(position).getHometeamname());
        ((TextView) vi.findViewById(R.id.awayTeamLabel)).setText(matches.get(position).getAwayteamname());
        ((TextView) vi.findViewById(R.id.homeScoreLabel)).setText(matches.get(position).getHomegoal());
        ((TextView) vi.findViewById(R.id.awayScoreLabel)).setText(matches.get(position).getAwaygoal());
        ((TextView) vi.findViewById(R.id.dateLabel)).setText(matches.get(position).getMatchdate());
        return vi;
    }

    private int getNumberOfHeaders(){
        ArrayList<String> foundHeaders = new ArrayList<>();

        for(TournamentMatch match : matches){
            if(!foundHeaders.contains(match.getSortorder())){
                foundHeaders.add(match.getSortorder());
            }
        }
        return foundHeaders.size();
    }
}