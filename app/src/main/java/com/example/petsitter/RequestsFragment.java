package com.example.petsitter;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
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
        ArrayList<JSONObject> requestsDatabase = DB.getRequests();
        int type;
        String serviceType,animalName, time, nOfCandidates;
        LocalDate DateNow = LocalDate.now(), requestDate;
        for(JSONObject request: requestsDatabase){
            type = (Integer) request.get("type");
            if(type == 1){ serviceType = "Pet Sitting"; }
            else if(type == 2){ serviceType = "Pet Hosting"; }
            else serviceType = "Dog Walking";
            animalName = (String) request.get("name");
            requestDate = (LocalDate) request.get("date");
            nOfCandidates = Integer.toString(0);
            requestsPetOwnerModel.add(new RequestsPetOwnerModel(serviceType,animalName,
                    Integer.toString(Period.between(DateNow, requestDate).getDays()),nOfCandidates));
        }
    }
}