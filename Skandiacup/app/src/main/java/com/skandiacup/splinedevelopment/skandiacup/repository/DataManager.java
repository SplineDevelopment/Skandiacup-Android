package com.skandiacup.splinedevelopment.skandiacup.repository;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.skandiacup.splinedevelopment.skandiacup.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.SoapRequestCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.Arena;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.domain.Field;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentClub;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.domain.RSSObject;
import com.skandiacup.splinedevelopment.skandiacup.mappers.ArenasMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.FieldsMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.TournamentClubMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.MatchClassesMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.TournamentTeamMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.TournamentMatchesMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.RSSMapper;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import cz.msebera.android.httpclient.Header;

import java.io.IOException;
import java.lang.reflect.Array;
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
                ArrayList<TournamentMatch> matches = TournamentMatchesMapper.mapMatches((SoapObject) data);
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

    public void getFields(String arenaId, String limit, String timestampSince, final SoapCallback<ArrayList<Field>> callback){
        List <String> params = new ArrayList<>();
        params.add("getFields");
        if (arenaId != null){
            params.add("clubId");
            params.add(arenaId);
        }
        if(limit != null){
            params.add("limit");
            params.add(limit);
        }
        if(timestampSince != null){
            params.add("timestamp_since");
            params.add(timestampSince);
        }
        SoapRequest req = new SoapRequest(new SoapRequestCallback() {
            @Override
            public void successCallback(Object data) {
                ArrayList<Field> fields = FieldsMapper.mapFields((SoapObject) data);
                callback.successCallback(fields);
            }

            @Override
            public void errorCallback() {
                callback.errorCallback();
            }
        });

        req.execute(params.toArray(new String[params.size()]));
    }

    public void getTournamentClubs(String limit, final SoapCallback<ArrayList<TournamentClub>> callback){
        List <String> params = new ArrayList<>();
        params.add("getClubs");
        if(limit != null){
            params.add("limit");
            params.add(limit);
        }
        SoapRequest req = new SoapRequest(new SoapRequestCallback() {
            @Override
            public void successCallback(Object data) {
                ArrayList<TournamentClub> clubs = TournamentClubMapper.mapTournamentClubs((SoapObject) data);
                callback.successCallback(clubs);
            }

            @Override
            public void errorCallback() {
                callback.errorCallback();
            }
        });

        req.execute(params.toArray(new String[params.size()]));
    }

    public void getMatchClasses(final SoapCallback<ArrayList<MatchClass>> callback){
        List <String> params = new ArrayList<>();
        params.add("getMatchClasses");

        SoapRequest req = new SoapRequest(new SoapRequestCallback() {
            @Override
            public void successCallback(Object data) {
                ArrayList<MatchClass> matchClasses = MatchClassesMapper.mapMatchClasses((SoapObject)data);
                callback.successCallback(matchClasses);
            }

            @Override
            public void errorCallback() {
                callback.errorCallback();
            }
        });

        req.execute(params.toArray(new String[params.size()]));
    }
}
