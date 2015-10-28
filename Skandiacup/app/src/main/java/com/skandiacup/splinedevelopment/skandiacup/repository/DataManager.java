package com.skandiacup.splinedevelopment.skandiacup.repository;

import com.skandiacup.splinedevelopment.skandiacup.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.Arena;
import com.skandiacup.splinedevelopment.skandiacup.mappers.ArenasMapper;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

/**
 * Created by Jorgen on 28/10/15.
 */
public class DataManager {
    private static DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
    }

    public void getArenas(final SoapCallback callback){
        SoapRequest req = new SoapRequest(new SoapCallback() {
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
        req.execute("getArenas");
    }
}
