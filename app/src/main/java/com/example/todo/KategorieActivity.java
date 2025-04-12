package com.example.todo;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.databinding.ActivityKategorieBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class KategorieActivity extends AppCompatActivity {
    private ActivityKategorieBinding binding;
    private KategoriaViewModel kategoriaViewModel;
    private int projektId;
    private RecyclerView recyclerViewKategorie;
    private KategoriaAdapter adapter;
    private TextView textViewTytul;
    private TextView textViewOpis;
    private FloatingActionButton dodajKategorie;
    private FloatingActionButton usunProjekt;
    private FloatingActionButton edytujProjekt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKategorieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        projektId = getIntent().getIntExtra("projektId", -1);
        textViewTytul = binding.textViewKategorie;
        textViewOpis = binding.textViewOpisProjektu;
        kategoriaViewModel = new ViewModelProvider(this).get(KategoriaViewModel.class);
        kategoriaViewModel.pobierzProjektPoId(projektId);
        kategoriaViewModel.getProjekt().observe(this, projekt -> {
            textViewTytul.setText(projekt.getNazwa());
            textViewOpis.setText(projekt.getOpis());
        });
        recyclerViewKategorie = binding.recyclerViewKategorie;
        adapter = new KategoriaAdapter(this,this,this);
        kategoriaViewModel.pobierzKategorie(projektId);
        recyclerViewKategorie.setAdapter(adapter);
        recyclerViewKategorie.setLayoutManager(new LinearLayoutManager(this));
        kategoriaViewModel.getKategorie().observe(this, kategorie -> {
            adapter.setKategorie(kategorie);
        });
        dodajKategorie = binding.dodajKategorie;
        edytujProjekt = binding.edytujProjekt;
        usunProjekt = binding.usunProjekt;
        dodajKategorie.setOnClickListener(v->{
                    pokazDialogDodawniaKategorii();
                }
                );
        usunProjekt.setOnClickListener(v->{
                    usunProjekt();
                }
        );
        edytujProjekt.setOnClickListener(v->{
                    pokazDialogEdytowaniaProjektu();
                }
        );
        adapter.setOnKategoriaClickListener(kategoria -> {
            Intent intent = new Intent(KategorieActivity.this, ListaZadanWKateogriiActivity.class);
            intent.putExtra("kategoriaId", kategoria.getId());
            startActivity(intent);
        });
    }
    private void pokazDialogDodawniaKategorii() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_dodaj_kategorie, null);
        EditText editNazwa = dialogView.findViewById(R.id.editNazwaKategorii);

        new AlertDialog.Builder(this)
                .setTitle("Nowy kategoria")
                .setView(dialogView)
                .setPositiveButton("Dodaj", (dialog, which) -> {
                    String nazwa = editNazwa.getText().toString();
                    if (!nazwa.isEmpty()) {
                        kategoriaViewModel.dodajKategroia(new Kategoria(nazwa, projektId));
                    }
                })
                .setNegativeButton("Anuluj", null)
                .show();
    }
    private void usunProjekt() {
        kategoriaViewModel.usunProjekt(kategoriaViewModel.getProjekt().getValue());
    }
    private void pokazDialogEdytowaniaProjektu() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edyt_projekt, null);
        Projekt projekt = kategoriaViewModel.getProjekt().getValue();
        EditText editNazwa = dialogView.findViewById(R.id.editNazwaProjektu);
        EditText editOpis = dialogView.findViewById(R.id.editOpisProjektu);
        editNazwa.setText(projekt.getNazwa());
        editOpis.setText(projekt.getOpis());
        new AlertDialog.Builder(this)
                .setTitle("Edytuj projekt")
                .setView(dialogView)
                .setPositiveButton("Dodaj", (dialog, which) -> {
                    String nazwa = editNazwa.getText().toString();
                    if (!nazwa.isEmpty()) {
                        projekt.setNazwa(editNazwa.getText().toString());
                        projekt.setOpis(editOpis.getText().toString());
                        kategoriaViewModel.edytujProjekt(projekt);
                    }
                })
                .setNegativeButton("Anuluj", null)
                .show();
    }
}
