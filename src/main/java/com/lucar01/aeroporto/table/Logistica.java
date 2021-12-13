package com.lucar01.aeroporto.table;

import java.sql.Time;

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

    public int getNum_Personale() {
        return numPersonale;
    }

    public String getMateriali() {
        return materiali;
    }

    // Metto quantità con l'accento soltanto perchè così l'ho scritto nel database e se non lo scrivo uguale, non riesce a prendermi i dati e metterli nella colonna della tableView.
    public int getQuantità() {
        return quantity;
    }

    public int getCodCompagnia() {
        return codCompagnia;
    }

    public Time getOra_inizio() {
        return oraInizio;
    }

    public Time getOra_fine() {
        return oraFine;
    }
}
