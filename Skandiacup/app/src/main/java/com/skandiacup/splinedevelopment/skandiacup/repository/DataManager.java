/**
 * Copyright 2016 Bjørn Hoxmark, Borgar Lie, Eirik Sandberg, Jørgen Wilhelmsen
 */

package com.skandiacup.splinedevelopment.skandiacup.repository;

import android.media.Image;
import android.os.Environment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
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
import com.skandiacup.splinedevelopment.skandiacup.utils.ExternalConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import cz.msebera.android.httpclient.Header;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private String configFile = "appConfig.json";
    private String fieldImageUrl = null;
    private String application_key = null;
    private int tournamentID = 0;
    private String instagramHashtag = null;
    private int version = 1;

    public DataManager() {
        FTPdownload ftpdownloader = new FTPdownload(new FTPcallback() {
            @Override
            public void successCallback(Object data) {
                JSONObject jObject = null;
                try {
                    jObject = new JSONObject(new String((byte[]) data, "UTF-8"));
                } catch (JSONException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject conf = jObject != null ? jObject.getJSONObject("config") : null;
                    if (conf != null) {
                        System.out.println(conf);
                        fieldImageUrl = conf.getString("fieldImageUrl");
                        application_key = conf.getString("application_key");
                        tournamentID = conf.getInt("tournamentID");
                        instagramHashtag = conf.getString("instagramHashtag");
                        version = conf.getInt("version");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void errorCallback() {
                System.out.println("error callback from ftpdownloader");
            }
        }, this.configFile);
        ftpdownloader.execute();


    }

    private static DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    public void getFieldImage(final SoapCallback<byte[]> callback) {
        // getting version number from phone memory
        int last_saved_version = 0;
        final String versionfile = "versionFieldImage.txt";
        final File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        final File file_version = new File(path, "/" + versionfile);
        int size_version = (int) file_version.length();
        byte[] version_bytes = new byte[size_version];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file_version));
            int read = buf.read(version_bytes, 0, version_bytes.length);
            buf.close();
            if (read > 0) {
                String str = new String(version_bytes, "UTF-8");
                last_saved_version = Integer.parseInt(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // check version from config vs last saved version
        if (last_saved_version != this.version) {
            // download new version
            FTPdownload ftp = new FTPdownload(new FTPcallback() {
                @Override
                public void successCallback(Object data) {
                    callback.successCallback((byte[]) data);
                    String imgfile = "fieldImage.png";
                    File file = new File(path, "/" + imgfile);
                    BufferedOutputStream bos = null;
                    try {
                        bos = new BufferedOutputStream(new FileOutputStream(file));
                        bos.write((byte[]) data);
                        bos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (bos != null) {
                                bos.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // save version number
                    File file_version_save = new File(path, "/" + versionfile);
                    BufferedOutputStream bos_version = null;
                    try {
                        bos_version = new BufferedOutputStream(new FileOutputStream(file_version_save));
                        bos_version.write(("" + version).getBytes());
                        bos_version.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (bos_version != null) {
                                bos_version.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void errorCallback() {
                    callback.errorCallback();
                }
            }, this.fieldImageUrl);
            ftp.execute();
        }
        else {
            // get image from phone memory
            String imgfile = "fieldImage.png";
            File file = new File(path, "/" + imgfile);
            int size = (int) file.length();
            byte[] bytes = new byte[size];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                int read = buf.read(bytes, 0, bytes.length);
                buf.close();
                if (read > 0) {
                    callback.successCallback(bytes);
                } else {
                    callback.errorCallback();
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.errorCallback();
            }
        }
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
        }, this.application_key, this.tournamentID);

        req.execute(params.toArray(new String[params.size()]));
    }

    public void getRSSFeed(final SoapCallback<ArrayList<RSSObject>> callback) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://skandiacup.no/category/nyheter/feed/";
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

    public void getRSSFeedInfo(final SoapCallback<ArrayList<RSSObject>> callback) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://skandiacup.no/category/info/feed/";
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
                System.out.println("Info failure");
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
                callback.errorCallback();
            }
        }, this.application_key, this.tournamentID);
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
         }, this.application_key, this.tournamentID);

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
        }, this.application_key, this.tournamentID);

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
        }, this.application_key, this.tournamentID);

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
        }, this.application_key, this.tournamentID);

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
        }, this.application_key, this.tournamentID);
        req.execute(params.toArray(new String[params.size()]));
    }

    public void getInstagramPhotos(final SoapCallback<ArrayList<InstagramItem>> callback){

//        String tag = "Norwaycup2014";
        String id = ExternalConfig.instagram_id;
        String get_uri = "https://api.instagram.com/v1/tags/" + this.instagramHashtag + "/media/recent?client_id=" + id;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(get_uri, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                InstagramMapper instagramMapper = new InstagramMapper();
                callback.successCallback(instagramMapper.mapFromJson(response));
            }

            // is all 3 neeeded?
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                callback.errorCallback();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                callback.errorCallback();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.errorCallback();
            }

        });

    }

    public void getInstagramItem(String url, final SoapCallback<byte[]> callback) {
        AsyncHttpClient client = new AsyncHttpClient();

        // is this needed?
        try{
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            MySSLSocketFactory socketFactory = new MySSLSocketFactory(trustStore);
            socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client = new AsyncHttpClient();
            client.setTimeout(5*1000);
            client.setSSLSocketFactory(socketFactory);
        } catch (Exception e) {
            System.out.println("error: "+e);
        }

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                try {
                    callback.successCallback(response);
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.errorCallback();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                System.out.println("ERROR: "+ statusCode + " " + e);
                callback.errorCallback();
            }
        });
    }

}
