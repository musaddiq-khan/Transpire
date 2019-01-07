package com.example.junaid.transpire;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junaid.transpire.Database.BmiDB;
import com.example.junaid.transpire.Database.StepDB;
import com.example.junaid.transpire.SaveSharedPreference;

public class Profile_fragment extends Fragment implements SensorEventListener{
SessionManager sessionManager;
SensorManager sensorManager;
boolean running =true;
TextView steps,targt,bmiCount,norm,targt_comp;
StepDB stepDB;
BmiDB bmiDB;
String tr;
int trr,stt;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment,container,false);
        getActivity().setTitle("Profile");
        sessionManager=new SessionManager(getActivity());
       sessionManager.checkLogin();


        TextView name= v.findViewById(R.id.name_p);


        //steps id
         steps=v.findViewById(R.id.steps);
         targt=v.findViewById(R.id.target);
         stepDB=new StepDB(getActivity());
        // targt.setText(String.valueOf(stepDB.getSteps()));
            tr=(String.valueOf(stepDB.getSteps()));
            trr=stepDB.getSteps();
            targt.setText(tr);

         //bmi
        bmiCount=(TextView)v.findViewById(R.id.bmiCount);
        norm=(TextView)v.findViewById(R.id.norms);
        bmiDB=new BmiDB(getActivity());
        float disBMi=(float)bmiDB.getBmi();
        bmiCount.setText(String.valueOf(disBMi));

        String label=diplayBMI(disBMi);
        norm.setText(label);


        //names
       HashMap<String,String> user= sessionManager.getUserDetails();
       String mName=user.get(sessionManager.NAME);
       name.setText(mName);


       targt_comp=(TextView)v.findViewById(R.id.comp_target);
       //sensormanger

        sensorManager =(SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);


        return v;



    }

    private String diplayBMI(float bmi) {
        String bmiLabel="";
        if(Float.compare(bmi,15f)<=0){
            bmiLabel=getString(R.string.very_severly_underweight);
        }else if (Float.compare(bmi,15f)>0 && Float.compare(bmi,16f)<=0){
            bmiLabel=getString(R.string.severly_underweight);
        }else if (Float.compare(bmi,16f)>0 && Float.compare(bmi,18.5f)<=0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi,18.5f)>0 && Float.compare(bmi,25f)<0) {
            bmiLabel = getString(R.string.normal);
        }else if (Float.compare(bmi,25f)>0 && Float.compare(bmi,30f)<0) {
            bmiLabel = getString(R.string.overweight);
        }else if (Float.compare(bmi,30f)>0 && Float.compare(bmi,35f)<0) {
            bmiLabel = getString(R.string.obese_class_i);
        }else if (Float.compare(bmi,35f)>0 && Float.compare(bmi,40f)<0) {
            bmiLabel = getString(R.string.obese_class_ii);
        }else{
            bmiLabel=getString(R.string.obese_class_iii);
        }

        return bmiLabel;
    }

    @Override
    public void onResume() {
        super.onResume();
        running=true;
        Sensor countSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null)
        {
            sensorManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);

        }else{
            Toast.makeText(getActivity(),"Sensor not fouund",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        running=false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running)
        {
            steps.setText(String.valueOf(event.values[0]));
            stt= (int) event.values[0];
            if(stt >= trr)
            {
                targt_comp.setText("Target Reached");
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}

