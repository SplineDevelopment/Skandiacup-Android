package com.skandiacup.splinedevelopment.skandiacup.MainViews.Tournament;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.skandiacup.splinedevelopment.skandiacup.Extention.EditTextBackEvent;
import com.skandiacup.splinedevelopment.skandiacup.Extention.EditTextImeBackListener;
import com.skandiacup.splinedevelopment.skandiacup.R;
import com.skandiacup.splinedevelopment.skandiacup.repository.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

public class TournamentActivityFragment extends Fragment {
    ListView lv = null;
    private EditTextBackEvent filterText;
    private Spinner sexPicker;
    private Spinner countryPicker;
    private ArrayList<TournamentTeam> teamNames;
    private ArrayList<TournamentTeam> filteredTeams;
    private ArrayList<MatchClass> matchClasses;
    private ProgressBar spinner;

    public TournamentActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tournament, container, false);
        lv = (ListView)rootView.findViewById(R.id.teamsList);

        spinner = (ProgressBar)rootView.findViewById(R.id.progressBar1);

        final View filterView = inflater.inflate(R.layout.filterview, null, false);
        filterText = (EditTextBackEvent) filterView.findViewById(R.id.filterText);
        sexPicker = (Spinner) filterView.findViewById(R.id.sexPicker);
        countryPicker = (Spinner) filterView.findViewById(R.id.countryPicker);
        filteredTeams = new ArrayList<>();

        Button clearText = (Button)filterView.findViewById(R.id.filtertext_deletebutton);
        clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterText.setText("");
            }
        });

        filterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterTeams();
            }
        });

        sexPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterTeams();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        countryPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterTeams();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        final PopupWindow[] filterPopup = new PopupWindow[1];

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterPopup[0] = new PopupWindow(filterView, GridLayout.LayoutParams.FILL_PARENT,
                        GridLayout.LayoutParams.WRAP_CONTENT, true); // Creation of pop
                filterPopup[0].setAnimationStyle(android.R.style.Animation_Toast);
                filterPopup[0].setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                filterPopup[0].showAsDropDown(rootView);

                filterPopup[0].setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ImageView darkenScreen = (ImageView) rootView.findViewById(R.id.darkenScreen);
                        AlphaAnimation alpha = new AlphaAnimation(0.5f, 0.0f);
                        alpha.setDuration(500);
                        alpha.setFillAfter(true);
                        darkenScreen.startAnimation(alpha);
                    }
                });

                ImageView darkenScreen = (ImageView) rootView.findViewById(R.id.darkenScreen);
                ViewGroup.LayoutParams darkenParams = darkenScreen.getLayoutParams();
                darkenParams.height = 2000;
                darkenParams.width = 2000;
                darkenScreen.setLayoutParams(darkenParams);
                AlphaAnimation alpha = new AlphaAnimation(0.0f, 0.5f);
                alpha.setDuration(500);
                alpha.setFillAfter(true);
                darkenScreen.startAnimation(alpha);
                InputMethodManager inputMethodManager =(InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
            }
        });

        filterText.setOnEditTextImeBackListener(new EditTextImeBackListener() {
            @Override
            public void onImeBack(EditTextBackEvent ctrl, String text) {
                filterPopup[0].dismiss();
            }
        });

        filterText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    filterPopup[0].dismiss();
                    return true;
                }
                return false;
            }
        });

        DataManager.getInstance().getMatchClasses(new SoapCallback<ArrayList<MatchClass>>() {
            @Override
            public void successCallback(ArrayList<MatchClass> data) {
                matchClasses = data;
            }

            @Override
            public void errorCallback() {

            }
        });

        DataManager.getInstance().getTournamentTeams(null, null, null, null, new SoapCallback<ArrayList<TournamentTeam>>() {
            @Override
            public void successCallback(ArrayList<TournamentTeam> data) {
                spinner.setVisibility(View.GONE);
                teamNames = data;
                addCountryPickerValues();
                if (getContext() == null) {
                    return;
                }
                lv.setAdapter(new TeamsAdapter(getContext(), teamNames, matchClasses));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getContext(), TeamActivity.class);
                        TournamentTeam team = (TournamentTeam) lv.getAdapter().getItem(position);
                        intent.putExtra("TeamName", team);
                        for (MatchClass mc : matchClasses) {
                            if (mc.getId().equals(team.getMatchClassId())) {
                                for (MatchGroup mg : mc.getMatchGroups()) {
                                    if (mg.getId().equals(team.getMatchGroupId())) {
                                        intent.putExtra("matchClassName", mc.getName() + " - " + mg.getName());
                                    }
                                }

                            }
                        }
//                        intent.putExtra()
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void errorCallback() {

            }
        });
        return rootView;
    }

    private void filterTeams(){
        filteredTeams.clear();
        for(TournamentTeam team: teamNames){
            if(team.getName().toLowerCase().contains(filterText.getText().toString().toLowerCase()) && team.getCountryCode().equals(countryPicker.getSelectedItem().toString())){
                if(sexPicker.getSelectedItemPosition() == 0){
                    filteredTeams.add(team);
                }
                for(MatchClass mc : matchClasses){
                    if(team.getMatchClassId().equals(mc.getId()) && sexPicker.getSelectedItemPosition()==1 && mc.getGender().equals("M")){
                        filteredTeams.add(team);
                    }else if(team.getMatchClassId().equals(mc.getId()) && sexPicker.getSelectedItemPosition()==2 && mc.getGender().equals("F")){
                        filteredTeams.add(team);
                    }
                }
            }
        }

//        Cannot use this method - it deletes all existing objects in the teams arraylist.
//        ((ArrayAdapter<TournamentTeam>)lv.getAdapter()).clear();

//        ((ArrayAdapter<TournamentTeam>)lv.getAdapter()).addAll(filteredTeams);
//        ((ArrayAdapter<TournamentTeam>)lv.getAdapter()).notifyDataSetChanged();

//        Therefore have to do this
//        @TODO Check if this is causing poor performance
        lv.setAdapter(new TeamsAdapter(getContext(), filteredTeams, matchClasses));
    }

    private void addCountryPickerValues(){
        ArrayList<String> countryCodes = new ArrayList<>();
        for(TournamentTeam team : teamNames){
            if(!countryCodes.contains(team.getCountryCode())){
                countryCodes.add(team.getCountryCode());
            }
        }
        if (getActivity() == null) {
            return;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, countryCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryPicker.setAdapter(adapter);
    }
}
