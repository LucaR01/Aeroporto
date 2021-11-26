package com.lucar01.aeroporto.table;

import java.sql.Time;
import java.util.Optional;

public class Soccorsi {

    private int codSoccorso;
    private Optional<Time> orarioInizio;
    private Optional<Time> orarioFine;

    public Soccorsi(int codSoccorso, Optional<Time> orarioInizio, Optional<Time> orarioFine) {
        this.codSoccorso = codSoccorso;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
    }

    public int getCodSoccorso() {
        return codSoccorso;
    }

    public Optional<Time> getOrarioInizio() {
        return orarioInizio;
    }

    public Optional<Time> getOrarioFine() {
        return orarioFine;
    }
}
