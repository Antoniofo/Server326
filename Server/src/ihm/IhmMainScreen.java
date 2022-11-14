package ihm;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:38:45
 */
public class IhmMainScreen {

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


	public void finalize() throws Throwable {

	}
	public IhmMainScreen(){

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