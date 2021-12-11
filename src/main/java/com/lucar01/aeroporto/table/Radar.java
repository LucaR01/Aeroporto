package com.lucar01.aeroporto.table;

public class Radar implements Tables{

    private final int codRadar;
    private final int raggio;
    private final int frequenza;
    private final int altitudine;
    private final String tipologia;

    private final int codAereo;

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
