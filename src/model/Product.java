package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    /**
     * Holds the list of parts
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * The product ID
     */
    private int id;

    /**
     * The product name
     */
    private String name;

    /**
     * The product price
     */
    private double price;

    /**
     * The product stock count
     */
    private int stock;

    /**
     * The product minimum stock
     */
    private int min;

    /**
     * The product maximum stock
     */
    private int max;

    /**
     * Constructor method for a product
     *
     * @param id          Part ID
     * @param name        Part name
     * @param price       Part price
     * @param stock       Part stock
     * @param min         Minimum part Stock
     * @param max         Maximum part Stock
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Part ID setter
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Part name setter
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Part price setter
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Part stock setter
     *
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Part min setter
     *
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Part max setter
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Part ID getter
     *
     * @param id
     */
    public int getId() {
        return id;
    }

    /**
     * Part name getter
     *
     * @param name
     */
    public String getName() {
        return name;
    }

    /**
     * Part price getter
     *
     * @param price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Part stock getter
     *
     * @param stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Part min getter
     *
     * @param min
     */
    public int getMin() {
        return min;
    }

    /**
     * Part max getter
     *
     * @param max
     */
    public int getMax() {
        return max;
    }

    /**
     * Add an associated part to the product
     *
     * @param part
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Delete an associated part from the product
     *
     * @param part
     */
    public void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }

    /**
     * Get all parts associated with the product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
