package com.lucar01.aeroporto.table;

import java.sql.Time;

public class CentroControlloAerea {

    private int codCentro;
    private int numPersonale;

    private Time oraInizio;
    private Time oraFine;

    public CentroControlloAerea(int codCentro, int numPersonale, Time oraInizio, Time oraFine) {
        this.codCentro = codCentro;
        this.numPersonale = numPersonale;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public int getCodCentro() {
        return codCentro;
    }

    public int getNumPersonale() {
        return numPersonale;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }
}
