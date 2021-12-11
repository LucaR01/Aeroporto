package com.lucar01.aeroporto.table;

public class Gate implements Tables{

    private final int codGate;
    private final int codTerminal;

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
