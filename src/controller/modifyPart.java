package controller;

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
import model.Part;

import java.io.IOException;

public class modifyPart {

    /**
     * In-house part source radio button
     */
    @FXML
    private RadioButton inhouseRadioButton;

    /**
     * Outsourced part source radio button
     */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * Part source Label
     */
    @FXML
    private Label partSourceLabel;

    /**
     * Part ID field
     */
    @FXML
    private TextField partIdField;

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
     * Part custom field
     *
     * This field will hold the machine ID for in-house parts
     * and the company name for outsourced parts.
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
     * holds the selected part from the main screen table view
     */
    private Part part;

    /**
     * Ppopulate the form fields with data from the selected part
     *
     * @return void
     */
    @FXML
    public void initialize() {

        part = Main.getSelectedPart();

        if (part instanceof Outsourced) {
            outsourcedRadioButton.setSelected(true);
            onOutsourcedRadioSelect(new ActionEvent());
            partCustomField.setText(((Outsourced) part).getCompanyName());
        } else {
            partCustomField.setText(String.valueOf(((InHouse) part).getMachineId()));
        }

        partIdField.setText(String.valueOf(part.getId()));
        partNameField.setText(part.getName());
        partStockField.setText(String.valueOf(part.getStock()));
        partPriceField.setText(String.valueOf(part.getPrice()));
        partMaxField.setText(String.valueOf(part.getMax()));
        partMinField.setText(String.valueOf(part.getMin()));
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

        try {

            part.setName(validator.getName());
            part.setPrice(validator.getPrice());
            part.setStock(validator.getStock());
            part.setMin(validator.getMin());
            part.setMax(validator.getMax());

            if (inhouseRadioButton.isSelected()) {
                ((InHouse) part).setMachineId(validator.getMachineId());
            } else {
                ((Outsourced) part).setCompanyName(validator.getCompanyName());
            }

            onCancelButtonClick(event);

        } catch (Exception e) {
            Text text = new Text("An unknown error occurred!   \n");
            text.setFill(Color.RED);
            text.setFont(Font.font("CiscoSans", FontWeight.BOLD, 15));
            addPartMessageText.getChildren().addAll(text);
        }
    }

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
                text.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
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

