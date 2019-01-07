package com.example.junaid.transpire;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class    yoga extends Fragment {


    public yoga() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_yoga, container, false);
        getActivity().setTitle("Yoga");
        Button btnExercise=(Button)v.findViewById(R.id.btn_exer);
        Button btnSetting=(Button)v.findViewById(R.id.btn_set);

        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ListExercise.class);
                startActivity(intent);
            }
        });


        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingsPage.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
