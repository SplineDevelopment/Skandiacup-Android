/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.mappers;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchGroup;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

public class MatchGroupsMapper {
    public static ArrayList<MatchGroup> mapMatchGroups(SoapObject soapObject) {
        ArrayList<MatchGroup> matchGroups = new ArrayList<>();

        for (int i = 0; i < soapObject.getPropertyCount(); i++) {
            SoapObject obj2 = (SoapObject) soapObject.getProperty(i);
            MatchGroup matchGroup = new MatchGroup();
            matchGroup.setId(obj2.getPrimitiveProperty("id").toString());
            matchGroup.setName(obj2.getPrimitiveProperty("Name").toString());
            matchGroup.setMatchClassId(obj2.getPrimitiveProperty("MatchClassId").toString());
            matchGroup.setIsPlayOff(obj2.getPrimitiveProperty("IsPlayoff").toString());
            matchGroup.setPlayOffId(obj2.getPrimitiveProperty("PlayoffId").toString());
            matchGroup.setEndGameLevel(obj2.getPrimitiveProperty("EndGameLevel").toString());

            matchGroups.add(matchGroup);
        }
        return matchGroups;
    }
}