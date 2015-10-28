package com.skandiacup.splinedevelopment.skandiacup.repository;

import com.skandiacup.splinedevelopment.skandiacup.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.SoapRequestCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.Arena;
import com.skandiacup.splinedevelopment.skandiacup.mappers.ArenasMapper;

import org.ksoap2.serialization.SoapObject;

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
}
