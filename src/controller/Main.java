package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

import java.io.IOException;

/**
 * Inventory management system main controller class
 *
 */
public class Main {

    /**
     * The part search text field
     */
    @FXML
    private TextField partSearch;

    private Inventory inventory = Inventory.getInstance();

    /**
     * Convenience method to handle switching scenes
     *
     * @param event Button click ActionEvent
     * @param view  The name of the view to load
     * @return void
     */
    private void switchScenes(ActionEvent event, String view) throws IOException {
        Stage screen = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/" + view + ".fxml"));
        screen.setTitle("Add Part");
        screen.setScene(new Scene(scene));
        screen.show();
    }

    /**
     * Convenience method to handle switching scenes
     *
     * @param event Button click ActionEvent
     * @return void
     */
    @FXML
    protected void onAddPartButtonClick(ActionEvent event) throws IOException {
        switchScenes(event, "AddPart");
    }

    /**
     * Handles exit button click to terminate the application
     *
     * @param event Button click ActionEvent
     */
    @FXML
    protected void onExitButtonClick(ActionEvent event) {
        System.exit(0);
    }
}