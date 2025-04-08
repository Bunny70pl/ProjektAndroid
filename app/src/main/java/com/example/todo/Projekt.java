package com.example.todo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "projekty")
public class Projekt {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nazwaProjektu;
    public String opis;
    public Projekt() {

    }
    public Projekt(String nazwa, String opis) {
        this.nazwaProjektu = nazwa;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwaProjektu;
    }

    public void setNazwa(String nazwa) {
        this.nazwaProjektu = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
