package com.skandiacup.splinedevelopment.skandiacup.mappers;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import org.ksoap2.serialization.SoapObject;
import java.util.ArrayList;

public class TournamentTeamMapper {
    public static ArrayList<TournamentTeam> mapTournamentTeams(SoapObject soapObject){
        ArrayList<TournamentTeam> teams = new ArrayList<>();

        for(int i=0; i<soapObject.getPropertyCount(); i++) {
            SoapObject obj2 = (SoapObject) soapObject.getProperty(i);
            TournamentTeam tournamentTeam = new TournamentTeam();
            tournamentTeam.setId(obj2.getPrimitiveProperty("id").toString());
            tournamentTeam.setName(obj2.getPrimitiveProperty("name").toString());
            tournamentTeam.setClubId(obj2.getPrimitiveProperty("clubid").toString());
            tournamentTeam.setMatchGroupId(obj2.getPrimitiveProperty("MatchGroupId").toString());
            tournamentTeam.setMatchClassId(obj2.getPrimitiveProperty("MatchClassId").toString());
            tournamentTeam.setTournamentTeamRegistrationId(obj2.getPrimitiveProperty("TournamentTeamRegistrationId").toString());
            tournamentTeam.setUpdateTimestamp(obj2.getPrimitiveProperty("UpdateTimestamp").toString());
            tournamentTeam.setShirtColor(obj2.getPrimitiveProperty("ShirtColor").toString());
            tournamentTeam.setCountryCode(obj2.getPrimitiveProperty("countryCode").toString());
            teams.add(tournamentTeam);
        }
        return teams;
    }
}