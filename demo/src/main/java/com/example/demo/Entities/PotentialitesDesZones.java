package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PotentialitesDesZones {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idPotentialitesDesZones;
    public String Potentialite;
    public String Ressource;

    public PotentialitesDesZones(int idPotentialitesDesZones, String potentialite, String ressource) {
        this.idPotentialitesDesZones = idPotentialitesDesZones;
        Potentialite = potentialite;
        Ressource = ressource;
    }

    public PotentialitesDesZones() {

    }

    @Override
    public String toString() {
        return "PotentialitesDesZones{" +
                "idPotentialitesDesZones=" + idPotentialitesDesZones +
                ", Potentialite='" + Potentialite + '\'' +
                ", Ressource='" + Ressource + '\'' +
                '}';
    }


    public void setIdPotentialitesDesZones(int idPotentialitesDesZones) {
        this.idPotentialitesDesZones = idPotentialitesDesZones;
    }

    public String getPotentialite() {
        return Potentialite;
    }

    public void setPotentialite(String potentialite) {
        Potentialite = potentialite;
    }

    public String getRessource() {
        return Ressource;
    }

    public void setRessource(String ressource) {
        Ressource = ressource;
    }
}
