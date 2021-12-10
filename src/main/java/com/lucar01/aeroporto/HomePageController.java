package com.lucar01.aeroporto;

import com.lucar01.aeroporto.controllers.DatabaseController;
import com.lucar01.aeroporto.save.Data;
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
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.*;

public class HomePageController implements Initializable { //TODO: mettere nel package controllers, add javadoc.

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

    @FXML
    private Label lblAddTable;

    @FXML
    private Label lblSettings;

    @FXML
    private Label lblSubTitle;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblUpdateTable;

    @FXML
    private Label lblLanguage;

    @FXML
    private Label lblTheme;

    @FXML
    private VBox vBoxRoot;

    private boolean isLightTheme = true; //TODO: caricare da file
    private boolean isEnglish; //TODO: rename in isLanguageEnglish o isInEnglish

    private HashMap<String, String> settingsMap = Data.loadSettings();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //TODO: aggiungere pulsante per eliminare dati tabella
        ObservableList<String> tablesList = DatabaseController.getNamesOfTables();
        this.combo_tables.setItems(tablesList);
        this.combo_add_table.setItems(tablesList);
        this.comboEditTable.setItems(tablesList);

        ObservableList<String> themesList = Settings.Theme.LIGHT.getThemes();
        ObservableList<String> languagesList = Settings.Languages.ENGLISH.getLanguages();
        this.comboTheme.setItems(themesList);
        this.comboLanguage.setItems(languagesList);

        //TODO: verrà caricata la scelta dal file del salvataggio dei dati.
        this.comboLanguage.getSelectionModel().select(this.settingsMap.get(Data.LANG_STRING)); //TODO: salvare in un file.
        this.comboTheme.getSelectionModel().select(this.settingsMap.get(Data.THEME_STRING));

        //TODO: searchData();
        //searchData();

