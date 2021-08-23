package guru.bug.bullsandcows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class BullsAndCowsApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Bulls and Cows");

        var root = (Pane) FXMLLoader.load(
                getClass().getClassLoader().getResource("ui/MainScreen.fxml")
        );

        var scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
