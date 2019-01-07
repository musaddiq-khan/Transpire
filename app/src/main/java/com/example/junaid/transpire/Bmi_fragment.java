package com.example.junaid.transpire;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junaid.transpire.Database.BmiDB;



/**
 * A simple {@link Fragment} subclass.
 */
public class Bmi_fragment extends Fragment   {
    EditText heightbmi,weightbmi;
    Button btnbmi;
    TextView bmiresulttext;
    BmiDB bmiDB;

    public Bmi_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_bmi, container, false);
        getActivity().setTitle("Bmi calculator");
        heightbmi=(EditText) v.findViewById(R.id.height_bmi);
        weightbmi=(EditText) v.findViewById(R.id.weight_bmi);
        btnbmi=v.findViewById(R.id.btn_bmi);
        bmiresulttext=(TextView) v.findViewById(R.id.bmi_result);

        bmiDB=new BmiDB(getActivity());



        btnbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height_str=heightbmi.getText().toString();
                String weight_str=weightbmi.getText().toString();

                if(height_str !=null && !"".equals(height_str) && weight_str !=null && !"".equals(weight_str))
                {
                    float bmi_height=Float.parseFloat(height_str)/100;
                    float bmi_weight=Float.parseFloat(weight_str);

                    float bmi=bmi_weight/(bmi_height*bmi_height);
                    bmiDB.insertBmi(bmi);
                    displayBMI(bmi);
                }
            }
        });

        return v;
    }

    private void displayBMI(float bmi)
    {
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
        bmiLabel=bmi+ "\n\n" +bmiLabel;
        bmiresulttext.setText(bmiLabel);
    }


}
