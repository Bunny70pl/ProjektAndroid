package com.example.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProjektAdapter extends RecyclerView.Adapter<ProjektAdapter.ProjektViewHolder> {
    private List<Projekt> projekty = new ArrayList<>();

    public void setProjekty(List<Projekt> lista) {
        this.projekty = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProjektViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_projekt, parent, false);
        return new ProjektViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjektViewHolder holder, int position) {
        Projekt projekt = projekty.get(position);
        holder.nazwa.setText(projekt.getNazwa());
        holder.opis.setText(projekt.getOpis());
    }

    @Override
    public int getItemCount() {
        return projekty.size();
    }

    public static class ProjektViewHolder extends RecyclerView.ViewHolder {
        TextView nazwa, opis;

        public ProjektViewHolder(@NonNull View itemView) {
            super(itemView);
            nazwa = itemView.findViewById(R.id.textNazwaProjektu);
            opis = itemView.findViewById(R.id.textOpisProjektu);
        }
    }
}
