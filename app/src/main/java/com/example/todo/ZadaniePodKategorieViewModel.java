package com.example.todo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ZadaniePodKategorieViewModel extends AndroidViewModel {
    private Repozytorium repozytorium;
    private LiveData<List<Zadanie>> wszystkieZadania;

    public ZadaniePodKategorieViewModel(@NonNull Application application) {
        super(application);
        repozytorium = Repozytorium.getInstance(application);
    }
    public void pobierzKategorie(int id){
        wszystkieZadania = repozytorium.pobierzZadaniaDlaKategorii(id);
    }

    public LiveData<List<Zadanie>> getWszystkieZadania() {
        return wszystkieZadania;
    }
}
