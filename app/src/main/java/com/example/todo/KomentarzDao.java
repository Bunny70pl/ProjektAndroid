package com.example.todo;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KomentarzDao {
    @Insert
    void insert(Komentarz komentarz);

    @Update
    void update(Komentarz komentarz);

    @Delete
    void delete(Komentarz komentarz);

    @Query("SELECT * FROM komentarze WHERE idZadania = :idZadania")
    LiveData<List<Komentarz>> pobierzKomentarzPoIdZadania(int idZadania);
}
