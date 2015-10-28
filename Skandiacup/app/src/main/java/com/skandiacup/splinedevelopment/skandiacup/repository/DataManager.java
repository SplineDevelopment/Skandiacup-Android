package com.skandiacup.splinedevelopment.skandiacup.repository;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.skandiacup.splinedevelopment.skandiacup.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.SoapRequestCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.Arena;
import com.skandiacup.splinedevelopment.skandiacup.domain.RSSObject;
import com.skandiacup.splinedevelopment.skandiacup.mappers.ArenasMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.RSSMapper;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import cz.msebera.android.httpclient.Header;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorgen on 28/10/15.
 */
public class DataManager {
    private static DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    public void getArenas(String limit, String timestampSince, final SoapCallback<ArrayList<Arena>> callback){
        List<String> params = new ArrayList<>();
        params.add("getArenas");
        if(limit != null){
            params.add("limit");
            params.add(limit);
        }
        if (timestampSince != null){
            params.add("timestamp_since");
            params.add(timestampSince);
        }

        SoapRequest req = new SoapRequest(new SoapRequestCallback() {
            @Override
            public void successCallback(Object data) {
                ArrayList<Arena> arenas = ArenasMapper.mapArenas((SoapObject) data);
                callback.successCallback(arenas);
            }

            @Override
            public void errorCallback() {
                callback.errorCallback();
            }
        });

        req.execute(params.toArray(new String[params.size()]));
    }

    public void getRSSFeed(final SoapCallback<ArrayList<RSSObject>> callback) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://skandiacup.no/feed/";
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                ArrayList<RSSObject> arr;
                try {
                    arr = RSSMapper.mapRSS(response);
                    callback.successCallback(arr);
                } catch (XmlPullParserException | IOException e) {
                    e.printStackTrace();
                    callback.errorCallback();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                callback.errorCallback();
            }
        });
    }

}
