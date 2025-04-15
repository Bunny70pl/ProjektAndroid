package com.example.todo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ZadanieViewModel extends AndroidViewModel {
    private Repozytorium repozytorium;
    private LiveData<List<Komentarz>> wszystkieKomentzarze;
    private LiveData<Zadanie> zadanieLiveData;

        public ZadanieViewModel(@NonNull Application application) {
            super(application);
            repozytorium = Repozytorium.getInstance(application);
        }

        public LiveData<List<Komentarz>> getWszystkieKomentzarze() {
            return wszystkieKomentzarze;
        }

        public void pobierzKomentarzeDlaZadania(int id) {
            wszystkieKomentzarze = repozytorium.pobierzKomentarzeDlaZadania(id);
        }

        public void pobierzZadanie(int id) {
            zadanieLiveData = repozytorium.pobierzZadaniePoIdZadania(id);
        }

        public void usunZadanie(Zadanie zadanie) {
            repozytorium.usunZadanie(zadanie);
        }

        public void usunKomentarz(Komentarz komentarz) {
              repozytorium.usunKomentarz(komentarz);
        }

        public void edytujZadanie(Zadanie zadanie) {
            repozytorium.edytujZadanie(zadanie);
        }

        public LiveData<Zadanie> getZadanie() {
            return zadanieLiveData;
        }
        public void dodajKomentarz(Komentarz komentarz) {
            repozytorium.insertKomentarz(komentarz);
        }
}
