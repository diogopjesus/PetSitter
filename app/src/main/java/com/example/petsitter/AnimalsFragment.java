package com.example.petsitter;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AnimalsFragment extends Fragment {

    ArrayList<AnimalsModel> animalsModel = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animals, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.animalsRecyclerView);
        setUpAnimalsModel();
        AnimalsRecyclerViewAdapter adapter = new AnimalsRecyclerViewAdapter(view.getContext(), animalsModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        // Inflate the layout for this fragment

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AddAnimal.class));
            }
        });

        return view;
    }

    public void setUpAnimalsModel(){
        for (int i = 0; i < 10; i++) {
            animalsModel.add(new AnimalsModel("Tobi"));
        }
    }
}