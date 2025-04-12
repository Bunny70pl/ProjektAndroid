package com.example.todo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repozytorium {

    private ProjektDao projektDao;
    private KategoriaDao kategoriaDao;
    private ZadanieDao zadanieDao;
    private KomentarzDao komentarzDao;
    private ExecutorService executor;

    // Singleton Bazy Danych
    private static Repozytorium instance;

    private Repozytorium(Context context) {
        BazaDanychToDo db = BazaDanychToDo.getDatabase(context);
        projektDao = db.projektDao();
        kategoriaDao = db.kategoriaDao();
        zadanieDao = db.zadanieDao();
        komentarzDao = db.komentarzDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public static synchronized Repozytorium getInstance(Context context) {
        if (instance == null) {
            instance = new Repozytorium(context);
        }
        return instance;
    }

    public void insertProjekt(Projekt projekt) {
        executor.execute(() -> projektDao.insert(projekt));
    }

    public void deleteProjekt(Projekt projekt) {
        executor.execute(() -> projektDao.delete(projekt));
    }

    public void updateProjekt(Projekt projekt) {
        executor.execute(() -> projektDao.update(projekt));
    }

    public LiveData<List<Projekt>> pobierzWszystkieProjekty() {
        return projektDao.pobierzWszystkieProjekty();
    }
    public LiveData<Projekt> pobierzProjektPoId(int id) {
        return projektDao.pobierzProjektPoId(id);
    }
    public LiveData<Kategoria> pobierzKategoriaPoId(int id) {
        return kategoriaDao.pobierzKategoriaPoId(id);
    }

    public void insertKategoria(Kategoria kategoria) {
        executor.execute(() -> kategoriaDao.insert(kategoria));
    }

    public void edytujKategorie(Kategoria kategoria) {
        executor.execute(() -> kategoriaDao.update(kategoria));
    }
    public void usunKategorie(Kategoria kategoria) {
        executor.execute(() -> kategoriaDao.delete(kategoria));
    }

    public LiveData<List<Kategoria>> pobierzKategoriePoIdProjektu(int projektId) {
        return kategoriaDao.pobierzKategoriePoId(projektId);
    }

    public void insertZadanie(Zadanie zadanie) {
        executor.execute(() -> zadanieDao.insert(zadanie));
    }
    public void zmienKategorieZadania(int zadanieId, int nowaKategoriaId) {
        executor.execute(() -> {
            Zadanie zadanie = zadanieDao.pobierzZadaniePoId(zadanieId);
            if (zadanie != null) {
                zadanie.setIdKategorii(nowaKategoriaId);
                zadanieDao.update(zadanie);
            }
        });
    }


    public LiveData<List<Zadanie>> pobierzZadaniaDlaKategorii(int kategoriaId) {
        return zadanieDao.pobierzZadaniaDlaKategorii(kategoriaId);
    }

    public void insertKomentarz(Komentarz komentarz) {
        executor.execute(() -> komentarzDao.insert(komentarz));
    }

    public LiveData<List<Komentarz>> pobierzKomentarzeDlaZadania(int zadanieId) {
        return komentarzDao.pobierzKomentarzPoIdZadania(zadanieId);
    }
}