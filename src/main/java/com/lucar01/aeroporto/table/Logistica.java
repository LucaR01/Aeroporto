package com.lucar01.aeroporto.table;

import java.sql.Time;
import java.util.Optional;

public class Logistica implements Tables{

    private final int codLogistica;
    private final String nome;
    private final int numPersonale;
    private final String materiali;
    private final int quantity;

    private final int codCompagnia;
    private final Time oraInizio;
    private final Time oraFine;

    public Logistica(int codLogistica, String nome, int numPersonale, String materiali, int quantity, int codCompagnia, Time oraInizio, Time oraFine) {
        this.codLogistica = codLogistica;
        this.nome = nome;
        this.numPersonale = numPersonale;
        this.materiali = materiali;
        this.quantity = quantity;
        this.codCompagnia = codCompagnia;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public int getCodLogistica() {
        return codLogistica;
    }

    public String getNome() {
        return nome;
    }

    public int getNumPersonale() {
        return numPersonale;
    }

    public String getMateriali() {
        return materiali;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCodCompagnia() {
        return codCompagnia;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }
}
