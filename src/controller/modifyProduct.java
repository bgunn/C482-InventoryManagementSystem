package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.function.Predicate;

public class modifyProduct {

    /**
     * The inventory singleton
     */
    private Inventory inventory = Inventory.getInstance();

    /**
     * List of associated parts
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Parts table
     */
    @FXML
    private TableView<Part> partsTable;

    /**
     * Parts table
     */
    @FXML
    private TableView<Part> associatedPartsTable;

    /**
     * The part search text field
     */
    @FXML
    private TextField partSearch;

    /**
     * Parts table id column
     */
    @FXML
    private TableColumn<Part, Integer> partsIdCol;

    /**
     * Parts table name column
     */
    @FXML
    private TableColumn<Part, String> partsNameCol;

    /**
     * Parts table price column
     */
    @FXML
    private TableColumn<Part, Double> partsPriceCol;

    /**
     * Parts table stock column
     */
    @FXML
    private TableColumn<Part, Integer> partsStockCol;

    /**
     * Associated parts table id column
     */
    @FXML
    private TableColumn<Part, Integer> assocPartsIdCol;

    /**
     * Associated parts table name column
     */
    @FXML
    private TableColumn<Part, String> assocPartsNameCol;

    /**
     * Associated parts table price column
     */
    @FXML
    private TableColumn<Part, Double> assocPartsPriceCol;

    /**
     * Associated parts table stock column
     */
    @FXML
    private TableColumn<Part, Integer> assocPartsStockCol;

    /**
     * The part section error label
     */
    @FXML
    private Label partsErrorLabel;

    /**
     * The part section error label
     */
    @FXML
    private Label associatedPartsErrorLabel;

    /**
     * Product name field
     */
    @FXML
    private TextField productNameField;

    /**
     * Product ID field
     */
    @FXML
    private TextField productIdField;

    /**
     * Product stock / inventory field
     */
    @FXML
    private TextField productStockField;

    /**
     * Product price field
     */
    @FXML
    private TextField productPriceField;

    /**
     * Product max stock field
     */
    @FXML
    private TextField productMaxField;

    /**
     * Product min stock field
     */
    @FXML
    private TextField productMinField;

    /**
     * Product TextFlow area for managing messages
     */
    @FXML
    private TextFlow addProductMessageText;

    /**
     * Holds the data validation object
     */
    private Validator validator;

    /**
     * holds the selected product from the main screen table view
     */
    private Product product;

    /**
     * Initialize data and fields
     *
     * @return void
     */
    @FXML
    public void initialize() {

        Platform.runLater(() -> productNameField.requestFocus());

        // Initialize the parts table
        partsTable.getItems().setAll(inventory.getAllParts());
        partsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        FilteredList<Part> filteredParts = new FilteredList<>(inventory.getAllParts(), b -> true);

        partSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredParts.setPredicate(partPredicate(newValue));

            if (filteredParts.size() == 0) {
                partsErrorLabel.setText("No matching parts found!");
            } else {
                partsErrorLabel.setText("");
            }
        });

        partsTable.setItems(filteredParts);

        // Initialize the associated parts table
        assocPartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        product = Main.getSelectedProduct();

        productIdField.setText(String.valueOf(product.getId()));
        productNameField.setText(product.getName());
        productStockField.setText(String.valueOf(product.getStock()));
        productPriceField.setText(String.valueOf(product.getPrice()));
        productMaxField.setText(String.valueOf(product.getMax()));
        productMinField.setText(String.valueOf(product.getMin()));

        associatedParts = product.getAllAssociatedParts();
        associatedPartsTable.setItems(associatedParts);
    }

    /**
     * Part predicate
     *
     * @param string The search string
     * @return boolean
     */
    private Predicate<Part> partPredicate(String string) {

        return part -> {

            if (string == null || string.isEmpty()) {
                return true;
            }

            return searchParts(part, string);
        };
    }

    /**
     * Part search method
     *
     * @param part the part to compare
     * @param string The search string
     * @return boolean
     */
    private boolean searchParts(Part part, String string) {
        return (part.getName().toLowerCase().contains(string.toLowerCase())) ||
                Integer.valueOf(part.getId()).toString().equals(string.toLowerCase());
    }

    /**
     * Handles the add associated part button click
     *
     * @param event Button click ActionEvent
     */
    @FXML
    protected void onAddPartButtonClick(ActionEvent event) {

        clearErrors();

        Part part = partsTable.getSelectionModel().getSelectedItem();

        if (part == null) {
            partsErrorLabel.setText("You must select a part");
            return;
        }

        for (Part p : associatedParts) {
            if (p.getId() == part.getId()) {
                partsErrorLabel.setText("Part " + part.getId() + " is already associated");
                return;
            }
        }

        associatedParts.add(part);
        associatedPartsTable.setItems(associatedParts);
    }

    /**
     * Handles the add associated part button click
     *
     * @param event Button click ActionEvent
     */
    @FXML
    protected void onRemovePartButtonClick(ActionEvent event) {

        clearErrors();

        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();

        if (part == null) {
            associatedPartsErrorLabel.setText("You must select a part");
            return;
        }

        associatedParts.remove(part);
    }

    /**
     * <pre>
     * Saves the product in the inventory products collection
     *
     * RUNTIME ERROR
     * On save the application was throwing a ConcurrentModificationException exception
     * because I was looping through the associatedParts ObservableList to delete and re-add
     * the associated parts. This was unnecessary because the associatedParts was fully updated
     * as parts were added or removed. The fix was to just remove that code.
     * </pre>
     *
     * @param event Save button clicked event
     */
    @FXML
    protected void onSaveButtonClick(ActionEvent event) {

        clearErrors();

        if (!doValidate()) return;

        try {

            product.setName(validator.getName());
            product.setPrice(validator.getPrice());
            product.setStock(validator.getStock());
            product.setMin(validator.getMin());
            product.setMax(validator.getMax());

            onCancelButtonClick(event);

        } catch (Exception e) {
            System.out.println(e);
            Text text = new Text("An unknown error occurred!   \n");
            text.setFill(Color.RED);
            text.setFont(Font.font("CiscoSans", FontWeight.BOLD, 15));
            addProductMessageText.getChildren().addAll(text);
        }
    }

    /**
     * <pre>
     * Instantiates the Validator class and sets any validation error messages.
     *
     * customField and customValue are not used for products so passing arbitrary valid values
     *</pre>
     *
     * @return boolean
     */
    private Boolean doValidate() {

        validator = new Validator(
                productNameField.getText(),
                productPriceField.getText(),
                productStockField.getText(),
                productMinField.getText(),
                productMaxField.getText(),
                "Company",
                "Foobar"
        );

        if (!validator.isValid()) {
            for (String msg : validator.getMessages()) {
                Text text = new Text(msg + "   \n");
                text.setFill(Color.RED);
                text.setFont(Font.font("CiscoSans", FontWeight.BOLD, 15));
                addProductMessageText.getChildren().addAll(text);
            }

            addProductMessageText.getChildren().addAll(new Text("\n"));

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
    protected void onCancelButtonClick(ActionEvent event) throws IOException {
        Stage screen = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        screen.setScene(new Scene(scene));
        screen.show();
    }

    private void clearErrors() {
        associatedPartsErrorLabel.setText("");
        partsErrorLabel.setText("");
    }
}

