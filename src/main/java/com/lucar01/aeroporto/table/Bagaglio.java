package com.lucar01.aeroporto.table;

public class Bagaglio implements Tables{

    private int codBagaglio;
    private int peso;
    private String codiceFiscale;

    public Bagaglio(int codBagaglio, int peso, String codiceFiscale) {
        this.codBagaglio = codBagaglio;
        this.peso = peso;
        this.codiceFiscale = codiceFiscale;
    }

    public int getCodBagaglio() {
        return this.codBagaglio;
    }

    public int getPeso() {
        return this.peso;
    }

    public String getCodiceFiscale() {
        return this.codiceFiscale;
    }
}
