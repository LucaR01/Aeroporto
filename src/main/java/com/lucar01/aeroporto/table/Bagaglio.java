package com.lucar01.aeroporto.table;

public class Bagaglio implements Tables{

    private final int codBagaglio;
    private final int peso;
    private final String codiceFiscale;

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
