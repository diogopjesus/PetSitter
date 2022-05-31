package com.example.petsitter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RequestsFragment extends Fragment {
    ArrayList<RequestsPetOwnerModel> requestsPetOwnerModel = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requests, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.myRecyclerView);
        setUpRequestsPetOwnerModels();
        RecyclerViewPetOwnerRequestsAdapter adapter = new RecyclerViewPetOwnerRequestsAdapter(view.getContext(), requestsPetOwnerModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        // Inflate the layout for this fragment
        return view;
    }

    public void setUpRequestsPetOwnerModels(){
        // ir buscar ao ficheiro json
    }
}