/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.MainViews.Field;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.Field;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

import static com.skandiacup.splinedevelopment.skandiacup.utils.ErrorMessageGenerator.getErrorMessage;

public class FieldActivityFragmentDiaries extends Fragment {
    ListView lv = null;
    public FieldActivityFragmentDiaries() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_field_diary, container, false);
        lv = (ListView)rootView.findViewById(R.id.fieldDiariesList);
        DataManager.getInstance().getFields(null, null, null, new SoapCallback<ArrayList<Field>>() {
            @Override
            public void successCallback(ArrayList<Field> data) {
                final ArrayList<Field> fieldnames = new ArrayList<Field>();
                for(Field f: data){
                    fieldnames.add(f);
                }
                lv.setAdapter(new DiariesAdapter(getContext(), fieldnames));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            final int position, long id) {
                        final Intent intent = new Intent(getContext(), FieldDiariesTeamSelectedActivity.class);
                        intent.putExtra("fieldId", fieldnames.get(position).getFieldID());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void errorCallback() {
                TextView tv = (TextView) rootView.findViewById(R.id.onErrorMessage);
                if(tv.getText().length() == 0) {
                    Context c = getContext();
                    if (c != null) {
                        String errorMessage = getErrorMessage(c);
                        tv.setText(errorMessage);
                    }
                }
            }
        });
        return rootView;
    }
}
