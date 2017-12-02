package application;
/**
 * This is just used for closing the pop up of long text message
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;


public class howToUseController {
	@FXML
	private Button close;

	public void close(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
}
