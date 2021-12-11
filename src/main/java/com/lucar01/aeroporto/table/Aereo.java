package com.lucar01.aeroporto.table;

public class Aereo implements Tables{

    private final int codAereo;
    private final String nome;
    private final int numEquipaggio;
    private final int peso;
    private final String tipologia;
    private final int numPasseggeri;
    private final int numMerci;
    private final boolean isCommerciale;

    private final int codPista;
    private final int codHangar;
    private final int codVia;

    public Aereo(int codAereo, int codPista, String nome, int numEquipaggio, int peso, String tipologia, int numPasseggeri, int numMerci, boolean isCommerciale, int codHangar, int codVia){
        this.codAereo = codAereo;
        this.codPista = codPista;
        this.nome = nome;
        this.numEquipaggio = numEquipaggio;
        this.peso = peso;
        this.tipologia = tipologia;
        this.numPasseggeri = numPasseggeri;
        this.numMerci = numMerci;
        this.isCommerciale = isCommerciale;
        this.codHangar = codHangar;
        this.codVia = codVia;
    }

    public int getCodAereo() {
        return this.codAereo;
    }

    public int getCodPista(){
        return this.codPista;
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
