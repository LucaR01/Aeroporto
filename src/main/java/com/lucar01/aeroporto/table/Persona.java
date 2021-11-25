package com.lucar01.aeroporto.table;

import java.sql.Time;
import java.util.Optional;

public class Persona { //TODO: lasciare un singolo costruttore, quando si sarà fixato tutto.

    private String codiceFiscale;

    private String nome;
    private String cognome;
    private int age;

    private Optional<String> ruolo;
    private Optional<Time> oraInizio;
    private Optional<Time> oraFine;

    //TODO: con optional non va, usare semplicemente string
    public Persona(String codiceFiscale, String nome, String cognome, int age, Optional<String> ruolo, Optional<Time> oraInizio, Optional<Time> oraFine){
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.age = age;
        this.ruolo = ruolo;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public Persona(String codiceFiscale, String nome, String cognome, int age, Optional<String> ruolo){
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.age = age;
        this.ruolo = ruolo;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getAge() {
        return age;
    }

    public Optional<String> getRuolo() {
        return ruolo;
    }

    public Optional<Time> getOraInizio() {
        return oraInizio;
    }

    public Optional<Time> getOraFine() {
        return oraFine;
    }
}
