package com.example.junaid.transpire;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class weighTraining extends android.support.v4.app.Fragment {
    Button btn_gym;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_weigh_training, container, false);
        getActivity().setTitle("weight Training");
        btn_gym=(Button)v.findViewById(R.id.btn_weights);
        btn_gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(getActivity(),list_days.class);
                    startActivity(intent);
            }
        });
        return v;
    }

}
