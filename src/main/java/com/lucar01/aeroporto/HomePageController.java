package com.lucar01.aeroporto;

import com.lucar01.aeroporto.controllers.DatabaseController;
import com.lucar01.aeroporto.table.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomePageController implements Initializable { //TODO: mettere nel package controllers

    @FXML
    private Button btnAddTable;

    @FXML
    private Button btnAlterTable;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnSettings;

    @FXML
    private Pane paneStatus;

    @FXML
    private Label lblStatusSubtitle;

    @FXML
    private Label lblStatusTitle;

    @FXML
    private ImageView btnMaximize; //TODO: i don't even need to work with the image view, i believe.

    @FXML
    private ImageView btnMinimize;

    @FXML
    private ImageView btnClose;

    @FXML
    private GridPane paneAddTable;

    @FXML
    private GridPane paneAlterTable;

    @FXML
    private GridPane paneOverview;

    @FXML
    private GridPane paneSettings;

    //TODO: le cose riguardanti la tabella sono da mettere in un altro controller

    @FXML
    private TableView<Persona> table; //TODO: da modificare e mostrare diversi dati.

    @FXML
    private TableColumn<Persona, String> col_id;

    @FXML
    private TableColumn<Persona, String> col1;

    @FXML
    private TableColumn<Persona, String> col2;

    @FXML
    private TableColumn<Persona, Integer> col3;

    @FXML
    private TableColumn<Persona, Optional<String>> col4;

    @FXML
    private ComboBox<String> combo_tables;

    @FXML
    private Button btnSelectedTable;

    @FXML
    ObservableList<Persona> personaObservableList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tablesList = FXCollections.observableArrayList("Persona", "Aereo"); //TODO: li prendo da un enum tables del database
        this.combo_tables.setItems(tablesList);

        //TODO: col_id.setCellValueFactory(new PropertyValueFactory<Persona, String>("CodiceFiscale"));
        //TODO: aggiungere anche le altre colonne.

        // Devono essere scritti come in Persona.
        this.col_id.setCellValueFactory(new PropertyValueFactory<Persona, String>("codiceFiscale"));
        this.col1.setCellValueFactory(new PropertyValueFactory<Persona, String>("nome"));
        this.col2.setCellValueFactory(new PropertyValueFactory<Persona, String>("cognome"));
        this.col3.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("age")); // se metto Età non va. Credo che debbano essere come in Persona
        this.col4.setCellValueFactory(new PropertyValueFactory<Persona, Optional<String>>("ruolo"));

        TableColumn ora_inizio = new TableColumn("Ora_inizio");
        table.getColumns().add(ora_inizio);
        TableColumn ora_fine = new TableColumn("Ora_fine");
        table.getColumns().add(ora_fine);

        //ora_inizio.setCellValueFactory(new PropertyValueFactory<Persona, Optional<Time>>("oraInizio")); // se metto Età non va. Credo che debbano essere come in Persona
        //ora_fine.setCellValueFactory(new PropertyValueFactory<Persona, Optional<Time>>("oraFine"));

        //table.getColumns().addAll(ora_inizio, ora_fine);

        // Questo funziona
        this.col_id.setText("CodiceFiscale"); //TODO: remove
        this.col1.setText("Nome");

        personaObservableList = DatabaseController.getPersone();
        this.table.setItems(personaObservableList);

        /*TableColumn id = new TableColumn("ID"); // "ID" nome della colonna //TODO: remove
        table.getColumns().add(id);

        table.getColumns().removeAll(col_id);*/ //TODO: remove
    }

    @FXML
    private void handleButtonsClick(ActionEvent event){ //TODO: rename in handleMenuButtons?
        //TODO: try to use a switch if possible
        if(event.getSource() == this.btnOverview){
            this.lblStatusTitle.setText("Overview");
            this.lblStatusSubtitle.setText("Visualizza le tabelle");
            this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(18, 162, 237), CornerRadii.EMPTY, Insets.EMPTY)));
            this.paneOverview.toFront();
        }
        else if(event.getSource() == this.btnAddTable){
            this.lblStatusTitle.setText("Add Table");
            this.lblStatusSubtitle.setText("Aggiungi tabelle al database");
            this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(123, 20, 23), CornerRadii.EMPTY, Insets.EMPTY)));
            this.paneAddTable.toFront();
        }
        else if(event.getSource() == this.btnAlterTable){
            this.lblStatusTitle.setText("Alter Table");
            this.lblStatusSubtitle.setText("Modifica le tabelle");
            this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(14, 64, 28), CornerRadii.EMPTY, Insets.EMPTY))); // 44, 125, 51
            this.paneAlterTable.toFront();
        }
        else if(event.getSource() == this.btnSettings){
            this.lblStatusTitle.setText("Settings");
            this.lblStatusSubtitle.setText("Modifica le tue impostazioni");
            this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(56, 11, 90), CornerRadii.EMPTY, Insets.EMPTY))); // 109, 21, 170
            this.paneSettings.toFront();
        } else {
            if(event.getSource() == btnQuit){
                // TODO: message dialog : Are you sure to exit? Yes or No
                // TODO: System.exit(0);
            }
        }
    }

    //TODO: fix icons positions when stage is set fullscreen
    @FXML
    private void handleCloseButton(MouseEvent event){
        /*if(event.getSource() == btnClose){ //TODO: either keep this one or the other.
            System.exit(0);
        }*/
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //TODO: this one is faster than the first way
        stage.close();

        //javafx.application.Platform.exit(); //TODO: or keep this one
    }

    @FXML
    private void handleMaximizeButton(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setFullScreen(!stage.isFullScreen());
    }

    @FXML
    private void handleMinimizeButton(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void handleTableSelection(ActionEvent event) { //TODO: or just call it selectTables?
        //TODO: quando seleziona una tabella diversa, deve cambiare la tabella nella schermata di table, deve mostrare i dati della tabella nelle colonne.
        String selectedTable = this.combo_tables.getSelectionModel().getSelectedItem().toString();
        this.btnSelectedTable.setText(selectedTable);

        //TODO: switch o if
    }
}