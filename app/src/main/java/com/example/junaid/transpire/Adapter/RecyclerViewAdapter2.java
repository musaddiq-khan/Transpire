package com.example.junaid.transpire.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junaid.transpire.Interface.ItemClickListener;
import com.example.junaid.transpire.Model.Days;
import com.example.junaid.transpire.Model.Exercise;
import com.example.junaid.transpire.R;
import com.example.junaid.transpire.ViewExercise;

import java.util.List;

class RecyclerViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ImageView image2;
    public TextView text2;

    private ItemClickListener itemClickListener;


    public RecyclerViewHolder2(@NonNull View itemView) {
        super(itemView);
        image2=(ImageView)itemView.findViewById(R.id.ex_img1);
        text2= (TextView) itemView.findViewById(R.id.ex_name1);

        itemView.setOnClickListener(this);

    }
    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v)
    {
        itemClickListener.OnClick(v,getAdapterPosition());
    }
}
public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewHolder2> {

    private List<Days> exercisesList1;
    private Context context1;


    public RecyclerViewAdapter2(List<Days> exercisesList1, Context context1) {
        this.exercisesList1 = exercisesList1;
        this.context1 = context1;
    }


    @NonNull
    @Override
    public RecyclerViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemview=inflater.inflate(R.layout.item_days,parent,false);

        return new RecyclerViewHolder2(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder2 holder2, int i) {
        holder2.image2.setImageResource(exercisesList1.get(i).getImage_id());
        holder2.text2.setText(exercisesList1.get(i).getName());

        holder2.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
               // Toast.makeText(context1,"click to"+exercisesList1.get(position),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context1,ViewExercise.class);
                intent.putExtra("image_id",exercisesList1.get(position).getImage_id());
                intent.putExtra("name",exercisesList1.get(position).getName());
                context1.startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return exercisesList1.size();
    }
}
