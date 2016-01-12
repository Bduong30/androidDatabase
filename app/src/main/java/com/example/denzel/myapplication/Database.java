package com.example.denzel.myapplication;


import android.os.AsyncTask;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by denzel on 1/9/16.
 */
public class Database extends AsyncTask<Void,Void,String>  {

    protected String doInBackground(Void...urls) {

        try {
            //get the url
            URL url = new URL("https://api.mongolab.com/api/1/databases/androiddatabase/collections/Quotes?apiKey=Cwu6zkK0ogTwaK3wOLy2eQQ35WarAhGd");

            //open URL connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //build the output to the stringbuilder
            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }finally{
                urlConnection.disconnect();
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }


}
