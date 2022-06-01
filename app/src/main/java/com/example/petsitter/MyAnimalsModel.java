package com.example.petsitter;

public class MyAnimalsModel {
    String animalName, animalType;

    public MyAnimalsModel(String animalName, String typeName) {
        this.animalName = animalName;
        this.animalType = typeName;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalType() {
        return animalType;
    }
}
