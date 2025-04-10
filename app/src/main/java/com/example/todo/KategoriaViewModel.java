package com.example.todo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class KategoriaViewModel extends AndroidViewModel {
    private Repozytorium repozytorium;
    private LiveData<List<Kategoria>> kategorie;
    private LiveData<List<Zadanie>> zadania;
    private LiveData<Projekt> projektLiveData;

    public KategoriaViewModel(@NonNull Application application) {
        super(application);
        repozytorium = Repozytorium.getInstance(application);
    }

    public void pobierzKategorie(int id){
        kategorie = repozytorium.pobierzKategoriePoIdProjektu(id);
    }
    public void pobierzZadania(int id){
        zadania = repozytorium.pobierzZadaniaDlaKategorii(id);
    }
    public void pobierzProjektPoId(int id) {
        projektLiveData = repozytorium.pobierzProjektPoId(id);
    }

    public LiveData<List<Kategoria>> getKategorie() {
        return kategorie;
    }
    public LiveData<List<Zadanie>> getZadaniaDlaKategorii() {
        return zadania;
    }
    public LiveData<Projekt> getProjekt() {
        return projektLiveData;
    }

    public void dodajKategroia(Kategoria kategoria) {
        repozytorium.insertKategoria(kategoria);
    }
    public void edytujProjekt(Projekt projekt) {
        repozytorium.updateProjekt(projekt);
    }
    public void usunProjekt(Projekt projekt) {
        repozytorium.deleteProjekt(projekt);
    }
}
