package com.skandiacup.splinedevelopment.skandiacup.repository;

import com.skandiacup.splinedevelopment.skandiacup.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.SoapRequestCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.Arena;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.mappers.ArenasMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.TournamentTeamMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.TournamentMatchesMapper;

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

    public void getTournamentMatches(String classID, String groupID, String matchID, String endplay,
                                     String since, String limit, String matchTXID,
                                     final SoapCallback<ArrayList<TournamentMatch>> callback){
        List<String> params = new ArrayList<>();
        params.add("getMatches");
        if (classID != null){
            params.add("classID");
            params.add(classID);
        }
        if(groupID != null){
            params.add("groupID");
            params.add(groupID);
        }
        if(matchID != null){
            params.add("matchID");
            params.add(matchID);
        }
        if(endplay != null) {
            params.add("endplay");
            params.add(endplay);
        }
        if (since != null) {
            params.add("since");
            params.add(since);
        }

        if (limit != null) {
            params.add("limit");
            params.add(limit);
        }
        if (matchTXID != null) {
            params.add("matchTXID");
            params.add(matchTXID);
        }
        SoapRequest req = new SoapRequest(new SoapRequestCallback() {
            @Override
            public void successCallback(Object data) {
                ArrayList<TournamentMatch> matches = TournamentMatchesMapper.mapMatches((SoapObject)data);
                callback.successCallback(matches);
            }

            @Override
            public void errorCallback() {

            }
        });
        req.execute(params.toArray(new String[params.size()]));
    }


     public void getTournamentTeams(String clubId, String limit, String offset, String timestampSince, final SoapCallback<ArrayList<TournamentTeam>> callback){
         List <String> params = new ArrayList<>();
         params.add("getTeams");
         if (clubId != null){
             params.add("clubId");
             params.add(clubId);
         }
         if(limit != null){
             params.add("limit");
             params.add(limit);
         }
         if(offset != null){
             params.add("offset");
             params.add(offset);
         }
         if(timestampSince != null){
             params.add("timestamp_since");
             params.add(timestampSince);
         }

         SoapRequest req = new SoapRequest(new SoapRequestCallback() {
             @Override
             public void successCallback(Object data) {
                 ArrayList<TournamentTeam> teams = TournamentTeamMapper.mapTournamentTeams((SoapObject) data);
                 callback.successCallback(teams);
             }

             @Override
             public void errorCallback() {
                 callback.errorCallback();
             }
         });

         req.execute(params.toArray(new String[params.size()]));
     }
}
