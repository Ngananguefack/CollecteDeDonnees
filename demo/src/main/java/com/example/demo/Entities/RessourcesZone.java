package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RessourcesZone {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idRessourcesZone;
    public String Ressource;
    public String Carateristique;
    public String UtilisationActuelle;
    public String AccesControler;
    public String Archive;

    public RessourcesZone() {

    }

    @Override
    public String toString() {
        return "RessourcesZone{" +
                "idRessourcesZone=" + idRessourcesZone +
                ", Ressource='" + Ressource + '\'' +
                ", Carateristique='" + Carateristique + '\'' +
                ", UtilisationActuelle='" + UtilisationActuelle + '\'' +
                ", AccesControler=" + AccesControler +
                ", Archive=" + Archive +
                '}';
    }

    public void setIdRessourcesZone(int idRessourcesZone) {
        this.idRessourcesZone = idRessourcesZone;
    }

    public String getRessource() {
        return Ressource;
    }

    public void setRessource(String ressource) {
        Ressource = ressource;
    }

    public String getCarateristique() {
        return Carateristique;
    }

    public void setCarateristique(String carateristique) {
        Carateristique = carateristique;
    }

    public String getUtilisationActuelle() {
        return UtilisationActuelle;
    }

    public void setUtilisationActuelle(String utilisationActuelle) {
        UtilisationActuelle = utilisationActuelle;
    }

    public String getAccesControler() {
        return AccesControler;
    }

    public void setAccesControler(String string) {
        AccesControler = string;
    }

    public String getArchive() {
        return Archive;
    }

    public void setArchive(String string) {
        Archive = string;
    }

    public RessourcesZone(int idRessourcesZone, String ressource, String carateristique, String utilisationActuelle, String accesControler, String archive) {
        this.idRessourcesZone = idRessourcesZone;
        Ressource = ressource;
        Carateristique = carateristique;
        UtilisationActuelle = utilisationActuelle;
        AccesControler = accesControler;
        Archive = archive;
    }
}
