package com.example.petsitter;

public class RequestsPetOwnerModel {
    String serviceType, animalName, time, nOfCandidates;


    public RequestsPetOwnerModel(String serviceType, String animalName, String time, String nOfCandidates) {
        this.serviceType = serviceType;
        this.animalName = animalName;
        this.time = time;
        this.nOfCandidates = nOfCandidates;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getTime() {
        return time;
    }

    public String getnOfCandidates() {
        return nOfCandidates;
    }
}
