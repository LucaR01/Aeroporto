package com.lucar01.aeroporto.table;

public class ComponenteAereo { //TODO: mettere tutto in inglese come da buone prassi?

    private int componenteAereo;
    private String nome;
    private int quantity; //TODO: insomma, quantita meglio?
    private boolean isWorking; //TODO: funzionante, isFunzionante?
    private String tipologia;

    private int codAereo;

    public ComponenteAereo(int componenteAereo, String nome, int quantity, boolean isWorking, String tipologia, int codAereo) {
        this.componenteAereo = componenteAereo;
        this.nome = nome;
        this.quantity = quantity;
        this.isWorking = isWorking;
        this.tipologia = tipologia;
        this.codAereo = codAereo;
    }

    public int getComponenteAereo() {
        return componenteAereo;
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
