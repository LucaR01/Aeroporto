package com.lucar01.aeroporto.table;

import com.mysql.cj.conf.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ComponenteAereo implements Tables{

    private final int codComponenteAereo;
    private final String nome;
    private final int quantity;
    private final boolean isWorking;
    private final String tipologia;

    private final int codAereo;

    public ComponenteAereo(int codComponenteAereo, String nome, int quantity, boolean isWorking, String tipologia, int codAereo) {
        this.codComponenteAereo = codComponenteAereo;
        this.nome = nome;
        this.quantity = quantity;
        this.isWorking = isWorking;
        this.tipologia = tipologia;
        this.codAereo = codAereo;
    }

    public int getCodComponente() {
        return codComponenteAereo;
    }

    public String getNome() {
        return nome;
    }

    // Metto quantità con l'accento soltanto perchè così l'ho scritto nel database e se non lo scrivo uguale, non riesce a prendermi i dati e metterli nella colonna della tableView.
    public int getQuantità() {
        return quantity;
    }

    public boolean isFunzionante() {
        return isWorking;
    }

    public String getTipologia() {
        return tipologia;
    }

    public int getCodAereo() {
        return codAereo;
    }
}