        //TODO: settare l'icona della lingua dall'inizio del programma?.
    }

    @FXML
    private void handleButtonsClick(ActionEvent event){ //TODO: rename in handleMenuButtons?
        //TODO: try to use a switch if possible
        if(event.getSource() == this.btnOverview){
            if(this.isEnglish){
                this.lblStatusTitle.setText("Overview");
                this.lblStatusSubtitle.setText("View the tables");
            } else {
                this.lblStatusTitle.setText("Visualizza");
                this.lblStatusSubtitle.setText("Visualizza le tabelle");
            }

            this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(18, 162, 237), CornerRadii.EMPTY, Insets.EMPTY)));
            this.paneOverview.toFront();
        }
        else if(event.getSource() == this.btnAddTable){
            if(this.isEnglish){
                this.lblStatusTitle.setText("Add Table");
                this.lblStatusSubtitle.setText("Add tables to the database");
            } else {
                this.lblStatusTitle.setText("Aggiungi Tabelle");
                this.lblStatusSubtitle.setText("Aggiungi tabelle al database");
            }

            this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(123, 20, 23), CornerRadii.EMPTY, Insets.EMPTY)));
            this.paneAddTable.toFront();
        }
        else if(event.getSource() == this.btnUpdateTable){
            if(this.isEnglish){
                this.lblStatusTitle.setText("Update Table");
                this.lblStatusSubtitle.setText("Edit the tables");
            } else {
                this.lblStatusTitle.setText("Aggiorna le tabelle");
                this.lblStatusSubtitle.setText("Modifica le tabelle");
            }


            this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(14, 64, 28), CornerRadii.EMPTY, Insets.EMPTY))); // 44, 125, 51
            this.paneUpdateTable.toFront();
        }
        else if(event.getSource() == this.btnSettings){
            if(this.isEnglish){
                this.lblStatusTitle.setText("Settings");
                this.lblStatusSubtitle.setText("Edit your settings");
            } else {
                this.lblStatusTitle.setText("Impostazioni");
                this.lblStatusSubtitle.setText("Modifica le tue impostazioni");
            }


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
                showTable(Table.PERSONA, this.table);
                break;
            case "BAGAGLIO":
                showTable(Table.BAGAGLIO, this.table);
                break;
            case "TERMINAL":
                showTable(Table.TERMINAL, this.table);
                break;
            default:
                this.table.getColumns().clear();
                break;
        }
    }

    private void createTable(final int numOfColumns, final ObservableList<String> columnsName, TableView<Tables> tableView){

        for(int i = 0; i < numOfColumns; i++){
            TableColumn<Tables, String> column = new TableColumn<>();
            column.setText(columnsName.get(i).toUpperCase());
            column.setCellValueFactory(new PropertyValueFactory<>(columnsName.get(i)));
            tableView.getColumns().add(column);
        }
    }

    private void showTable(final Table table, TableView<Tables> tableView){
        tableView.getColumns().clear();

        final ObservableList<String> namesOfColumnsList = DatabaseController.getNamesOfColumns(table);
        final int numOfColumns = DatabaseController.getNumberOfColumns(table);
        final ObservableList<Tables> tableDataList = DatabaseController.getTableData(table);

        System.out.println("numOfColumns: " + numOfColumns); //TODO: remove
        System.out.println("tableDataList: " + tableDataList); //TODO: remove
        createTable(numOfColumns, namesOfColumnsList, tableView);

        tableView.setItems(tableDataList);
    }

    private void createTextFields(final Table table, AnchorPane anchorPane, ObservableList<TextField> textFieldObservableList,
                                   final int xLabel, final int yLabel, final int xTextField, final int yTextField, final String color, final int limitTextFields){ //TODO: aggiungere boolean se non voglio i labels.
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
                createTextFields(Table.BAGAGLIO, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 10);
                break;
            case "TERMINAL":
                createTextFields(Table.TERMINAL, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 10);
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
                showTable(Table.BAGAGLIO, this.editTable);
                createTextFields(Table.BAGAGLIO, this.editAnchorPane, this.editTextFields, 0, 40, 73, 40, "#0e401c", 10);
                break;
            case "TERMINAL":
                showTable(Table.TERMINAL, this.editTable);
                createTextFields(Table.TERMINAL, this.editAnchorPane, this.editTextFields, 0, 40, 73, 40, "#0e401c", 10);
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
                //this.comboTheme.getScene().getRoot().getStylesheets().add(getClass().getResource("").toString()); //TODO: aggiungere path
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
    void handleLanguageSelection(ActionEvent event) throws IOException {
        //TODO: faccio uno switch ed in base alla lingua scelta cambio i vari labels con setText.

        switch(this.comboLanguage.getSelectionModel().getSelectedItem()){ //TODO: metterlo dentro ad un altro metodo, così lo chiamo anche all'inizio, remove.
            case "Italiano":
                setLanguageSetting(Settings.Languages.ITALIANO);
                break;

            default:
            case "English":
                setLanguageSetting(Settings.Languages.ENGLISH);
                break;
        }

    }

    //TODO: potrei usare Internationalization dependency oppure uso direttamente i setText.
    private void setLanguageSetting(Settings.Languages language) throws IOException {
        //TODO: faccio uno switch ed in base alla lingua scelta cambio i vari labels con setText.
        this.comboLanguage.getSelectionModel().select(language.getLanguage());

        switch(language.getLanguage()){
            case "Italiano":

                Image imgLanguageIt = new Image(Files.newInputStream(Paths.get("res/assets/icons/icons8_italy_48px_1.png")));
                this.imgViewLanguage.setImage(imgLanguageIt);

                this.lblLanguage.setText("Lingua");
                this.lblTheme.setText("Tema");

                this.lblSettings.setText("Impostazioni");

                this.lblAddTable.setText("Aggiungi");

                this.lblUpdateTable.setText("Aggiorna Tabella");
                this.lblTitle.setText("Aeroporto");

                this.lblSubTitle.setText("Gestisci i tuoi Aeroporti");

                this.btnOverview.setText("Visualizza");
                this.btnAddTable.setText("Aggiungi Tabellle");
                this.btnUpdateTable.setText("Aggiorna Tabelle");
                this.btnSettings.setText("Impostazioni");
                this.btnQuit.setText("Esci");
                break;

            default:
            case "English":
                Image imgLanguageEng = new Image(Files.newInputStream(Paths.get("res/assets/icons/icons8_great_britain_48px.png")));
                this.imgViewLanguage.setImage(imgLanguageEng);

                this.lblLanguage.setText("Language");
                this.lblTheme.setText("Theme");

                this.lblSettings.setText("Settings");

                this.lblAddTable.setText("Add Table");

                this.lblUpdateTable.setText("Update Table");
                this.lblTitle.setText("Airport");

                this.lblSubTitle.setText("Manage your Airports");

                this.btnOverview.setText("Overview");
                this.btnAddTable.setText("Add Table");
                this.btnUpdateTable.setText("Edit Table");
                this.btnSettings.setText("Settings");
                this.btnQuit.setText("Quit");
                break;
        }
    }

    @FXML
    void swapLanguage(MouseEvent event) throws IOException {
        this.isEnglish = !this.isEnglish;

        if(this.isEnglish){
            setLanguageSetting(Settings.Languages.ENGLISH);
        } else {
            setLanguageSetting(Settings.Languages.ITALIANO);
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

        Data.saveSettings(this.comboTheme.getSelectionModel().getSelectedItem(), this.comboLanguage.getSelectionModel().getSelectedItem());

        //TODO: forse mi servirebbe una notifica e forse saveSettings dovrebbe restituire un boolean.
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
    void swapTheme(MouseEvent event) throws IOException, URISyntaxException {
        this.isLightTheme = !this.isLightTheme;
        if(this.isLightTheme){
            setLightTheme();
        } else {
            setDarkTheme();
        }
    }

    private void setLightTheme() throws IOException, URISyntaxException {

        this.comboTheme.getSelectionModel().select(Settings.Theme.LIGHT.getTheme());

        /*String lightColor = "#0d7bf2"; // PLAN B
        String backgroundColor = "-fx-background-color: ";

        this.btnOverview.setStyle(backgroundColor + lightColor);
        this.btnAddTable.setStyle(backgroundColor + lightColor);
        this.btnUpdateTable.setStyle(backgroundColor + lightColor);
        this.btnSettings.setStyle(backgroundColor + lightColor);
        this.btnQuit.setStyle(backgroundColor + lightColor);*/

        //String css = Objects.requireNonNull(this.getClass().getResource("style.css")).toExternalForm(); // NON VA

        //this.vBoxRoot.getStylesheets().remove("res/assets/css/style.css"); //TODO: remove dark theme
        //this.vBoxRoot.getStylesheets().add(Main.class.getResource("../css/style.css").toExternalForm()); // NON VA
        //this.vBoxRoot.getStylesheets().add(getClass().getResource( "res/assets/css/style.css" ).toExternalForm()); // NON VA

        //this.vBoxRoot.getStylesheets().clear();
        //this.vBoxRoot.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm()); // NON VA
        //this.vBoxRoot.getScene().getStylesheets().add("stylesheet.css"); // NON VA

        //this.vBoxRoot.getScene().getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm()); // NON VA

        //this.vBoxRoot.getStylesheets().add("src/main/resources/style.css"); // NON VA
        //this.vBoxRoot.getStylesheets().add(String.valueOf(Main.class.getResource("style.css").toURI())); // NON VA

        //this.btnOverview.getStylesheets().add(Paths.get("res/assets/css/style.css").toString()); // NON VA

        //scene.getStylesheets().add("file:///E:/csse2002-7023/src/csse2002/block/world/main.css"); // REFERENCE

        // Paths.get("E","csse2002-7023","src","csse2002","block","world","main.css").toUri().toString()) // REFERENCE 2

        this.vBoxRoot.getStylesheets().clear();
        this.vBoxRoot.getStylesheets().add("file:///D:/Documenti/IntelliJ-workspace/Aeroporto/src/main/resources/com/lucar01/css/light/style.css"); // FUNZIONAAAAAAAAAAAAAAA ALLELUIA

        //this.vBoxRoot.getStylesheets().add(Paths.get("D", "Documenti", "IntelliJ-workspace", "Aeroporto", "src", "main", "resources", "com", "lucar01", "css", "style.css").toUri().toString()); // QUESTO è da fixare

        Image imgTheme = new Image(Files.newInputStream(Paths.get("res/assets/icons/icons8_haze_40px.png")));
        this.imgViewTheme.setImage(imgTheme);
    }

    private void setDarkTheme() throws IOException {

        this.comboTheme.getSelectionModel().select(Settings.Theme.DARK.getTheme());

        /*String darkColor = "#3e3c3f"; // PLAN B
        String backgroundColor = "-fx-background-color: ";

        this.btnOverview.setStyle(backgroundColor + darkColor + "; -fx-cursor: hand" + "button:hover");
        this.btnAddTable.setStyle(backgroundColor + darkColor);
        this.btnUpdateTable.setStyle(backgroundColor + darkColor);
        this.btnSettings.setStyle(backgroundColor + darkColor);
        this.btnQuit.setStyle(backgroundColor + darkColor);*/

        this.vBoxRoot.getStylesheets().clear();
        this.vBoxRoot.getStylesheets().add("file:///D:/Documenti/IntelliJ-workspace/Aeroporto/src/main/resources/com/lucar01/css/dark/dark_style.css");

        Image imgTheme = new Image(Files.newInputStream(Paths.get("res/assets/icons/icons8_night_40px.png")));
        //Image imgTheme = new Image("res/assets/icons/icons8_night_40px.png"); // questo invece non va
        this.imgViewTheme.setImage(imgTheme);


    }

}