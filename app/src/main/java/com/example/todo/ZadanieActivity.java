package com.example.todo;

import android.app.AlertDialog;
import android.content.Intent;
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

import com.example.todo.databinding.ActivityKategorieBinding;
import com.example.todo.databinding.ActivityZadanieBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ZadanieActivity extends AppCompatActivity {
    private ActivityZadanieBinding binding;
    private ZadanieViewModel zadanieViewModel;
    private TextView nazwaZadania;
    private TextView opisZadania;
    private int zadanieId;
    private KomentarzeAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton dodajKomentarz;
    private FloatingActionButton usunZadanie;
    private FloatingActionButton edytujZadanie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityZadanieBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        zadanieId = getIntent().getIntExtra("zadanieId", -1);
        zadanieViewModel = new ViewModelProvider(this).get(ZadanieViewModel.class);
        recyclerView = binding.recyclerViewKomentarze;
        nazwaZadania = binding.textViewZadanie;
        opisZadania = binding.textViewOpisZadania;
        zadanieViewModel.pobierzZadanie(zadanieId);
        zadanieViewModel.getZadanie().observe(this, zadanie -> {
            if (zadanie != null) {
                nazwaZadania.setText(zadanie.getTytul());
                opisZadania.setText(zadanie.getOpisZadania());
            } else {
                finish();
            }
        });
        zadanieViewModel.pobierzKomentarzeDlaZadania(zadanieId);
        adapter = new KomentarzeAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        zadanieViewModel.pobierzKomentarzeDlaZadania(zadanieId);
        zadanieViewModel.getWszystkieKomentzarze().observe(this, komentarze -> {
            adapter.setKomentarze(komentarze);
        });
        dodajKomentarz = binding.dodajKomentarz;
        edytujZadanie = binding.edytujZadanie;
        usunZadanie = binding.usunZadanie;
        dodajKomentarz.setOnClickListener(v->{
                    pokazDialogDodawniaKomentarza();
                }
        );
        usunZadanie.setOnClickListener(v->{
                    usunZadanie();
                }
        );
        edytujZadanie.setOnClickListener(v->{
                    pokazDialogEdytowaniaZadania();
                }
        );
        adapter.setOnKomentarzClickListener(komentarz -> {
            usunKomentarz(komentarz);
        });
    }
    private void pokazDialogDodawniaKomentarza() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_dodaj_komentarz, null);
        EditText editKomentarz = dialogView.findViewById(R.id.editKomentarz);

        new AlertDialog.Builder(this)
                .setTitle("Nowe zadanie")
                .setView(dialogView)
                .setPositiveButton("Dodaj", (dialog, which) -> {
                    String komentarzString = editKomentarz.getText().toString();

                    if (!komentarzString.isEmpty() && zadanieId != -1) {
                        zadanieViewModel.dodajKomentarz(new Komentarz(komentarzString,zadanieId));
                    }
                })
                .setNegativeButton("Anuluj", null)
                .show();
    }
    private void usunZadanie() {
        Zadanie zadanie = zadanieViewModel.getZadanie().getValue();
        if (zadanie != null) {
            zadanieViewModel.usunZadanie(zadanie);
        }
    }
    private void usunKomentarz(Komentarz komentarz) {
        if (komentarz != null) {
            zadanieViewModel.usunKomentarz(komentarz);
        }
    }
    private void pokazDialogEdytowaniaZadania() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_dodaj_zadanie, null);
        Zadanie zadanieDoEdycji = zadanieViewModel.getZadanie().getValue();
        EditText editNazwa = dialogView.findViewById(R.id.editNazwaZadania);
        EditText editOpis = dialogView.findViewById(R.id.editOpisZadania);
        editNazwa.setText(zadanieDoEdycji.getTytul());
        editOpis.setText(zadanieDoEdycji.getOpisZadania());
        new AlertDialog.Builder(this)
                .setTitle("Edytuj Zadanie")
                .setView(dialogView)
                .setPositiveButton("Dodaj", (dialog, which) -> {
                    String nazwa = editNazwa.getText().toString();
                    String opis = editOpis.getText().toString();
                    if (!nazwa.isEmpty()) {
                        zadanieDoEdycji.setTytul(editNazwa.getText().toString());
                        zadanieDoEdycji.setOpisZadania(editOpis.getText().toString());
                        zadanieViewModel.edytujZadanie(zadanieDoEdycji);
                    }
                })
                .setNegativeButton("Anuluj", null)
                .show();
    }
}