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
import javafx.scene.control.ToggleGroup;
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
import java.util.ArrayList;

public class addPart {

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
     * Toggle Group for Radio Buttons
     */
    @FXML
    private ToggleGroup partSource;

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

        if (!verifyInputData()) {
            return;
        }

        Inventory inv = Inventory.getInstance();

        try {

            int id = inv.getNextPartId();
            String name = partNameField.getText();
            double price = Double.parseDouble(partPriceField.getText());
            int stock = Integer.parseInt(partStockField.getText());
            int min = Integer.parseInt(partMinField.getText());
            int max = Integer.parseInt(partMaxField.getText());

            if (inhouseRadioButton.isSelected()) {
                inv.addPart(new InHouse(id, name, price, stock, min, max, Integer.parseInt(partCustomField.getText())));
            } else {
                inv.addPart(new Outsourced(id, name, price, stock, min, max, partCustomField.getText()));
            }

            onCancelButtonClick(event);

        } catch (Exception e) {
            Text text = new Text("An unknown error occurred!   \n");
            text.setFill(Color.RED);
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
            addPartMessageText.getChildren().addAll(text);
            return;
        }
    }

    /**
     * Saves the part in the inventory parts collection
     *
     * @param event Save button clicked event
     */
    @FXML
    private boolean verifyInputData() {

        Boolean valid = true;
        ArrayList<String> messages = new ArrayList<String>();
        Integer stock = -1;
        Integer min = -1;
        Integer max = -1;

        // Validate partNameField
        if (partNameField.getText().isEmpty()) {
            messages.add("Name cannot be empty");
            valid = false;
        }

        // Validate partStockField
        if (partStockField.getText().isEmpty()) {
            messages.add("Inventory cannot be empty");
            valid = false;
        } else {
            try {
                stock = Integer.parseInt(partStockField.getText());
                if (stock < 0) {
                    messages.add("Inventory must be equal or greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException nfe) {
                messages.add("Inventory must be a number");
                valid = false;
            }
        }

        // Validate partPriceField
        if (partPriceField.getText().isEmpty()) {
            messages.add("Price cannot be empty");
            valid = false;
        } else {
            try {
                Double price = Double.parseDouble(partPriceField.getText());
                if (price <= 0.0) {
                    messages.add("Price must be greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException nfe) {
                messages.add("Price must be a number");
                valid = false;
            }
        }

        // Validate partMaxField
        if (partMaxField.getText().isEmpty()) {
            messages.add("Max cannot be empty");
            valid = false;
        } else {
            try {
                max = Integer.parseInt(partMaxField.getText());
                if (max <= 0) {
                    messages.add("Max must be greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException nfe) {
                messages.add("Max must be a number");
                valid = false;
            }
        }

        // Validate partMinField
        if (partMinField.getText().isEmpty()) {
            messages.add("Min cannot be empty");
            valid = false;
        } else {
            try {
                min = Integer.parseInt(partMinField.getText());
                if (min < 0) {
                    messages.add("Min must be equal or greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException nfe) {
                messages.add("Min must be a number");
                valid = false;
            }
        }

        // Validate partCustomField
        String customField = partSourceLabel.getText();
        if (partCustomField.getText().isEmpty()) {
            messages.add(customField + " cannot be empty");
            valid = false;
        } else if (inhouseRadioButton.isSelected()) {
            try {
                Integer.parseInt(partCustomField.getText());
            } catch (NumberFormatException nfe) {
                messages.add(customField + " must be a number");
                valid = false;
            }
        }

        // Validate stock/min/max
        // Min should be less than Max; and Inv should be between those two values.
        if (stock != -1 && min != -1 && max != -1) {
            if (max <= min) {
                messages.add("Max must be greater than " + min);
                valid = false;
            } else if (stock < min || stock > max) {
                messages.add("Inventory must be between " + min + " and " + max);
                valid = false;
            }
        }

        // Display error messages
        for (String msg : messages) {
            Text text = new Text(msg + "   \n");
            text.setFill(Color.RED);
            text.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
            addPartMessageText.getChildren().addAll(text);
        }

        addPartMessageText.getChildren().addAll(new Text("\n"));

        return valid;
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
