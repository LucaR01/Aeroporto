package com.lucar01.aeroporto.table;

public class GroundSupportEquipment {

    private int codMacchinario;
    private int quantity;
    private String tipologia;

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
