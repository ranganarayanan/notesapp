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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<String> ntitle;
    private List<String> nmessage;
    private List<String> ntime;
    private Context context;
    private ItemClickListener listener;
    ImageView delete;
    Adapter(List<String> ntitle, List<String> nmessage, List<String> ntime, Context context,ItemClickListener listener){
        this.ntitle=ntitle;
        this.nmessage=nmessage;
        this.ntime=ntime;
        this.context=context;
        this.listener=listener;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

      String title= ntitle.get(position);
      String message= nmessage.get(position);
      String time= ntime.get(position);
      holder.title.setText(title);
      holder.message.setText(message);
      holder.time.setText(time);
      holder.deleteicon.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              listener.deleteItem(position);
          }
      });
      holder.editicon.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              listener.updateItem(position);
          }
      });
    }

    @Override
    public int getItemCount() {
        return ntitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView message;
        private TextView time;
        private ImageView deleteicon;
        private ImageView editicon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
              title=itemView.findViewById(R.id.notetitle);
            message=itemView.findViewById(R.id.notemessage);
            time=itemView.findViewById(R.id.notetime);
            deleteicon=itemView.findViewById(R.id.deleteicon);
            editicon=itemView.findViewById(R.id.editicon);
        }

    }

}
