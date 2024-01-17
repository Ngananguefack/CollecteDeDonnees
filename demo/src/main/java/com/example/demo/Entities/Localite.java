package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Localite")
public class Localite {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int codeLocalite;
    public String Libelle;
    public int PNombreMenage;
    public float PPolutaion;
    public String IEEcodeMaternelle;
    public String IEEcodePrimaire;
    public String IEEcodeSecondaire;

    public Localite(int codeLocalite, String libelle, int PNombreMenage, float PPolutaion, String IEEcodeMaternelle, String IEEcodePrimaire, String IEEcodeSecondaire) {
        this.codeLocalite = codeLocalite;
        Libelle = libelle;
        this.PNombreMenage = PNombreMenage;
        this.PPolutaion = PPolutaion;
        this.IEEcodeMaternelle = IEEcodeMaternelle;
        this.IEEcodePrimaire = IEEcodePrimaire;
        this.IEEcodeSecondaire = IEEcodeSecondaire;

    }

    public Localite() {

    }

    public int getCodeLocalite() {
        return codeLocalite;
    }

    public void setCodeLocalite(int codeLocalite) {
        this.codeLocalite = codeLocalite;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public int getPNombreMenage() {
        return PNombreMenage;
    }

    public void setPNombreMenage(int PNombreMenage) {
        this.PNombreMenage = PNombreMenage;
    }

    public float getPPolutaion() {
        return PPolutaion;
    }

    public void setPPolutaion(float PPolutaion) {
        this.PPolutaion = PPolutaion;
    }

    public String getIEEcodeMaternelle() {
        return IEEcodeMaternelle;
    }

    public void setIEEcodeMaternelle(String IEEcodeMaternelle) {
        this.IEEcodeMaternelle = IEEcodeMaternelle;
    }

    public String getIEEcodePrimaire() {
        return IEEcodePrimaire;
    }

    public void setIEEcodePrimaire(String IEEcodePrimaire) {
        this.IEEcodePrimaire = IEEcodePrimaire;
    }

    public String getIEEcodeSecondaire() {
        return IEEcodeSecondaire;
    }

    public void setIEEcodeSecondaire(String IEEcodeSecondaire) {
        this.IEEcodeSecondaire = IEEcodeSecondaire;
    }

    @Override
    public String toString() {
        return "Localite{" +
                "codeLocalite=" + codeLocalite +
                ", Libelle='" + Libelle + '\'' +
                ", PNombreMenage=" + PNombreMenage +
                ", PPolutaion=" + PPolutaion +
                ", IEEcodeMaternelle='" + IEEcodeMaternelle + '\'' +
                ", IEEcodePrimaire='" + IEEcodePrimaire + '\'' +
                ", IEEcodeSecondaire='" + IEEcodeSecondaire + '\'' +
                '}';
    }
}
