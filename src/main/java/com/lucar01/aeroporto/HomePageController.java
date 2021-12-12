package com.lucar01.aeroporto;

import com.lucar01.aeroporto.controllers.DataController;
import com.lucar01.aeroporto.controllers.DatabaseController;
import com.lucar01.aeroporto.save.Data;
import com.lucar01.aeroporto.table.Table;
import com.lucar01.aeroporto.table.Tables;
import com.mysql.cj.conf.StringProperty;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.*;

public class HomePageController implements Initializable { //TODO: mettere nel package controllers, add javadoc.

    private static final String PERSONA = "PERSONA";
    private static final String BAGAGLIO = "BAGAGLIO";
    private static final String TERMINAL = "TERMINAL";
    private static final String AEREO = "AEREO";
    private static final String ASSICURAZIONE = "ASSICURAZIONE";
    private static final String CARGO = "CARGO";
    private static final String CENTRO_CONTROLLO_AREA = "CENTRO_CONTROLLO_AREA";
    private static final String COMPAGNIA_AEREA = "COMPAGNIA_AEREA";
    private static final String COMPONENTE_AEREO = "COMPONENTE_AEREO";
    private static final String GATE = "GATE";
    private static final String GROUND_SUPPORT_EQUIPMENT = "GROUND_SUPPORT_EQUIPMENT";
    private static final String HANGAR = "HANGAR";
    private static final String LOGISTICA = "LOGISTICA";
    private static final String MANTENIMENTO = "MANTENIMENTO";
    private static final String NEGOZIO = "NEGOZIO";
    private static final String PISTA = "PISTA";
    private static final String RADAR = "RADAR";
    private static final String SERVIZIO_CLIENTI = "SERVIZIO_CLIENTI";
    private static final String SOCCORSI = "SOCCORSI";
    private static final String TORRE_CONTROLLO = "TORRE_DI_CONTROLLO";
    private static final String TRATTA = "TRATTA";
    private static final String VIA_RULLAGGIO = "VIA_DI_RULLAGGIO";
    private static final String VOLO = "VOLO";

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

    @FXML
    private HBox hBoxTitleBar;

    private HashMap<String, String> settingsMap = Data.loadSettings();

    private boolean isLightTheme = Objects.equals(this.settingsMap.get(Data.THEME_STRING), Settings.Theme.LIGHT.getTheme()); //TODO: caricare da file
    private boolean isEnglish = Objects.equals(this.settingsMap.get(Data.LANG_STRING), Settings.Languages.ENGLISH.getLanguage()); //TODO: rename in isLanguageEnglish o isInEnglish, caricare dal file


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
        /*this.comboLanguage.getSelectionModel().select(this.settingsMap.get(Data.LANG_STRING)); //TODO: salvare in un file.
        this.comboTheme.getSelectionModel().select(this.settingsMap.get(Data.THEME_STRING));*/

        System.out.println("settingsMap: " + this.settingsMap); //TODO: remove
        System.out.println("isLightTheme: " + this.isLightTheme); //TODO: remove
        System.out.println("isEnglish: " + this.isEnglish); //TODO: remove

