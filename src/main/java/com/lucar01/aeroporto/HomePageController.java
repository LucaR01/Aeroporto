package com.lucar01.aeroporto;

import com.lucar01.aeroporto.controllers.DataController;
import com.lucar01.aeroporto.controllers.DatabaseController;
import com.lucar01.aeroporto.controllers.TableController;
import com.lucar01.aeroporto.table.Bagaglio;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Time;
import java.util.*;

public class HomePageController implements Initializable { //TODO: mettere nel package controllers

    @FXML
    private StackPane root;

    @FXML
    private Button btnAddTable;

    @FXML
    private Button btnUpdateTable;

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
    private GridPane paneUpdateTable;

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

    @FXML
    private TableView<Tables> editTable;

    @FXML
    private ComboBox<String> comboEditTable;

    @FXML
    private Button btnSubmitEdit;

    @FXML
    private Button btnEditSelectedTable;

    @FXML
    private AnchorPane editAnchorPane;

    private ObservableList<TextField> editTextFields = FXCollections.observableArrayList();

    private ObservableList<String> editOldData = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> comboLanguage;

    @FXML
    private ComboBox<String> comboTheme;

    @FXML
    private Button btnDeleteRow;

    @FXML
    private ImageView imgViewLanguage;

    @FXML
    private ImageView imgViewTheme;

    private boolean isLightTheme = true; //TODO: caricare da file
    private boolean isEnglish; //TODO: rename in isLanguageEnglish o isInEnglish


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //TODO: aggiungere pulsante per eliminare dati tabella
        ObservableList<String> tablesList = DatabaseController.getNamesOfTables();
        this.combo_tables.setItems(tablesList);
        this.combo_add_table.setItems(tablesList);
        this.comboEditTable.setItems(tablesList);

        ObservableList<String> themesList = FXCollections.observableArrayList();
        themesList.addAll("Light", "Dark"); //TODO: prendere queste informazioni da un enum o da una stringa.
        ObservableList<String> languagesList = FXCollections.observableArrayList();
        languagesList.addAll("English", "Italiano");
        this.comboTheme.setItems(themesList);
        this.comboLanguage.setItems(languagesList);

        //TODO: verrà caricata la scelta dal file del salvataggio dei dati.
        //this.comboLanguage.getSelectionModel().select(1); // VANNO ENTRAMBE
        this.comboLanguage.getSelectionModel().select("Italiano"); //TODO: salvare in un file.
        this.comboTheme.getSelectionModel().select("Light");

