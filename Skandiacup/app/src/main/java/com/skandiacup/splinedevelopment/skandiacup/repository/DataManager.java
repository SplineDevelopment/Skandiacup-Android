package com.skandiacup.splinedevelopment.skandiacup.repository;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.skandiacup.splinedevelopment.skandiacup.InstagramRestClient;
import com.skandiacup.splinedevelopment.skandiacup.SoapCallback;
import com.skandiacup.splinedevelopment.skandiacup.SoapRequestCallback;
import com.skandiacup.splinedevelopment.skandiacup.domain.Arena;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchClass;
import com.skandiacup.splinedevelopment.skandiacup.domain.Field;
import com.skandiacup.splinedevelopment.skandiacup.domain.MatchTable;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentClub;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentTeam;
import com.skandiacup.splinedevelopment.skandiacup.domain.TournamentMatch;
import com.skandiacup.splinedevelopment.skandiacup.domain.RSSObject;
import com.skandiacup.splinedevelopment.skandiacup.mappers.ArenasMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.FieldsMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.InstagramItem;
import com.skandiacup.splinedevelopment.skandiacup.mappers.InstagramMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.MatchTablesMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.TournamentClubMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.MatchClassesMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.TournamentTeamMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.TournamentMatchesMapper;
import com.skandiacup.splinedevelopment.skandiacup.mappers.RSSMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

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
                ArrayList<MatchClass> matchClasses = MatchClassesMapper.mapMatchClasses((SoapObject) data);
                callback.successCallback(matchClasses);
            }

            @Override
            public void errorCallback() {
                callback.errorCallback();
            }
        });

        req.execute(params.toArray(new String[params.size()]));
    }

    public void getMatchTables(String groupID, String playoffID, String teamID, String language, String playoffLevel, final SoapCallback<ArrayList<MatchTable>> callback){
        List <String> params = new ArrayList<>();
        params.add("getTable");
        if(groupID != null){
            params.add("groupID");
            params.add(groupID);
        }
        if(playoffID != null){
            params.add("playoffID");
            params.add(playoffID);
        }
        if(teamID != null){
            params.add("teamID");
            params.add(teamID);
        }
        if(language != null){
            params.add("language");
            params.add(language);
        }
        if(playoffLevel != null){
            params.add("playoffLevel");
            params.add(playoffLevel);
        }
        SoapRequest req = new SoapRequest(new SoapRequestCallback() {
            @Override
            public void successCallback(Object data) {
                ArrayList<MatchTable> tables = MatchTablesMapper.mapMatchTables((SoapObject) data);
                callback.successCallback(tables);
            }

            @Override
            public void errorCallback() {
                callback.errorCallback();
            }
        });

        req.execute(params.toArray(new String[params.size()]));
    }

    public void getInstagramPhotos(final SoapCallback<ArrayList<InstagramItem>> callback){

        String tag = "Skandiacup2015";
        String id = "121ee171e8534280918447ab69eb8c5b";
        String get_uri = "https://api.instagram.com/v1/tags/" + tag + "/media/recent?client_id=" + id;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(get_uri, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                InstagramMapper instagramMapper = new InstagramMapper();
                callback.successCallback(instagramMapper.mapFromJson(response));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                System.out.println(statusCode);
                System.out.println("? JSONArray timeline --- getInstagramPhotos -- DataManager ");
            }
        });

    }
}
