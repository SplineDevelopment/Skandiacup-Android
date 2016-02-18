/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.MainViews.Field;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.domain.Field;

import java.util.ArrayList;

public class DiariesAdapter extends BaseAdapter{
    Context context;
    ArrayList<Field> fields;
    private static LayoutInflater inflater = null;


    public DiariesAdapter(Context context, ArrayList<Field> fields) {
        this.context = context;
        this.fields = fields;
        if (context != null) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }

    @Override
    public int getCount() {
        return fields.size();
    }

    @Override
    public Object getItem(int position) {
        return fields.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = inflater.inflate(R.layout.teams_table_view_item, null);
        TextView text = (TextView) vi.findViewById(R.id.teamsListItem);
        text.setText(fields.get(position).getFieldName());
        return vi;
    }
}
