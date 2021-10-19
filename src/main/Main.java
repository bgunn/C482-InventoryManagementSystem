package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.*;

import java.io.*;

/**
 * <pre>
 * Inventory management system main entry point
 *
 * FUTURE ENHANCEMENT
 * The next version of this application should include a database to persist the parts and products inventory.
 * The Apache Derby database would provide a good embedded solution to start with. Following that, the application
 * could be updated to use a remote MySQL or PostgreSQL database.
 * </pre>
 *
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
        Inventory inv = Inventory.getInstance();

        inv.addPart(new InHouse(inv.getNextPartId(), "Brakes", 15.00, 10, 5, 20, 263));
        inv.addPart(new InHouse(inv.getNextPartId(), "Wheel", 11.00, 16, 5, 20, 172));
        inv.addPart(new InHouse(inv.getNextPartId(), "Seat", 15.00, 10, 5, 20, 927));

        inv.addPart(new Outsourced(inv.getNextPartId(), "Bar ends", 12.50, 18, 5, 20, "U.S. bike Parts"));
        inv.addPart(new Outsourced(inv.getNextPartId(), "Bell", 5.99, 6, 5, 20, "Chimes-R-Us"));

        inv.addProduct(new Product(inv.getNextProductId(), "Giant Bike", 299.99, 5, 2, 10));
        inv.addProduct(new Product(inv.getNextProductId(), "Tricycle", 99.99, 3, 2,10));
    }
}