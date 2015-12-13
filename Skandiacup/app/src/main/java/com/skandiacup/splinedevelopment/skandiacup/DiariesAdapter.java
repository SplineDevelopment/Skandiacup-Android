package com.skandiacup.splinedevelopment.skandiacup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.Field;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;

import java.util.ArrayList;

/**
 * Created by eiriksandberg on 13.12.2015.
 */
public class DiariesAdapter extends BaseAdapter{
    Context context;
    ArrayList<Field> fields;
    private static LayoutInflater inflater = null;


    public DiariesAdapter(Context context, ArrayList<Field> fields) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.fields = fields;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return fields.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return fields.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        vi = inflater.inflate(R.layout.teams_table_view_item, null);
        // }
        TextView text = (TextView) vi.findViewById(R.id.teamsListItem);
        text.setText(fields.get(position).getFieldName());
        return vi;
    }
}
