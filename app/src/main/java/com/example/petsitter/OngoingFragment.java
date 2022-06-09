package com.example.petsitter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OngoingFragment extends Fragment {

    ArrayList<RequestModel> requestsModel = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ongoing, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.ongoingRecyclerView);
        setUpRequestsModel();
        RequestsRecyclerViewAdapter adapter = new RequestsRecyclerViewAdapter(view.getContext(), requestsModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    public void setUpRequestsModel(){
        for (int i = 0; i < 1; i++) {
            requestsModel.add(new RequestModel("Pet Sitting", "10/10/2001", "10:25", true));
        }
    }
}