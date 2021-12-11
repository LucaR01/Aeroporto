package com.lucar01.aeroporto.table;

import java.sql.Time;

public class Assicurazione implements Tables{

    private final int codAssicurazione;
    private final String nome;
    private final String partitaIva;
    private final Time oraInizio;
    private final Time oraFine;

    public Assicurazione(int codAssicurazione, String nome, String partitaIva, Time oraInizio, Time oraFine) {
        this.codAssicurazione = codAssicurazione;
        this.nome = nome;
        this.partitaIva = partitaIva;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public int getCodAssicurazione() {
        return this.codAssicurazione;
    }

    public String getNome(){
        return this.nome;
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
