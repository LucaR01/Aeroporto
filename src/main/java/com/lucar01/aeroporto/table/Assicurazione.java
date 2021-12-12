package com.lucar01.aeroporto.table;

import com.dlsc.formsfx.view.controls.SimpleDateControl;
import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class Assicurazione implements Tables{

    private final int codAssicurazione;
    private final String nome;
    private final String partitaIVA;
    private final Time oraInizio;
    private final Time Ora_fine;

    public Assicurazione(int codAssicurazione, String nome, String partitaIVA, Time oraInizio, Time Ora_fine) {
        this.codAssicurazione = codAssicurazione;
        this.nome = nome;
        this.partitaIVA = partitaIVA;
        this.oraInizio = oraInizio;
        this.Ora_fine = Ora_fine;
    }

    public int getCodAssicurazione() {
        return this.codAssicurazione;
    }

    public String getNome(){
        return this.nome;
    }

    public String getPartita_IVA() {
        return this.partitaIVA;
    }

    public Time getOra_inizio() {
        return this.oraInizio;
    }

    public Time getOra_fine() {
        return this.Ora_fine;
    }
}
