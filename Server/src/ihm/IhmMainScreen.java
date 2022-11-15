package ihm;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;


/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:38:45
 */
public class IhmMainScreen {

	private final String fxml = "/ihm/MainScreen.fxml";
	@FXML
	private ListView<?> lstConnectedClients;
	@FXML
	private ListView<?> lstUserList;
	@FXML
	private TextArea richTextBoxLogs;
	@javafx.fxml.FXML
	private Button addUser;
	@javafx.fxml.FXML
	private Button modifyUser;
	@javafx.fxml.FXML
	private Button removeUser;
	private Stage stage;

	private Ihm link;

	public IhmMainScreen(Ihm link) {
		this.link = link;
	}

	public void finalize() throws Throwable {

	}
	public IhmMainScreen(){

	}
	public void start(){
		IhmMainScreen myself = this;
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
	public void quit(){
		stage.close();
	}
	/**
	 * 
	 * @param event
	 */
	@FXML
	private void addUser(ActionEvent event){

	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	private void logOut(ActionEvent event){
		//TODO STUFF

		link.showPasswordAccess();
	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	private void modifyUser(ActionEvent event){

	}

	/**
	 * 
	 * @param event
	 */
	@FXML
	private void removeUser(ActionEvent event){

	}
}//end IhmMainScreen