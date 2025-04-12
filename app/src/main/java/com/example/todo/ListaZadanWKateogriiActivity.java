package com.example.todo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.databinding.ActivityListaZadanWkateogriiBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaZadanWKateogriiActivity extends AppCompatActivity {
    private ActivityListaZadanWkateogriiBinding binding;
    private KategoriaViewModel kategoriaViewModel;
    private int kategoriaId;
    private TextView textViewTytul;
    private FloatingActionButton dodajZadanie;
    private FloatingActionButton usunKategorie;
    private FloatingActionButton edytujKateogire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListaZadanWkateogriiBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        kategoriaId = getIntent().getIntExtra("kategoriaId", -1);
        textViewTytul = binding.textViewNazwaKategorii;
        kategoriaViewModel = new ViewModelProvider(this).get(KategoriaViewModel.class);
        kategoriaViewModel.pobierzKategoria(kategoriaId);
        kategoriaViewModel.getKategoria().observe(this, kategoria -> {
            textViewTytul.setText(kategoria.getNazwaKategorii());
        });
        RecyclerView recyclerView = binding.recyclerViewZadaniaPodKategoria;
        ZadanieAdapterPodListe adapter = new ZadanieAdapterPodListe();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        kategoriaViewModel.pobierzZadania(kategoriaId);
        kategoriaViewModel.getZadaniaDlaKategorii().observe(this, zadania -> {
            adapter.setZadania(zadania);
        });
        dodajZadanie = binding.dodajZadanie;
        edytujKateogire = binding.edytujKategorie;
        usunKategorie = binding.usunKategorie;
        dodajZadanie.setOnClickListener(v->{
                    pokazDialogDodawniaZadania();
                }
        );
        /*usunKategorie.setOnClickListener(v->{
                usunKategoriaMetoda();
                }
        );
        edytujKateogire.setOnClickListener(v->{
                    pokazDialogEdytowaniaKategoria();
                }
        );*/

    }
    private void pokazDialogDodawniaZadania() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_dodaj_zadanie, null);
        EditText editNazwa = dialogView.findViewById(R.id.editNazwaZadania);
        EditText editOpis = dialogView.findViewById(R.id.editOpisZadania);

        new AlertDialog.Builder(this)
                .setTitle("Nowe zadanie")
                .setView(dialogView)
                .setPositiveButton("Dodaj", (dialog, which) -> {
                    String nazwa = editNazwa.getText().toString();
                    String opis = editOpis.getText().toString();
                    Log.d("DEBUG", "Nazwa: " + nazwa + ", Opis: " + opis + ", kategoriaId: " + kategoriaId);

                    if (!nazwa.isEmpty() && kategoriaId != -1) {
                        kategoriaViewModel.dodajZadanie(new Zadanie(nazwa, opis, kategoriaId));
                    } else {
                        Log.e("DEBUG", "Nie można dodać zadania: brak nazwy lub nieprawidłowy kategoriaId");
                    }
                })
                .setNegativeButton("Anuluj", null)
                .show();
    }
    private void usunKategoriaMetoda() {
        kategoriaViewModel.usunKategorie(kategoriaViewModel.getKategoria().getValue());
    }
    private void pokazDialogEdytowaniaKategoria() {
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