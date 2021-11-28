package com.lucar01.aeroporto.table;

public class Hangar extends AbstractTable {

    private int codHangar;
    private int numAerei;

    public Hangar(int codHangar, int numAerei) {
        this.codHangar = codHangar;
        this.numAerei = numAerei;
    }

    public int getCodHangar() {
        return codHangar;
    }

    public int getNumAerei() {
        return numAerei;
    }
}
