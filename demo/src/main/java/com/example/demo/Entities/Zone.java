package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Zone {

    public int NumEnregistrement;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idZone;

    public int codePays;
    public  String Pays;
    public  int CodeRegion;
    public String Region;
    public  int CodeDepartement;

    public String Departement;

    public int codeCommune;
    public  String Commune;

    public int codeLocalite;

    public String Localite;
    public String Zone;
    public String ZNiveau;
    public int NbRegion;
    public int NbDepartement;
    public int NbCommune;
    public int NbLocalite;
    public int Superficies;

    public  int Densite;
    public String Adresse;

    public String Date;
    public  String Email;
    public  int Telephone;
    public  String icone;
    public  String image;

    public int Masculin;
    public int Feminin;
    public  int Total;
    public  String Accessible;

    public  String DelimitationJSON;
    public  String Limites;
    public  String Localisation;


    public Zone(int numEnregistrement, int idZone, int codePays, String pays, int codeRegion, String region, int codeDepartement, String departement, int codeCommune, String commune, int codeLocalite, String localite, String zone, String ZNiveau, int nbRegion, int nbDepartement, int nbCommune, int nbLocalite, int superficies, int densite, String adresse, String date, String email, int telephone, String icone, String image, int masculin, int feminin, int total, String accessible, String delimitationJSON, String limites, String localisation) {
        NumEnregistrement = numEnregistrement;
        this.idZone = idZone;
        this.codePays = codePays;
        Pays = pays;
        CodeRegion = codeRegion;
        Region = region;
        CodeDepartement = codeDepartement;
        Departement = departement;
        this.codeCommune = codeCommune;
        Commune = commune;
        this.codeLocalite = codeLocalite;
        Localite = localite;
        Zone = zone;
        this.ZNiveau = ZNiveau;
        NbRegion = nbRegion;
        NbDepartement = nbDepartement;
        NbCommune = nbCommune;
        NbLocalite = nbLocalite;
        Superficies = superficies;
        Densite = densite;
        Adresse = adresse;
        Date = date;
        Email = email;
        Telephone = telephone;
        this.icone = icone;
        this.image = image;
        Masculin = masculin;
        Feminin = feminin;
        Total = total;
        Accessible = accessible;
        DelimitationJSON = delimitationJSON;
        Limites = limites;
        Localisation = localisation;
    }

    public Zone() {

    }

    public int getNumEnregistrement() {
        return NumEnregistrement;
    }

    public void setNumEnregistrement(int numEnregistrement) {
        NumEnregistrement = numEnregistrement;
    }

    public int getIdZone() {
        return idZone;
    }

    public void setIdZone(int idZone) {
        this.idZone = idZone;
    }

    public int getCodePays() {
        return codePays;
    }

    public void setCodePays(int codePays) {
        this.codePays = codePays;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String pays) {
        Pays = pays;
    }

    public int getCodeRegion() {
        return CodeRegion;
    }

    public void setCodeRegion(int codeRegion) {
        CodeRegion = codeRegion;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public int getCodeDepartement() {
        return CodeDepartement;
    }

    public void setCodeDepartement(int codeDepartement) {
        CodeDepartement = codeDepartement;
    }

    public String getDepartement() {
        return Departement;
    }

    public void setDepartement(String departement) {
        Departement = departement;
    }

    public int getCodeCommune() {
        return codeCommune;
    }

    public void setCodeCommune(int codeCommune) {
        this.codeCommune = codeCommune;
    }

    public String getCommune() {
        return Commune;
    }

    public void setCommune(String commune) {
        Commune = commune;
    }

    public int getCodeLocalite() {
        return codeLocalite;
    }

    public void setCodeLocalite(int codeLocalite) {
        this.codeLocalite = codeLocalite;
    }

    public String getLocalite() {
        return Localite;
    }

    public void setLocalite(String localite) {
        Localite = localite;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public String getZNiveau() {
        return ZNiveau;
    }

    public void setZNiveau(String ZNiveau) {
        this.ZNiveau = ZNiveau;
    }

    public int getNbRegion() {
        return NbRegion;
    }

    public void setNbRegion(int nbRegion) {
        NbRegion = nbRegion;
    }

    public int getNbDepartement() {
        return NbDepartement;
    }

    public void setNbDepartement(int nbDepartement) {
        NbDepartement = nbDepartement;
    }

    public int getNbCommune() {
        return NbCommune;
    }

    public void setNbCommune(int nbCommune) {
        NbCommune = nbCommune;
    }

    public int getNbLocalite() {
        return NbLocalite;
    }

    public void setNbLocalite(int nbLocalite) {
        NbLocalite = nbLocalite;
    }

    public int getSuperficies() {
        return Superficies;
    }

    public void setSuperficies(int superficies) {
        Superficies = superficies;
    }

    public int getDensite() {
        return Densite;
    }

    public void setDensite(int densite) {
        Densite = densite;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getTelephone() {
        return Telephone;
    }

    public void setTelephone(int telephone) {
        Telephone = telephone;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMasculin() {
        return Masculin;
    }

    public void setMasculin(int masculin) {
        Masculin = masculin;
    }

    public int getFeminin() {
        return Feminin;
    }

    public void setFeminin(int feminin) {
        Feminin = feminin;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getAccessible() {
        return Accessible;
    }

    public void setAccessible(String accessible) {
        Accessible = accessible;
    }

    public String getDelimitationJSON() {
        return DelimitationJSON;
    }

    public void setDelimitationJSON(String delimitationJSON) {
        DelimitationJSON = delimitationJSON;
    }

    public String getLimites() {
        return Limites;
    }

    public void setLimites(String limites) {
        Limites = limites;
    }

    public String getLocalisation() {
        return Localisation;
    }

    public void setLocalisation(String localisation) {
        Localisation = localisation;
    }
}
