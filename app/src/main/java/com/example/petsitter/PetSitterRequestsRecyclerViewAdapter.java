package com.example.petsitter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class PetSitterRequestsRecyclerViewAdapter extends RecyclerView.Adapter<PetSitterRequestsRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<RequestModel> requestsModel;
    String caller = "Requests";

    public PetSitterRequestsRecyclerViewAdapter(Context context, ArrayList<RequestModel> requestsModel, String caller) {
        this.context = context;
        this.requestsModel = requestsModel;
        this.caller = caller;
    }

    public PetSitterRequestsRecyclerViewAdapter(Context context, ArrayList<RequestModel> requestsModel) {
        this.context = context;
        this.requestsModel = requestsModel;
    }

    @NonNull
    @Override
    public PetSitterRequestsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.petsitter_card_request,parent,false);
        return new PetSitterRequestsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetSitterRequestsRecyclerViewAdapter.MyViewHolder holder, int position) {

        String requestType = requestsModel.get(position).getRequestType();
        String requestStartDate = requestsModel.get(position).getRequestStartDate();
        String requestTime = requestsModel.get(position).getRequestTime();

        holder.requestType.setText(requestType);
        holder.requestStartDate.setText(requestStartDate);
        holder.requestTime.setText(requestTime);

        holder.moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), RequestPageActivity.class));
            }
        });

        if(caller.equals("Applied")) {
            holder.removeCandidature.setVisibility(View.VISIBLE);
        }

        if(caller.equals("Offers")) {
            holder.candidateToOffer.setVisibility(View.VISIBLE);
        }

        if(requestType.equals("Pet Hosting")) {
            holder.requestVisitsPerDayText.setVisibility(View.INVISIBLE);
            holder.requestVisitsPerDay.setVisibility(View.INVISIBLE);
        } else if (requestType.equals("Dog Walking")) {
            holder.requestVisitsPerDayText.setVisibility(View.INVISIBLE);
            holder.requestVisitsPerDay.setVisibility(View.INVISIBLE);
            holder.requestTimeText.setVisibility(View.VISIBLE);
            holder.requestTime.setVisibility(View.VISIBLE);
            holder.requestEndDate.setVisibility(View.INVISIBLE);
            holder.requestEndDateText.setVisibility(View.INVISIBLE);
        }

        holder.animalName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), AnimalPageActivity.class));
            }
        });

        holder.requestPetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), AnimalPageActivity.class));
            }
        });

        holder.requestPetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), AnimalPageActivity.class));
            }
        });

        holder.requestAnimalName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), PetOwnerProfileActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return requestsModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView requestStartDate, requestTime, requestType,moreInfo,candidateToOffer;
        TextView requestVisitsPerDayText, requestVisitsPerDay, requestAnimalName, removeCandidature;
        TextView requestTimeText, requestEndDateText, requestEndDate, animalName;
        ShapeableImageView requestPetImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            requestStartDate = itemView.findViewById(R.id.requestStartDate);
            requestTime = itemView.findViewById(R.id.requestTime);
            requestType = itemView.findViewById(R.id.requestType);
            moreInfo = itemView.findViewById(R.id.moreInfo);

            requestVisitsPerDayText = itemView.findViewById(R.id.requestVisitsPerDayText);
            requestVisitsPerDay = itemView.findViewById(R.id.requestVisitsPerDay);
            requestTimeText = itemView.findViewById(R.id.requestTimeText);
            requestEndDateText = itemView.findViewById(R.id.requestEndDateText);
            requestEndDate = itemView.findViewById(R.id.requestEndDate);

            animalName = itemView.findViewById(R.id.animalName);
            requestPetImage = itemView.findViewById(R.id.requestPetImage);

            requestAnimalName = itemView.findViewById(R.id.requestAnimalName);

            candidateToOffer = itemView.findViewById(R.id.candidateToOffer);

            removeCandidature = itemView.findViewById(R.id.removeCandidature);
        }
    }
}

