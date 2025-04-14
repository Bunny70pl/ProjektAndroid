package com.example.todo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "zadania",
    foreignKeys = @ForeignKey(entity = Kategoria.class,
    parentColumns = "id",
    childColumns = "idKategorii",
    onDelete = ForeignKey.CASCADE)
)
public class Zadanie {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String tytul;

    public String opisZadania;

    public int idKategorii;

    public Zadanie( String tytul, String opisZadania, int idKategorii) {
        this.tytul = tytul;
        this.opisZadania = opisZadania;
        this.idKategorii = idKategorii;
    }

    public Zadanie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getOpisZadania() {
        return opisZadania;
    }

    public void setOpisZadania(String opisZadania) {
        this.opisZadania = opisZadania;
    }

    public int getIdKategorii() {
        return idKategorii;
    }

    public void setIdKategorii(int idKategorii) {
        this.idKategorii = idKategorii;
    }
}
