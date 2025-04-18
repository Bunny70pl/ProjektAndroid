    package com.example.todo;

    import android.app.AlertDialog;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.widget.EditText;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;
    import androidx.lifecycle.ViewModelProvider;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.todo.databinding.ActivityMainBinding;
    import com.google.android.material.floatingactionbutton.FloatingActionButton;

    public class ProjektActivity extends AppCompatActivity {
        private ActivityMainBinding binding;
        private ProjektViewModel projektViewModel;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            View view = binding.getRoot();
            EdgeToEdge.enable(this);
            setContentView(view);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
            projektViewModel = new ViewModelProvider(this).get(ProjektViewModel.class);
            RecyclerView recyclerView = binding.recyclerViewProjekty;
            ProjektAdapter adapter = new ProjektAdapter();
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            projektViewModel.getWszystkieProjekty().observe(this, projekty -> {
                adapter.setProjekty(projekty);
            });
            FloatingActionButton fab = binding.dodajProjekt;
            fab.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pokazDialogDodawaniaProjektu();
                        }
                    }
            );
            adapter.setOnProjektClickListener(projekt -> {
                Intent intent = new Intent(ProjektActivity.this, KategorieActivity.class);
                intent.putExtra("projektId", projekt.getId());
                startActivity(intent);
            });
        }
        private void pokazDialogDodawaniaProjektu() {
            View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_dodaj_projekt, null);
            EditText editNazwa = dialogView.findViewById(R.id.editNazwaProjektu);
            EditText editOpis = dialogView.findViewById(R.id.editOpisProjektu);

            new AlertDialog.Builder(this)
                    .setTitle("Nowy projekt")
                    .setView(dialogView)
                    .setPositiveButton("Dodaj", (dialog, which) -> {
                        String nazwa = editNazwa.getText().toString();
                        String opis = editOpis.getText().toString();
                        if (!nazwa.isEmpty()) {
                            projektViewModel.dodajProjekt(new Projekt(nazwa, opis));
                        }
                    })
                    .setNegativeButton("Anuluj", null)
                    .show();
        }

    }