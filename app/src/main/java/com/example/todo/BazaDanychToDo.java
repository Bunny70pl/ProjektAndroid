package com.example.todo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Projekt.class, Kategoria.class, Zadanie.class, Komentarz.class}, version = 1,exportSchema = false)
public abstract class BazaDanychToDo extends RoomDatabase {
    public abstract ProjektDao projektDao();
    public abstract KategoriaDao kategoriaDao();
    public abstract ZadanieDao zadanieDao();
    public abstract KomentarzDao komentarzDao();
    private static BazaDanychToDo instance;

    public static BazaDanychToDo getDatabase(Context context) {
        if (instance == null) {
            synchronized (BazaDanychToDo.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    BazaDanychToDo.class, "bazaDanychTrelloToDo")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
