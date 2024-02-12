package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<String> ntitle;
    private List<String> nmessage;
    private List<String> ntime;
    private Context context;
    private Adapter(List<String> ntitle,List<String> nmessage,List<String> ntime,Context context){
        this.ntitle=ntitle;
        this.nmessage=nmessage;
        this.ntime=ntime;

    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

      String title= ntitle.get(position);
      String message= nmessage.get(position);
      String time= ntime.get(position);
      holder.title.setText(title);
      holder.message.setText(message);
      holder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView message;
        private TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
              title=itemView.findViewById(R.id.notetitle);
            message=itemView.findViewById(R.id.notemessage);
            time=itemView.findViewById(R.id.notetime);        }

    }
}
