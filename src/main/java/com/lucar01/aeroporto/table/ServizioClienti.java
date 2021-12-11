package com.lucar01.aeroporto.table;

import java.sql.Time;

public class ServizioClienti implements Tables{

    private final int codServizio;
    private final Time oraInzio;
    private final Time oraFine;

    public ServizioClienti(int codServizio, Time oraInzio, Time oraFine) {
        this.codServizio = codServizio;
        this.oraInzio = oraInzio;
        this.oraFine = oraFine;
    }

    public int getCodServizio() {
        return codServizio;
    }

    public Time getOraInzio() {
        return oraInzio;
    }

    public Time getOraFine() {
        return oraFine;
    }
}
