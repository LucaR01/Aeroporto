package com.lucar01.aeroporto.table;

import java.sql.Time;

public class Tratta implements Tables{

    private final int codTratta;
    private final String cittaPartenza;
    private final String cittaDestinazione;
    private final String aeroportoPartenza;
    private final String aeroportoDestinazione;

    private final Time oraPartenza;
    private final Time oraFine;

    public Tratta(int codTratta, String cittaPartenza, String cittaDestinazione, String aeroportoPartenza, String aeroportoDestinazione, Time oraPartenza, Time oraFine) {
        this.codTratta = codTratta;
        this.cittaPartenza = cittaPartenza;
        this.cittaDestinazione = cittaDestinazione;
        this.aeroportoPartenza = aeroportoPartenza;
        this.aeroportoDestinazione = aeroportoDestinazione;
        this.oraPartenza = oraPartenza;
        this.oraFine = oraFine;
    }

    public int getCodTratta() {
        return codTratta;
    }

    public String getCittaPartenza() {
        return cittaPartenza;
    }

    public String getCittaDestinazione() {
        return cittaDestinazione;
    }

    public String getAeroportoPartenza() {
        return aeroportoPartenza;
    }

    public String getAeroportoDestinazione() {
        return aeroportoDestinazione;
    }

    public Time getOraPartenza() {
        return oraPartenza;
    }

    public Time getOraFine() {
        return oraFine;
    }
}
