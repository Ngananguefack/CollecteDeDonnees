package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ministere {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idMinistere;
    public String Designation;
    public String SiteInternet;
    public String Localisation;
    public String Ministere;
    public String SecretariatEtat;

    public Ministere() {

    }

    @Override
    public String toString() {
        return "Ministere{" +
                "idMinistere=" + idMinistere +
                ", Designation='" + Designation + '\'' +
                ", SiteInternet='" + SiteInternet + '\'' +
                ", Localisation='" + Localisation + '\'' +
                ", Ministere='" + Ministere + '\'' +
                ", SecretariatEtat='" + SecretariatEtat + '\'' +
                '}';
    }

    public void setIdMinistere(int idMinistere) {
        this.idMinistere = idMinistere;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getSiteInternet() {
        return SiteInternet;
    }

    public void setSiteInternet(String siteInternet) {
        SiteInternet = siteInternet;
    }

    public String getLocalisation() {
        return Localisation;
    }

    public void setLocalisation(String localisation) {
        Localisation = localisation;
    }

    public String getMinistere() {
        return Ministere;
    }

    public void setMinistere(String ministere) {
        Ministere = ministere;
    }

    public String getSecretariatEtat() {
        return SecretariatEtat;
    }

    public void setSecretariatEtat(String secretariatEtat) {
        SecretariatEtat = secretariatEtat;
    }

    public Ministere(int idMinistere, String designation, String siteInternet, String localisation, String ministere, String secretariatEtat) {
        this.idMinistere = idMinistere;
        Designation = designation;
        SiteInternet = siteInternet;
        Localisation = localisation;
        Ministere = ministere;
        SecretariatEtat = secretariatEtat;
    }
}
