package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Probleme {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idProbleme;
    public String Probleme;
    public String Archive;

    public Probleme(int idProbleme, String probleme, String archive) {
        this.idProbleme = idProbleme;
        Probleme = probleme;
        Archive = archive;
    }

    public Probleme() {

    }

    @Override
    public String toString() {
        return "Probleme{" +
                "idProbleme=" + idProbleme +
                ", Probleme='" + Probleme + '\'' +
                ", Archive=" + Archive +
                '}';
    }

    public void setIdProbleme(int idProbleme) {
        this.idProbleme = idProbleme;
    }

    public String getProbleme() {
        return Probleme;
    }

    public void setProbleme(String probleme) {
        Probleme = probleme;
    }

    public String getArchive() {
        return Archive;
    }

    public void setArchive(String string) {
        Archive = string;
    }
}
