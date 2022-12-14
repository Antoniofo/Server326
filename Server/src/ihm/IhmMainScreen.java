package ihm;


import beans.Users;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import app.helpers.JfxPopup;


/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:38:45
 */
public class IhmMainScreen implements Initializable {

    private final String fxml = "/ihm/MainScreen.fxml";
    @FXML
    private ListView<Users> lstConnectedClients;
    @FXML
    private ListView<Users> lstUserList;
    @FXML
    private TextArea richTextBoxLogs;
    private Stage stage;
    private Ihm link;

    public Users getSelectedUser() {
        return lstUserList.getSelectionModel().getSelectedItem();
    }

    public IhmMainScreen(Ihm link) {
        this.link = link;

    }

    public void init(){
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
                    stage.setTitle("Main Screen");
                    richTextBoxLogs.setEditable(false);

                    stage.setOnCloseRequest((e)-> {
                        link.killThread();
                        e.consume();
                        System.exit(0);
                    });
                } catch (IOException ex) {
                    System.out.println("Can't start the IHM because : " + ex);
                    Platform.exit();
                }
            });
        });
    }
    public void start() {
        stage.show();
    }

    public void quit() {
        stage.close();
    }

    /**
     * @param event
     */
    @FXML
    private void addUser(ActionEvent event) {

        link.showUserManagement(true);

    }

    /**
     * @param event
     */
    @FXML
    private void logOut(ActionEvent event) {
        //TODO STUFF

        link.showPasswordAccess();

    }

    /**
     * @param event
     */
    @FXML
    private void modifyUser(ActionEvent event) {

        if (lstUserList.getSelectionModel().getSelectedItem() == null) {
            JfxPopup.displayError("Error", "Please select a user.", "Select a user");
        } else {
            link.showUserManagement(false);
        }
    }

    /**
     * @param event
     */
    @FXML
    private void removeUser(ActionEvent event) {
        log("User : " + lstUserList.getSelectionModel().getSelectedItem() + " Deleted");
        link.deleteUser(lstUserList.getSelectionModel().getSelectedItem());
        lstUserList.getItems().setAll(link.readUsers());


    }

    public void updateUsers(Users u) {
        Platform.runLater(()-> {
            lstUserList.getItems().setAll(link.readUsers());
            if(u != null){
                lstConnectedClients.getItems().clear();
                lstConnectedClients.getItems().add(u);
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateUsers(null);
    }

    public void log(String text) {
        richTextBoxLogs.appendText(text + System.lineSeparator());
    }

    public void updateConnectedUser(Users u) {
        Platform.runLater(() -> {
            lstConnectedClients.getItems().clear();
            if(u != null){
                lstConnectedClients.getItems().add(u);
            }
        });

    }

    public Users getConnectedUser() {
        if(lstConnectedClients.getItems().isEmpty()){
            return null;
        }else{
            return lstConnectedClients.getItems().get(0);
        }

    }
}//end IhmMainScreen