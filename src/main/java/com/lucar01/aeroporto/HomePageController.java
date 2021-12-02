package com.lucar01.aeroporto;

import com.lucar01.aeroporto.controllers.DataController;
import com.lucar01.aeroporto.controllers.DatabaseController;
import com.lucar01.aeroporto.controllers.TableController;
import com.lucar01.aeroporto.table.Persona;
import com.lucar01.aeroporto.table.Table;
import com.lucar01.aeroporto.table.Tables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
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
    //private TableView<Persona> table; //TODO: da modificare e mostrare diversi dati.
    private TableView<Tables> table;

    @FXML
    private ComboBox<String> combo_tables;

    @FXML
    private Button btnSelectedTable;

    @FXML
    private TextField searchField;

    //private final static String PERSONA = "PERSONA"; //TODO: remove

    @FXML
    //ObservableList<Persona> personaObservableList;
    ObservableList<Tables> personaObservableList; //TODO: rename

    @FXML
    private AnchorPane addAnchorPane;

    @FXML
    private ComboBox<String> combo_add_table;

    @FXML
    private Button btnSubmitAddTable;

    @FXML
    private Button btnAddSelectedTable;

    private ObservableList<TextField> textFields = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //TODO: aggiungere pulsante per eliminare dati tabella
        ObservableList<String> tablesList = DatabaseController.getNamesOfTables();
        this.combo_tables.setItems(tablesList);
        this.combo_add_table.setItems(tablesList);

        //TODO: searchData();
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
                // TODO: message dialog : Are you sure to exit? Yes or No (Are you sure you want to exit? Yes : No)
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
    private void handleTableSelection(ActionEvent event) { //TODO: or just call it selectTables?
        String selectedTable = this.combo_tables.getSelectionModel().getSelectedItem();
        this.btnSelectedTable.setText(selectedTable);

        switch(this.combo_tables.getSelectionModel().getSelectedItem()){ //TODO: aggiungere le altre tabelle
            case "PERSONA":
                showTable(Table.PERSONA);
                break;
            case "BAGAGLIO":
                showTable(Table.BAGAGLIO);
                break;
            case "TERMINAL":
                showTable(Table.TERMINAL);
                break;
            default:
                this.table.getColumns().clear();
                break;
        }
    }

    private void createTable(int numOfColumns, ObservableList<String> columnsName){

        for(int i = 0; i < numOfColumns; i++){
            TableColumn<Tables, String> column = new TableColumn<>();
            column.setText(columnsName.get(i).toUpperCase());
            column.setCellValueFactory(new PropertyValueFactory<>(columnsName.get(i)));
            this.table.getColumns().add(column);
        }
    }

    private void showTable(Table table){
        this.table.getColumns().clear();

        final ObservableList<String> namesOfColumnsList = DatabaseController.getNamesOfColumns(table);
        final int numOfColumns = DatabaseController.getNumberOfColumns(table);
        final ObservableList<Tables> tableDataList = DataController.getTableData3(table);

        System.out.println("numOfColumns: " + numOfColumns); //TODO: remove
        System.out.println("tableDataList: " + tableDataList); //TODO: remove
        createTable(numOfColumns, namesOfColumnsList);

        this.table.setItems(tableDataList);
    }

    private void createTextFields(Table table){
        this.textFields.clear();
        this.addAnchorPane.getChildren().clear();

        final int numberOfColumns = DatabaseController.getNumberOfColumns(table);
        final ObservableList<String> observableList = DatabaseController.getNamesOfColumns(table);

        for(int i = 0; i < numberOfColumns; i++){
            Label label = new Label();
            label.setText(observableList.get(i));
            label.setLayoutX(-9);
            label.setLayoutY(i * 40);

            TextField textField = new TextField();
            textField.setLayoutX(i > 10 ? 2 * 63 : 63); // 63
            textField.setLayoutY(i * 40);
            textField.setStyle("-fx-border-color: #7b1417");

            this.textFields.add(textField);
            this.addAnchorPane.getChildren().add(label);
        }

        this.addAnchorPane.getChildren().addAll(this.textFields);

    }

    /*private void searchData(){ //TODO: uncomment and fix
        FilteredList<Tables> filteredDataList = new FilteredList<>(, b -> true); // primo parametro data
        this.searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataList.setPredicate(e -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if( != -1){
                    return true;
                } else if( != -1){
                    return true;
                }
                else if( != -1){
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Tables> sortedDataList = new SortedList<>(filteredDataList);
        sortedDataList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedDataList);
    }*/

    @FXML
    void handleAddTableSelection(ActionEvent event) {
        String selectedTable = this.combo_add_table.getSelectionModel().getSelectedItem();
        this.btnAddSelectedTable.setText(selectedTable);

        switch(this.combo_add_table.getSelectionModel().getSelectedItem()){ //TODO: aggiungere le altre tabelle
            case "PERSONA":
                break;
            case "BAGAGLIO":
                createTextFields(Table.BAGAGLIO);
                break;
            case "TERMINAL":
                createTextFields(Table.TERMINAL);
                break;
            default:
                this.addAnchorPane.getChildren().clear();
                break;
        }
    }

    @FXML
    private void submitAddTable(ActionEvent event) {
        this.textFields.forEach(n -> System.out.println(n.getText() + " nome_tabella: " + this.combo_add_table.getSelectionModel().getSelectedItem().toLowerCase())); //TODO: remove
        System.out.println(Table.valueOf(this.combo_add_table.getSelectionModel().getSelectedItem())); //TODO: remove

        /*ObservableList<String> data = FXCollections.observableArrayList();
        DatabaseController.addDataToTable(Table.valueOf(this.combo_add_table.getSelectionModel().getSelectedItem()), data);*/

        //TODO: se l'operazione è stata eseguita con successo si dovrà mostrare una notifica.
    }

}