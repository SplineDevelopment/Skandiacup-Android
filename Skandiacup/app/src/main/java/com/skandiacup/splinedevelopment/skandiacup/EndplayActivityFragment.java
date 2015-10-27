package com.skandiacup.splinedevelopment.skandiacup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.Proxy;
import java.util.ArrayList;

/**
 * Created by Jorgen on 27/10/15.
 */
public class EndplayActivityFragment extends Fragment {
    private static final String NAMESPACE = "http://www.w3schools.com/webservices/";
    private static final String MAIN_REQUEST_URL = "http://www.w3schools.com/webservices/tempconvert.asmx";
    private static final String SOAP_ACTION = "http://www.w3schools.com/webservices/FahrenheitToCelsius";
    private ArrayList<Object> data;

    public EndplayActivityFragment() {
        this.data = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("ON CREATE VIEW");
        SoapRequest req = new SoapRequest(new SoapCallback() {
            @Override
            public void successCallback(ArrayList<Object> data) {
                TextView txt = (TextView) getView().findViewById(R.id.testViewEndPlay);
                txt.setText(data.toString());
            }

            @Override
            public void errorCallback() {

            }
        });
        req.execute();
        return inflater.inflate(R.layout.fragment_endplay, container, false);
    }
}
