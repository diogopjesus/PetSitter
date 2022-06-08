package com.example.petsitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CandidatesRecyclerViewAdapter extends RecyclerView.Adapter<CandidatesRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<CandidateModel> candidateModel;

    public CandidatesRecyclerViewAdapter(Context context, ArrayList<CandidateModel> candidateModel) {
        this.context = context;
        this.candidateModel = candidateModel;
    }

    @NonNull
    @Override
    public CandidatesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_candidate, parent, false);
        return new CandidatesRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandidatesRecyclerViewAdapter.MyViewHolder holder, int position) {

        String candidateName = candidateModel.get(position).getName();

        holder.candidateName.setText(candidateName);
    }

    @Override
    public int getItemCount() {
        return candidateModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView candidateName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            candidateName = itemView.findViewById(R.id.candidateName);
        }
    }
}