package com.example.petsitter;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
        return view;
    }

    public void setUpRequestsPetOwnerModels(){
        try {
            ArrayList<JSONObject> requestsDatabase = DB.getRequests();
            int type, animalId;
            String serviceType, nOfCandidates, temp, animalName = "";
            LocalDate DateNow = LocalDate.now(), requestDate;
            ArrayList<JSONObject> animalsDatabase = DB.getAnimals();
            assert requestsDatabase != null;
            for (JSONObject request : requestsDatabase) {
                type = ((Long) request.get("type")).intValue();
                if (type == 1) {
                    serviceType = "Pet Sitting";
                } else if (type == 2) {
                    serviceType = "Pet Hosting";
                } else serviceType = "Dog Walking";
                animalId = ((Long) request.get("pet_id")).intValue();
                assert animalsDatabase != null;
                for (JSONObject animalRequest : animalsDatabase) {
                    if (animalId == ((Long) animalRequest.get("animal_id")).intValue()) {
                        animalName = (String) animalRequest.get("name");
                        break;
                    }
                }
                temp = (String) request.get("date");
                requestDate = LocalDate.parse(temp);
                nOfCandidates = Integer.toString(0);
                requestsPetOwnerModel.add(new RequestsPetOwnerModel(serviceType, animalName,
                        Integer.toString(Period.between(DateNow, requestDate).getDays()), nOfCandidates));
            }
        } catch (Exception e){
            Log.d("TAG", e.toString());
        }

    }
}