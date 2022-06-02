package com.example.petsitter;

import android.content.Context;
import android.util.Log;

import java.io.*;
import java.util.*;
import java.time.*;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class DB implements Serializable  {

    // Type:
    //  1 -> pet sitting
    //  2 -> pet hosting
    //  3 -> dog walking
    // Status:
    //  0 -> upcoming and no candidate chosen
    //  1 -> upcoming and candidate chosen
    //  2 -> ongoing
    //  3 -> ended
    public static int addPetSittingEntry(String pet_owner, int pet_id, String details, LocalDate from, LocalDate until, int numberOfVisitsPerDay, String address) {
        // TODO: assertions
        // TODO: verify entry validity

        JSONObject entry = new JSONObject();

        entry.put("type", 1);
        entry.put("owner_contact", pet_owner);
        entry.put("pet_id", pet_id);
        entry.put("details", details);
        entry.put("from", from.toString());
        entry.put("until", until.toString());
        entry.put("numberOfVisitsPerDay", numberOfVisitsPerDay);
        entry.put("address", address);
        entry.put("status", 0);
        entry.put("chosen_candidate", null);

        try {
            Object obj = parser.parse(new FileReader(database));
            JSONObject jsonObject = (JSONObject)obj;
            JSONObject requests = (JSONObject)jsonObject.get("requests");
            int id = requests.size() + 1;
            entry.put("request_id", id);
            requests.put(id, entry);
            jsonObject.put("requests", requests);

            FileWriter file = new FileWriter(database);
            file.write(jsonObject.toString());
            file.close();

            return id;

        } catch(Exception e) {
            // TODO: verify
            Log.d("Ola", e.toString());
        }
        return -1;
    }

    public static int addPetHostingEntry(String pet_owner, int pet_id, String details, LocalDate from, LocalDate until) {
        JSONObject entry = new JSONObject();
        entry.put("type", 2);
        entry.put("owner_contact", pet_owner);
        entry.put("pet_id", pet_id);
        entry.put("details", details);
        entry.put("from", from.toString());
        entry.put("until", until.toString());
        entry.put("status", 0);
        entry.put("chosen_candidate", null);

        try {
            Object obj = parser.parse(new FileReader(database));
            JSONObject jsonObject = (JSONObject)obj;
            JSONObject requests = (JSONObject)jsonObject.get("requests");

            int id = requests.size() + 1;
            entry.put("request_id", id);
            requests.put(id, entry);
            jsonObject.put("requests", requests);

            FileWriter file = new FileWriter(database);
            file.write(jsonObject.toString());
            file.close();

            return id;

        } catch(Exception e) {
            // TODO: verify
            System.out.println(e);
        }
        return -1;
    }

    public static int addDogWalkingEntry(String pet_owner, int pet_id, String details, LocalDate date, LocalTime time, String regularity) {
        JSONObject entry = new JSONObject();
        entry.put("owner_contact", pet_owner);
        entry.put("type", 3);
        entry.put("pet_id", pet_id);
        entry.put("details", details);
        entry.put("date", date.toString());
        entry.put("time", time.toString());
        entry.put("regularity", regularity);
        entry.put("status", 0);
        entry.put("chosen_candidate", null);

        try {
            Object obj = parser.parse(new FileReader(database));
            JSONObject jsonObject = (JSONObject)obj;
            JSONObject requests = (JSONObject)jsonObject.get("requests");

            int id = requests.size() + 1;
            entry.put("request_id", id);
            requests.put(id, entry);
            jsonObject.put("requests", requests);

            FileWriter file = new FileWriter(database);
            file.write(jsonObject.toString());
            file.close();

            return id;

        } catch(Exception e) {
            // TODO: verify
            System.out.println(e);
        }
        return -1;
    }

    public static int addAnimal(String type, String sex, String breed, String name, double size, String description, ArrayList<String> images) {
        JSONObject entry = new JSONObject();
        entry.put("type", type);
        entry.put("sex", sex);
        entry.put("breed", breed);
        entry.put("name", name);
        entry.put("size", size);
        entry.put("description", description);
        entry.put("images", images);

        try {
            Object obj = parser.parse(new FileReader(database));
            JSONObject jsonObject = (JSONObject)obj;
            JSONObject animals = (JSONObject)jsonObject.get("animals");

            int id = animals.size() + 1;
            entry.put("animal_id", id);
            animals.put(id, entry);
            jsonObject.put("animals", animals);
            FileWriter file = new FileWriter(database);
            file.write(jsonObject.toString());
            file.close();
            return id;

        } catch(Exception e) {
            // TODO: verify
            Log.d("Error", e.toString());
        }
        return -1;
    }

    public static ArrayList<JSONObject> getAnimals() {
        try {
            Object obj = parser.parse(new FileReader(database));
            JSONObject jsonObject = (JSONObject)obj;
            JSONObject requests = (JSONObject)jsonObject.get("animals");

            ArrayList<JSONObject> list = new ArrayList<>();
            for(Object animal: requests.values()) {
                list.add((JSONObject)animal);
            }
            return list;

        } catch(Exception e) {
            // TODO: verify
            System.out.println(e);
        }
        return null;
    }

    public static ArrayList<JSONObject> getRequests() {
        try {
            Object obj = parser.parse(new FileReader(database));
            JSONObject jsonObject = (JSONObject)obj;
            JSONObject requests = (JSONObject)jsonObject.get("requests");

            ArrayList<JSONObject> list = new ArrayList<>();
            for(Object request: requests.values()) {
                list.add((JSONObject)request);
            }
            return list;

        } catch(Exception e) {
            // TODO: verify
            System.out.println(e);
        }
        return null;
    }

    public static File database;

    private static final JSONParser parser = new JSONParser();
}
