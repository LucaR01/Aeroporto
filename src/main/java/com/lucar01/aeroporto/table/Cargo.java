package com.lucar01.aeroporto.table;

import java.sql.Time;

public class Cargo {

    private int codCargo;
    private int numDipendenti;

    private int codAereo;
    private int codLogistica;

    private Time oraInizio;
    private Time oraFine;

    public Cargo(int codCargo, int numDipendenti, int codAereo, int codLogistica, Time oraInizio, Time oraFine) {
        this.codCargo = codCargo;
        this.numDipendenti = numDipendenti;
        this.codAereo = codAereo;
        this.codLogistica = codLogistica;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public int getCodCargo() {
        return codCargo;
    }

    public int getNumDipendenti() {
        return numDipendenti;
    }

    public int getCodAereo() {
        return codAereo;
    }

    public int getCodLogistica() {
        return codLogistica;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }
}