        //TODO: searchData();
        //searchData();
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
        else if(event.getSource() == this.btnUpdateTable){
            this.lblStatusTitle.setText("Update Table");
            this.lblStatusSubtitle.setText("Modifica le tabelle");
            this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(14, 64, 28), CornerRadii.EMPTY, Insets.EMPTY))); // 44, 125, 51
            this.paneUpdateTable.toFront();
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

        switch(this.combo_tables.getSelectionModel().getSelectedItem()){ //TODO: aggiungere le altre tabelle, usare selectedTable
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

    private void createTable(int numOfColumns, ObservableList<String> columnsName){ //TODO: usare createTable2()

        for(int i = 0; i < numOfColumns; i++){
            TableColumn<Tables, String> column = new TableColumn<>();
            column.setText(columnsName.get(i).toUpperCase());
            column.setCellValueFactory(new PropertyValueFactory<>(columnsName.get(i)));
            this.table.getColumns().add(column);
        }
    }

    private void createTable2(int numOfColumns, ObservableList<String> columnsName, TableView<Tables> tableView){

        for(int i = 0; i < numOfColumns; i++){
            TableColumn<Tables, String> column = new TableColumn<>();
            column.setText(columnsName.get(i).toUpperCase());
            column.setCellValueFactory(new PropertyValueFactory<>(columnsName.get(i)));
            tableView.getColumns().add(column);
        }
    }

    private void showTable(Table table){ //TODO: usare showTable2()
        this.table.getColumns().clear();

        final ObservableList<String> namesOfColumnsList = DatabaseController.getNamesOfColumns(table);
        final int numOfColumns = DatabaseController.getNumberOfColumns(table);
        final ObservableList<Tables> tableDataList = DataController.getTableData3(table);

        System.out.println("numOfColumns: " + numOfColumns); //TODO: remove
        System.out.println("tableDataList: " + tableDataList); //TODO: remove
        createTable(numOfColumns, namesOfColumnsList);

        this.table.setItems(tableDataList);
    }

    private void showTable2(Table table, TableView<Tables> tableView){
        tableView.getColumns().clear();

        final ObservableList<String> namesOfColumnsList = DatabaseController.getNamesOfColumns(table);
        final int numOfColumns = DatabaseController.getNumberOfColumns(table);
        final ObservableList<Tables> tableDataList = DataController.getTableData3(table);

        System.out.println("numOfColumns: " + numOfColumns); //TODO: remove
        System.out.println("tableDataList: " + tableDataList); //TODO: remove
        createTable2(numOfColumns, namesOfColumnsList, tableView);

        tableView.setItems(tableDataList);
    }

    private void createTextFields(Table table){ //TODO: usare createTextFields2()
        this.textFields.clear();
        this.addAnchorPane.getChildren().clear();

        final int numberOfColumns = DatabaseController.getNumberOfColumns(table);
        final ObservableList<String> observableList = DatabaseController.getNamesOfColumns(table);

        for(int i = 0; i < numberOfColumns; i++){
            Label label = new Label();
            label.setText(observableList.get(i));
            label.setLayoutX(0); // prima -9
            label.setLayoutY(i * 40);

            TextField textField = new TextField();
            textField.setLayoutX(i > 10 ? 2 * 73 : 73); // prima era 10; 63
            textField.setLayoutY(i * 40);
            textField.setStyle("-fx-border-color: #7b1417");

            this.textFields.add(textField);
            this.addAnchorPane.getChildren().add(label);
        }

        this.addAnchorPane.getChildren().addAll(this.textFields);

    }

    //TODO: aggiungere final nei parametri.
    private void createTextFields2(Table table, AnchorPane anchorPane, ObservableList<TextField> textFieldObservableList,
                                   int xLabel, int yLabel, int xTextField, int yTextField, String color, int limitTextFields){ //TODO: aggiungere boolean se non voglio i labels.
        textFieldObservableList.clear();
        anchorPane.getChildren().clear();

        final int numberOfColumns = DatabaseController.getNumberOfColumns(table);
        final ObservableList<String> observableList = DatabaseController.getNamesOfColumns(table);

        for(int i = 0; i < numberOfColumns; i++){
            Label label = new Label();
            label.setText(observableList.get(i));
            label.setLayoutX(xLabel);
            label.setLayoutY(i * yLabel);

            TextField textField = new TextField();
            textField.setLayoutX(i > limitTextFields ? 2 * xTextField : xTextField);
            textField.setLayoutY(i * yTextField);
            textField.setStyle("-fx-border-color: " + color);

            textFieldObservableList.add(textField);
            anchorPane.getChildren().add(label);
        }

        anchorPane.getChildren().addAll(textFieldObservableList);

    }

    /*private void searchData(){ //TODO: uncomment and fix
        FilteredList<Tables> filteredDataList = new FilteredList<>(DatabaseController.getTableData(Table.valueOf(this.combo_tables.getSelectionModel().getSelectedItem())), b -> true); // primo parametro data
        this.searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataList.setPredicate(e -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                } else if(getQualcosaltro().to.blabla != -1){
                    return true;
                }
                else if(getAltro().bla.bla.come.sopra != -1){
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Tables> sortedDataList = new SortedList<>(filteredDataList);
        sortedDataList.comparatorProperty().bind(this.table.comparatorProperty());
        this.table.setItems(sortedDataList);
    }*/

    @FXML
    void handleAddTableSelection(ActionEvent event) {
        String selectedTable = this.combo_add_table.getSelectionModel().getSelectedItem();
        this.btnAddSelectedTable.setText(selectedTable);

        switch(this.combo_add_table.getSelectionModel().getSelectedItem()){ //TODO: aggiungere le altre tabelle, usare selectedTable
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

        ObservableList<String> data = FXCollections.observableArrayList();
        this.textFields.forEach( t -> data.add(t.getText()));
        System.out.println("data: " + data); //TODO: remove

        final boolean hasAddedTable = DatabaseController.addDataToTable(Table.valueOf(this.combo_add_table.getSelectionModel().getSelectedItem()), data);

        //TODO: se l'operazione è stata eseguita con successo si dovrà mostrare una notifica.
        //TODO: magari quella sorta di notifica che viene su dal basso, di colore verde se è andata a buon fine, in rosso altrimenti.
        if(hasAddedTable){
            System.out.println("operazione add table eseguita con successo."); //TODO: remove
        } else {
            System.out.println("operazione add table non eseguita."); //TODO: remove
        }
    }

    @FXML
    void handleEditTableSelection(ActionEvent event) {
        String selectedTable = this.comboEditTable.getSelectionModel().getSelectedItem();
        this.btnEditSelectedTable.setText(selectedTable);

        switch(selectedTable){ //TODO: aggiungere le altre tabelle
            case "BAGAGLIO":
                showTable2(Table.BAGAGLIO, this.editTable);
                createTextFields2(Table.BAGAGLIO, this.editAnchorPane, this.editTextFields, 0, 40, 73, 40, "#0e401c", 10); //TODO: modificare parametri
                break;
            case "TERMINAL":
                showTable2(Table.TERMINAL, this.editTable);
                createTextFields2(Table.TERMINAL, this.editAnchorPane, this.editTextFields, 0, 40, 73, 40, "#0e401c", 10); //TODO: modificare parametri
                break;
            case "PERSONA":
                break;
            default:
                this.editTable.getColumns().clear();
                this.editAnchorPane.getChildren().clear();
                break;
        }
    }

    @FXML
    void submitEditTable(ActionEvent event) {
        ObservableList<String> newData = FXCollections.observableArrayList();

        final int numOfColumns = DatabaseController.getNumberOfColumns(Table.valueOf(this.comboEditTable.getSelectionModel().getSelectedItem()));

        for(int i = 0; i < numOfColumns; i++){
            newData.add(this.editTextFields.get(i).getText());
        }

        System.out.println("New data: " + newData);

        //TODO: Chiamare metodo nel DatabaseController
        final boolean hasUpdated = DatabaseController.editTableData(Table.valueOf(this.comboEditTable.getSelectionModel().getSelectedItem()), this.editOldData, newData); //TODO: uncomment.

        //TODO: mostrare una notifica se l'operazione è avvenuta con successo oppure no.
        if(hasUpdated){
            System.out.println("operazione eseguita con successo"); //TODO: remove
            this.editTable.refresh(); // Questo non funziona, o meglio non mi refresha i dati in tempo reale.
        } else {
            System.out.println("operazione fallita"); //TODO: remove
        }

    }

    @FXML
    void rowSelected(MouseEvent event) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        // Quando una nuova riga viene selezionata, devo svuotare i vecchi dati.
        this.editOldData.clear();
        //this.editTextFields.clear(); //TODO: remove?

        Tables selectedRow = this.editTable.getSelectionModel().getSelectedItem(); //TODO: remove
        int index = this.editTable.getSelectionModel().getSelectedIndex();

        System.out.println("index: " + index);

        final int numOfColumns = DatabaseController.getNumberOfColumns(Table.valueOf(this.comboEditTable.getSelectionModel().getSelectedItem()));
        System.out.println("numOfColumns: " + numOfColumns); //TODO: remove

        for(int i = 0; i < numOfColumns; i++){
            this.editOldData.add(this.editTable.getColumns().get(i).getCellData(index).toString());
            //this.editTextFields.get(i).setText(this.editOldData.get(i)); //TODO: potrei anche mettere la stessa roba che ho messo in editOldData
            this.editTextFields.get(i).setText(this.editTable.getColumns().get(i).getCellData(index).toString());
            System.out.println("editTextFields: " + editTextFields.get(i).getText()); //TODO: remove
        }

        System.out.println("editOldData: " + editOldData); //TODO: remove


        //TODO: Devo prendere i dati dalla row e metterli negli editTextFields
        //TODO: tenere in memoria i vecchi dati perchè credo che servano per la query
        //TODO: quando poi preme submit dell'edit allora:
        //TODO: pulire gli editTextFields e aggiornare i dati nella tabella (potrei o mettere il simbolo per il refresh oppure direttamente chiamare .refresh() sulla table).
        //TODO: salvare i nuovi dati e alterare la table con una query.
    }

    @FXML
    void handleThemeSelection(ActionEvent event) {
        //TODO: devo settare i vari style in base alla scelta.



        switch(this.comboTheme.getSelectionModel().getSelectedItem()){
            case "Light":
                //TODO: metto pane per pane
                //TODO: remove stylesheet dark mode
                this.comboTheme.getScene().getRoot().getStylesheets().add(getClass().getResource("").toString()); //TODO: aggiungere path
                this.isLightTheme = true;

                //TODO: chiamare setLightTheme()
                break;
            case "Dark":
                //TODO: remove light mode
                this.isLightTheme = false;

                //TODO: chiamare setDarkTheme();
                break;
        }
    }

    @FXML
    void handleLanguageSelection(ActionEvent event) {
        //TODO: faccio uno switch ed in base alla lingua scelta cambio i vari labels con setText.

        /*switch(this.comboLanguage.getSelectionModel().getSelectedItem()){ //TODO: metterlo dentro ad un altro metodo, così lo chiamo anche all'inizio, remove.
            case "Italiano":
                break;

            default:
            case "English":
                break;
        }*/

        //TODO: chiamare setLanguageSetting();
        setLanguageSetting();
    }

    //TODO: potrei usare Internationalization dependency oppure uso direttamente i setText.
    private void setLanguageSetting(){
        //TODO: faccio uno switch ed in base alla lingua scelta cambio i vari labels con setText.
        switch(this.comboLanguage.getSelectionModel().getSelectedItem()){
            case "Italiano":
                break;

            default:
            case "English":
                break;
        }
    }

    @FXML
    void submitResetSettings(ActionEvent event) {
        //TODO: settare nei combo i valori di default (il salvataggio lo faccio nella submitSaveSettings());
        this.comboTheme.getSelectionModel().select(Settings.Theme.LIGHT.getDefaultTheme());
        this.comboLanguage.getSelectionModel().select(Settings.Languages.ENGLISH.getDefaultLanguage());
    }

    @FXML
    void submitSaveSettings(ActionEvent event) {
        //TODO: salvare i dati in un file. this.comboLang e theme .getSelectionModel().getSelectedItem();
    }

    private void error(String message){ //TODO: message-dialog

    }

    @FXML
    private void submitDeleteRow(ActionEvent event) {
        //TODO: in base alla riga della tabella selezionata selezionata, se clicco questo pulsante me la cancella.

        //TODO: potrei aggiungere un dialog per chiedere all'utente se è sicuro di voler eliminare i dati. Tipo: Are you sure? Yes No

        final int index = this.table.getSelectionModel().getSelectedIndex();

        final int numOfColumns = DatabaseController.getNumberOfColumns(Table.valueOf(this.combo_tables.getSelectionModel().getSelectedItem()));

        ObservableList<String> dataToDelete = FXCollections.observableArrayList();

        for(int i = 0; i < numOfColumns; i++){
            dataToDelete.add(this.table.getColumns().get(i).getCellData(index).toString());
        }

        System.out.println("dataToDelete: " + dataToDelete); //TODO: remove

        final boolean hasDeleted = DatabaseController.deleteTableData(Table.valueOf(this.combo_tables.getSelectionModel().getSelectedItem()), dataToDelete);

        //TODO: usare un message dialog.
        if(hasDeleted){
            System.out.println("Operazione cancellazione eseguita con successo."); //TODO: remove
        } else {
            System.out.println("Operazione cancellazione failed."); //TODO: remove
        }

    }

    @FXML
    void swapLanguage(MouseEvent event) {

    }

    @FXML
    void swapTheme(MouseEvent event) {
        this.isLightTheme = !this.isLightTheme;
        if(this.isLightTheme){
            setLightTheme();
        } else {
            setDarkTheme();
        }
    }

    private void setLightTheme(){
        //root.getStylesheets().remove("");// dark mode //TODO: uncomment
        //root.getStylesheets().add(""); // light mode

        Image imgTheme = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/icons8_haze_40px.png"))); //src/main/resources/com/lucar01/icons/icons8_haze_40px.png
        this.imgViewTheme.setImage(imgTheme);
    }

    private void setDarkTheme(){
        //root.getStylesheets().remove("");// light mode //TODO: uncomment
        //root.getStylesheets().add(""); // dark mode

        //Image imgTheme = new Image("/resources/icons8_night_40px.png"); //src/main/resources/com/lucar01/icons/icons8_night_40px.png
        //imgViewTheme.class.getResource("icons/icons8_night_40px.png"); // NON VA
        Image imgTheme = new Image(getClass().getClassLoader().getResource("../../../../resources/com/lucar01/icons/icons8_night_40px.png").toString(), true); // NON VA
        this.imgViewTheme.setImage(imgTheme);
    }

}