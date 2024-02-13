package com.example.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<NoteModel> noteModelList;
    private ItemClickListener listener;
    NotesAdapter(List<NoteModel> noteModelList, ItemClickListener listener){
        this.noteModelList=noteModelList;
        this.listener=listener;
    }
    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NoteModel model=noteModelList.get(position);
      String title= model.getTitle();
      String message= model.getMessage();
      String time= model.getTime();
      holder.title.setText(title);
      holder.message.setText(message);
      holder.time.setText(time);
      holder.deleteicon.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              listener.deleteItem(position);
          }
      });

    }

    @Override
    public int getItemCount() {
        return noteModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView message;
        private TextView time;
        private ImageView deleteicon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
              title=itemView.findViewById(R.id.notetitle);
            message=itemView.findViewById(R.id.notemessage);
            time=itemView.findViewById(R.id.notetime);
            deleteicon=itemView.findViewById(R.id.deleteicon);
        }

    }

}
