package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Chefferie")
public class Chefferie {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idChefferie;
    public String LibelleChefferie;
    public String Classification;
    public String NActeDeterminant;
    public String NomDuChef;
    public String Qualification;
    public String AnneAuTrone;

    public Chefferie(int idChefferie, String libelleChefferie, String classification, String NActeDeterminant, String nomDuChef, String qualification, String anneAuTrone) {
        this.idChefferie = idChefferie;
        LibelleChefferie = libelleChefferie;
        Classification = classification;
        this.NActeDeterminant = NActeDeterminant;
        NomDuChef = nomDuChef;
        Qualification = qualification;
        AnneAuTrone = anneAuTrone;
    }

    public Chefferie() {

    }

    public String getLibelleChefferie() {
        return LibelleChefferie;
    }

    public String getClassification() {
        return Classification;
    }

    public String getNActeDeterminant() {
        return NActeDeterminant;
    }

    public String getNomDuChef() {
        return NomDuChef;
    }

    public String getQualification() {
        return Qualification;
    }

    public String getAnneAuTrone() {
        return AnneAuTrone;
    }

    public void setIdChefferie(int idChefferie) {
        this.idChefferie = idChefferie;
    }

    public void setLibelleChefferie(String libelleChefferie) {
        LibelleChefferie = libelleChefferie;
    }

    public void setClassification(String classification) {
        Classification = classification;
    }

    public void setNActeDeterminant(String NActeDeterminant) {
        this.NActeDeterminant = NActeDeterminant;
    }

    public void setNomDuChef(String nomDuChef) {
        NomDuChef = nomDuChef;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public void setAnneAuTrone(String anneAuTrone) {
        AnneAuTrone = anneAuTrone;
    }

    @Override
    public String toString() {
        return "Chefferie{" +
                "idChefferie=" + idChefferie +
                ", LibelleChefferie='" + LibelleChefferie + '\'' +
                ", Classification='" + Classification + '\'' +
                ", NActeDeterminant='" + NActeDeterminant + '\'' +
                ", NomDuChef='" + NomDuChef + '\'' +
                ", Qualification='" + Qualification + '\'' +
                ", AnneAuTrone='" + AnneAuTrone + '\'' +
                '}';
    }
}
