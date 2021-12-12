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

    // Metto quantità con l'accento soltanto perchè così l'ho scritto nel database e se non lo scrivo uguale, non riesce a prendermi i dati e metterli nella colonna della tableView.
    public int getQuantità() {
        return quantity;
    }

    public String getTipologia() {
        return tipologia;
    }
}
