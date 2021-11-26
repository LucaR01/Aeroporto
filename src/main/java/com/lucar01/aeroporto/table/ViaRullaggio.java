package com.lucar01.aeroporto.table;

public class ViaRullaggio {

    private int codVia;
    private int numAerei;

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
