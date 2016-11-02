package com.example.taiken.p_iso2525;

import android.os.AsyncTask;
import android.telecom.Call;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class BarcodePostTask extends AsyncTask<String, Integer, String> {

    public interface BarcodePostCallback{
        void Post(String returnData);//serverからきたデータ
    }

    private String postData;//serverへ送るデータ
    private BarcodePostCallback callback;
    private URL apServer;


    public BarcodePostTask(URL apServerAddress,String postData, BarcodePostCallback callback){
        this.postData = postData;
        this.callback = callback;
        this.apServer = apServerAddress;
    }

    protected String doInBackground(String... strings) {
        String buffer = "";
        try {
            HttpURLConnection con = null;
            con = (HttpURLConnection) apServer.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "text/html; charset=utf-8");
            OutputStream os = con.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.print("scanData=" + postData);
            ps.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            buffer = reader.readLine();

            con.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

    protected void onPostExecute (String result){
        callback.Post(result);
    }
}