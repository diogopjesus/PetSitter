package com.example.petsitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewPetOwnerRequestsAdapter extends RecyclerView.Adapter<RecyclerViewPetOwnerRequestsAdapter.MyViewHolder>{
    Context context;
    ArrayList<RequestsPetOwnerModel> requestsPetOwnerModel;

    public RecyclerViewPetOwnerRequestsAdapter(Context context, ArrayList<RequestsPetOwnerModel> requestsPetOwnerModel) {
        this.context = context;
        this.requestsPetOwnerModel = requestsPetOwnerModel;
    }

    @NonNull
    @Override
    public RecyclerViewPetOwnerRequestsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_requests_petowner,parent,false);
        return new RecyclerViewPetOwnerRequestsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewPetOwnerRequestsAdapter.MyViewHolder holder, int position) {
        String serviceType = requestsPetOwnerModel.get(position).getServiceType();
        String animalName = requestsPetOwnerModel.get(position).getAnimalName();
        String time = requestsPetOwnerModel.get(position).getTime();
        String nOfCandidates = requestsPetOwnerModel.get(position).getnOfCandidates();

        holder.serviceType.setText(serviceType);
        holder.animalName.setText(animalName);
        holder.time.setText("Time Left: " + time + " days");
        holder.numberOfCandidates.setText("Number of Candidates: " +nOfCandidates);
    }

    @Override
    public int getItemCount() {
        return requestsPetOwnerModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView serviceType, animalName, time, numberOfCandidates;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            serviceType = itemView.findViewById(R.id.serviceType);
            animalName = itemView.findViewById(R.id.animalName);
            time = itemView.findViewById(R.id.time);
            numberOfCandidates = itemView.findViewById(R.id.numberOfCandidates);
        }
    }
}
