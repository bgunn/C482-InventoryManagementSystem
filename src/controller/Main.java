package controller;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.util.function.Predicate;

/**
 * Inventory management system main controller class
 *
 */
public class Main {

    /**
     * The inventory singleton
     */
    private Inventory inventory = Inventory.getInstance();

    /**
     * The part search text field
     */
    @FXML
    private TextField partSearch;

    /**
     * The product search text field
     */
    @FXML
    private TextField productSearch;

    /**
     * The part section error label
     */
    @FXML
    private Label partsErrorLabel;

    /**
     * The product section error label
     */
    @FXML
    private Label productsErrorLabel;

    /**
     * Parts table
     */
    @FXML
    private TableView<Part> partsTable;

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
     * Products table
     */
    @FXML
    private TableView<Product> productsTable;

    /**
     * Parts table id column
     */
    @FXML
    private TableColumn<Product, Integer> productsIdCol;

    /**
     * Parts table name column
     */
    @FXML
    private TableColumn<Product, String> productsNameCol;

    /**
     * Parts table price column
     */
    @FXML
    private TableColumn<Product, Double> productsPriceCol;

    /**
     * Parts table stock column
     */
    @FXML
    private TableColumn<Product, Integer> productsStockCol;

    /**
     * Initialize data and fields
     *
     * @return void
     */
    @FXML
    public void initialize() {

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

        // Initialize the products table
        productsTable.getItems().setAll(inventory.getAllProducts());
        productsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        FilteredList<Product> filteredProducts = new FilteredList<>(inventory.getAllProducts(), b -> true);

        productSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProducts.setPredicate(productPredicate(newValue));

            if (filteredProducts.size() == 0) {
                productsErrorLabel.setText("No matching products found!");
            } else {
                productsErrorLabel.setText("");
            }

        });

        productsTable.setItems(filteredProducts);
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
     * Product predicate
     *
     * @param string The search string
     * @return boolean
     */
    private Predicate<Product> productPredicate(String string) {
        return product -> {
            if (string == null || string.isEmpty()) return true;
            return searchProducts(product, string);
        };
    }

    /**
     * Product search method
     *
     * @param product the product to compare
     * @param string The search string
     * @return boolean
     */
    private boolean searchProducts(Product product, String string) {
        return (product.getName().toLowerCase().contains(string.toLowerCase())) ||
                Integer.valueOf(product.getId()).toString().equals(string.toLowerCase());
    }

    /**
     * Convenience method to handle switching scenes
     *
     * @param event Button click ActionEvent
     * @param view  The name of the view to load
     * @return void
     */
    private void switchScenes(ActionEvent event, String view, String title) throws IOException {
        Stage screen = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/" + view + ".fxml"));
        screen.setTitle(title);
        screen.setScene(new Scene(scene));
        screen.show();
    }

    /**
     * launches the add part form
     *
     * @param event Button click ActionEvent
     * @return void
     */
    @FXML
    protected void onAddPartButtonClick(ActionEvent event) throws IOException {
        switchScenes(event, "addPart", "Add Part");
    }

    /**
     * launches the modify part form for the selected part
     *
     * @param event Button click ActionEvent
     * @return void
     */
    @FXML
    protected void onModifyPartButtonClick(ActionEvent event) throws IOException {
        switchScenes(event, "modifyPart", "Modify Part");
    }

    /**
     * Deletes the selected part from the inventory
     *
     * @param event Button click ActionEvent
     * @return void
     */
    @FXML
    protected void onDeletePartButtonClick(ActionEvent event) {

    }

    /**
     * launches the add product form
     *
     * @param event Button click ActionEvent
     * @return void
     */
    @FXML
    protected void onAddProductButtonClick(ActionEvent event) throws IOException {
        switchScenes(event, "addProduct", "Add Product");
    }

    /**
     * launches the modify part form for the selected product
     *
     * @param event Button click ActionEvent
     * @return void
     */
    @FXML
    protected void onModifyProductButtonClick(ActionEvent event) throws IOException {
        switchScenes(event, "modifyProduct", "Modify Product");
    }

    /**
     * Deletes the selected product from the inventory
     *
     * @param event Button click ActionEvent
     * @return void
     */
    @FXML
    protected void onDeleteProductButtonClick(ActionEvent event) {

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