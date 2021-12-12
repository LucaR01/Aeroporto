package com.lucar01.aeroporto.table;

import java.sql.Time;

public class Soccorsi implements Tables{

    private final int codSoccorso;
    private final Time orarioInizio;
    private final Time orarioFine;

    public Soccorsi(int codSoccorso, Time orarioInizio, Time orarioFine) {
        this.codSoccorso = codSoccorso;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
    }

    public int getCodSoccorso() {
        return codSoccorso;
    }

    public Time getOrario_inizio() {
        return orarioInizio;
    }

    public Time getOrario_fine() {
        return orarioFine;
    }
}
