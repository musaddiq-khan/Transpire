package com.example.junaid.transpire.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junaid.transpire.Interface.ItemClickListener;
import com.example.junaid.transpire.Model.Exercise;
import com.example.junaid.transpire.R;
import com.example.junaid.transpire.ViewExercise;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public ImageView image;
    public TextView text;

    private ItemClickListener itemClickListener;


    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        image=(ImageView)itemView.findViewById(R.id.ex_img);
        text=(TextView)itemView.findViewById(R.id.ex_name);

        itemView.setOnClickListener(this);
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.OnClick(view,getAdapterPosition());
    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Exercise> exercisesList;
    private Context context;

    public RecyclerViewAdapter(List<Exercise> exercisesList, Context context) {
        this.exercisesList = exercisesList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemview=inflater.inflate(R.layout.item_exercise,parent,false);

        return new RecyclerViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.image.setImageResource(exercisesList.get(position).getImage_id());
        holder.text.setText(exercisesList.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                Intent intent=new Intent(context,ViewExercise.class);
                intent.putExtra("image_id",exercisesList.get(position).getImage_id());
                intent.putExtra("name",exercisesList.get(position).getName());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }
}
