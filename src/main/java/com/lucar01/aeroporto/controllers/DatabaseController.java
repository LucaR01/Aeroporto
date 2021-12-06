package com.lucar01.aeroporto.controllers;

import com.lucar01.aeroporto.Database;
import com.lucar01.aeroporto.table.Persona;
import com.lucar01.aeroporto.table.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class DatabaseController {  //TODO: generalizzare questi metodi, altrimenti ci metterei troppo tempo e farei troppi metodi per fare la stessa cosa!
    //TODO: metodi generali: getTable(String tableName), getNumberOfColumns(String tableName), getNamesOfColumns(String tableName);
    //TODO: al posto di una stringa per il table name posso usare un enum.
    //TODO: oppure potrei direttamente mettere nell'enum il numero di colonne, i loro nomi, e la loro tipologia ecc..

    private final static Connection CONNECTION = Database.getConnection();

    public static int getNumberOfColumns(Table table){
        int numberOfColumns = 0;

        try{
            String query = "SELECT COUNT(*) AS NUM_OF_COLUMNS FROM information_schema.columns WHERE TABLE_NAME = '" + table.getTableName() + "'";
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                numberOfColumns = resultSet.getInt("NUM_OF_COLUMNS");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return numberOfColumns;
    }

    public static ObservableList<String> getNamesOfColumnsAndDataTypes(Table table){
        ObservableList<String> observableList = FXCollections.observableArrayList();

        try{
            String query = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + table.getTableName() + "'";
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            //TODO: for number of column put column_name.
            while(resultSet.next()){
                observableList.add(resultSet.getString("COLUMN_NAME"));
                observableList.add(resultSet.getString("DATA_TYPE"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return observableList;
    }

    public static ObservableList<String> getNamesOfColumns(Table table){
        ObservableList<String> observableList = FXCollections.observableArrayList();

        try{
            String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + table.getTableName() + "'";
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                observableList.add(resultSet.getString("COLUMN_NAME"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return observableList;
    }

    public static ObservableList<String> getNamesOfTables(){

        ObservableList<String> observableList = FXCollections.observableArrayList();

        try{
            String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA = 'aeroporto' ";
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                observableList.add(resultSet.getString("TABLE_NAME").toUpperCase());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("name of tables: " + observableList);

        return observableList;
    }

    private static StringBuilder getNamesOfColumnsInString(Table table){
        ObservableList<String> namesOfColumns = DatabaseController.getNamesOfColumns(table);

        StringBuilder namesOfColumnsString = new StringBuilder();

        for(int i = 0; i < namesOfColumns.size(); i++){
            if(i == namesOfColumns.size() - 1){
                namesOfColumnsString.append(namesOfColumns.get(i));
            } else {
                namesOfColumnsString.append(namesOfColumns.get(i)).append(", ");
            }
        }

        return namesOfColumnsString;
    }

    public static boolean addDataToTable(Table table, ObservableList<String> data){

        /*ObservableList<String> namesOfColumns = DatabaseController.getNamesOfColumns(table);

        StringBuilder namesOfColumnsString = new StringBuilder();

        for(int i = 0; i < namesOfColumns.size(); i++){ //TODO: chiamare getNamesOfColumnsInString
            if(i == namesOfColumns.size() - 1){
                namesOfColumnsString.append(namesOfColumns.get(i));
            } else {
                namesOfColumnsString.append(namesOfColumns.get(i)).append(", ");
            }
        }

        System.out.println("namesOfColumnsString: " + namesOfColumnsString);*/ //TODO: remove

        StringBuilder namesOfColumnsString = getNamesOfColumnsInString(table);

        StringBuilder dataString = new StringBuilder();

        for(int i = 0; i < data.size(); i++){
            if(i == data.size() - 1){
                dataString.append(data.get(i));
            } else {
                dataString.append(data.get(i)).append(", ");
            }
        }

        System.out.println("dataString: " + dataString); //TODO: remove

        try {
            String query = "INSERT INTO " + table.getTableName() + " ( " + namesOfColumnsString + " )" + " VALUES ( " + dataString + " ) ";
            System.out.println("query add data table: " + query); //TODO: remove
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            preparedStatement.execute(); //TODO: return .execute(); al posto di mettere true e false sotto. (in realtà non va se lo metto qui, forse perchè è dentro al try ).
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean editTableData(Table table, ObservableList<String> oldData, ObservableList<String> newData){

        //TODO: devo prendere l'id dell'oldData e per il resto modificare con la newData.
        //StringBuilder stringBuilder = getNamesOfColumnsInString(table); //TODO: remove

        ObservableList<String> namesOfColumns = DatabaseController.getNamesOfColumns(table);

        StringBuilder namesOfColumnsString = new StringBuilder();

        for(int i = 0; i < namesOfColumns.size(); i++){
            if(i == namesOfColumns.size() - 1){
                namesOfColumnsString.append(namesOfColumns.get(i));
                namesOfColumnsString.append(" = '").append(newData.get(i)).append("' "); //TODO: aggiungo '' altrimenti le stringhe non mi vanno.
            } else {
                namesOfColumnsString.append(namesOfColumns.get(i));
                namesOfColumnsString.append(" = '").append(newData.get(i)).append("', "); //TODO: aggiungo '' altrimenti le stringhe non mi vanno.
            }
        }

        System.out.println("namesOfColumnsString: " + namesOfColumnsString); //TODO: remove

        String condition = "WHERE " + namesOfColumns.get(0) + " = " + oldData.get(0); // Metto 0 perchè voglio soltanto l'id, che si trova come primo elemento.

        try {
            String query = "UPDATE " + table.getTableName() + " SET " + namesOfColumnsString + " " + condition; // ho aggiunto il \n prima del where, ma in realtà non cambia niente
            System.out.println("query edit table: " + query); //TODO: remove
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            preparedStatement.execute(); // se metto il return qui non va.
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
