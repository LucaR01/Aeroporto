package com.lucar01.aeroporto.table;

import java.sql.Time;
import java.util.Optional;

public class TorreControllo {

    private int codTorre;
    private int numDipendenti;
    private int numAereiInComunicazione;

    private Optional<Time> orarioInizio;
    private Optional<Time> orarioFine;

    public TorreControllo(int codTorre, int numDipendenti, int numAereiInComunicazione, Optional<Time> orarioInizio, Optional<Time> orarioFine) {
        this.codTorre = codTorre;
        this.numDipendenti = numDipendenti;
        this.numAereiInComunicazione = numAereiInComunicazione;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
    }

    public int getCodTorre() {
        return codTorre;
    }

    public int getNumDipendenti() {
        return numDipendenti;
    }

    public int getNumAereiInComunicazione() {
        return numAereiInComunicazione;
    }

    public Optional<Time> getOrarioInizio() {
        return orarioInizio;
    }

    public Optional<Time> getOrarioFine() {
        return orarioFine;
    }
}
