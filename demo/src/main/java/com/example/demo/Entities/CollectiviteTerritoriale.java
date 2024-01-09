package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CollectiviteTerritoriale {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idCollectiviteTerritoriale;
    public String filed;

    public CollectiviteTerritoriale(int idCollectiviteTerritoriale, String filed) {
        this.idCollectiviteTerritoriale = idCollectiviteTerritoriale;
        this.filed = filed;
    }

    public CollectiviteTerritoriale() {

    }

    @Override
    public String toString() {
        return "CollectiviteTerritoriale{" +
                "idCollectiviteTerritoriale=" + idCollectiviteTerritoriale +
                ", filed='" + filed + '\'' +
                '}';
    }

    public int getIdCollectiviteTerritoriale() {
        return idCollectiviteTerritoriale;
    }

    public void setIdCollectiviteTerritoriale(int idCollectiviteTerritoriale) {
        this.idCollectiviteTerritoriale = idCollectiviteTerritoriale;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }
}
