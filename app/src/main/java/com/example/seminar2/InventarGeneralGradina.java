package com.example.seminar2;

import java.io.Serializable;

public class InventarGeneralGradina implements Serializable {

    String scule;
    String insecte;
    String plante;
    int suprafata;
    boolean vreme_ploaie;

    public InventarGeneralGradina(
            String scule,
            String insecte,
            String plante,
            int suprafata,
            boolean vreme_ploaie) {

        this.scule = scule;
        this.insecte = insecte;
        this.plante = plante;
        this.suprafata = suprafata;
        this.vreme_ploaie = vreme_ploaie;
    }

    public String afisare() {
        return "Scule: " + scule +
                "\nInsecte: " + insecte +
                "\nPlante: " + plante +
                "\nSuprafață: " + suprafata + " mp" +
                "\nVreme ploioasă: " + (vreme_ploaie ? "Da" : "Nu");
    }

    @Override
    public String toString() {
        return afisare();
    }
}