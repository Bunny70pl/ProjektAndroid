package com.example.todo;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface ZadanieDao {
    @Insert
    void insert(Zadanie zadanie);

    @Update
    void update(Zadanie zadanie);

    @Delete
    void delete(Zadanie zadanie);

    @Query("SELECT * FROM zadania WHERE idKategorii = :kategoriaId")
    LiveData<List<Zadanie>> pobierzZadaniaDlaKategorii(int kategoriaId);

    @Query("SELECT * FROM zadania WHERE id = :id")
    LiveData<Zadanie> pobierzZadaniePoId(int id);

    @Query("SELECT * FROM zadania WHERE id = :id")
    Zadanie pobierzZadaniePoIdv2(int id);


}
