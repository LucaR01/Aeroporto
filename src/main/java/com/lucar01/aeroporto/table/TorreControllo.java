package com.lucar01.aeroporto.table;

import java.sql.Time;

public class TorreControllo implements Tables{

    private final int codTorre;
    private final int numDipendenti;
    private final int numAereiInComunicazione;

    private final Time orarioInizio;
    private final Time orarioFine;

    public TorreControllo(int codTorre, int numDipendenti, int numAereiInComunicazione, Time orarioInizio, Time orarioFine) {
        this.codTorre = codTorre;
        this.numDipendenti = numDipendenti;
        this.numAereiInComunicazione = numAereiInComunicazione;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
    }

    public int getCodTorre() {
        return codTorre;
    }

    public int getNum_dipendenti() {
        return numDipendenti;
    }

    public int getNum_Aerei_in_comunicazione() {
        return numAereiInComunicazione;
    }

    public Time getOrario_inizio() {
        return orarioInizio;
    }

    public Time getOrario_fine() {
        return orarioFine;
    }
}
