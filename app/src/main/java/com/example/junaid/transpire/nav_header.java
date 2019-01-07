package com.example.junaid.transpire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class nav_header extends AppCompatActivity {
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);

        sessionManager=new SessionManager(this);
        sessionManager.checkLogin();


        TextView name= findViewById(R.id.name);
        TextView email= findViewById(R.id.email);
       // Button button=findViewById(R.id.btn_logout);

        HashMap<String,String> user= sessionManager.getUserDetails();
       String mName=user.get(sessionManager.NAME);
       String mEmail=user.get(sessionManager.EMAIL);


        name.setText(mName);
        email.setText(mEmail);




    }
}
