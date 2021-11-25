package com.lucar01.aeroporto.controllers;

import javafx.scene.control.TableColumn;

public class TableController {
    //TODO: deve caricare i dati in base alla tabella selezionata.

    public static void createTablePersona(){
        final int numberOfColumns = DatabaseController.getNumberOfColumnsPersona();

        for(int i = 0; i < numberOfColumns; i++){
            TableColumn column = new TableColumn(""); //TODO: mi servirebbe prendere il nome della colonna.
            //TODO: chiamare DatabaseController.getNamesOfColumns e metterlo sopra.
        }
    }
}
