package com.skandiacup.splinedevelopment.skandiacup.mappers;

import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

public class TournamentMatchesMapper {
    public static ArrayList<TournamentMatch> mapMatches(SoapObject soapObject) {
        ArrayList<TournamentMatch> matches = new ArrayList<>();
        for (int i = 0; i < soapObject.getPropertyCount(); i++) {
            SoapObject obj2 = (SoapObject) soapObject.getProperty(i);
            TournamentMatch match = new TournamentMatch();

            match.setId(obj2.getPrimitiveProperty("id").toString());
            match.setMatchTXID(obj2.getPrimitiveProperty("matchTXID").toString());
            match.setMatchno(obj2.getPrimitiveProperty("matchno").toString());
            match.setMatchname(obj2.getPrimitiveProperty("matchno").toString());
            match.setClassid(obj2.getPrimitiveProperty("classid").toString());
            match.setHometeamid(obj2.getPrimitiveProperty("hometeamid").toString());
            match.setHometeamname(obj2.getPrimitiveProperty("hometeamname").toString());
            match.setAwayteamid(obj2.getPrimitiveProperty("awayteamid").toString());
            match.setAwayteamname(obj2.getPrimitiveProperty("awayteamname").toString());
            match.setHomeclubid(obj2.getPrimitiveProperty("homeclubid").toString());
            match.setHomeclubname(obj2.getPrimitiveProperty("homeclubname").toString());
            match.setAwayclubid(obj2.getPrimitiveProperty("awayclubid").toString());
            match.setAwayclubname(obj2.getPrimitiveProperty("awayclubname").toString());
            match.setMatchdate(obj2.getPrimitiveProperty("matchdate").toString());
            match.setMatchcomment(obj2.getPrimitiveProperty("matchcomment").toString());
            match.setHometeamtext(obj2.getPrimitiveProperty("hometeamtext").toString());
            match.setAwayteamtext(obj2.getPrimitiveProperty("awayteamtext").toString());
            match.setHomegoal(obj2.getPrimitiveProperty("homegoal").toString());
            match.setAwaygoal(obj2.getPrimitiveProperty("awaygoal").toString());
            match.setFieldid(obj2.getPrimitiveProperty("fieldid").toString());
            match.setEndgamelevel(obj2.getPrimitiveProperty("endgamelevel").toString());
            match.setSortorder(obj2.getPrimitiveProperty("sortorder").toString());
            match.setReason(obj2.getPrimitiveProperty("reason").toString());
            match.setWinner(obj2.getPrimitiveProperty("winner").toString());
            match.setPeriodLengthInMinutes(obj2.getPrimitiveProperty("PeriodLengthInMinutes").toString());
            match.setNumberOfPeriodsInMatch(obj2.getPrimitiveProperty("NumberOfPeriodsInMatch").toString());
            match.setUpdateTimeStamp(obj2.getPrimitiveProperty("UpdateTimeStamp").toString());
            match.setMatchGroupId(obj2.getPrimitiveProperty("MatchGroupId").toString());

            matches.add(match);
        }
        return matches;
    }
}