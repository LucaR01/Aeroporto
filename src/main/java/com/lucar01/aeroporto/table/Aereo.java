package com.lucar01.aeroporto.table;

public class Aereo extends AbstractTable{ //TODO: non li metto final perchè poi li devo cambiare con i setter.

    private int codAereo;
    private String nome;
    private int numEquipaggio;
    private int peso;
    private String tipologia;
    private int numPasseggeri;
    private int numMerci;
    private boolean isCommerciale;

    private int codHangar;
    private int codVia;

    public Aereo(int codAereo, String nome, int numEquipaggio, int peso, String tipologia, int numPasseggeri, int numMerci, boolean isCommerciale){
        this.codAereo = codAereo;
        this.nome = nome;
        this.numEquipaggio = numEquipaggio;
        this.peso = peso;
        this.tipologia = tipologia;
        this.numPasseggeri = numPasseggeri;
        this.numMerci = numMerci;
        this.isCommerciale = isCommerciale;
    }

    public int getCodAereo() {
        return this.codAereo;
    }

    public String getNome() {
        return this.nome;
    }

    public int getNumEquipaggio() {
        return this.numEquipaggio;
    }

    public int getPeso() {
        return this.peso;
    }

    public String getTipologia() {
        return this.tipologia;
    }

    public int getNumPasseggeri() {
        return this.numPasseggeri;
    }

    public int getNumMerci() {
        return this.numMerci;
    }

    public boolean isCommerciale() {
        return this.isCommerciale;
    }

    public int getCodHangar() {
        return this.codHangar;
    }

    public int getCodVia() {
        return this.codVia;
    }
}
