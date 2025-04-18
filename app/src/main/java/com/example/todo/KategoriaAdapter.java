package com.example.todo;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KategoriaAdapter extends RecyclerView.Adapter<KategoriaAdapter.KategoriaViewHolder> {
    private List<Kategoria> kategorie = new ArrayList<>();
    private final Context context;
    private final LifecycleOwner lifecycleOwner;
    private final ViewModelStoreOwner viewModelStoreOwner;

    public KategoriaAdapter(Context context, LifecycleOwner lifecycleOwner, ViewModelStoreOwner viewModelStoreOwner) {
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.viewModelStoreOwner = viewModelStoreOwner;
    }
    public void setKategorie(List<Kategoria> lista) {
        this.kategorie = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KategoriaAdapter.KategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kategoria, parent, false);
        return new KategoriaAdapter.KategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriaAdapter.KategoriaViewHolder holder, int position) {
        Kategoria kategoria = kategorie.get(position);
        KategoriaViewModel kategoriaViewModel = new ViewModelProvider(viewModelStoreOwner).get(KategoriaViewModel.class);
        holder.nazwa.setText(kategoria.getNazwaKategorii());
        ZadanieAdapterPodKategorie adapter = new ZadanieAdapterPodKategorie();
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(adapter);
        holder.itemView.setOnDragListener((v, event) -> {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);
                case DragEvent.ACTION_DROP:
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    int zadanieId = Integer.parseInt(item.getText().toString());
                    int nowaKategoriaId = kategoria.getId();
                    kategoriaViewModel.przeniesZadanieDoKategorii(zadanieId, nowaKategoriaId);
                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.argb(128, 169, 169, 169));
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.TRANSPARENT);
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(Color.TRANSPARENT);
                    return true;
                default:
                    return true;
            }
        });
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onKategoriaClick(kategoria);
            }
        });
        kategoriaViewModel.pobierzZadania(kategoria.getId());
        kategoriaViewModel.getZadania().observe(lifecycleOwner, zadania -> {
            if (zadania.size() > 0 && zadania.get(0).getIdKategorii() == kategoria.getId()) {
                adapter.setZadania(zadania);
            }else{
                adapter.setZadania(zadania);
            }
        });
    }


    @Override
    public int getItemCount() {
        return kategorie.size();
    }

    public static class KategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView nazwa;
        RecyclerView recyclerView;

        public KategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            nazwa = itemView.findViewById(R.id.textNazwaKategorii);
            recyclerView = itemView.findViewById(R.id.recyclerViewZadaniaPodKategoria);
        }
    }
    public interface OnKategoriaClickListener {
        void onKategoriaClick(Kategoria kategoria);
    }
    private KategoriaAdapter.OnKategoriaClickListener listener;

    public void setOnKategoriaClickListener(KategoriaAdapter.OnKategoriaClickListener listener) {
        this.listener = listener;
    }
}

