package com.skandiacup.splinedevelopment.skandiacup.repository;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Jorgen on 27/10/15.
 */
public class SoapRequest extends AsyncTask<String, Void, Object>{
    private static String NAMESPACE = "http://profixio.com/soap/tournament/ForTournamentExt.php";
    private static String URL = "http://profixio.com/soap/tournament/ForTournamentExt.php";
    // does the timeout value work properly?
    private static int HTTP_TIMEOUT_MS = 5000;
    private SoapRequestCallback callback;

    public SoapRequest (SoapRequestCallback callback){
        this.callback = callback;
    }

    @Override
    protected Object doInBackground(String... params) {
       try {
           for(int i = 0; i<params.length; i++){
               System.out.println("PARAMTER " + params[i]);
           }
           String soap_action = "http://profixio.com/soap/tournament/ForTournamentExt.php#" + params[0];
           SoapObject Request = new SoapObject(NAMESPACE, params[0]);
           Request.addProperty("application_key", "enKHJhF");
           Request.addProperty("tournamentID", 16209);

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
