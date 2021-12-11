package com.lucar01.aeroporto.table;

public class Terminal implements Tables{

    private final int codTerminal;

    public Terminal(int codTerminal) {
        this.codTerminal = codTerminal;
    }

    public int getCodTerminal() {
        return codTerminal;
    }
}
