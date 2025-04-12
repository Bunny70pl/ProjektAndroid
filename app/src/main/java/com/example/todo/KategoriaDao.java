package com.example.todo;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KategoriaDao {
    @Insert
    void insert(Kategoria kategoria);

    @Update
    void update(Kategoria kategoria);

    @Delete
    void delete(Kategoria kategoria);

    @Query("SELECT * FROM kategorie WHERE idProjektu = :projektId")
    LiveData<List<Kategoria>> pobierzKategoriePoId(int projektId);
    @Query("SELECT * FROM kategorie WHERE id = :id")
    LiveData<Kategoria> pobierzKategoriaPoId(int id);
}
