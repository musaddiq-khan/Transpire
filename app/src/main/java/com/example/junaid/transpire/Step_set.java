package com.example.junaid.transpire;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.junaid.transpire.Database.StepDB;



public class Step_set extends android.support.v4.app.Fragment {
    StepDB stepDB;
    EditText get_step;
    Button save_step;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_step_set, container, false);
        getActivity().setTitle("Settings");
        get_step=(EditText)v.findViewById(R.id.edit_step);
        save_step=(Button)v.findViewById(R.id.step_save);
        stepDB=new StepDB(getActivity());

        save_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String save_foot=get_step.getText().toString().trim();
                final int s=Integer.parseInt(save_foot);
                stepDB.insertSteps(s);
                Toast.makeText(getActivity(),"saved="+s+"",Toast.LENGTH_SHORT).show();
                Profile_fragment f=new Profile_fragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, f);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });





        return v;

    }

    /*private void saveStep(String save_foot) {
        int s= Integer.parseInt(save_foot);
        yogaDB.insertSteps(s);
    }*/


}
