package com.skandiacup.splinedevelopment.skandiacup.MainViews.Field;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;

import java.util.ArrayList;

public class FieldDiariesSelectedTeamAdapter extends BaseAdapter {
    Context context;
    ArrayList<TournamentMatch> matches;
    private static LayoutInflater inflater = null;


    public FieldDiariesSelectedTeamAdapter(Context context, ArrayList<TournamentMatch> matches) {
        this.context = context;
        this.matches = matches;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Object getItem(int position) {
        return matches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = inflater.inflate(R.layout.matches_field_diaries, null);
        TextView text1 = (TextView) vi.findViewById(R.id.fieldDiaryMatchDate);
        String date = matches.get(position).getMatchdate();
        String[] s = date.split("T");
        date = s[0];
        String time = s[1];
        StringBuilder sb = new StringBuilder(time);
        sb.delete(5,8);
        time = sb.toString();
        text1.setText(date);
        TextView text2 = (TextView) vi.findViewById(R.id.fieldDiaryMatchTeams);
        text2.setText(matches.get(position).getHometeamname() + " - " + matches.get(position).getAwayteamname());
        TextView text3 = (TextView) vi.findViewById(R.id.fieldDiaryMatchTime);
        text3.setText(time);

        return vi;
    }
}
