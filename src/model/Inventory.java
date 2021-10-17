package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    /**
     * Create a singleton Inventory object
     */
    private final static Inventory instance = new Inventory();

    /**
     * Holds the list of parts
     */
    private ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Holds the list of products
     */
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Private constructor so that the class cannot be instantiated
     */
    private Inventory() { }

    /**
     * Public getter method for the singleton
     */
    public static Inventory getInstance() {
        return instance;
    }

    /**
     * Add a part to the parts list
     */
    public void addPart(Part part)
    {
        allParts.add(part);
    }

    /**
     * Add a product to the products list
     */
    public void addProduct(Product product)
    {
        allProducts.add(product);
    }

    /**
     * Search inventory for a part by ID
     */
    public Part lookupPart(int partId) {
        return null;
    }

    /**
     * Search inventory for a product by ID
     */
    public Product lookupProduct(int productId) {
        return null;
    }

    /**
     * Search inventory for a part by name
     */
    public ObservableList<Part> lookupPart(String partName) {
        return null;
    }

    /**
     * Search inventory for a product by name
     */
    public ObservableList<Product> lookupProduct(String productName) {
        return null;
    }

    /**
     * Update a part by the inventory index
     */
    public void updatePart(int index, Part part) {

    }

    /**
     * Update a product by the inventory index
     */
    public void updateProduct(int index, Product product) {

    }

    /**
     * Delete a part
     */
    public Boolean deletePart(Part part) {
        if (allParts.contains(part)) {
            allParts.remove(part);
            return true;
        }

        return false;
    }

    /**
     * Delete a product
     */
    public void deleteProduct(Product product) {

    }

    /**
     * Get the next available part ID
     */
    public int getNextPartId() {
        return getAllParts().size() + 101;
    }

    /**
     * Get the next available product ID
     */
    public int getNextProductId() {
        return getAllProducts().size() + 2001;
    }

    /**
     * Returns all parts from inventory
     */
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Returns all products from inventory
     */
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
