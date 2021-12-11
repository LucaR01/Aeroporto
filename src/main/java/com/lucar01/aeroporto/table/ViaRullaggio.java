package com.lucar01.aeroporto.table;

public class ViaRullaggio implements Tables{

    private final int codVia;
    private final int numAerei;

    public ViaRullaggio(int codVia, int numAerei) {
        this.codVia = codVia;
        this.numAerei = numAerei;
    }

    public int getCodVia() {
        return codVia;
    }

    public int getNumAerei() {
        return numAerei;
    }
}
