package com.example.junaid.transpire;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import com.example.junaid.transpire.MainActivity;
import com.example.junaid.transpire.R;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide status bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        new BackgroundTask().execute();
    }
    class BackgroundTask extends AsyncTask
    {
        Intent intent;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            intent = new Intent(splash.this,MainActivity.class);

        }

        @Override
        protected Object doInBackground(Object[] params)
        {

            /*  Use this method to load background
             * data that your app needs. */

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o)
        {
        //   super.onPostExecute(o);
//            Pass your loaded data here using Intent

//            intent.putExtra("data_key", "");
            startActivity(intent);
            finish();
        }
    }
}
