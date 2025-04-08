package com.example.todo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Projekt.class, Kategoria.class, Zadanie.class}, version = 1,exportSchema = false)
public abstract class BazaDanychToDo extends RoomDatabase {
    public abstract ProjektDao projektDao();
    public abstract KategoriaDao kategoriaDao();
    public abstract ZadanieDao zadanieDao();
}
