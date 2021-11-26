package com.lucar01.aeroporto.table;

public class Volo {

    private int codVolo;
    private int codTratta;
    private int codAereo;
    private String nome;

    public Volo(int codVolo, int codTratta, int codAereo, String nome) {
        this.codVolo = codVolo;
        this.codTratta = codTratta;
        this.codAereo = codAereo;
        this.nome = nome;
    }

    public int getCodVolo() {
        return codVolo;
    }

    public int getCodTratta() {
        return codTratta;
    }

    public int getCodAereo() {
        return codAereo;
    }

    public String getNome() {
        return nome;
    }
}
