package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Secteurs {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idSecteurs;
    public String Libelle;

    public Secteurs() {

    }

    @Override
    public String toString() {
        return "Secteurs{" +
                "idSecteurs=" + idSecteurs +
                ", Libelle='" + Libelle + '\'' +
                '}';
    }

    public void setIdSecteurs(int idSecteurs) {
        this.idSecteurs = idSecteurs;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public Secteurs(int idSecteurs, String libelle) {
        this.idSecteurs = idSecteurs;
        Libelle = libelle;
    }
}
