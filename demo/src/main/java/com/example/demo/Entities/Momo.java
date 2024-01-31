package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Cadre1")
public class Momo {

    private int numeroEnregistrement;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCadre;
    private int idSecteur;
    private int idChapitre;
    private int idProgramme;
    private int idAction;
    private int idActivite;
    private String cadre;
    private int cNiveau;
    private int accessible;

}
