package com.example.junaid.transpire;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;


import java.util.HashMap;

public class home_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        sessionManager=new SessionManager(this);
        sessionManager.checkLogin();

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //NavigationView navigationView = (NavigationView) v.findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        final TextView navUsername = (TextView) headerView.findViewById(R.id.d_name);
        final TextView navEmail = (TextView) headerView.findViewById(R.id.d_email);






        HashMap<String,String> user= sessionManager.getUserDetails();
        String mName=user.get(sessionManager.NAME);
        String mEmail=user.get(sessionManager.EMAIL);


        navUsername.setText(mName);
        navEmail.setText(mEmail);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Profile_fragment()).commit();
        navigationView.setCheckedItem(R.id.profile);
        }

    }

    @SuppressLint("CommitTransaction")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Profile_fragment()).commit();
                break;

            case R.id.bmi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Bmi_fragment()).commit();
                break;
            case R.id.yoga:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new yoga()).commit();
                break;

            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Step_set()).commit();
                break;

            case R.id.gym:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new weighTraining()).commit();
                break;

            case R.id.logout:
                showAlertlogout();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            showAlertDialog();
        }
    }

    private void showAlertDialog()
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyDialogTheme);
        builder.setCancelable(false);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to leave?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showAlertlogout()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyDialogTheme);
        builder.setCancelable(false);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SaveSharedPreference.setLoggedIn(getApplicationContext().getApplicationContext(), false);
                sessionManager.logout();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }




}
