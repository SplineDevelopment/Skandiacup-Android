package com.skandiacup.splinedevelopment.skandiacup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skandiacup.splinedevelopment.skandiacup.domain.Arena;
import com.skandiacup.splinedevelopment.skandiacup.repository.DataManager;

import java.util.ArrayList;

/**
 * Created by Jorgen on 27/10/15.
 */
public class EndplayActivityFragment extends Fragment {
    private static final String NAMESPACE = "http://www.w3schools.com/webservices/";
    private static final String MAIN_REQUEST_URL = "http:/2/www.w3schools.com/webservices/tempconvert.asmx";
    private static final String SOAP_ACTION = "http://www.w3schools.com/webservices/FahrenheitToCelsius";
    private ArrayList<Object> data;

    public EndplayActivityFragment() {
        this.data = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DataManager.getInstance().getArenas(null, null, new SoapCallback() {
            @Override
            public void successCallback(Object data) {
                ArrayList<Arena> arenas = (ArrayList<Arena>) data;
                TextView txt = (TextView) getView().findViewById(R.id.testViewEndPlay);
                txt.setText(arenas.toString());
            }

            @Override
            public void errorCallback() {

            }
        });
        return inflater.inflate(R.layout.fragment_endplay, container, false);
    }
}
