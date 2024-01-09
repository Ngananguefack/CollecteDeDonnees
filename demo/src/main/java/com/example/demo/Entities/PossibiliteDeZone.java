package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PossibiliteDeZone {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idPossibiliteDeZone;
    public String Specification;
    public String PossibiliteEmploi;
    public String AxeStrategique;

    public PossibiliteDeZone() {

    }

    @Override
    public String toString() {
        return "PossibiliteDeZone{" +
                "idPossibiliteDeZone=" + idPossibiliteDeZone +
                ", Specification='" + Specification + '\'' +
                ", PossibiliteEmploi='" + PossibiliteEmploi + '\'' +
                ", AxeStrategique='" + AxeStrategique + '\'' +
                '}';
    }

    public void setIdPossibiliteDeZone(int idPossibiliteDeZone) {
        this.idPossibiliteDeZone = idPossibiliteDeZone;
    }

    public String getSpecification() {
        return Specification;
    }

    public void setSpecification(String specification) {
        Specification = specification;
    }

    public String getPossibiliteEmploi() {
        return PossibiliteEmploi;
    }

    public void setPossibiliteEmploi(String possibiliteEmploi) {
        PossibiliteEmploi = possibiliteEmploi;
    }

    public String getAxeStrategique() {
        return AxeStrategique;
    }

    public void setAxeStrategique(String axeStrategique) {
        AxeStrategique = axeStrategique;
    }

    public PossibiliteDeZone(int idPossibiliteDeZone, String specification, String possibiliteEmploi, String axeStrategique) {
        this.idPossibiliteDeZone = idPossibiliteDeZone;
        Specification = specification;
        PossibiliteEmploi = possibiliteEmploi;
        AxeStrategique = axeStrategique;
    }
}
