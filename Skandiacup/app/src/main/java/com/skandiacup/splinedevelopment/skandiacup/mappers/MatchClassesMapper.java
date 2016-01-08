/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.mappers;

import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

public class MatchClassesMapper {
    public static ArrayList<MatchClass> mapMatchClasses(SoapObject soapObject){
        ArrayList<MatchClass> matchClasses = new ArrayList<>();

        for(int i=0; i<soapObject.getPropertyCount(); i++) {
            SoapObject obj2 = (SoapObject) soapObject.getProperty(i);
            MatchClass matchClass = new MatchClass();

            matchClass.setId(obj2.getPrimitiveProperty("id").toString());
            matchClass.setCode(obj2.getPrimitiveProperty("Code").toString());
            matchClass.setName(obj2.getPrimitiveProperty("Name").toString());
            matchClass.setGender(obj2.getPrimitiveProperty("Gender").toString());
            matchClass.setPeriodLengthInMinutes(obj2.getPrimitiveProperty("PeriodLengthInMinutes").toString());
            matchClass.setNumberOfPeriodsInMatch(obj2.getPrimitiveProperty("NumberOfPeriodsInMatch").toString());
            matchClass.setMatchGroups(MatchGroupsMapper.mapMatchGroups((SoapObject)obj2.getProperty("MatchGroups")));

            matchClasses.add(matchClass);
        }
        return matchClasses;
    }
}