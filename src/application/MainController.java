package application;
import javafx.application.Platform;
/**
 * This class is used to link the welcome page to Main page
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
	@FXML
	private Button exit;
	@FXML
	private Button toTwitter;
	
	public void exit(ActionEvent event) {
		Platform.exit();
	}
	
	public void goToTwitter(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage TwitterStage = new Stage();
		try {
			Parent rootOfNLPTwitterAnalysis = FXMLLoader.load(getClass().getResource("/application/NLPTwitterAnalysis.fxml"));
			Scene scene = new Scene(rootOfNLPTwitterAnalysis,850,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			TwitterStage.setScene(scene);
			TwitterStage.setTitle("NLP: Twitter tweets Analysis");
			TwitterStage.setResizable(false);
			TwitterStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
