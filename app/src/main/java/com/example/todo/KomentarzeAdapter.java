package com.example.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KomentarzeAdapter extends RecyclerView.Adapter<KomentarzeAdapter.KomentarzViewHolder> {
    private List<Komentarz> komentarze = new ArrayList<>();

    public void setKomentarze(List<Komentarz> lista) {
        this.komentarze = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KomentarzeAdapter.KomentarzViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_komentarz, parent, false);
        return new KomentarzeAdapter.KomentarzViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KomentarzeAdapter.KomentarzViewHolder holder, int position) {
        Komentarz komentarz = komentarze.get(position);
        holder.komentarzText.setText(komentarz.getKomentarzString());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onKomentarzClick(komentarz);
            }
        });
    }

    @Override
    public int getItemCount() {
        return komentarze.size();
    }

    public static class KomentarzViewHolder extends RecyclerView.ViewHolder {
        TextView komentarzText;

        public KomentarzViewHolder(@NonNull View itemView) {
            super(itemView);
            komentarzText = itemView.findViewById(R.id.textViewKomentarzItem);
        }
    }
    public interface OnKomentarzClickListener {
        void onKomentarzClick(Komentarz komentarz);
    }
    private KomentarzeAdapter.OnKomentarzClickListener listener;

    public void setOnKomentarzClickListener(OnKomentarzClickListener listener) {
        this.listener = listener;
    }

}
