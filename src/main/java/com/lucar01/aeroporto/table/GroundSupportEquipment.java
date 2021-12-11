package com.lucar01.aeroporto.table;

public class GroundSupportEquipment implements Tables{

    private final int codMacchinario;
    private final int quantity;
    private final String tipologia;

    public GroundSupportEquipment(int codMacchinario, int quantity, String tipologia) {
        this.codMacchinario = codMacchinario;
        this.quantity = quantity;
        this.tipologia = tipologia;
    }

    public int getCodMacchinario() {
        return codMacchinario;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTipologia() {
        return tipologia;
    }
}
