package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Senateur")
public class Senateur{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idSenateur;

    public String Qualite;

    public String Nom;

    public String Prenom;

    public String Mandat;

    public String Elu_Nomme;

    public Senateur() {

    }

    @Override
    public String toString() {
        return "Senateur{" +
                "idSenateur=" + idSenateur +
                ", Qualite='" + Qualite + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", Mandat='" + Mandat + '\'' +
                ", Elu_Nomme='" + Elu_Nomme + '\'' +
                '}';
    }

    public void setIdSenateur(int idSenateur) {
        this.idSenateur = idSenateur;
    }

    public String getQualite() {
        return Qualite;
    }

    public void setQualite(String qualite) {
        Qualite = qualite;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getMandat() {
        return Mandat;
    }

    public void setMandat(String mandat) {
        Mandat = mandat;
    }

    public String getElu_Nomme() {
        return Elu_Nomme;
    }

    public void setElu_Nomme(String elu_Nomme) {
        Elu_Nomme = elu_Nomme;
    }

    public Senateur(int idSenateur, String qualite, String nom, String prenom, String mandat, String elu_Nomme) {
        this.idSenateur = idSenateur;
        Qualite = qualite;
        Nom = nom;
        Prenom = prenom;
        Mandat = mandat;
        Elu_Nomme = elu_Nomme;
    }
}