        try {
            if(this.isLightTheme){
                setLightTheme();
            } else {
                setDarkTheme();
            }

            if(this.isEnglish){
                setLanguageSetting(Settings.Languages.ENGLISH);
            } else {
                setLanguageSetting(Settings.Languages.ITALIANO);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

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

            if(this.isLightTheme){
                this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(18, 162, 237), CornerRadii.EMPTY, Insets.EMPTY)));
            } else {
                this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(11, 61, 155), CornerRadii.EMPTY, Insets.EMPTY)));
            }

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

            if(this.isLightTheme){
                this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(123, 20, 23), CornerRadii.EMPTY, Insets.EMPTY)));
            } else {
                this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(71, 5, 5), CornerRadii.EMPTY, Insets.EMPTY)));
            }

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

            if(this.isLightTheme){
                this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(14, 64, 28), CornerRadii.EMPTY, Insets.EMPTY))); // 44, 125, 51
            } else {
                this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(3, 34, 7), CornerRadii.EMPTY, Insets.EMPTY)));
            }

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

            if(this.isLightTheme){
                this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(56, 11, 90), CornerRadii.EMPTY, Insets.EMPTY))); // 109, 21, 170
            } else {
                this.paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(30, 4, 51), CornerRadii.EMPTY, Insets.EMPTY)));
            }

            this.paneSettings.toFront();
        } else {
            if(event.getSource() == btnQuit){
                final Alert quitAlert = new Alert(Alert.AlertType.CONFIRMATION);

                if(this.isEnglish){
                    quitAlert.setTitle("Exit");
                    quitAlert.setHeaderText("Exit from the program");
                    quitAlert.setContentText("Are you sure to exit?");
                } else {
                    quitAlert.setTitle("Esci");
                    quitAlert.setHeaderText("Uscita dal programma");
                    quitAlert.setContentText("Sei sicuro di voler uscire?");
                }

                //quitAlert.show();

                Optional<ButtonType> quitResult = quitAlert.showAndWait();
                if(quitResult.get() == ButtonType.OK){
                    javafx.application.Platform.exit();
                }
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

        switch(this.combo_tables.getSelectionModel().getSelectedItem()){
            case PERSONA:
                showTable(Table.PERSONA, this.table);
                break;
            case BAGAGLIO:
                showTable(Table.BAGAGLIO, this.table);
                break;
            case TERMINAL:
                showTable(Table.TERMINAL, this.table);
                break;
            case AEREO:
                showTable(Table.AEREO, this.table);
                break;
            case COMPONENTE_AEREO:
                showTable(Table.COMPONENTE_AEREO, this.table);
                break;
            case ASSICURAZIONE:
                showTable(Table.ASSICURAZIONE, this.table);
                break;
            case CARGO:
                showTable(Table.CARGO, this.table);
                break;
            case CENTRO_CONTROLLO_AREA:
                showTable(Table.CENTRO_CONTROLLO_AREA, this.table);
                break;
            case COMPAGNIA_AEREA:
                showTable(Table.COMPAGNIA_AEREA, this.table);
                break;
            case GATE:
                showTable(Table.GATE, this.table);
                break;
            case GROUND_SUPPORT_EQUIPMENT:
                showTable(Table.GROUND_SUPPORT_EQUIPMENT, this.table);
                break;
            case HANGAR:
                showTable(Table.HANGAR, this.table);
                break;
            case LOGISTICA:
                showTable(Table.LOGISTICA, this.table);
                break;
            case MANTENIMENTO:
                showTable(Table.MANTENIMENTO, this.table);
                break;
            case NEGOZIO:
                showTable(Table.NEGOZIO, this.table);
                break;
            case PISTA:
                showTable(Table.PISTA, this.table);
                break;
            case RADAR:
                showTable(Table.RADAR, this.table);
                break;
            case SERVIZIO_CLIENTI:
                showTable(Table.SERVIZIO_CLIENTI, this.table);
                break;
            case SOCCORSI:
                showTable(Table.SOCCORSI, this.table);
                break;
            case TORRE_CONTROLLO:
                showTable(Table.TORRE_DI_CONTROLLO, this.table);
                break;
            case TRATTA:
                showTable(Table.TRATTA, this.table);
                break;
            case VIA_RULLAGGIO:
                showTable(Table.VIA_DI_RULLAGGIO, this.table);
                break;
            case VOLO:
                showTable(Table.VOLO, this.table);
                break;
            default:
                this.table.getColumns().clear();
                break;
        }
    }

    private void createTable(final int numOfColumns, final ObservableList<String> columnsName, TableView<Tables> tableView){

        /*for(int i = 0; i < columnsName.size(); i++){
            columnsName.set(i, columnsName.get(i).replaceAll("_", ""));
            columnsName.set(i, columnsName.get(i).replaceAll("[^\\p{ASCII}]", "")); // Per rimuovere gli accenti (però toglie tutta la lettera non solo l'accento).
        }*/

        //System.out.println("columnsName senza _ e accenti: " + columnsName); //TODO: remove

        for(int i = 0; i < numOfColumns; i++){
            TableColumn<Tables, String> column = new TableColumn<>();
            column.setText(columnsName.get(i).toUpperCase());
            column.setCellValueFactory(new PropertyValueFactory<>(columnsName.get(i))); // Non riesce a leggere gli underscores _ ?
            tableView.getColumns().add(column);
        }
    }

    // I NOMI DEI GETTERS DEVONO COMBACIARE CON QUELLI DEL DATABASE!

    private void showTable(final Table table, TableView<Tables> tableView){
        tableView.getColumns().clear();

        final ObservableList<String> namesOfColumnsList = DatabaseController.getNamesOfColumns(table);
        final int numOfColumns = DatabaseController.getNumberOfColumns(table);
        final ObservableList<Tables> tableDataList = DatabaseController.getTableData(table); //TODO: <String>?, tableDataList è vuoto, questo è il problema!

        //final ObservableList<String> data = FXCollections.observableArrayList("Ciao", "Yee"); //TODO: remove

        System.out.println("numOfColumns: " + numOfColumns); //TODO: remove
        System.out.println("tableDataList: " + tableDataList); //TODO: remove
        createTable(numOfColumns, namesOfColumnsList, tableView);

        tableView.setItems(tableDataList);
        //tableView.setItems(data); //TODO: remove
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
            label.setLayoutX(i > limitTextFields ? 6 * (xLabel + 40) : xLabel);
            label.setLayoutY(i > limitTextFields ? (i - limitTextFields) * yLabel : i * yLabel); // Se cambia colonna, devo tornare a zero la y, ovvero deve tornare su

            TextField textField = new TextField();
            textField.setLayoutX(i > limitTextFields ? 6 * xTextField : xTextField);
            textField.setLayoutY(i > limitTextFields ? (i - limitTextFields) * yTextField : i * yTextField); // Se cambia colonna, devo tornare a zero la y, ovvero deve tornare su
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

    /*searchBox.textProperty().addListener((observable, oldValue, newValue) ->
            exampleTable.setItems(filterList(orders, newValue))
            );

    FilteredList<Order> filteredData = new FilteredList<>(FXCollections.observableList(orders));
exampleTable.setItems(filteredData);

    private Predicate<Order> createPredicate(String searchText){
        return order -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(order, searchText);
        };
    }

    searchBox.textProperty().addListener((observable, oldValue, newValue) ->
            filteredData.setPredicate(createPredicate(newValue))
            );*/

    /*@FXML
    void handleSearchBar(InputMethodEvent event) { //TODO: fix
        System.out.println("searchbar"); //TODO: remove
        this.table.getItems().stream().filter(item -> Objects.equals(item, this.searchField.getText())).findAny().ifPresent(item -> {
            this.table.getSelectionModel().select(item);
            this.table.scrollTo(item);
        });
    }*/

    @FXML
    void handleSearchBar(KeyEvent event) throws NoSuchFieldException { //TODO: fix, questo viene chiamato rispetto al primo.
        System.out.println("searchbar2"); //TODO: remove
        this.table.getItems().stream().filter(item -> Objects.equals(item.toString(), this.searchField.getText())).findAny().ifPresent(item -> {
            this.table.getSelectionModel().select(item);
            this.table.scrollTo(item);
        });

        /*if(this.table.getColumns().get().getCellData()) //TODO: fix
            if(cellValue.contains(txtField.textProperty().get().toLowerCase())) {

                tableItems.add(data.get(i));

                break;*/

        DataController dataController = new DataController(); // DataController<Tables>

        final ObservableList observableList = dataController.getTableData(Table.valueOf(this.combo_tables.getSelectionModel().getSelectedItem()));

        System.out.println("DataController.getTableData: " + observableList); //TODO: remove
        System.out.println("DataController.getTableData: " + Arrays.toString(observableList.getClass().getFields())); //TODO: remove
        System.out.println("DataController.getTableData: " + observableList.size()); //TODO: remove
    }

    /*private void initFilter() {

        //searchField = TextFields.createSearchField();
        //searchField.setPromptText("Filter");

        searchField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(javafx.beans.Observable observable) {

                if (searchField.textProperty().get().isEmpty()) {

                    table.setItems(data);
                    //handleTableSelection(); //TODO: richiamare un metodo, in cui metto
                    //TODO: lo switch di handleTableSelection() e lo richiamo anche lì.
                    return;

                }

                ObservableList<Tables> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Tables, ?>> cols = table.getColumns();

                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < cols.size(); j++) {

                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(data.get(i)).toString();
                        cellValue = cellValue.toLowerCase();

                        if (cellValue.contains(searchField.textProperty().get().toLowerCase())) {

                            tableItems.add(data.get(i));

                            break;
                        }
                    }
                }
                table.setItems(tableItems); //TODO: handleTableSelection()
            }
        });
    }*/

    @FXML
    void handleAddTableSelection(ActionEvent event) {
        String selectedTable = this.combo_add_table.getSelectionModel().getSelectedItem();
        this.btnAddSelectedTable.setText(selectedTable);

        switch(this.combo_add_table.getSelectionModel().getSelectedItem()){
            case PERSONA:
                createTextFields(Table.PERSONA, this.addAnchorPane, this.textFields, 0, 25, 83, 25, "#7b1417", 9);
                break;
            case BAGAGLIO:
                createTextFields(Table.BAGAGLIO, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 10);
                break;
            case TERMINAL:
                createTextFields(Table.TERMINAL, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 10);
                break;
            case AEREO:
                createTextFields(Table.AEREO, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 5);
                break;
            case ASSICURAZIONE:
                createTextFields(Table.ASSICURAZIONE, this.addAnchorPane, this.textFields, 0, 40, 93, 40, "#7b1417", 5);
                break;
            case CARGO:
                createTextFields(Table.CARGO, this.addAnchorPane, this.textFields, 0, 40, 93, 40, "#7b1417", 5);
                break;
            case CENTRO_CONTROLLO_AREA:
                createTextFields(Table.CENTRO_CONTROLLO_AREA, this.addAnchorPane, this.textFields, 0, 40, 83, 40, "#7b1417", 5);
                break;
            case COMPAGNIA_AEREA:
                createTextFields(Table.COMPAGNIA_AEREA, this.addAnchorPane, this.textFields, 0, 40, 93, 40, "#7b1417", 8);
                break;
            case COMPONENTE_AEREO:
                createTextFields(Table.COMPONENTE_AEREO, this.addAnchorPane, this.textFields, 0, 40, 93, 40, "#7b1417", 5);
                break;
            case GATE:
                createTextFields(Table.GATE, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 5);
                break;
            case GROUND_SUPPORT_EQUIPMENT:
                createTextFields(Table.GROUND_SUPPORT_EQUIPMENT, this.addAnchorPane, this.textFields, 0, 40, 88, 40, "#7b1417", 5);
                break;
            case HANGAR:
                createTextFields(Table.HANGAR, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 5);
                break;
            case LOGISTICA:
                createTextFields(Table.LOGISTICA, this.addAnchorPane, this.textFields, 0, 40, 88, 40, "#7b1417", 8);
                break;
            case MANTENIMENTO:
                createTextFields(Table.MANTENIMENTO, this.addAnchorPane, this.textFields, 0, 40, 93, 40, "#7b1417", 5);
                break;
            case NEGOZIO:
                createTextFields(Table.NEGOZIO, this.addAnchorPane, this.textFields, 0, 40, 83, 40, "#7b1417", 5);
                break;
            case PISTA:
                createTextFields(Table.PISTA, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 5);
                break;
            case RADAR:
                createTextFields(Table.RADAR, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 5);
                break;
            case SERVIZIO_CLIENTI:
                createTextFields(Table.SERVIZIO_CLIENTI, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 5);
                break;
            case SOCCORSI:
                createTextFields(Table.SOCCORSI, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 5);
                break;
            case TORRE_CONTROLLO:
                createTextFields(Table.TORRE_DI_CONTROLLO, this.addAnchorPane, this.textFields, 0, 40, 98, 40, "#7b1417", 5);
                break;
            case TRATTA:
                createTextFields(Table.TRATTA, this.addAnchorPane, this.textFields, 0, 40, 103, 40, "#7b1417", 7);
                break;
            case VIA_RULLAGGIO:
                createTextFields(Table.VIA_DI_RULLAGGIO, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 5);
                break;
            case VOLO:
                createTextFields(Table.VOLO, this.addAnchorPane, this.textFields, 0, 40, 73, 40, "#7b1417", 5);
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

        switch(selectedTable){
            case BAGAGLIO:
                showTable(Table.BAGAGLIO, this.editTable);
                createTextFields(Table.BAGAGLIO, this.editAnchorPane, this.editTextFields, 0, 40, 73, 40, "#0e401c", 10);
                break;
            case TERMINAL:
                showTable(Table.TERMINAL, this.editTable);
                createTextFields(Table.TERMINAL, this.editAnchorPane, this.editTextFields, 0, 40, 73, 40, "#0e401c", 10);
                break;
            case PERSONA:
                showTable(Table.PERSONA, this.editTable);
                createTextFields(Table.PERSONA, this.editAnchorPane, this.editTextFields, 0, 20, 73, 20, "#0e401c", 20);
                break;
            case AEREO:
                showTable(Table.AEREO, this.editTable);
                createTextFields(Table.AEREO, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case ASSICURAZIONE:
                showTable(Table.ASSICURAZIONE, this.editTable);
                createTextFields(Table.ASSICURAZIONE, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case CARGO:
                showTable(Table.CARGO, this.editTable);
                createTextFields(Table.CARGO, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case CENTRO_CONTROLLO_AREA:
                showTable(Table.CENTRO_CONTROLLO_AREA, this.editTable);
                createTextFields(Table.CENTRO_CONTROLLO_AREA, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case COMPAGNIA_AEREA:
                showTable(Table.COMPAGNIA_AEREA, this.editTable);
                createTextFields(Table.COMPAGNIA_AEREA, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case COMPONENTE_AEREO:
                showTable(Table.COMPONENTE_AEREO, this.editTable);
                createTextFields(Table.COMPONENTE_AEREO, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case GATE:
                showTable(Table.GATE, this.editTable);
                createTextFields(Table.GATE, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case GROUND_SUPPORT_EQUIPMENT:
                showTable(Table.GROUND_SUPPORT_EQUIPMENT, this.editTable);
                createTextFields(Table.GROUND_SUPPORT_EQUIPMENT, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case HANGAR:
                showTable(Table.HANGAR, this.editTable);
                createTextFields(Table.HANGAR, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case LOGISTICA:
                showTable(Table.LOGISTICA, this.editTable);
                createTextFields(Table.LOGISTICA, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case MANTENIMENTO:
                showTable(Table.MANTENIMENTO, this.editTable);
                createTextFields(Table.MANTENIMENTO, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case NEGOZIO:
                showTable(Table.NEGOZIO, this.editTable);
                createTextFields(Table.NEGOZIO, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case PISTA:
                showTable(Table.PISTA, this.editTable);
                createTextFields(Table.PISTA, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case RADAR:
                showTable(Table.RADAR, this.editTable);
                createTextFields(Table.RADAR, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case SERVIZIO_CLIENTI:
                showTable(Table.SERVIZIO_CLIENTI, this.editTable);
                createTextFields(Table.SERVIZIO_CLIENTI, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case SOCCORSI:
                showTable(Table.SOCCORSI, this.editTable);
                createTextFields(Table.SOCCORSI, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case TORRE_CONTROLLO:
                showTable(Table.TORRE_DI_CONTROLLO, this.editTable);
                createTextFields(Table.TORRE_DI_CONTROLLO, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case TRATTA:
                showTable(Table.TRATTA, this.editTable);
                createTextFields(Table.TRATTA, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case VIA_RULLAGGIO:
                showTable(Table.VIA_DI_RULLAGGIO, this.editTable);
                createTextFields(Table.VIA_DI_RULLAGGIO, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
                break;
            case VOLO:
                showTable(Table.VOLO, this.editTable);
                createTextFields(Table.VOLO, this.editAnchorPane, this.editTextFields, 0, 30, 73, 30, "#0e401c", 99); //TODO: fix
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

        Alert updateAlertSuccess = new Alert(Alert.AlertType.INFORMATION);
        Alert updateAlertError = new Alert(Alert.AlertType.ERROR);

        if(this.isEnglish){
            updateAlertSuccess.setTitle("Update");
            updateAlertSuccess.setHeaderText("Update Data");
            updateAlertSuccess.setContentText("Update Data successfully executed");

            updateAlertError.setTitle("Update Error");
            updateAlertError.setHeaderText("Update Data Error");
            updateAlertError.setContentText("Update data failed. An error has occurred");
        } else {
            updateAlertSuccess.setTitle("Aggiorna");
            updateAlertSuccess.setHeaderText("Aggiorna Dati");
            updateAlertSuccess.setContentText("Dati aggiornati correttamente");

            updateAlertError.setTitle("Aggiorna Errore");
            updateAlertError.setHeaderText("Aggiorna Dati Errore");
            updateAlertError.setContentText("Dati non aggiornati. Un errore si è presentato");
        }

        if(hasUpdated){
            System.out.println("operazione eseguita con successo"); //TODO: remove
            updateAlertSuccess.show();
            this.editTable.refresh(); // Questo non funziona, o meglio non mi refresha i dati in tempo reale.
        } else {
            updateAlertError.show();
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
    }

    @FXML
    void handleThemeSelection(ActionEvent event) {

        switch(this.comboTheme.getSelectionModel().getSelectedItem()){
            case "Light":
                this.isLightTheme = true;

                setLightTheme();
                break;
            case "Dark":
                this.isLightTheme = false;

                setDarkTheme();
                break;
        }
    }

    @FXML
    void handleLanguageSelection(ActionEvent event) throws IOException {

        switch(this.comboLanguage.getSelectionModel().getSelectedItem()){
            case "Italiano":
                setLanguageSetting(Settings.Languages.ITALIANO);
                break;

            default:
            case "English":
                setLanguageSetting(Settings.Languages.ENGLISH);
                break;
        }

    }

    private void setLanguageSetting(Settings.Languages language) throws IOException {
        this.comboLanguage.getSelectionModel().select(language.getLanguage());

        switch(language.getLanguage()){
            case "Italiano":

                Image imgLanguageIt = new Image(Objects.requireNonNull(getClass().getResource("/com/lucar01/icons/icons8_italy_48px_1.png")).toExternalForm());

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
                Image imgLanguageEng = new Image(Objects.requireNonNull(getClass().getResource("/com/lucar01/icons/icons8_great_britain_48px.png")).toExternalForm());
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

        final boolean hasSaved = Data.saveSettings(this.comboTheme.getSelectionModel().getSelectedItem(), this.comboLanguage.getSelectionModel().getSelectedItem());

        final Alert saveAlertSuccess = new Alert(Alert.AlertType.INFORMATION);
        final Alert saveAlertError = new Alert(Alert.AlertType.ERROR);

        if(this.isEnglish){
            saveAlertSuccess.setTitle("Save");
            saveAlertSuccess.setHeaderText("Save Data");
            saveAlertSuccess.setContentText("Data has been saved successfully");

            saveAlertError.setTitle("Save Error");
            saveAlertError.setHeaderText("Save Data Error");
            saveAlertError.setContentText("Data save failed. An error has occurred");
        } else {
            saveAlertSuccess.setTitle("Salva");
            saveAlertSuccess.setHeaderText("Salva Dati");
            saveAlertSuccess.setContentText("I dati sono stati salvati correttamente");

            saveAlertError.setTitle("Salva Errore");
            saveAlertError.setHeaderText("Salva Dati Errore");
            saveAlertError.setContentText("I dati non sono stati salvati. Un errore è stato riscontrato");
        }

        if(hasSaved){
            saveAlertSuccess.show();
        } else {
            saveAlertError.show();
        }
    }

    @FXML
    private void submitDeleteRow(ActionEvent event) {

        final Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);

        if(this.isEnglish){
            deleteConfirmation.setTitle("Delete Confirm");
            deleteConfirmation.setHeaderText("Delete Confirmation");
            deleteConfirmation.setContentText("Are you sure to delete?");
        } else {
            deleteConfirmation.setTitle("Cancella Conferma");
            deleteConfirmation.setHeaderText("Conferma cancellazione");
            deleteConfirmation.setContentText("Sei sicuro di voler cancellare?");
        }

        Optional<ButtonType> deleteResult = deleteConfirmation.showAndWait();

        if(deleteResult.get() == ButtonType.OK){
            final int index = this.table.getSelectionModel().getSelectedIndex();

            final int numOfColumns = DatabaseController.getNumberOfColumns(Table.valueOf(this.combo_tables.getSelectionModel().getSelectedItem()));

            ObservableList<String> dataToDelete = FXCollections.observableArrayList();

            for(int i = 0; i < numOfColumns; i++){
                dataToDelete.add(this.table.getColumns().get(i).getCellData(index).toString());
            }

            System.out.println("dataToDelete: " + dataToDelete); //TODO: remove

            final boolean hasDeleted = DatabaseController.deleteTableData(Table.valueOf(this.combo_tables.getSelectionModel().getSelectedItem()), dataToDelete);

            Alert deleteAlertSuccess = new Alert(Alert.AlertType.INFORMATION);
            Alert deleteAlertError = new Alert(Alert.AlertType.ERROR);

            if(this.isEnglish){
                deleteAlertSuccess.setTitle("Delete");
                deleteAlertSuccess.setHeaderText("Delete Data");
                deleteAlertSuccess.setContentText("Data successfully deleted"); // Operation delete successfully executed

                deleteAlertError.setTitle("Delete Error");
                deleteAlertError.setHeaderText("Delete Data Error");
                deleteAlertError.setContentText("Data has not been deleted. Error has occurred"); // Operation delete failed. An error has occurred
            } else {
                deleteAlertSuccess.setTitle("Cancella");
                deleteAlertSuccess.setHeaderText("Cancella Dati");
                deleteAlertSuccess.setContentText("Operazione di cancellazione eseguita con successo");

                deleteAlertError.setTitle("Cancella Errore");
                deleteAlertError.setHeaderText("Cancella Dati Errore");
                deleteAlertError.setContentText("Operazione di cancellazione non eseguita. Un errore è capitato");
            }

            if(hasDeleted){
                System.out.println("Operazione cancellazione eseguita con successo."); //TODO: remove
                deleteAlertSuccess.show();
            } else {
                System.out.println("Operazione cancellazione failed."); //TODO: remove
                deleteAlertError.show();
            }
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

    private void setLightTheme() {

        this.comboTheme.getSelectionModel().select(Settings.Theme.LIGHT.getTheme());

        this.vBoxRoot.getStylesheets().clear();

        this.paneOverview.getStylesheets().clear();
        this.paneAddTable.getStylesheets().clear();
        this.paneUpdateTable.getStylesheets().clear();
        this.paneSettings.getStylesheets().clear();

        this.hBoxTitleBar.getStylesheets().clear();

        this.vBoxRoot.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/light/style.css")).toExternalForm());

        this.paneOverview.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/light/table_style.css")).toExternalForm());
        this.paneAddTable.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/light/add_table_style.css")).toExternalForm());
        this.paneUpdateTable.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/light/edit_table_style.css")).toExternalForm());
        this.paneSettings.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/light/settings_style.css")).toExternalForm());

        this.hBoxTitleBar.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/light/title_bar_style.css")).toExternalForm());

        Image imgTheme = new Image(Objects.requireNonNull(getClass().getResource("/com/lucar01/icons/icons8_haze_40px.png")).toExternalForm());
        this.imgViewTheme.setImage(imgTheme);
    }

    private void setDarkTheme() {

        this.comboTheme.getSelectionModel().select(Settings.Theme.DARK.getTheme());

        this.vBoxRoot.getStylesheets().clear();

        this.paneOverview.getStylesheets().clear();
        this.paneAddTable.getStylesheets().clear();
        this.paneUpdateTable.getStylesheets().clear();
        this.paneSettings.getStylesheets().clear();

        this.hBoxTitleBar.getStylesheets().clear();


        this.vBoxRoot.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/dark/dark_style.css")).toExternalForm());

        this.paneOverview.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/dark/dark_table_style.css")).toExternalForm());
        this.paneAddTable.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/dark/dark_add_table_style.css")).toExternalForm());
        this.paneUpdateTable.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/dark/dark_edit_table_style.css")).toExternalForm());
        this.paneSettings.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/dark/dark_settings_style.css")).toExternalForm());

        this.hBoxTitleBar.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lucar01/css/dark/dark_title_bar_style.css")).toExternalForm());

        Image imgTheme = new Image(Objects.requireNonNull(getClass().getResource("/com/lucar01/icons/icons8_night_40px.png")).toExternalForm());
        this.imgViewTheme.setImage(imgTheme);


    }

}