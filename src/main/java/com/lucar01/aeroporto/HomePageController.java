package com.lucar01.aeroporto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleButtonsClick(ActionEvent event){ //TODO: rename in handleMenuButtons?
        //TODO: try to use a switch if possible
        if(event.getSource() == btnOverview){
            lblStatusTitle.setText("Overview");
            lblStatusSubtitle.setText("Visualizza le tabelle");
            paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(18, 162, 237), CornerRadii.EMPTY, Insets.EMPTY)));
            paneOverview.toFront();
        }
        else if(event.getSource() == btnAddTable){
            lblStatusTitle.setText("Add Table");
            lblStatusSubtitle.setText("Aggiungi tabelle al database");
            paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(123, 20, 23), CornerRadii.EMPTY, Insets.EMPTY)));
            paneAddTable.toFront();
        }
        else if(event.getSource() == btnAlterTable){
            lblStatusTitle.setText("Alter Table");
            lblStatusSubtitle.setText("Modifica le tabelle");
            paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(14, 64, 28), CornerRadii.EMPTY, Insets.EMPTY))); // 44, 125, 51
            paneAlterTable.toFront();
        }
        else if(event.getSource() == btnSettings){
            lblStatusTitle.setText("Settings");
            lblStatusSubtitle.setText("Modifica le tue impostazioni");
            paneStatus.setBackground(new Background(new BackgroundFill(Color.rgb(56, 11, 90), CornerRadii.EMPTY, Insets.EMPTY))); // 109, 21, 170
            paneSettings.toFront();
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
}