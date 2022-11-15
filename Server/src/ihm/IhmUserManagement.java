package ihm;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:38:45
 */
public class IhmUserManagement {
	private final String fxml = "/ihm/UsermManagement.fxml";
	@FXML
	private ComboBox<?> cbxPrivilege;
	@FXML
	private TextField txtfUsername;
	private Stage stage;

	private Ihm link;

	public IhmUserManagement(Ihm link) {
		this.link = link;
	}

	public void start(){
		IhmUserManagement myself = this;
		Callback<Class<?>, Object> controllerFactory = type -> {
			return myself;
		};
		// Commence l'initialisation dans le thread de java
		SwingUtilities.invokeLater(() -> {
			new JFXPanel(); // Permet d'initializer le toolkit et l'environment JavaFX
			// Accède au dispatcher thread tant aimé par JavaFX
			Platform.runLater(() -> {
				try {
					stage = new Stage();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
					fxmlLoader.setControllerFactory(controllerFactory);
					Parent root = (Parent) fxmlLoader.load();
					Scene scene = new Scene(root);
					stage.setResizable(false);
					stage.setScene(scene);
					stage.setTitle("Client Admin");
					stage.show();
				} catch (IOException ex) {
					System.out.println("Can't start the IHM because : " + ex);
					Platform.exit();
				}
			});
		});
	}
	public void finalize() throws Throwable {

	}
	public IhmUserManagement(){

	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	private void cancel(ActionEvent event){

	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	private void done(ActionEvent event){

	}

	public void quit(){
		stage.close();
	}
}//end IhmUserManagement