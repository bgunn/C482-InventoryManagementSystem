package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class addPart {

    /**
     * Handles cancel button click to return to the main scene
     *
     * @param event Button click ActionEvent
     * @throws IOException FXMLLoader
     */
    @FXML
    void onCancelButtonClick(ActionEvent event) throws IOException {
        Stage screen = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        screen.setScene(new Scene(scene));
        screen.show();
    }
}
