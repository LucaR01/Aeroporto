package com.lucar01.aeroporto.controllers;

import com.lucar01.aeroporto.Database;
import com.lucar01.aeroporto.table.*;
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

    //TODO: prossima volta usare per ottenere il numero di colonne resultSet.getMetaData().getColumnCount();

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

        for(int i = 0; i < namesOfColumns.size(); i++){ //TODO: alla fine non penso ci sia nemmeno il bisogno di usare il for per ottenere tutti i dati, uso solo il get(0); (guardare deleteTableData come reference)
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

    public static boolean deleteTableData(Table table, ObservableList<String> dataToDelete){

        final ObservableList<String> namesOfColumns = DatabaseController.getNamesOfColumns(table);

        final String condition = namesOfColumns.get(0) + " = '" + dataToDelete.get(0) + "'"; // Faccio 0 perchè mi basta l'ID.

        try {
            String query = "DELETE FROM " + table.getTableName() + " WHERE " + condition;
            System.out.println("delete query: " + query); //TODO: remove
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
            preparedStatement.execute();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ObservableList<Tables> getTableData(Table tableName){
        ObservableList<Tables> observableList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM " + tableName.getTableName();
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
                        observableList.add(new ComponenteAereo(resultSet.getInt("CodComponente"), resultSet.getString("Nome"), Integer.parseInt(resultSet.getString("Quantità")), resultSet.getBoolean("Funzionante"),
                                resultSet.getString("Tipologia"), resultSet.getInt("CodAereo"))); //TODO: fix
                        break;
                    case ASSICURAZIONE:
                        observableList.add(new Assicurazione(resultSet.getInt("CodAssicurazione"),resultSet.getString("Nome"), resultSet.getString("Partita_IVA"), resultSet.getTime("Ora_inizio"), resultSet.getTime("Ora_fine"))); //TODO: fix
                        break;
                    case CARGO:
                        observableList.add(new Cargo(resultSet.getInt("CodCargo"), resultSet.getInt("Num_dipendenti"), resultSet.getInt("CodAereo"),
                                resultSet.getInt("CodLogistica"), resultSet.getTime("Ora_inizio"), resultSet.getTime("Ora_fine"))); //TODO: fix
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
                    case TORRE_CONTROLLO:
                        observableList.add(new TorreControllo(resultSet.getInt("CodTorre"), resultSet.getInt("Num_dipendenti"), resultSet.getInt("Num_Aerei_in_comunicazione"),
                                resultSet.getTime("Orario_inizio"), resultSet.getTime("Orario_fine")));
                        break;
                    case TRATTA:
                        observableList.add(new Tratta(resultSet.getInt("CodTratta"), resultSet.getString("Città_partenza"),
                                resultSet.getString("Città_destinazione"), resultSet.getString("Aeroporto_partenza"),
                                resultSet.getString("Aeroporto_destinazione"), resultSet.getTime("Ora_partenza"),
                                resultSet.getTime("Ora_fine")));
                        break;
                    case VIA_RULLAGGIO:
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
