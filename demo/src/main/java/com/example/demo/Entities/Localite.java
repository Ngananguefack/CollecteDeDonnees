package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Localite")
public class Localite {
    public int NumEnregistrement;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int codesLocalite;
    public int codeCommune;
    public int codeLocalite;
    public String Libelle;
    public int Masculin;
    public int Feminin;
    public int Total;
    public String Chefferie;
    public int PNombreMenage;
    public int PHomme;
    public int PFemme;
    public int PNombrePersHandicape;
    public int PEnfants_5ans;
    public int PEnfants_15ans;
    public float PPolutaion;
    public int IEEcoleMaternelle;
    public int IEEcolePrimaire;
    public int IEEcoleSecondaire;
    public int IEEColeTechnique;
    public int IHForage;
    public int IHPuits;
    public int IHAdductionEau;
    public int IHAutres;
    public int IHAutresDetails;
    public int ICSCSI;
    public int ICSCMA;
    public int ICSHopital;
    public int ICSPrivee;
    public int ICSAutres;
    public int ICSAutresDetails;
    public int ISEPFoyers;
    public int ISEPCentreFemme;
    public int ISEPCentreMultifonctionnel;
    public int ISEPCentreSociaux;
    public int ISEPAutres;
    public int ISEPAutresDetails;
    public int EPMMagasins;
    public int EPMMarches;
    public int EPMAbbatoir;
    public int EPMGareRoutiere;
    public int  EPMParcaBetail;
    public int ACElectrification;

    public int ACTelephone;
    public int  AVRouteBitumee;
    public int AVRouteEnTerreAmenage;
    public int AVPiste;
    public int ERAccessibleTouteSaison;
    public int ERInaccessibleEnSaisonDePluie;
    public int ERInaccessibleEnTouteSaison;

    public int ONombreQuartier;

    public int OExistenceComiteDeveloppement;

    public String Accessible;


    public Localite(int numEnregistrement, int codesLocalite, int codeCommune, int codeLocalite, String libelle, int masculin, int feminin, int total, String chefferie, int PNombreMenage, int PHomme, int PFemme, int PNombrePersHandicape, int PEnfants_5ans, int PEnfants_15ans, float PPolutaion, int IEEcoleMaternelle, int IEEcolePrimaire, int IEEcoleSecondaire, int IEEColeTechnique, int IHForage, int IHPuits, int IHAdductionEau, int IHAutres, int IHAutresDetails, int ICSCSI, int ICSCMA, int ICSHopital, int ICSPrivee, int ICSAutres, int ICSAutresDetails, int ISEPFoyers, int ISEPCentreFemme, int ISEPCentreMultifonctionnel, int ISEPCentreSociaux, int ISEPAutres, int ISEPAutresDetails, int EPMMagasins, int EPMMarches, int EPMAbbatoir, int EPMGareRoutiere, int EPMParcaBetail, int ACElectrification, int ACTelephone, int AVRouteBitumee, int AVRouteEnTerreAmenage, int AVPiste, int ERAccessibleTouteSaison, int ERInaccessibleEnSaisonDePluie, int ERInaccessibleEnTouteSaison, int ONombreQuartier, int OExistenceComitéDeveloppement, String accessible) {
        NumEnregistrement = numEnregistrement;
        this.codesLocalite = codesLocalite;
        this.codeCommune = codeCommune;
        this.codeLocalite = codeLocalite;
        Libelle = libelle;
        Masculin = masculin;
        Feminin = feminin;
        Total = total;
        Chefferie = chefferie;
        this.PNombreMenage = PNombreMenage;
        this.PHomme = PHomme;
        this.PFemme = PFemme;
        this.PNombrePersHandicape = PNombrePersHandicape;
        this.PEnfants_5ans = PEnfants_5ans;
        this.PEnfants_15ans = PEnfants_15ans;
        this.PPolutaion = PPolutaion;
        this.IEEcoleMaternelle = IEEcoleMaternelle;
        this.IEEcolePrimaire = IEEcolePrimaire;
        this.IEEcoleSecondaire = IEEcoleSecondaire;
        this.IEEColeTechnique = IEEColeTechnique;
        this.IHForage = IHForage;
        this.IHPuits = IHPuits;
        this.IHAdductionEau = IHAdductionEau;
        this.IHAutres = IHAutres;
        this.IHAutresDetails = IHAutresDetails;
        this.ICSCSI = ICSCSI;
        this.ICSCMA = ICSCMA;
        this.ICSHopital = ICSHopital;
        this.ICSPrivee = ICSPrivee;
        this.ICSAutres = ICSAutres;
        this.ICSAutresDetails = ICSAutresDetails;
        this.ISEPFoyers = ISEPFoyers;
        this.ISEPCentreFemme = ISEPCentreFemme;
        this.ISEPCentreMultifonctionnel = ISEPCentreMultifonctionnel;
        this.ISEPCentreSociaux = ISEPCentreSociaux;
        this.ISEPAutres = ISEPAutres;
        this.ISEPAutresDetails = ISEPAutresDetails;
        this.EPMMagasins = EPMMagasins;
        this.EPMMarches = EPMMarches;
        this.EPMAbbatoir = EPMAbbatoir;
        this.EPMGareRoutiere = EPMGareRoutiere;
        this.EPMParcaBetail = EPMParcaBetail;
        this.ACElectrification = ACElectrification;
        this.ACTelephone = ACTelephone;
        this.AVRouteBitumee = AVRouteBitumee;
        this.AVRouteEnTerreAmenage = AVRouteEnTerreAmenage;
        this.AVPiste = AVPiste;
        this.ERAccessibleTouteSaison = ERAccessibleTouteSaison;
        this.ERInaccessibleEnSaisonDePluie = ERInaccessibleEnSaisonDePluie;
        this.ERInaccessibleEnTouteSaison = ERInaccessibleEnTouteSaison;
        this.ONombreQuartier = ONombreQuartier;
        this.OExistenceComiteDeveloppement = OExistenceComitéDeveloppement;
        Accessible = accessible;
    }


