package com.lucar01.aeroporto.controllers;

import com.lucar01.aeroporto.Database;
import com.lucar01.aeroporto.table.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class DatabaseController {  //TODO: generalizzare questi metodi, altrimenti ci metterei troppo tempo e farei troppi metodi per fare la stessa cosa!
    //TODO: metodi generali: getTable(String tableName), getNumberOfColumns(String tableName), getNamesOfColumns(String tableName);

    //private static final Database database = new Database();
    private final static Connection CONNECTION = Database.getConnection();

    public void showTable(){
        //TODO:
    }

    public static ObservableList<Persona> getPersone(){ //TODO: or getPersona
        Connection connection = Database.getConnection();
        ObservableList<Persona> personaObservableList = FXCollections.observableArrayList();

        try{
            PreparedStatement ps = connection.prepareStatement("SELECT CodiceFiscale, Nome, Cognome, Età, Ruolo, Ora_inizio, Ora_fine FROM persona"); //TODO: forse aggiungere aeroporto.persona
            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                /*personaObservableList.add(new Persona(resultSet.getString("CodiceFiscale"), resultSet.getString("Nome"), resultSet.getString("Cognome"),
                        Integer.parseInt(resultSet.getString("Età")), Optional.of(resultSet.getString("Ruolo")), Optional.of(resultSet.getTime("Ora_inizio")),
                        Optional.of(resultSet.getTime("Ora_fine"))));*/ //TODO: to fix and uncomment
                personaObservableList.add(new Persona(resultSet.getString("CodiceFiscale"), resultSet.getString("Nome"), resultSet.getString("Cognome"),
                        Integer.parseInt(resultSet.getString("Età")), Optional.of(resultSet.getString("Ruolo"))));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return personaObservableList;
    }

    public static int getNumberOfColumnsPersona(){ //TODO: fix numberOfColumns.
        Connection connection = Database.getConnection();

        int numberOfColumns = 0;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) AS NUM_OF_COLUMNS FROM information_schema.columns WHERE TABLE_NAME = 'persona' ");
            ResultSet resultSet = preparedStatement.executeQuery();
            numberOfColumns = Integer.parseInt(resultSet.getString("NUM_OF_COLUMNS"));
        }catch(Exception e){
            e.printStackTrace();
        }

        return numberOfColumns;
        //return 0;
    }

    public static String[] getNamesOfColumnsPersona(){
        //Connection connection = Database.getConnection();
        try{
            PreparedStatement preparedStatement = DatabaseController.CONNECTION.prepareStatement("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'persona' ");
            ResultSet resultSet = preparedStatement.executeQuery();
            //TODO: for number of column put column_name.
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
