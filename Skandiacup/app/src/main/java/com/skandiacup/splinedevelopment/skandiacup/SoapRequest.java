package com.skandiacup.splinedevelopment.skandiacup;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by Jorgen on 27/10/15.
 */
public class SoapRequest extends AsyncTask<String, Void, Void>{
    private static String NAMESPACE = "http://profixio.com/soap/tournament/ForTournamentExt.php";
    private static String METHOD_NAME = "getFields";
    private static String SOAP_ACTION = "http://profixio.com/soap/tournament/ForTournamentExt.php#getFields";
    private static String URL = "http://profixio.com/soap/tournament/ForTournamentExt.php";
    private ArrayList<Object> data;
    private SoapCallback callback;

    public SoapRequest (SoapCallback callback){
        this.callback = callback;
        this.data = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(String... params) {
       try {
           String response = null;
           SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
           Request.addProperty("application_key", "enKHJhF");
           Request.addProperty("tournamentID", 14218);

           SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
           soapEnvelope.setOutputSoapObject(Request);

           HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
           androidHttpTransport.debug = true;

           androidHttpTransport.call(SOAP_ACTION, soapEnvelope);

           // Get the SoapResult from the envelope body.
           SoapObject obj1 = (SoapObject) soapEnvelope.getResponse();

           for(int i=0; i<obj1.getPropertyCount(); i++){
               SoapObject obj2 = (SoapObject) obj1.getProperty(i);
               this.data.add(obj2);
           }
       }catch(Exception e){
           System.out.println("EXCEPTION");
           e.printStackTrace();
       }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        this.callback.successCallback(data);
    }

    @Override
    protected void onCancelled() {
        this.callback.errorCallback();
    }
}