    public Localite() {
        super();
    }

    @Override
    public String toString() {
        return "Localite{" +
                "NumEnregistrement=" + NumEnregistrement +
                ", codesLocalite=" + codesLocalite +
                ", codeCommune=" + codeCommune +
                ", codeLocalite=" + codeLocalite +
                ", Libelle='" + Libelle + '\'' +
                ", Masculin=" + Masculin +
                ", Feminin=" + Feminin +
                ", Total=" + Total +
                ", Chefferie='" + Chefferie + '\'' +
                ", PNombreMenage=" + PNombreMenage +
                ", PHomme=" + PHomme +
                ", PFemme=" + PFemme +
                ", PNombrePersHandicape=" + PNombrePersHandicape +
                ", PEnfants_5ans=" + PEnfants_5ans +
                ", PEnfants_15ans=" + PEnfants_15ans +
                ", PPolutaion=" + PPolutaion +
                ", IEEcoleMaternelle=" + IEEcoleMaternelle +
                ", IEEcolePrimaire=" + IEEcolePrimaire +
                ", IEEcoleSecondaire=" + IEEcoleSecondaire +
                ", IEEColeTechnique=" + IEEColeTechnique +
                ", IHForage=" + IHForage +
                ", IHPuits=" + IHPuits +
                ", IHAdductionEau=" + IHAdductionEau +
                ", IHAutres=" + IHAutres +
                ", IHAutresDetails=" + IHAutresDetails +
                ", ICSCSI=" + ICSCSI +
                ", ICSCMA=" + ICSCMA +
                ", ICSHopital=" + ICSHopital +
                ", ICSPrivee=" + ICSPrivee +
                ", ICSAutres=" + ICSAutres +
                ", ICSAutresDetails=" + ICSAutresDetails +
                ", ISEPFoyers=" + ISEPFoyers +
                ", ISEPCentreFemme=" + ISEPCentreFemme +
                ", ISEPCentreMultifonctionnel=" + ISEPCentreMultifonctionnel +
                ", ISEPCentreSociaux=" + ISEPCentreSociaux +
                ", ISEPAutres=" + ISEPAutres +
                ", ISEPAutresDetails=" + ISEPAutresDetails +
                ", EPMMagasins=" + EPMMagasins +
                ", EPMMarches=" + EPMMarches +
                ", EPMAbbatoir=" + EPMAbbatoir +
                ", EPMGareRoutiere=" + EPMGareRoutiere +
                ", EPMParcaBetail=" + EPMParcaBetail +
                ", ACElectrification=" + ACElectrification +
                ", ACTelephone=" + ACTelephone +
                ", AVRouteBitumee=" + AVRouteBitumee +
                ", AVRouteEnTerreAmenage=" + AVRouteEnTerreAmenage +
                ", AVPiste=" + AVPiste +
                ", ERAccessibleTouteSaison=" + ERAccessibleTouteSaison +
                ", ERInaccessibleEnSaisonDePluie=" + ERInaccessibleEnSaisonDePluie +
                ", ERInaccessibleEnTouteSaison=" + ERInaccessibleEnTouteSaison +
                ", ONombreQuartier=" + ONombreQuartier +
                ", OExistenceComitéDeveloppement=" + OExistenceComiteDeveloppement +
                ", Accessible='" + Accessible + '\'' +
                '}';
    }
}
