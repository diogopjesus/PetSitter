package com.example.petsitter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PetSitterAppliedFragment extends Fragment {

    ArrayList<RequestModel> upcomingModel = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_sitter_applied, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.appliedRecyclerView);
        setUpRequestsModel();
        PetSitterRequestsRecyclerViewAdapter adapter = new PetSitterRequestsRecyclerViewAdapter(view.getContext(), upcomingModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    public void setUpRequestsModel(){
        for (int i = 0; i < 3; i++) {
            upcomingModel.add(new RequestModel("Pet Sitting", "10/10/2001", "10:25"));
        }
    }
}