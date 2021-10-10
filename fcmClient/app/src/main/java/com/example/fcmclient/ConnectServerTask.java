package com.example.fcmclient;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectServerTask extends AsyncTask<String, String, String> {

    private static final String TAG="ASYNC TASK";

    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground");
        try{
            JSONObject object = new JSONObject();
            object.accumulate("user_id", "android");
            object.accumulate("user_name", "Enterprise09");
            object.accumulate("user_token", strings[1]);

            HttpURLConnection conn = null;
            BufferedReader reader = null;
            try{
                URL url = new URL(strings[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Cache-Control", "no-cache");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "text/html");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                writer.write(object.toString());
                writer.flush();
                writer.close();

                InputStream inputStream = conn.getInputStream();
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                return buffer.toString();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(conn != null){
                    conn.disconnect();
                }
                if(reader != null){
                    reader.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s == null){
            Log.w("SERVER RES", "ERROR");
        }else{
            Log.d("SERVER RES", s);
        }
    }
}
