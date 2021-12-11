package com.lucar01.aeroporto.table;

import java.sql.Time;

public class Mantenimento implements Tables{

    private final int codMantenimento;

    private final int codAereo;
    private final int codMacchinario;

    private final Time oraInizio;
    private final Time oraFine;

    public Mantenimento(int codMantenimento, int codAereo, int codMacchinario, Time oraInizio, Time oraFine) {
        this.codMantenimento = codMantenimento;
        this.codAereo = codAereo;
        this.codMacchinario = codMacchinario;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public int getCodMantenimento() {
        return codMantenimento;
    }

    public int getCodAereo() {
        return codAereo;
    }

    public int getCodMacchinario() {
        return codMacchinario;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }
}
