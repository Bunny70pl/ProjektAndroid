package com.example.todo;

import android.content.Context;
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
    ZadanieAdapterPodKategorie zadanieAdapterPodKategorie = new ZadanieAdapterPodKategorie();
    KategoriaViewModel kategoriaViewModel;

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
        holder.nazwa.setText(kategoria.getNazwaKategorii());
        holder.recyclerView.setAdapter(zadanieAdapterPodKategorie);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        kategoriaViewModel = new ViewModelProvider(viewModelStoreOwner).get(KategoriaViewModel.class);
        for (int i =0;i<kategorie.size();i++){
            kategoria = kategorie.get(i);
            kategoriaViewModel.pobierzZadania(kategoria.getId());
        }
        kategoriaViewModel.getZadaniaDlaKategorii().observe(lifecycleOwner, zadanie -> {
            zadanieAdapterPodKategorie.setZadania(zadanie);
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
}

