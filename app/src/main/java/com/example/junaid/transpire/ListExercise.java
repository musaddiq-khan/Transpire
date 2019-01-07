package com.example.junaid.transpire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.junaid.transpire.Adapter.RecyclerViewAdapter;
import com.example.junaid.transpire.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercise extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercise);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initData();
        recyclerView = (RecyclerView) findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exerciseList, this);
        // layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        exerciseList.add(new Exercise(R.drawable.crow, "crow pose"));
        exerciseList.add(new Exercise(R.drawable.butterfly, "butterfly pose"));
        exerciseList.add(new Exercise(R.drawable.pigeon, "half pigeon"));
        exerciseList.add(new Exercise(R.drawable.chair, "chair pose"));
        exerciseList.add(new Exercise(R.drawable.wheel, "wheel pose"));
        exerciseList.add(new Exercise(R.drawable.hero_pose, "hero pose"));
        exerciseList.add(new Exercise(R.drawable.downward_facing, "downwardfaing pose"));
        exerciseList.add(new Exercise(R.drawable.four_limbed, "four limbed pose"));
        exerciseList.add(new Exercise(R.drawable.bridge, "bridge pose"));
        exerciseList.add(new Exercise(R.drawable.bow, "bow pose"));
        exerciseList.add(new Exercise(R.drawable.boat, "boat pose"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}