package com.lucar01.aeroporto.controllers;

import com.lucar01.aeroporto.Database;
import com.lucar01.aeroporto.table.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseController {

    //TODO: prossima volta usare per ottenere il numero di colonne resultSet.getMetaData().getColumnCount();

    private final static Connection CONNECTION = Database.getConnection();

    public static int getNumberOfColumns(final Table table){
        int numberOfColumns = 0;

        try{
            final String query = "SELECT COUNT(*) AS NUM_OF_COLUMNS FROM information_schema.columns WHERE TABLE_NAME = '" + table.getTableName() + "'";
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

    public static ObservableList<String> getNamesOfColumns(final Table table){
        ObservableList<String> observableList = FXCollections.observableArrayList();

        try{
            final String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + table.getTableName() + "'";
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
            final String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA = 'aeroporto' ";
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                observableList.add(resultSet.getString("TABLE_NAME").toUpperCase());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return observableList;
    }

    private static StringBuilder getNamesOfColumnsInString(final Table table){
        final ObservableList<String> namesOfColumns = DatabaseController.getNamesOfColumns(table);

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

    public static boolean addDataToTable(final Table table, final ObservableList<String> data){

        final StringBuilder namesOfColumnsString = getNamesOfColumnsInString(table);

        StringBuilder dataString = new StringBuilder();

        for(int i = 0; i < data.size(); i++){
            if(i == data.size() - 1){
                dataString.append(" '").append(data.get(i)).append("' "); // aggiungo '' altrimenti le stringhe non mi vanno.
            } else {
                dataString.append(" '").append(data.get(i)).append("', "); // aggiungo '' altrimenti le stringhe non mi vanno.
            }
        }

        try {
            final String query = "INSERT INTO " + table.getTableName() + " ( " + namesOfColumnsString + " )" + " VALUES ( " + dataString + " ) ";
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            preparedStatement.execute();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean editTableData(final Table table, final ObservableList<String> oldData, final ObservableList<String> newData){

        final ObservableList<String> namesOfColumns = DatabaseController.getNamesOfColumns(table);

        StringBuilder namesOfColumnsString = new StringBuilder();

        for(int i = 0; i < namesOfColumns.size(); i++){
            if(i == namesOfColumns.size() - 1){
                namesOfColumnsString.append(namesOfColumns.get(i));
                namesOfColumnsString.append(" = '").append(newData.get(i)).append("' "); // aggiungo '' altrimenti le stringhe non mi vanno.
            } else {
                namesOfColumnsString.append(namesOfColumns.get(i));
                namesOfColumnsString.append(" = '").append(newData.get(i)).append("', "); // aggiungo '' altrimenti le stringhe non mi vanno.
            }
        }

        final String condition = "WHERE " + namesOfColumns.get(0) + " = " + oldData.get(0); // Metto 0 perchè voglio soltanto l'id, che si trova come primo elemento.

        try {
            final String query = "UPDATE " + table.getTableName() + " SET " + namesOfColumnsString + " " + condition; // ho aggiunto il \n prima del where, ma in realtà non cambia niente
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            preparedStatement.execute(); // se metto il return qui non va.
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deleteTableData(final Table table, final ObservableList<String> dataToDelete){

        final ObservableList<String> namesOfColumns = DatabaseController.getNamesOfColumns(table);

        final String condition = namesOfColumns.get(0) + " = '" + dataToDelete.get(0) + "'"; // Faccio 0 perchè mi basta l'ID.

        try {
            final String query = "DELETE FROM " + table.getTableName() + " WHERE " + condition;
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            preparedStatement.execute();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //TODO: next time use Reflection.
    public static ObservableList<Tables> getTableData(final Table tableName){
        ObservableList<Tables> observableList = FXCollections.observableArrayList();

        try {
            final String query = "SELECT * FROM " + tableName.getTableName();
            PreparedStatement ps = CONNECTION.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                switch(tableName){
                    case PERSONA:
                        observableList.add(new Persona(resultSet.getString("CodiceFiscale"), resultSet.getString("Nome"), resultSet.getString("Cognome"),
                                resultSet.getInt("Età"), resultSet.getString("Ruolo"), resultSet.getTime("Ora_inizio"), resultSet.getTime("Ora_fine"),
                                resultSet.getInt("CodCentro"), resultSet.getInt("CodLogistica"), resultSet.getInt("CodMantenimento"),
                                resultSet.getInt("CodNegozio"), resultSet.getInt("CodServizio"), resultSet.getInt("CodTerminal"),
                                resultSet.getInt("CodTorre"), resultSet.getInt("CodVolo"), resultSet.getInt("CodCompagnia"),
                                resultSet.getInt("CodAereo"), resultSet.getInt("CodRadar"), resultSet.getInt("CodSoccorso")));
                        break;
                    case BAGAGLIO:
                        observableList.add(new Bagaglio(resultSet.getInt("CodBagaglio"), resultSet.getInt("peso"), resultSet.getString("CodiceFiscale")));
                        break;
                    case TERMINAL:
                        observableList.add(new Terminal(resultSet.getInt("CodTerminal")));
                        break;
                    case AEREO:
                        observableList.add(new Aereo(resultSet.getInt("CodAereo"),resultSet.getInt("CodPista"), resultSet.getString("Nome"), resultSet.getInt("Num_Equipaggio"), resultSet.getInt("Peso"),
                                resultSet.getString("Tipologia"), resultSet.getInt("Num_Passeggeri"), resultSet.getInt("Num_Merci"), resultSet.getBoolean("Commerciale"),
                                resultSet.getInt("CodHangar"), resultSet.getInt("CodVia")));
                        break;
                    case COMPONENTE_AEREO:
                        observableList.add(new ComponenteAereo(resultSet.getInt("CodComponente"), resultSet.getString("Nome"), resultSet.getInt("Quantità"), resultSet.getBoolean("Funzionante"),
                                resultSet.getString("Tipologia"), resultSet.getInt("CodAereo")));
                        break;
                    case ASSICURAZIONE:
                        observableList.add(new Assicurazione(resultSet.getInt("CodAssicurazione"), resultSet.getString("Nome"), resultSet.getString("Partita_IVA"), resultSet.getTime("Ora_inizio"), resultSet.getTime("Ora_fine")));
                        break;
                    case CARGO:
                        observableList.add(new Cargo(resultSet.getInt("CodCargo"), resultSet.getInt("Num_dipendenti"), resultSet.getInt("CodAereo"),
                                resultSet.getInt("CodLogistica"), resultSet.getTime("Ora_inizio"), resultSet.getTime("Ora_fine")));
                        break;
                    case CENTRO_CONTROLLO_AREA:
                        observableList.add(new CentroControlloAerea(resultSet.getInt("CodCentro"), resultSet.getInt("Num_Personale"), resultSet.getTime("Orario_inizio"), resultSet.getTime("Orario_fine")));
                        break;
                    case COMPAGNIA_AEREA:
                        observableList.add(new CompagniaAerea(resultSet.getInt("CodCompagnia"), resultSet.getString("Nome"), resultSet.getString("Partita_Iva"),
                                resultSet.getInt("Num_Personale"), resultSet.getInt("Num_Aerei"), resultSet.getInt("CodAssicurazione"),
                                resultSet.getTime("Ora_inizio"), resultSet.getTime("Ora_fine")));
                        break;
                    case GATE:
                        observableList.add(new Gate(resultSet.getInt("CodGate"), resultSet.getInt("CodTerminal")));
                        break;
                    case GROUND_SUPPORT_EQUIPMENT:
                        observableList.add(new GroundSupportEquipment(resultSet.getInt("CodMacchinario"), resultSet.getInt("Quantità"), resultSet.getString("Tipologia")));
                        break;
                    case HANGAR:
                        observableList.add(new Hangar(resultSet.getInt("CodHangar"), resultSet.getInt("Num_aerei")));
                        break;
                    case LOGISTICA:
                        observableList.add(new Logistica(resultSet.getInt("CodLogistica"), resultSet.getString("Nome"), resultSet.getInt("Num_Personale"),
                                resultSet.getString("Materiali"), resultSet.getInt("Quantità"), resultSet.getInt("CodCompagnia"),
                                resultSet.getTime("Ora_inizio"), resultSet.getTime("Ora_fine")));
                        break;
                    case MANTENIMENTO:
                        observableList.add(new Mantenimento(resultSet.getInt("CodMantenimento"), resultSet.getInt("CodAereo"),
                                resultSet.getInt("CodMacchinario"), resultSet.getTime("Ora_inizio"), resultSet.getTime("Ora_fine")));
                        break;
                    case NEGOZIO:
                        observableList.add(new Negozio(resultSet.getInt("CodNegozio"), resultSet.getString("Prodotti"),
                                resultSet.getTime("Orario_inizio"), resultSet.getTime("Orario_fine"), resultSet.getString("Tipologia"), resultSet.getInt("CodCompagnia")));
                        break;
                    case PISTA:
                        observableList.add(new Pista(resultSet.getInt("CodPista")));
                        break;
                    case RADAR:
                        observableList.add(new Radar(resultSet.getInt("CodRadar"), resultSet.getInt("Raggio"),
                                resultSet.getInt("Frequenza"), resultSet.getInt("Altitudine"),
                                resultSet.getString("Tipologia"), resultSet.getInt("CodAereo")));
                        break;
                    case SERVIZIO_CLIENTI:
                        observableList.add(new ServizioClienti(resultSet.getInt("CodServizio"), resultSet.getTime("Orario_inizio"), resultSet.getTime("Orario_fine")));
                        break;
                    case SOCCORSI:
                        observableList.add(new Soccorsi(resultSet.getInt("CodSoccorso"), resultSet.getTime("Orario_inizio"), resultSet.getTime("Orario_fine")));
                        break;
                    case TORRE_DI_CONTROLLO:
                        observableList.add(new TorreControllo(resultSet.getInt("CodTorre"), resultSet.getInt("Num_dipendenti"), resultSet.getInt("Num_Aerei_in_comunicazione"),
                                resultSet.getTime("Orario_inizio"), resultSet.getTime("Orario_fine")));
                        break;
                    case TRATTA:
                        observableList.add(new Tratta(resultSet.getInt("CodTratta"), resultSet.getString("Città_partenza"),
                                resultSet.getString("Città_destinazione"), resultSet.getString("Aeroporto_partenza"),
                                resultSet.getString("Aeroporto_destinazione"), resultSet.getTime("Ora_partenza"),
                                resultSet.getTime("Ora_fine")));
                        break;
                    case VIA_DI_RULLAGGIO:
                        observableList.add(new ViaRullaggio(resultSet.getInt("CodVia"), resultSet.getInt("Num_Aerei")));
                        break;
                    case VOLO:
                        observableList.add(new Volo(resultSet.getInt("CodVolo"), resultSet.getInt("CodTratta"),
                                resultSet.getInt("CodAereo"), resultSet.getString("Nome")));
                        break;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return observableList;
    }
}
