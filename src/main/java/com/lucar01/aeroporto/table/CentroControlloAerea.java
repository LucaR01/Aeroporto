package com.lucar01.aeroporto.table;

import java.sql.Time;

public class CentroControlloAerea implements Tables{

    private final int codCentro;
    private final int numPersonale;

    private final Time oraInizio;
    private final Time oraFine;

    public CentroControlloAerea(int codCentro, int numPersonale, Time oraInizio, Time oraFine) {
        this.codCentro = codCentro;
        this.numPersonale = numPersonale;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public int getCodCentro() {
        return codCentro;
    }

    public int getNum_Personale() {
        return numPersonale;
    }

    public Time getOrario_inizio() {
        return oraInizio;
    }

    public Time getOrario_fine() {
        return oraFine;
    }
}
