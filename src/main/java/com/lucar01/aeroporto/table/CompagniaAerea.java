package com.lucar01.aeroporto.table;

import java.sql.Time;
import java.util.Optional;

public class CompagniaAerea {

    private int codCompagnia;
    private String nome;
    private String partitaIva;
    private int numPersonale;
    private int numAerei;

    private int codAssicurazione;

    private Optional<Time> oraInizio;
    private Optional<Time> oraFine;

    public CompagniaAerea(int codCompagnia, String nome, String partitaIva, int numPersonale, int numAerei, int codAssicurazione, Optional<Time> oraInizio, Optional<Time> oraFine) {
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

    public Optional<Time> getOraInizio() {
        return oraInizio;
    }

    public Optional<Time> getOraFine() {
        return oraFine;
    }
}
