package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Cadre")
public class Cadre {

    public int NumEnregistrement;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idCadre;
    public  int idSecteur;
    public  int idChapitre;
    public  int idProgramme;
    public  int idAction;
    public  int idActivite;
    public int CNiveau;
    public String Cadre;
    public String Accessible;

    public Cadre(int numEnregistrement, int idCadre, int idSecteur, int idChapitre, int idProgramme, int idAction, int idActivite, int CNiveau, String cadre, String accessible) {
        NumEnregistrement = numEnregistrement;
        this.idCadre = idCadre;
        this.idSecteur = idSecteur;
        this.idChapitre = idChapitre;
        this.idProgramme = idProgramme;
        this.idAction = idAction;
        this.idActivite = idActivite;
        this.CNiveau = CNiveau;
        Cadre = cadre;
        Accessible = accessible;
    }

    public Cadre() {

    }

    public void setIdCadre(int idCadre) {
        this.idCadre = idCadre;
    }

    public void setCNiveau(int CNiveau) {
        this.CNiveau = CNiveau;
    }

    public void setCadre(String cadre) {
        Cadre = cadre;
    }

    public void setAccessible(String string) {
        Accessible = string;
    }

    public void setNumEnregistrement(int numEnregistrement) {
        NumEnregistrement = numEnregistrement;
    }

    public void setIdSecteur(int idSecteur) {
        this.idSecteur = idSecteur;
    }

    public void setIdChapitre(int idChapitre) {
        this.idChapitre = idChapitre;
    }

    public void setIdProgramme(int idProgramme) {
        this.idProgramme = idProgramme;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public int getNumEnregistrement() {
        return NumEnregistrement;
    }

    public int getIdCadre() {
        return idCadre;
    }

    public int getIdSecteur() {
        return idSecteur;
    }

    public int getIdChapitre() {
        return idChapitre;
    }

    public int getIdProgramme() {
        return idProgramme;
    }

    public int getIdAction() {
        return idAction;
    }

    public int getIdActivite() {
        return idActivite;
    }

    public int getCNiveau() {
        return CNiveau;
    }

    public String getCadre() {
        return Cadre;
    }

    public String getAccessible() {
        return Accessible;
    }


    @Override
    public String toString() {
        return "Cadre{" +
                "idCadre=" + idCadre +
                ", CNiveau=" + CNiveau +
                ", Cadre='" + Cadre + '\'' +
                ", Accessible=" + Accessible +
                '}';
    }
}
