package org.hackermen.migrantssansfrontieres.util;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 01/12/2016.
 */

public class MapUtil {

    private Context context;

    public MapUtil(Context context) {
        this.context = context;
    }

    public List<Marker> getNearbyPointers() {
        final List<Marker> list = new ArrayList<>();

        new AsyncTask<String, Void, Void>() {

            @Override
            protected Void doInBackground(String... strings) {
                try {
                    // TODO: 01/12/2016 Change URL
                    URL url = new URL("url");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String inputStr;
                        while ((inputStr = reader.readLine()) != null)
                            stringBuilder.append(inputStr);

                        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                        JSONArray pointers = jsonObject.getJSONArray("pts");
                        for (int i = 0; i < pointers.length(); i++) {
                            JSONObject marker = pointers.getJSONObject(i);
                            list.add(new Marker(marker.getLong("long"), marker.getLong("lat"),
                                    marker.getString("name"), MarkerType.getMarker("type")));
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

            }
        }.execute();


        return list;
    }


    public void addMarker(final long longitude, final long latitude, final String title, final String type) {
        new AsyncTask<String, Void, Void>() {

            @Override
            protected Void doInBackground(String... strings) {
                try {
                    // TODO: 01/12/2016 Change URL
                    URL url = new URL("url");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    JSONObject json = new JSONObject("long :\"" + longitude + "\", lat:\"" +
                            latitude + "\", name:\"" + title + "\", type:\"" +
                            type + "\"");

                    byte[] postDataBytes = json.toString().getBytes("UTF-8");

                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
                    conn.setDoOutput(true);
                    conn.getOutputStream().write(postDataBytes);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

            }
        }.execute();
    }


}
