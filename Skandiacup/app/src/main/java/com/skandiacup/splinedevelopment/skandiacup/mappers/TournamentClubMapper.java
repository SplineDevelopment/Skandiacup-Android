/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.mappers;

import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentClub;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

public class TournamentClubMapper {
    public static ArrayList<TournamentClub> mapTournamentClubs(SoapObject soapObject) {
        ArrayList<TournamentClub> clubs = new ArrayList<>();
        for (int i = 0; i < soapObject.getPropertyCount(); i++) {
            SoapObject obj2 = (SoapObject) soapObject.getProperty(i);
            TournamentClub club = new TournamentClub();
            club.setId(obj2.getPrimitiveProperty("id").toString());
            club.setName(obj2.getPrimitiveProperty("name").toString());
            club.setCountryCode(obj2.getPrimitiveProperty("CountryCode").toString());
            clubs.add(club);
        }
        return clubs;
    }
}