package com.example.petsitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<MessageModel> messagesModel;

    public MessagesRecyclerViewAdapter(Context context, ArrayList<MessageModel> messagesModel) {
        this.context = context;
        this.messagesModel = messagesModel;
    }

    @NonNull
    @Override
    public MessagesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_message,parent,false);
        return new MessagesRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesRecyclerViewAdapter.MyViewHolder holder, int position) {

        String userName = messagesModel.get(position).getUserName();
        String lastMessage = messagesModel.get(position).getLastMessage();

        holder.userName.setText(userName);
        holder.lastMessage.setText(lastMessage);
    }

    @Override
    public int getItemCount() {
        return messagesModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView userName, lastMessage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            lastMessage = itemView.findViewById(R.id.lastMessge);
        }
    }
}
