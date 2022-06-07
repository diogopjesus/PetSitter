package com.example.petsitter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessagesFragment extends Fragment {

    ArrayList<MessageModel> messagesModel = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.chatlistrecycle);
        setUpMessagesModel();
        MessagesRecyclerViewAdapter adapter = new MessagesRecyclerViewAdapter(view.getContext(), messagesModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    public void setUpMessagesModel(){
        for (int i = 0; i < 15; i++) {
            messagesModel.add(new MessageModel("Carlos", "Thank you!"));
        }
    }
}