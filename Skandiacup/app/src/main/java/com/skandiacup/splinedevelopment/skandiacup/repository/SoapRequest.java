/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.repository;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class SoapRequest extends AsyncTask<String, Void, Object>{
    private static String NAMESPACE = "http://profixio.com/soap/tournament/ForTournamentExt.php";
    private static String URL = "http://profixio.com/soap/tournament/ForTournamentExt.php";
    // does the timeout value work properly?
    private static int HTTP_TIMEOUT_MS = 5000;
    private SoapRequestCallback callback;
    private String application_key;
    private int tournamentID;

    public SoapRequest (SoapRequestCallback callback, String application_key, int tournamentID){
        this.callback = callback;
        this.application_key = application_key;
        this.tournamentID = tournamentID;
    }

    @Override
    protected Object doInBackground(String... params) {
       try {
           String soap_action = "http://profixio.com/soap/tournament/ForTournamentExt.php#" + params[0];
           SoapObject Request = new SoapObject(NAMESPACE, params[0]);
           Request.addProperty("application_key", this.application_key);
           Request.addProperty("tournamentID", this.tournamentID);

           for(int i = 1;i<params.length; i+=2){
               try{
                   String property = params[i];
                   String value = params[i+1];
                   Request.addProperty(property, value);
               }catch(ArrayIndexOutOfBoundsException e){
               }
           }

           SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
           soapEnvelope.setOutputSoapObject(Request);

           HttpTransportSE androidHttpTransport = new HttpTransportSE(URL, HTTP_TIMEOUT_MS);
           //androidHttpTransport.debug = true;

           //Make the HTTP call
           androidHttpTransport.call(soap_action, soapEnvelope);

           // Get the SoapResult from the envelope body.
           SoapObject obj1 = (SoapObject) soapEnvelope.getResponse();

           return obj1;
       }catch(Exception e){
           e.printStackTrace();
       }
        return null;
    }

    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
        if (object != null) {
            this.callback.successCallback(object);
        } else {
            System.out.println("object in onPostExecute (SoapRequest) is null");
            this.callback.errorCallback();
        }
    }

    @Override
    protected void onCancelled() {
        this.callback.errorCallback();
    }
}
