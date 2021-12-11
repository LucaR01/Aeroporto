package com.lucar01.aeroporto.table;

import java.sql.Time;

public class CompagniaAerea implements Tables{

    private final int codCompagnia;
    private final String nome;
    private final String partitaIva;
    private final int numPersonale;
    private final int numAerei;

    private final int codAssicurazione;

    private final Time oraInizio;
    private final Time oraFine;

    public CompagniaAerea(int codCompagnia, String nome, String partitaIva, int numPersonale, int numAerei, int codAssicurazione, Time oraInizio, Time oraFine) {
        this.codCompagnia = codCompagnia;
        this.nome = nome;
        this.partitaIva = partitaIva;
        this.numPersonale = numPersonale;
        this.numAerei = numAerei;
        this.codAssicurazione = codAssicurazione;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public int getCodCompagnia() {
        return codCompagnia;
    }

    public String getNome() {
        return nome;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public int getNumPersonale() {
        return numPersonale;
    }

    public int getNumAerei() {
        return numAerei;
    }

    public int getCodAssicurazione() {
        return codAssicurazione;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }
}
