package com.lucar01.aeroporto.table;

import java.sql.Time;
import java.util.Optional;

public class Negozio {

    private int codNegozio;
    private String prodotti;
    private Optional<Time> oraInizio;
    private Optional<Time> oraFine;
    private String tipologia;

    private int codCompagnia;

    public Negozio(int codNegozio, String prodotti, Optional<Time> oraInizio, Optional<Time> oraFine, String tipologia, int codCompagnia) {
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

    public Optional<Time> getOraInizio() {
        return oraInizio;
    }

    public Optional<Time> getOraFine() {
        return oraFine;
    }

    public String getTipologia() {
        return tipologia;
    }

    public int getCodCompagnia() {
        return codCompagnia;
    }
}
