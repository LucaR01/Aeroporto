package com.lucar01.aeroporto.controllers;

import com.lucar01.aeroporto.table.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Collections;

public class TableController { //TODO: usare generics
    //TODO: deve caricare i dati in base alla tabella selezionata.

    /*public static void createTablePersona(){
        final int numberOfColumns = DatabaseController.getNumberOfColumnsPersona();

        for(int i = 0; i < numberOfColumns; i++){
            TableColumn column = new TableColumn(""); //TODO: mi servirebbe prendere il nome della colonna.
            //TODO: chiamare DatabaseController.getNamesOfColumns e metterlo sopra.
        }
    }*/

    public static TableView createTable(){
        TableView tableView = new TableView();
        for(int i = 0; i < 3; i++) {
            TableColumn column = new TableColumn("C" + i);
            tableView.getColumns().add(column);

        }

        return tableView;
    }

    public static TableView<Persona> createTablePersona(){
        TableView<Persona> tableView = new TableView<>();
        ArrayList<String> personaList = new ArrayList<>();

        personaList.add("codiceFiscale");
        personaList.add("nome");
        personaList.add("cognome");

        for(int i = 0; i < 3; i++) {
            TableColumn<Persona, String> column = new TableColumn<>("COL" + i);
            column.setCellValueFactory(new PropertyValueFactory<Persona, String>(personaList.get(i)));
            tableView.getColumns().add(column);

        }

        return tableView;
    }

    public static TableView<Tables> createTable(Table table, int numOfColumns, ArrayList<String> columnsName){
        TableView<Tables> tableView = new TableView<>();
        //TableView<AbstractTable> tableView1 = new TableView<>();

        for(int i = 0; i < numOfColumns; i++){
            switch(table){
                case BAGAGLIO:
                    TableColumn<Tables, String> columnBagaglio = new TableColumn<>();
                    columnBagaglio.setCellValueFactory(new PropertyValueFactory<Tables, String>(columnsName.get(i)));
                    tableView.getColumns().add(columnBagaglio);
                    break;
                case CARGO:
                    TableColumn<Tables, String> columnCargo = new TableColumn<>();
                    columnCargo.setCellValueFactory(new PropertyValueFactory<Tables, String>(columnsName.get(i)));
                    tableView.getColumns().add(columnCargo);
                    break;
                /*case AEREO:
                    TableColumn<AbstractTable, String> columnAereo = new TableColumn<>();
                    columnAereo.setCellValueFactory(new PropertyValueFactory<AbstractTable, String>(columnsName.get(i)));
                    tableView1.getColumns().add(columnAereo);
                    break;*/
            }
        }
        return tableView;
    }

    /*public static TableView<Tables> createTable2(Table table, int numOfColumns, ArrayList<String> columnsName){
        TableView<Tables> tableView = new TableView<>();
        switch(table){
            case BAGAGLIO:
                TableView<Bagaglio> bagaglioTableView = new TableView<>();
                break;
            case CARGO:
                break;
        }

        return tableView;
    }*/
}
