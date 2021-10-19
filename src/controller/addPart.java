package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;

public class addPart {

    /**
     * In-house part source radio button
     */
    @FXML
    private RadioButton inhouseRadioButton;

    /**
     * Part source Label
     */
    @FXML
    private Label partSourceLabel;

    /**
     * Part name field
     */
    @FXML
    private TextField partNameField;

    /**
     * Part stock / inventory field
     */
    @FXML
    private TextField partStockField;

    /**
     * Part price field
     */
    @FXML
    private TextField partPriceField;

    /**
     * Part max stock field
     */
    @FXML
    private TextField partMaxField;

    /**
     * Part min stock field
     */
    @FXML
    private TextField partMinField;

    /**
     * <pre>
     * Part custom field
     *
     * This field will hold the machine ID for in-house parts
     * and the company name for outsourced parts.
     * </pre>
     */
    @FXML
    private TextField partCustomField;

    /**
     * Part TextFlow area for managing messages
     */
    @FXML
    private TextFlow addPartMessageText;

    /**
     * Holds the data validation object
     */
    private Validator validator;

    /**
     * Initialization method
     *
     * @return void
     */
    @FXML
    public void initialize() {
        Platform.runLater(() -> partNameField.requestFocus());
    }

    /**
     * Changes Part Type label when in-house Radio Button Clicked
     *
     * @param event In-House Radio Button click event
     */
    @FXML
    protected void onInhouseRadioSelect(ActionEvent event) {
        partSourceLabel.setText("Machine ID");
    }

    /**
     * Changes Part Type label when outsourced Radio Button Clicked
     *
     * @param event In-House Radio Button click event
     */
    @FXML
    protected void onOutsourcedRadioSelect(ActionEvent event) {
        partSourceLabel.setText("Company Name");
    }

    /**
     * Saves the part in the inventory parts collection
     *
     * @param event Save button clicked event
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent event) {

        // Clear any existing messages
        addPartMessageText.getChildren().clear();

        if (!doValidate()) return;

        Inventory inv = Inventory.getInstance();

        try {

            String name = validator.getName();
            double price = validator.getPrice();
            int stock = validator.getStock();
            int min = validator.getMin();
            int max = validator.getMax();

            if (inhouseRadioButton.isSelected()) {
                inv.addPart(new InHouse(inv.getNextPartId(), name, price, stock, min, max, validator.getMachineId()));
            } else {
                inv.addPart(new Outsourced(inv.getNextPartId(), name, price, stock, min, max, validator.getCompanyName()));
            }

            onCancelButtonClick(event);

        } catch (Exception e) {
            Text text = new Text("An unknown error occurred!   \n");
            text.setFill(Color.RED);
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
            addPartMessageText.getChildren().addAll(text);
        }
    }

    /**
     * Instantiates the Validator class and sets any validation error messages.
     *
     * @return boolean
     */
    private Boolean doValidate() {

        validator = new Validator(
            partNameField.getText(),
            partPriceField.getText(),
            partStockField.getText(),
            partMinField.getText(),
            partMaxField.getText(),
            partSourceLabel.getText(),
            partCustomField.getText()
        );

        if (!validator.isValid()) {
            for (String msg : validator.getMessages()) {
                Text text = new Text(msg + "   \n");
                text.setFill(Color.RED);
                text.setFont(Font.font("CiscoSans", FontWeight.BOLD, 15));
                addPartMessageText.getChildren().addAll(text);
            }

            addPartMessageText.getChildren().addAll(new Text("\n"));

            return false;
        }

        return true;
    }

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
