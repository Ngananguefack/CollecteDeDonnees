package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Pays {


    public int NumEnregistrement;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int CodePays;

    public String Libelle;

    public int Masculin;

    public int Feminin;

    public  int Total;
    public String Accessible;

    public  String DateCreation;
    public String Densite;
    public int Superficie;
    public  int NbRegion;
    public  int NbCommune;
    public  int NbDepartement;
    public int NbLocalite;
    public String DateIndependance;
    public String DateReunification;
    public String DateUnification;

    public Pays() {

    }

    public Pays(int numEnregistrement, int codePays, String libelle, int masculin, int feminin, int total, String accessible, String dateCreation, String densite, int superficie, int nbRegion, int nbCommune, int nbDepartement, int nbLocalite, String dateIndependance, String dateReunification, String dateUnification) {
        NumEnregistrement = numEnregistrement;
        CodePays = codePays;
        Libelle = libelle;
        Masculin = masculin;
        Feminin = feminin;
        Total = total;
        Accessible = accessible;
        DateCreation = dateCreation;
        Densite = densite;
        Superficie = superficie;
        NbRegion = nbRegion;
        NbCommune = nbCommune;
        NbDepartement = nbDepartement;
        NbLocalite = nbLocalite;
        DateIndependance = dateIndependance;
        DateReunification = dateReunification;
        DateUnification = dateUnification;
    }

    public int getNumEnregistrement() {
        return NumEnregistrement;
    }

    public int getCodePays() {
        return CodePays;
    }

    public String getLibelle() {
        return Libelle;
    }

    public int getMasculin() {
        return Masculin;
    }

    public int getFeminin() {
        return Feminin;
    }

    public int getTotal() {
        return Total;
    }

    public String getAccessible() {
        return Accessible;
    }

    public String getDateCreation() {
        return DateCreation;
    }

    public String getDensite() {
        return Densite;
    }

    public int getSuperficie() {
        return Superficie;
    }

    public int getNbRegion() {
        return NbRegion;
    }

    public int getNbCommune() {
        return NbCommune;
    }

    public int getNbDepartement() {
        return NbDepartement;
    }

    public int getNbLocalite() {
        return NbLocalite;
    }

    public String getDateIndependance() {
        return DateIndependance;
    }

    public String getDateReunification() {
        return DateReunification;
    }

    public String getDateUnification() {
        return DateUnification;
    }


}
