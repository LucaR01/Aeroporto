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
}
