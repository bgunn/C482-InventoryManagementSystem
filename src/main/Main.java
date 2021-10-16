package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Inventory management system main entry point
 *
 * @author William Gunn
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        initializeSampleData();
        launch();
    }

    /**
     * Add some sample data to the inventory
     */
    public static void initializeSampleData() {
        Inventory inventory = Inventory.getInstance();

        inventory.addPart(new InHouse(1, "Brakes", 15.00, 10, 5, 20, 263));
        inventory.addPart(new InHouse(2, "Wheel", 11.00, 16, 5, 20, 172));
        inventory.addPart(new InHouse(3, "Seat", 15.00, 10, 5, 20, 927));

        inventory.addPart(new Outsourced(5, "Bar ends", 12.50, 18, 5, 20, "U.S. bike Parts"));
        inventory.addPart(new Outsourced(6, "Bell", 5.99, 6, 5, 20, "Chimes-R-Us"));

        inventory.addProduct(new Product(1000, "Giant Bike", 299.99, 5, 2, 10));
        inventory.addProduct(new Product(1001, "Tricycle", 99.99, 3, 2,10));
    }
}