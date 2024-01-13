package com.isamm.myspace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewholder> {

    private Context context;
    private List<Planet> planets;
    private RecyclerViewClickListener mListener;

    public RecyclerAdapter(Context context, List<Planet> planets,RecyclerViewClickListener listener) {
        this.context = context;
        this.planets = planets;
        mListener = listener;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.item, null);
        return new viewholder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Planet planet=planets.get(position);
        holder.name.setText(planet.getName());
        holder.description.setText(planet.getBriefdesc());
        int planetImgId = context.getResources().getIdentifier(planet.getImgPath(),"drawable",context.getPackageName());
        holder.planetImg.setBackgroundResource(planetImgId);
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private TextView name, description;
        private ImageView planetImg;
        private RecyclerViewClickListener listener;

        public viewholder(@NonNull View itemView, RecyclerViewClickListener onClickListener) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            description=itemView.findViewById(R.id.description);
            planetImg = itemView.findViewById(R.id.planetImageView);
            this.listener = onClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,planets.get(getAdapterPosition()));
        }
    }
}
