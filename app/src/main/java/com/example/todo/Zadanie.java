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
    @PrimaryKey
    public int id;

    public String tytul;

    public String opisZadania;

    public boolean status;

    public int idKategorii;

    public Zadanie( String tytul, String opisZadania, boolean status, int idKategorii) {
        this.tytul = tytul;
        this.opisZadania = opisZadania;
        this.status = status;
        this.idKategorii = idKategorii;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdKategorii() {
        return idKategorii;
    }

    public void setIdKategorii(int idKategorii) {
        this.idKategorii = idKategorii;
    }
}
