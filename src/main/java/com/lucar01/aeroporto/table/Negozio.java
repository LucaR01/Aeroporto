package com.lucar01.aeroporto.table;

import java.sql.Time;

public class Negozio implements Tables{

    private final int codNegozio;
    private final String prodotti;
    private final Time oraInizio;
    private final Time oraFine;
    private final String tipologia;

    private final int codCompagnia;

    public Negozio(int codNegozio, String prodotti, Time oraInizio, Time oraFine, String tipologia, int codCompagnia) {
        this.codNegozio = codNegozio;
        this.prodotti = prodotti;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.tipologia = tipologia;
        this.codCompagnia = codCompagnia;
    }

    public int getCodNegozio() {
        return codNegozio;
    }

    public String getProdotti() {
        return prodotti;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }

    public String getTipologia() {
        return tipologia;
    }

    public int getCodCompagnia() {
        return codCompagnia;
    }
}
