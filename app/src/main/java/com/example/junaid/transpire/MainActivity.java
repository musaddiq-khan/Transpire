package com.example.junaid.transpire;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toolbar;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.app.ActionBar;

import com.example.junaid.transpire.SaveSharedPreference;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    Button button1,button;
    RelativeLayout mainpage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1= findViewById(R.id.button1);
        button= findViewById(R.id.button);
        mainpage= findViewById(R.id.main_page);

        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            Intent intent=new Intent(MainActivity.this,home_activity.class);
            startActivity(intent);
        } else {
            mainpage.setVisibility(View.VISIBLE);
        }


    }
    @Override
     public void onBackPressed()
    {
        showAlertDialog();
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
                finish();
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



        @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button1:
                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                startActivity(intent);
                break;

            case R.id.button:
                Intent intent1 = new Intent(MainActivity.this, signup.class);
                startActivity(intent1);
                break;

        }
    }
}
