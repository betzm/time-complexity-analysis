/*
 * Course: CSC1120A - 121
 * Spring 2024
 * Lab 11 - Auto Complete
 * Name: Madison Betz
 * Created: 4/3/2024
 */
package betzm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * main entry point for the program
 */
public class AutoComplete extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("your_fxml_file.fxml"));
        Controller controller = new Controller();
        loader.setController(controller);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
