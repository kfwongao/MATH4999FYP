package application;
/**
 * This is the starting UI of the application
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent rootOfWelcomePage = FXMLLoader.load(getClass().getResource("/application/welcomePage.fxml"));
            Scene scene = new Scene(rootOfWelcomePage, 300, 380);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("MATH4999 Capstone Project");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
