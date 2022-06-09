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

public class RequestsRecyclerViewAdapter extends RecyclerView.Adapter<RequestsRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<RequestModel> requestsModel;

    public RequestsRecyclerViewAdapter(Context context, ArrayList<RequestModel> requestsModel) {
        this.context = context;
        this.requestsModel = requestsModel;
    }

    @NonNull
    @Override
    public RequestsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_request,parent,false);
        return new RequestsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestsRecyclerViewAdapter.MyViewHolder holder, int position) {

        String requestType = requestsModel.get(position).getRequestType();
        String requestStartDate = requestsModel.get(position).getRequestStartDate();
        String requestTime = requestsModel.get(position).getRequestTime();
        boolean image = requestsModel.get(position).isImage();

        holder.requestType.setText(requestType);
        holder.requestStartDate.setText(requestStartDate);
        holder.requestTime.setText(requestTime);

        holder.requestCandidatesAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), CandidatesActivity.class));
            }
        });

        holder.moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), RequestPageActivity.class));
            }
        });

        holder.requestCandidateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CandidateProfileActivity.class);
                intent.putExtra("activity","MainActivity");
                v.getContext().startActivity(intent);
            }
        });

        holder.requestCandidateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CandidateProfileActivity.class);
                intent.putExtra("activity","MainActivity");
                v.getContext().startActivity(intent);
            }
        });

        if(image) {
            holder.requestCandidateImage.setVisibility(View.VISIBLE);
            holder.defaultImage.setVisibility(View.INVISIBLE);
            holder.requestCandidateName.setVisibility(View.VISIBLE);
            holder.requestCandidatesAvailable.setVisibility(View.INVISIBLE);
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

    }

    @Override
    public int getItemCount() {
        return requestsModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView requestStartDate, requestTime, requestType, requestCandidatesAvailable, moreInfo;
        ShapeableImageView requestCandidateImage;
        ShapeableImageView defaultImage;
        TextView requestCandidateName, requestVisitsPerDayText, requestVisitsPerDay;
        TextView requestTimeText, requestEndDateText, requestEndDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            requestStartDate = itemView.findViewById(R.id.requestStartDate);
            requestTime = itemView.findViewById(R.id.requestTime);
            requestType = itemView.findViewById(R.id.requestType);
            requestCandidatesAvailable = itemView.findViewById(R.id.requestCandidatesAvailable);
            moreInfo = itemView.findViewById(R.id.moreInfo);

            requestCandidateImage = itemView.findViewById(R.id.requestCandidateImage);
            defaultImage = itemView.findViewById(R.id.requestPetImage);
            requestCandidateName = itemView.findViewById(R.id.requestCandidateName);
            requestVisitsPerDayText = itemView.findViewById(R.id.requestVisitsPerDayText);
            requestVisitsPerDay = itemView.findViewById(R.id.requestVisitsPerDay);
            requestTimeText = itemView.findViewById(R.id.requestTimeText);
            requestEndDateText = itemView.findViewById(R.id.requestEndDateText);
            requestEndDate = itemView.findViewById(R.id.requestEndDate);
        }
    }
}
