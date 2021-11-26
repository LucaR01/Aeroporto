package com.lucar01.aeroporto.table;

public class Gate {

    private int codGate;
    private int codTerminal;

    public Gate(int codGate, int codTerminal) {
        this.codGate = codGate;
        this.codTerminal = codTerminal;
    }

    public int getCodGate() {
        return codGate;
    }

    public int getCodTerminal() {
        return codTerminal;
    }
}
