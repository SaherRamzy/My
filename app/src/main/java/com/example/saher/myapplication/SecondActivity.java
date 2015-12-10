package com.example.saher.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.google.gson.Gson;

public class SecondActivity extends ActionBarActivity {
    Gson gson;
    ServiceHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                gson = new Gson();
                handler = new ServiceHandler();
                String jsondata =handler.makeServiceCall("\n" +
                        "https://api.github.com/users/3bdoelnaggar", ServiceHandler.GET, null);
                JsonUser jsonUserr = new JsonUser();
                jsonUserr =gson.fromJson(jsondata,JsonUser.class);

                Toast.makeText(this,jsonUserr.id(),Toast.LENGTH_SHORT).show();


                return jsondata;
            }

            @Override
            protected void onPostExecute(Void Void) {
                super.onPostExecute(aVoid);

            }
        }.execute();
    }

}
