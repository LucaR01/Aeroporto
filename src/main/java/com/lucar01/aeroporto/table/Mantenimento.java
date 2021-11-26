package com.lucar01.aeroporto.table;

import java.sql.Time;
import java.util.Optional;

public class Mantenimento {

    private int codMantenimento;

    private int codAereo;
    private int codMacchinario;

    private Optional<Time> oraInizio;
    private Optional<Time> oraFine;

    public Mantenimento(int codMantenimento, int codAereo, int codMacchinario, Optional<Time> oraInizio, Optional<Time> oraFine) {
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

    public Optional<Time> getOraInizio() {
        return oraInizio;
    }

    public Optional<Time> getOraFine() {
        return oraFine;
    }
}
