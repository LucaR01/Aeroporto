package com.lucar01.aeroporto.table;

import java.sql.Time;
import java.util.Optional;

public class ServizioClienti {

    private int codServizio;
    private Optional<Time> oraInzio;
    private Optional<Time> oraFine;

    public ServizioClienti(int codServizio, Optional<Time> oraInzio, Optional<Time> oraFine) {
        this.codServizio = codServizio;
        this.oraInzio = oraInzio;
        this.oraFine = oraFine;
    }

    public int getCodServizio() {
        return codServizio;
    }

    public Optional<Time> getOraInzio() {
        return oraInzio;
    }

    public Optional<Time> getOraFine() {
        return oraFine;
    }
}
