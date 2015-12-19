package com.skandiacup.splinedevelopment.skandiacup.MainViews.Endplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.R;
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
        if (context == null) {
            return;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return matches.size() + getNumberOfHeaders();
    }

    @Override
    public Object getItem(int position) {
        return matches.get(position-getMinusFromPos(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
                text.setEnabled(false);
                text.setOnClickListener(null);
                return vi;
            }
            pow++;
        }
        position -= getMinusFromPos(position);
        View vi = inflater.inflate(R.layout.match_row, null);
        ((TextView) vi.findViewById(R.id.homeTeamLabel)).setText(matches.get(position).getHometeamname());
        ((TextView) vi.findViewById(R.id.awayTeamLabel)).setText(matches.get(position).getAwayteamname());
        TextView tv1 = (TextView) vi.findViewById(R.id.homeScoreLabel);
        TextView tv2 = (TextView) vi.findViewById(R.id.awayScoreLabel);
        if (matches.get(position).getHomegoal().equals("")){
            tv1.setText(context.getResources().getString(R.string.adapter_match_no));
            tv1.setTextSize(12);
            tv1.setPadding(60, 40, 0, 0);
            tv2.setText(context.getResources().getString(R.string.adapter_match_result));
            tv2.setTextSize(12);
            tv2.setPadding(50, 0, 0, 50);
        } else{
            tv1.setText(matches.get(position).getHomegoal());
            tv2.setText(matches.get(position).getAwaygoal());
        }
        String date = matches.get(position).getMatchdate();
        String[] s = date.split("T");
        date = s[0];
        String time = s[1];
        StringBuilder sb = new StringBuilder(time);
        sb.delete(5, 8);
        time = sb.toString();
        ((TextView) vi.findViewById(R.id.dateLabel)).setText(date);
        ((TextView) vi.findViewById(R.id.timeLabel)).setText(time);
        return vi;
    }

    private int getMinusFromPos(int pos) {
        int i = 1;
        int res = 2;
        while(res <= pos) res += (int)Math.pow(2, i++)+1;
        return i;
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