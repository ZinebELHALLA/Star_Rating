//Role : Adapte les données des stars pour l'affichage dans le RecyclerView.



package com.example.starrate.adapters;

import com.example.starrate.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

import com.example.starrate.beans.Star;

import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> {
    private List<Star> starList;

    public StarAdapter(List<Star> starList) {
        this.starList = starList;
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.star_item, parent, false);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Star star = starList.get(position);
        holder.name.setText(star.getName());
        holder.stars.setRating(star.getStar());
        Glide.with(holder.img.getContext())
                .load(star.getImg())
                .into(holder.img);
        holder.ids.setText(String.valueOf(star.getId()));
    }

    @Override
    public int getItemCount() {
        return starList.size();
    }

    public void updateList(List<Star> newList) {
        starList = newList;
        notifyDataSetChanged();
    }

    public static class StarViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView img;
        public TextView name, ids;
        public RatingBar stars;

        public StarViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
            ids = itemView.findViewById(R.id.ids);
        }
    }
}
