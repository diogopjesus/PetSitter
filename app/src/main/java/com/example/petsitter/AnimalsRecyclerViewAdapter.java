package com.example.petsitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        View view = inflater.inflate(R.layout.fragment_animals_recyclerview,parent,false);
        return new AnimalsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalsRecyclerViewAdapter.MyViewHolder holder, int position) {

        String animalName = animalsModel.get(position).getAnimalName();

        holder.animalName.setText(animalName);
    }

    @Override
    public int getItemCount() {
        return animalsModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView animalName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            animalName = itemView.findViewById(R.id.animalName);
        }
    }
}
