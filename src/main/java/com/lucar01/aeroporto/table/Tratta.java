package com.lucar01.aeroporto.table;

import java.sql.Time;

public class Tratta implements Tables{

    private final int codTratta;
    private final String cittaPartenza;
    private final String cittaDestinazione;
    private final String aeroportoPartenza;
    private final String aeroportoDestinazione;

    private final Time oraFine;
    private final Time oraPartenza;

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

    // Metto la lettera con l'accento soltanto perchè così l'ho scritto nel database e se non lo scrivo uguale, non riesce a prendermi i dati e metterli nella colonna della tableView.
    public String getCittà_partenza() {
        return cittaPartenza;
    }

    // Metto la lettera con l'accento soltanto perchè così l'ho scritto nel database e se non lo scrivo uguale, non riesce a prendermi i dati e metterli nella colonna della tableView.
    public String getCittà_destinazione() {
        return cittaDestinazione;
    }

    public String getAeroporto_partenza() {
        return aeroportoPartenza;
    }

    public String getAeroporto_destinazione() {
        return aeroportoDestinazione;
    }

    public Time getOra_fine() {
        return oraFine;
    }

    public Time getOra_partenza() {
        return oraPartenza;
    }
}
