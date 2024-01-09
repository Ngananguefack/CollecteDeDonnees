package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Region {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idCodeRegion;
    public int CodeRegion;

    public String Libelle;

    public String Accessible;

    public String DateCreation;

    public int Densite;

    public double Superficie;

    public Region() {

    }

    @Override
    public String toString() {
        return "Region{" +
                "idCodeRegion=" + idCodeRegion +
                ", CodeRegion=" + CodeRegion +
                ", Libelle='" + Libelle + '\'' +
                ", Accessible=" + Accessible +
                ", DateCreation=" + DateCreation +
                ", Densite=" + Densite +
                ", Superficie=" + Superficie +
                '}';
    }

    public void setIdCodeRegion(int idCodeRegion) {
        this.idCodeRegion = idCodeRegion;
    }

    public int getCodeRegion() {
        return CodeRegion;
    }

    public void setCodeRegion(int codeRegion) {
        CodeRegion = codeRegion;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public String getAccessible() {
        return Accessible;
    }

    public void setAccessible(String string) {
        Accessible = string;
    }

    public String getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(String string) {
        DateCreation = string;
    }

    public int getDensite() {
        return Densite;
    }

    public void setDensite(int densite) {
        Densite = densite;
    }

    public double getSuperficie() {
        return Superficie;
    }

    public void setSuperficie(double superficie) {
        Superficie = superficie;
    }

    public Region(int idCodeRegion, int codeRegion, String libelle, String accessible, String dateCreation, int densite, double superficie) {
        this.idCodeRegion = idCodeRegion;
        CodeRegion = codeRegion;
        Libelle = libelle;
        Accessible = accessible;
        DateCreation = dateCreation;
        Densite = densite;
        Superficie = superficie;
    }
}
