package com.lucar01.aeroporto.table;

import java.sql.Time;

public class Persona implements Tables{

    private final String codiceFiscale;

    private final String nome;
    private final String cognome;
    private final int age;

    private final String ruolo;
    private final Time oraInizio;
    private final Time oraFine;

    //FOREIGN KEYS
    private final int codCentro;
    private final int codLogistica;
    private final int codMantenimento;
    private final int codNegozio;
    private final int codServizio;
    private final int codTerminal;
    private final int codTorre;
    private final int codVolo;
    private final int codCompagnia;
    private final int codAereo;
    private final int codRadar;
    private final int codSoccorso;

    public Persona(String codiceFiscale, String nome, String cognome, int age, String ruolo, Time oraInizio, Time oraFine,
                   int codCentro, int codLogistica, int codMantenimento, int codNegozio, int codServizio, int codTerminal,
                   int codTorre, int codVolo, int codCompagnia, int codAereo, int codRadar, int codSoccorso){
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.age = age;
        this.ruolo = ruolo;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.codCentro = codCentro;
        this.codLogistica = codLogistica;
        this.codMantenimento = codMantenimento;
        this.codNegozio = codNegozio;
        this.codServizio = codServizio;
        this.codTerminal = codTerminal;
        this.codTorre = codTorre;
        this.codVolo = codVolo;
        this.codCompagnia = codCompagnia;
        this.codAereo = codAereo;
        this.codRadar = codRadar;
        this.codSoccorso = codSoccorso;
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

    public int getEtà() {
        return age;
    }

    public String getRuolo() {
        return ruolo;
    }

    public Time getOra_inizio() {
        return oraInizio;
    }

    public Time getOra_fine() {
        return oraFine;
    }

    public int getCodCentro() {
        return codCentro;
    }

    public int getCodLogistica() {
        return codLogistica;
    }

    public int getCodMantenimento() {
        return codMantenimento;
    }

    public int getCodNegozio() {
        return codNegozio;
    }

    public int getCodServizio() {
        return codServizio;
    }

    public int getCodTerminal() {
        return codTerminal;
    }

    public int getCodTorre() {
        return codTorre;
    }

    public int getCodVolo() {
        return codVolo;
    }

    public int getCodCompagnia() {
        return codCompagnia;
    }

    public int getCodAereo() {
        return codAereo;
    }

    public int getCodRadar() {
        return codRadar;
    }

    public int getCodSoccorso() {
        return codSoccorso;
    }
}
