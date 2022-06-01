package com.example.petsitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewMyAnimalsAdapter extends RecyclerView.Adapter<RecyclerViewMyAnimalsAdapter.MyViewHolder>{
    Context context;
    ArrayList<MyAnimalsModel> myAnimalsModel;

    public RecyclerViewMyAnimalsAdapter(Context context, ArrayList<MyAnimalsModel> myAnimalsModel) {
        this.context = context;
        this.myAnimalsModel = myAnimalsModel;
    }

    @NonNull
    @Override
    public RecyclerViewMyAnimalsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_myanimals,parent,false);
        return new RecyclerViewMyAnimalsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMyAnimalsAdapter.MyViewHolder holder, int position) {

        String animalName = myAnimalsModel.get(position).getAnimalName();
        String animalType = myAnimalsModel.get(position).getAnimalType();

        holder.animalType.setText(animalType);
        holder.animalName.setText(animalName);
    }

    @Override
    public int getItemCount() {
        return myAnimalsModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView animalName, animalType;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            animalName = itemView.findViewById(R.id.animalName);
            animalType = itemView.findViewById(R.id.animalType);
        }
    }
}
