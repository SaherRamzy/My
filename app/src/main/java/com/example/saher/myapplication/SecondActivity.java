package com.example.saher.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class SecondActivity extends Activity {
    Gson gson;
    ServiceHandler handler;
    ProgressDialog dialog;
    Restaurant__ json;
    ArrayList<Restaurant__> lstcontact;
    String jsondata;
    ListView lv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv_show = (ListView) findViewById(R.id.lv_show);

        new AsyncTask<Void, Void, ArrayList<Restaurant__>>(){
            @Override
            protected void onPreExecute()
            {
                dialog = new ProgressDialog(SecondActivity.this);
                dialog.setIcon(R.drawable.zomato);
                dialog.setTitle("");
                dialog.setMessage("PLEASE WAIT");
                dialog.show();
            }
            @Override
            protected ArrayList<Restaurant__> doInBackground(Void... voids) {
                gson = new Gson();
                handler = new ServiceHandler();
                lstcontact=new ArrayList<Restaurant__>();
                jsondata = handler.makeServiceCall("https://developers.zomato.com/api/v2.1/search?entity_type=city/", ServiceHandler.GET, null);
                try {
                    json = gson.fromJson(jsondata, Restaurant__.class);
                    Restaurant restaurant = new Restaurant();
                    for (int i=0;i<restaurant.getResultsFound();i++)
                        lstcontact.add(json);


                }
                catch (Exception ex){}
                return lstcontact;
            }
            @Override
            protected void onPostExecute(ArrayList<Restaurant__> lstcontacts ) {
                dialog.dismiss();
                ResturantAdapter adapter = new ResturantAdapter(SecondActivity.this,lstcontacts);
                lv_show.setAdapter(adapter);
            }
        }.execute();
    }

}
