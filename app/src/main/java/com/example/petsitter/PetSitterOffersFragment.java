package com.example.petsitter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class PetSitterOffersFragment extends Fragment {

    ArrayList<RequestModel> offersModel = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_sitter_offers, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.offersRecyclerView);
        setUpRequestsModel();
        PetSitterRequestsRecyclerViewAdapter adapter = new PetSitterRequestsRecyclerViewAdapter(view.getContext(), offersModel, "Offers");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    public void setUpRequestsModel() {
        if (offersModel.size() < 2) {
            offersModel.add(new RequestModel("Pet Hosting", "10/10/2001", "10:00"));
            offersModel.add(new RequestModel("Dog Walking", "10/10/2001", "10:00"));
        }
    }
}