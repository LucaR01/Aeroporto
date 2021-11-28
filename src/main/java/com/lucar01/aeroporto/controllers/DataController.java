package com.lucar01.aeroporto.controllers;

import com.lucar01.aeroporto.Database;
import com.lucar01.aeroporto.table.AbstractTable;
import com.lucar01.aeroporto.table.Aereo;
import com.lucar01.aeroporto.table.Hangar;
import com.lucar01.aeroporto.table.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataController<T> {

    private Class<T> classT;

    private final static Connection CONNECTION = Database.getConnection();

    //TODO: altri possibili parametri, tipo quante colonne vogliamo prendere
    //TODO: nella query possiamo mettere "LIMIT n".
    //TODO: al posto di String table potrei usare un enum.
    public ObservableList<T> getTableData(String table){
        ObservableList<T> observableList = FXCollections.observableArrayList();

        try{
            String query = "SELECT * FROM " + table;
            PreparedStatement ps = CONNECTION.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                //observableList.add(new T(resultSet.getString(""))); // questo da errore
                //observableList.add(classT.newInstance());
                observableList.add(classT.getConstructor(Class.forName(resultSet.getString("c"))).newInstance(resultSet.getString("")));
                observableList.add(classT.getConstructor().newInstance());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return observableList;
    }

    /*public static void getTable(Table tableName){ //TODO:

    }*/

    public static ObservableList<AbstractTable> getTableData(Table tableName){
        ObservableList<AbstractTable> observableList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM " + tableName.getTableName();
            PreparedStatement ps = CONNECTION.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            switch(tableName){
                case AEREO:
                    while(resultSet.next()){
                        observableList.add(new Aereo(resultSet.getInt("CodAereo"), resultSet.getString("nome"), resultSet.getInt("Num_equipaggio"), resultSet.getInt("peso"), resultSet.getString("Tipologia"),
                                resultSet.getInt("Num_passeggeri"), resultSet.getInt("Num_merci"), resultSet.getBoolean("Commerciale")));
                    }
                    break;
                case HANGAR:
                    while(resultSet.next()){
                        observableList.add(new Hangar(resultSet.getInt("CodHangar"), resultSet.getInt("Num_aerei")));
                    }
                    break;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return observableList;
    }

    //TODO: al posto di String table potrei usare un enum.
    public static int getNumberOfColumns(String table){
        int numberOfColumns = 0;

        try{
            String query = "SELECT COUNT(*) AS NUM_OF_COLUMNS FROM information_schema.columns WHERE TABLE_NAME = '" + table + "'";
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

    public static ObservableList<String> getNamesOfColumns(String table){
        ObservableList<String> observableList = FXCollections.observableArrayList();

        try{
            String query = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + table + "'";
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
}
