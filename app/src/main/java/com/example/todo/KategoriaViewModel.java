package com.example.todo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class KategoriaViewModel extends AndroidViewModel {
    private Repozytorium repozytorium;
    private LiveData<List<Kategoria>> kategorie;
    private LiveData<List<Zadanie>> zadania;
    private LiveData<Projekt> projektLiveData;
    private LiveData<Kategoria> kategoriaLiveData;

    public KategoriaViewModel(@NonNull Application application) {
        super(application);
        repozytorium = Repozytorium.getInstance(application);
    }

    public void pobierzKategorie(int id){
        kategorie = repozytorium.pobierzKategoriePoIdProjektu(id);
    }
    public void pobierzKategoria(int id){
        kategoriaLiveData = repozytorium.pobierzKategoriaPoId(id);
    }
    public void pobierzProjektPoId(int id) {
        projektLiveData = repozytorium.pobierzProjektPoId(id);
    }

    public LiveData<List<Kategoria>> getKategorie() {
        return kategorie;
    }
    public void pobierzZadania(int id){
        zadania = repozytorium.pobierzZadaniaDlaKategorii(id);
    }

    public LiveData<List<Zadanie>> getZadania() {
        return zadania;
    }
    public LiveData<Projekt> getProjekt() {
        return projektLiveData;
    }
    public LiveData<Kategoria> getKategoria() {
        return kategoriaLiveData;
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

    public void dodajZadanie(Zadanie zadanie) {
        Log.d("KategoriaViewModel", "Dodawanie zadania: " + zadanie.getTytul());
        repozytorium.insertZadanie(zadanie);
        pobierzZadania(zadanie.getIdKategorii());
    }
    public void edytujKategorie(Kategoria kategoria) {
        repozytorium.edytujKategorie(kategoria);
    }
    public void usunKategorie(Kategoria kategoria) {
        repozytorium.usunKategorie(kategoria);
    }

    public void przeniesZadanieDoKategorii(int zadanieId, int nowaKategoriaId) {
        repozytorium.zmienKategorieZadania(zadanieId, nowaKategoriaId);
        pobierzZadania(nowaKategoriaId);
    }
}
