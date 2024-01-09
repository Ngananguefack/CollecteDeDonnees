package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PaysagesUrbains {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idPaysagesUrbains;
    public String UnitePaysage;
    public String Utilisation;
    public String Potentialite;
    public String Utilisateur;
    public String Probleme;
    public String Causes;
    public String Consequences;
    public String Solutions;

    public PaysagesUrbains() {

    }

    @Override
    public String toString() {
        return "PaysagesUrbains{" +
                "idPaysagesUrbains=" + idPaysagesUrbains +
                ", UnitePaysage='" + UnitePaysage + '\'' +
                ", Utilisation='" + Utilisation + '\'' +
                ", Potentialite='" + Potentialite + '\'' +
                ", Utilisateur='" + Utilisateur + '\'' +
                ", Probleme='" + Probleme + '\'' +
                ", Causes='" + Causes + '\'' +
                ", Consequences='" + Consequences + '\'' +
                ", Solutions='" + Solutions + '\'' +
                '}';
    }

    public void setIdPaysagesUrbains(int idPaysagesUrbains) {
        this.idPaysagesUrbains = idPaysagesUrbains;
    }

    public String getUnitePaysage() {
        return UnitePaysage;
    }

    public void setUnitePaysage(String unitePaysage) {
        UnitePaysage = unitePaysage;
    }

    public String getUtilisation() {
        return Utilisation;
    }

    public void setUtilisation(String utilisation) {
        Utilisation = utilisation;
    }

    public String getPotentialite() {
        return Potentialite;
    }

    public void setPotentialite(String potentialite) {
        Potentialite = potentialite;
    }

    public String getUtilisateur() {
        return Utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        Utilisateur = utilisateur;
    }

    public String getProbleme() {
        return Probleme;
    }

    public void setProbleme(String probleme) {
        Probleme = probleme;
    }

    public String getCauses() {
        return Causes;
    }

    public void setCauses(String causes) {
        Causes = causes;
    }

    public String getConsequences() {
        return Consequences;
    }

    public void setConsequences(String consequences) {
        Consequences = consequences;
    }

    public String getSolutions() {
        return Solutions;
    }

    public void setSolutions(String solutions) {
        Solutions = solutions;
    }

    public PaysagesUrbains(int idPaysagesUrbains, String unitePaysage, String utilisation, String potentialite, String utilisateur, String probleme, String causes, String consequences, String solutions) {
        this.idPaysagesUrbains = idPaysagesUrbains;
        UnitePaysage = unitePaysage;
        Utilisation = utilisation;
        Potentialite = potentialite;
        Utilisateur = utilisateur;
        Probleme = probleme;
        Causes = causes;
        Consequences = consequences;
        Solutions = solutions;
    }
}
