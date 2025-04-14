package com.example.todo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ZadanieAdapterPodListe extends RecyclerView.Adapter<ZadanieAdapterPodListe.ZadaniaViewHolder> {
    private List<Zadanie> zadania = new ArrayList<>();

    @NonNull
    @Override
    public ZadaniaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_zadanie_lista, parent, false);
        return new ZadaniaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZadaniaViewHolder holder, int position) {
        Zadanie zadanie = zadania.get(position);
        holder.nazwa.setText(zadanie.getTytul());
        holder.opis.setText(zadanie.getOpisZadania());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onZadanieClick(zadanie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return zadania.size();
    }

    public void setZadania(List<Zadanie> zadania) {
        this.zadania = zadania;
        notifyDataSetChanged();
        Log.d("Adapter", "Zadania ustawione: " + zadania.size());
    }

    public static class ZadaniaViewHolder extends RecyclerView.ViewHolder {
        TextView nazwa,opis;

        public ZadaniaViewHolder(@NonNull View itemView) {
            super(itemView);
            nazwa = itemView.findViewById(R.id.textViewZadania);
            opis = itemView.findViewById(R.id.textViewOpisZadania);
        }
    }
    public interface OnZadanieClickListener {
        void onZadanieClick(Zadanie zadanie);
    }
    private ZadanieAdapterPodListe.OnZadanieClickListener listener;

    public void setOnZadanieClickListener(ZadanieAdapterPodListe.OnZadanieClickListener listener) {
        this.listener = listener;
    }
}
