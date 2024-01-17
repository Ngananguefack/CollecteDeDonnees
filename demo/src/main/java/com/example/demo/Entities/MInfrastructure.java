package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MInfrastructure {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idMInfrastructure;
    public String MInfrastructure;

    public MInfrastructure(int idMInfrastructure, String MInfrastructure) {
        this.idMInfrastructure = idMInfrastructure;
        this.MInfrastructure = MInfrastructure;
    }

    public MInfrastructure() {

    }

    @Override
    public String toString() {
        return "MInfrastructure{" +
                "idMInfrastructure=" + idMInfrastructure +
                ", MInfrastructure='" + MInfrastructure + '\'' +
                '}';
    }

    public void setIdMInfrastructure(int idMInfrastructure) {
        this.idMInfrastructure = idMInfrastructure;
    }

    public String getMInfrastructure() {
        return MInfrastructure;
    }

    public void setMInfrastructure(String MInfrastructure) {
        this.MInfrastructure = MInfrastructure;
    }
}
