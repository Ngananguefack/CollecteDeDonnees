package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Pays {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int CodePays;
    public String Libelle;
    public String Accessible;
    public String Densite;
    public int Superficie;
    public String DateIndependance;
    public String DateReunification;
    public String DateUnification;

    public Pays() {

    }

    public void setSuperficie(int superficie) {
        Superficie = superficie;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "CodePays=" + CodePays +
                ", Libelle='" + Libelle + '\'' +
                ", Accessible=" + Accessible +
                ", Densite='" + Densite + '\'' +
                ", Superficie=" + Superficie +
                ", DateIndependance=" + DateIndependance +
                ", DateReunification=" + DateReunification +
                ", DateUnification=" + DateUnification +
                '}';
    }

    public void setCodePays(int codePays) {
        CodePays = codePays;
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

    public String getDensite() {
        return Densite;
    }

    public void setDensite(String densite) {
        Densite = densite;
    }

    public int getSuperficie() {
        return Superficie;
    }

    public String getDateIndependance() {
        return DateIndependance;
    }

    public void setDateIndependance(String string) {
        DateIndependance = string;
    }

    public String getDateReunification() {
        return DateReunification;
    }

    public void setDateReunification(String string) {
        DateReunification = string;
    }

    public String getDateUnification() {
        return DateUnification;
    }

    public void setDateUnification(String string) {
        DateUnification = string;
    }

    public Pays(int codePays, String libelle, String accessible, String densite, int superficie, String dateIndependance, String dateReunification, String dateUnification) {
        CodePays = codePays;
        Libelle = libelle;
        Accessible = accessible;
        Densite = densite;
        Superficie = superficie;
        DateIndependance = dateIndependance;
        DateReunification = dateReunification;
        DateUnification = dateUnification;
    }
}
