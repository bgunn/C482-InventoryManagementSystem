package controller;

import java.util.ArrayList;

/**
 * Class to handle validation logic for parts and products
 *
 * @author William Gunn
 */
public class Validator {

    /**
     * Variables to hold the typed form inputs
     */
    private String name;
    private Double price;
    private Integer stock = -1;
    private Integer min = -1;
    private Integer max = -1;
    private Integer machineId;
    private String companyName;


    private Boolean valid = true;

    private ArrayList<String> messages = new ArrayList<String>();

    public Boolean isValid() {
        return valid;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    /**
     * <pre>
     * The validation constructor
     *
     * Implements the validation logic for parts and products.
     * When validating a product pass "Company" for the customField
     * and any string for the customValue.
     * </pre>
     *
     * @param name           The part or product name
     * @param price          The part or product price
     * @param stock          The part or product inventory
     * @param min            The part or product minimum inventory
     * @param max            The part or product maximum inventory
     * @param customField    The part machine ID or company name field
     * @param customValue    The part machine ID or company name value
     */
    public Validator(String name, String price, String stock, String min, String max, String customField, String customValue) {

        this.name = name;

        // Validate partNameField
        if (name.isEmpty()) {
            messages.add("Name cannot be empty");
            valid = false;
        }

        // Validate partStockField
        if (stock.isEmpty()) {
            messages.add("Inventory cannot be empty");
            valid = false;
        } else {
            try {
                this.stock = Integer.parseInt(stock);
                if (this.stock < 0) {
                    messages.add("Inventory must be equal or greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException nfe) {
                messages.add("Inventory must be a number");
                valid = false;
            }
        }

        // Validate partPriceField
        if (price.isEmpty()) {
            messages.add("Price cannot be empty");
            valid = false;
        } else {
            try {
                this.price = Double.parseDouble(price);
                if (this.price <= 0.0) {
                    messages.add("Price must be greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException nfe) {
                messages.add("Price must be a number");
                valid = false;
            }
        }

        // Validate partMaxField
        if (max.isEmpty()) {
            messages.add("Max cannot be empty");
            valid = false;
        } else {
            try {
                this.max = Integer.parseInt(max);
                if (this.max <= 0) {
                    messages.add("Max must be greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException nfe) {
                messages.add("Max must be a number");
                valid = false;
            }
        }

        // Validate partMinField
        if (min.isEmpty()) {
            messages.add("Min cannot be empty");
            valid = false;
        } else {
            try {
                this.min = Integer.parseInt(min);
                if (this.min < 0) {
                    messages.add("Min must be equal or greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException nfe) {
                messages.add("Min must be a number");
                valid = false;
            }
        }

        // Validate partCustomField
        if (customValue.isEmpty()) {
            messages.add(customField + " cannot be empty");
            valid = false;
        } else if (customField.contains("Machine")) {
            try {
                this.machineId = Integer.parseInt(customValue);
            } catch (NumberFormatException nfe) {
                messages.add(customField + " must be a number");
                valid = false;
            }
        } else if (customField.contains("Company")) {
            this.companyName = customValue;
        }

        // Validate stock/min/max
        // Min should be less than Max; and Inv should be between those two values.
        if (this.stock != -1 && this.min != -1 && this.max != -1) {
            if (this.max <= this.min) {
                messages.add("Max must be greater than " + this.min);
                valid = false;
            } else if (this.stock < this.min || this.stock > this.max) {
                messages.add("Inventory must be between " + this.min + " and " + this.max);
                valid = false;
            }
        }
    }

}
