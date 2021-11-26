package com.lucar01.aeroporto.table;

import java.sql.Time;
import java.util.Optional;

public class Tratta {

    private int codTratta;
    private String cittaPartenza; //TODO: insomma,  cityStart, startingCity
    private String cittaDestinazione;
    private String aeroportoPartenza;
    private String aeroportoDestinazione;

    private Optional<Time> oraPartenza;
    private Optional<Time> oraFine;

    public Tratta(int codTratta, String cittaPartenza, String cittaDestinazione, String aeroportoPartenza, String aeroportoDestinazione, Optional<Time> oraPartenza, Optional<Time> oraFine) {
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

    public Optional<Time> getOraPartenza() {
        return oraPartenza;
    }

    public Optional<Time> getOraFine() {
        return oraFine;
    }
}
