package com.lucar01.aeroporto.table;

public class Radar {

    private int codRadar;
    private int raggio;
    private int frequenza;
    private int altitudine;
    private String tipologia;

    private int codAereo;

    public Radar(int codRadar, int raggio, int frequenza, int altitudine, String tipologia, int codAereo) {
        this.codRadar = codRadar;
        this.raggio = raggio;
        this.frequenza = frequenza;
        this.altitudine = altitudine;
        this.tipologia = tipologia;
        this.codAereo = codAereo;
    }

    public int getCodRadar() {
        return codRadar;
    }

    public int getRaggio() {
        return raggio;
    }

    public int getFrequenza() {
        return frequenza;
    }

    public int getAltitudine() {
        return altitudine;
    }

    public String getTipologia() {
        return tipologia;
    }

    public int getCodAereo() {
        return codAereo;
    }
}
