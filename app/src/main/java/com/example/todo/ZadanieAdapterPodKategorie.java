package com.example.todo;

import android.content.ClipData;
import android.content.ClipDescription;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ZadanieAdapterPodKategorie extends RecyclerView.Adapter<ZadanieAdapterPodKategorie.ZadaniaViewHolder> {
    private List<Zadanie> zadania = new ArrayList<>();

    @NonNull
    @Override
    public ZadaniaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_zadanie_pod_kategoria, parent, false);
        return new ZadaniaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZadaniaViewHolder holder, int position) {
        Zadanie zadanie = zadania.get(position);
        holder.nazwa.setText(zadanie.getTytul());holder.nazwa.setText(zadanie.getTytul());
        holder.itemView.setOnLongClickListener(v -> {
            ClipData.Item item = new ClipData.Item(String.valueOf(zadanie.getId()));
            ClipData dragData = new ClipData("ZADANIE_ID", new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(dragData, shadowBuilder, null, 0);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return zadania.size();
    }

    public void setZadania(List<Zadanie> lista) {
        this.zadania = lista;
        notifyDataSetChanged();
    }

    public static class ZadaniaViewHolder extends RecyclerView.ViewHolder {
        TextView nazwa;

        public ZadaniaViewHolder(@NonNull View itemView) {
            super(itemView);
            nazwa = itemView.findViewById(R.id.textViewZadania);
        }
    }
}
