package com.lucar01.aeroporto.table;

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

    public int getCodComponenteAereo() {
        return codComponenteAereo;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public String getTipologia() {
        return tipologia;
    }

    public int getCodAereo() {
        return codAereo;
    }
}
