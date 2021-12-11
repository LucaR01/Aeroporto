package com.lucar01.aeroporto.table;

public class Pista implements Tables{

    private final int codPista;

    public Pista(int codPista) {
        this.codPista = codPista;
    }

    public int getCodPista() {
        return codPista;
    }
}
