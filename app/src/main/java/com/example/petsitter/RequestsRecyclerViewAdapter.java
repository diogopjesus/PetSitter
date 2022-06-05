package com.example.petsitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        holder.requestType.setText(requestType);
        holder.requestStartDate.setText(requestStartDate);
        holder.requestTime.setText(requestTime);
    }

    @Override
    public int getItemCount() {
        return requestsModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView requestStartDate, requestTime, requestType;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            requestStartDate = itemView.findViewById(R.id.requestStartDate);
            requestTime = itemView.findViewById(R.id.requestTime);
            requestType = itemView.findViewById(R.id.requestType);
        }
    }
}
