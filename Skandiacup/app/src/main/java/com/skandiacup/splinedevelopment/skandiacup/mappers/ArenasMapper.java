package com.skandiacup.splinedevelopment.skandiacup.mappers;

import com.skandiacup.splinedevelopment.skandiacup.domain.Arena;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

/**
 * Created by Jorgen on 28/10/15.
 */
public class ArenasMapper {
    public static ArrayList<Arena> mapArenas(SoapObject soapObject){
        ArrayList<Arena> arenas = new ArrayList<>();

        for(int i=0; i<soapObject.getPropertyCount(); i++){
            SoapObject obj2 = (SoapObject) soapObject.getProperty(i);
            Arena arena = new Arena();

            arena.setArenaID(Integer.parseInt(obj2.getPrimitiveProperty("arenaID").toString()));
            arena.setArenaName(obj2.getPrimitiveProperty("arenaName").toString());
            arena.setArenaDescription(obj2.getPrimitiveProperty("arenaDescription").toString());
            arena.setUpdate_timestamp(obj2.getPrimitiveProperty("update_timestamp").toString());

            arenas.add(arena);
        }
        return arenas;
    }
}
