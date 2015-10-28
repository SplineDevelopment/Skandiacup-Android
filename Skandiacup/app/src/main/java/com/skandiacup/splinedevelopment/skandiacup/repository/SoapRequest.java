package com.skandiacup.splinedevelopment.skandiacup.repository;

import android.os.AsyncTask;

import com.skandiacup.splinedevelopment.skandiacup.SoapCallback;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by Jorgen on 27/10/15.
 */
public class SoapRequest extends AsyncTask<String, Void, Object>{
    private static String NAMESPACE = "http://profixio.com/soap/tournament/ForTournamentExt.php";
    private static String URL = "http://profixio.com/soap/tournament/ForTournamentExt.php";
    private SoapCallback callback;

    public SoapRequest (SoapCallback callback){
        this.callback = callback;
    }

    @Override
    protected Object doInBackground(String... params) {
       try {
           String soap_action = "http://profixio.com/soap/tournament/ForTournamentExt.php#" + params[0];

           SoapObject Request = new SoapObject(NAMESPACE, params[0]);
           Request.addProperty("application_key", "enKHJhF");
           Request.addProperty("tournamentID", 14218);

           SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
           soapEnvelope.setOutputSoapObject(Request);

           HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
           androidHttpTransport.debug = true;

           //Make the HTTP call
           androidHttpTransport.call(soap_action, soapEnvelope);

           // Get the SoapResult from the envelope body.
           SoapObject obj1 = (SoapObject) soapEnvelope.getResponse();

           return obj1;
       }catch(Exception e){
           System.out.println("EXCEPTION");
           e.printStackTrace();
       }
        return null;
    }

    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
        this.callback.successCallback(object);
    }

    @Override
    protected void onCancelled() {
        this.callback.errorCallback();
    }
}
