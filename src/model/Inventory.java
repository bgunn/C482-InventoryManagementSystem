package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    /**
     * Holds the list of parts
     */
    private ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Holds the list of products
     */
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

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
    public void updatePart(int index, Part selectedPart) {

    }

    /**
     * Update a product by the inventory index
     */
    public void updateProduct(int index, Product selectedProduct) {

    }

    /**
     * Delete a part
     */
    public void deletePart(Part selectedPart) {

    }

    /**
     * Delete a product
     */
    public void deleteProduct(Product selectedProduct) {

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
