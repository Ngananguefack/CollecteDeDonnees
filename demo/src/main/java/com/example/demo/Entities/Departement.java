package com.example.demo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Departement")
public class Departement {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idDepartement;
    public String NomDepartement;

    public Departement(int idDepartement, String nomDepartement) {
        this.idDepartement = idDepartement;
        NomDepartement = nomDepartement;
    }

    public Departement() {

    }

    @Override
    public String toString() {
        return "Departement{" +
                "idDepartement=" + idDepartement +
                ", NomDepartement='" + NomDepartement + '\'' +
                '}';
    }

    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }

    public String getNomDepartement() {
        return NomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        NomDepartement = nomDepartement;
    }
}
