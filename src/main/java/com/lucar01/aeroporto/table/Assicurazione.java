package com.lucar01.aeroporto.table;

import java.sql.Time;

public class Assicurazione {

    private int codAssicurazione;
    private String partitaIva;
    private Time oraInizio;
    private Time oraFine;

    public Assicurazione(int codAssicurazione, String partitaIva, Time oraInizio, Time oraFine) {
        this.codAssicurazione = codAssicurazione;
        this.partitaIva = partitaIva;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public int getCodAssicurazione() {
        return this.codAssicurazione;
    }

    public String getPartitaIva() {
        return this.partitaIva;
    }

    public Time getOraInizio() {
        return this.oraInizio;
    }

    public Time getOraFine() {
        return this.oraFine;
    }
}
