package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Gouverneur")
public class Gouverneur {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idGouverneur;
    public String NomGouverneur;
    public String PrenomGouverneur;
    public String Elu_Nomme;

    public Gouverneur(int idGouverneur, String nomGouverneur, String prenomGouverneur, String elu_Nomme) {
        this.idGouverneur = idGouverneur;
        NomGouverneur = nomGouverneur;
        PrenomGouverneur = prenomGouverneur;
        Elu_Nomme = elu_Nomme;
    }

    public Gouverneur() {

    }

    @Override
    public String toString() {
        return "Gouverneur{" +
                "idGouverneur=" + idGouverneur +
                ", NomGouverneur='" + NomGouverneur + '\'' +
                ", PrenomGouverneur='" + PrenomGouverneur + '\'' +
                ", Elu_Nomme=" + Elu_Nomme +
                '}';
    }


    public void setIdGouverneur(int idGouverneur) {
        this.idGouverneur = idGouverneur;
    }

    public String getNomGouverneur() {
        return NomGouverneur;
    }

    public void setNomGouverneur(String nomGouverneur) {
        NomGouverneur = nomGouverneur;
    }

    public String getPrenomGouverneur() {
        return PrenomGouverneur;
    }

    public void setPrenomGouverneur(String prenomGouverneur) {
        PrenomGouverneur = prenomGouverneur;
    }

    public String getElu_Nomme() {
        return Elu_Nomme;
    }

    public void setElu_Nomme(String string) {
        Elu_Nomme = string;
    }

}
