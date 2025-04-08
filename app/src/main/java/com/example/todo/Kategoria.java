package com.example.todo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "kategorie",
    foreignKeys = @ForeignKey(entity = Projekt.class,
    parentColumns = "id",
    childColumns = "idProjektu",
    onDelete = ForeignKey.CASCADE)
)
public class Kategoria {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nazwaKategorii;
    public int idProjektu;

    public Kategoria(String nazwaKategorii, int idProjektu) {
        this.nazwaKategorii = nazwaKategorii;
        this.idProjektu = idProjektu;
    }

    public int getId() {
        return id;
    }

    public String getNazwaKategorii() {
        return nazwaKategorii;
    }

    public void setNazwaKategorii(String nazwaKategorii) {
        this.nazwaKategorii = nazwaKategorii;
    }

    public int getIdProjektu() {
        return idProjektu;
    }

    public void setIdProjektu(int idProjektu) {
        this.idProjektu = idProjektu;
    }
}
