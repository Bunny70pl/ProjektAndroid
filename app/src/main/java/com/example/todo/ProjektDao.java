package com.example.todo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProjektDao {
    @Insert
    void insert(Projekt projekt);

    @Update
    void update(Projekt projekt);

    @Delete
    void delete(Projekt projekt);

    @Query("SELECT * FROM projekty")
    LiveData<List<Projekt>> pobierzWszystkieProjekty();
    @Query("SELECT * FROM projekty WHERE id = :id")
    LiveData<Projekt> pobierzProjektPoId(int id);
}
