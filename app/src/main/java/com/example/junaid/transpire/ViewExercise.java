package com.example.junaid.transpire;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junaid.transpire.Database.YogaDB;
import com.example.junaid.transpire.Utils.Common;

public class ViewExercise extends AppCompatActivity {

    int image_id;
    String name;
    Button btnStart;
    YogaDB yogaDB;


    TextView timer,title1;
    ImageView detail_image;

    boolean isRunning=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

       yogaDB=new YogaDB(this);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        timer=(TextView)findViewById(R.id.timer);
        title1=(TextView)findViewById(R.id.title_name);
        btnStart=(Button)findViewById(R.id.btnStart);
        detail_image=(ImageView)findViewById(R.id.detaile_image);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRunning)
                {
                    btnStart.setText("DONE");

                    int TimeLimit=0;
                    if(yogaDB.getSettingMode()==0) {
                        TimeLimit = Common.TIME_LIMIT_EASY;
                        Toast.makeText(ViewExercise.this, "easy!!!", Toast.LENGTH_SHORT).show();
                    }
                        else if(yogaDB.getSettingMode()==1) {
                        TimeLimit = Common.TIME_LIMIT_MEDIUM;
                        Toast.makeText(ViewExercise.this, "med!!!", Toast.LENGTH_SHORT).show();
                    }
                        else if(yogaDB.getSettingMode()==2) {
                        TimeLimit = Common.TIME_LIMIT_HARD;
                        Toast.makeText(ViewExercise.this, "hard!!!", Toast.LENGTH_SHORT).show();
                    }


                    new CountDownTimer(TimeLimit,1000)
                    {

                        @Override
                        public void onTick(long l) {

                            timer.setText(""+l/1000);
                        }

                        @Override
                        public void onFinish() {

                            Toast.makeText(ViewExercise.this,"finish!!!",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }
                else
                {
                    Toast.makeText(ViewExercise.this,"finish!!!",Toast.LENGTH_SHORT).show();
                    finish();
                }

                isRunning=!isRunning;
            }
        });

        timer.setText("");

        if(getIntent()!=null)
        {
            image_id=getIntent().getIntExtra("image_id",-1);
            name=getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title1.setText(name);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
