package com.example.todo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ProjektViewModel extends AndroidViewModel {
    private Repozytorium repozytorium;
    private LiveData<List<Projekt>> wszystkieProjekty;

    public ProjektViewModel(@NonNull Application application) {
        super(application);
        repozytorium = Repozytorium.getInstance(application);
        wszystkieProjekty = repozytorium.pobierzWszystkieProjekty();
    }

    public LiveData<List<Projekt>> getWszystkieProjekty() {
        return wszystkieProjekty;
    }

    public void dodajProjekt(Projekt projekt) {
        repozytorium.insertProjekt(projekt);
    }
}
