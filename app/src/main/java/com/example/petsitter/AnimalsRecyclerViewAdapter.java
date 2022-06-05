package com.example.petsitter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AnimalsRecyclerViewAdapter extends RecyclerView.Adapter<AnimalsRecyclerViewAdapter.MyViewHolder>{
    Context context;
    ArrayList<AnimalsModel> animalsModel;

    public AnimalsRecyclerViewAdapter(Context context, ArrayList<AnimalsModel> myAnimalsModel) {
        this.context = context;
        this.animalsModel = myAnimalsModel;
    }

    @NonNull
    @Override
    public AnimalsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_animal,parent,false);
        return new AnimalsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalsRecyclerViewAdapter.MyViewHolder holder, int position) {

        String animalName = animalsModel.get(position).getAnimalName();

        holder.animalName.setText(animalName);
        holder.animalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), AnimalPageActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return animalsModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView animalName;
        CardView animalCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            animalName = itemView.findViewById(R.id.animalName);
            animalCard = itemView.findViewById(R.id.animalCard);
        }
    }
}
