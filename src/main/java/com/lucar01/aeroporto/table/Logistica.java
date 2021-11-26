package com.lucar01.aeroporto.table;

import java.sql.Time;
import java.util.Optional;

public class Logistica {

    private int codLogistica;
    private String nome;
    private int numPersonale;
    private String materiali;
    private int quantity;

    private int codCompagnia;
    private Optional<Time> oraInizio;
    private Optional<Time> oraFine;

    public Logistica(int codLogistica, String nome, int numPersonale, String materiali, int quantity, int codCompagnia, Optional<Time> oraInizio, Optional<Time> oraFine) {
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

    public Optional<Time> getOraInizio() {
        return oraInizio;
    }

    public Optional<Time> getOraFine() {
        return oraFine;
    }
}
