package com.example.todo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "komentarze",
        foreignKeys = @ForeignKey(entity = Zadanie.class,
                parentColumns = "id",
                childColumns = "idZadania",
                onDelete = ForeignKey.CASCADE)
)
public class Komentarz {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String komentarzString;

    public int idZadania;

    public Komentarz(String komentarzString, int idZadania) {
        this.komentarzString = komentarzString;
        this.idZadania = idZadania;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKomentarzString() {
        return komentarzString;
    }

    public void setKomentarzString(String komentarzString) {
        this.komentarzString = komentarzString;
    }

    public int getIdZadania() {
        return idZadania;
    }

    public void setIdZadania(int idZadania) {
        this.idZadania = idZadania;
    }
}

