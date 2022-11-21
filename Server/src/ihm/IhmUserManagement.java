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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:38:45
 */
public class IhmUserManagement implements Initializable {
    private final String fxml = "/ihm/UserManagement.fxml";
    @FXML
    private ComboBox<String> cbxPrivilege;
    @FXML
    private TextField txtfUsername;
    private Stage stage;

    private Ihm link;
    @FXML
    private Label lblPwd;
    @FXML
    private PasswordField txtfPwd;


    public IhmUserManagement(Ihm link) {
        this.link = link;
    }

    public void start(boolean pw) {
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

                    txtfPwd.setVisible(pw);
                    lblPwd.setVisible(pw);
                    if (!pw) {
                        Users u = link.getSelectedUser();
                        txtfUsername.setText(u.getUsername());
                        cbxPrivilege.getSelectionModel().select(u.getIsAdmin());

                    }

                } catch (IOException ex) {
                    System.out.println("Can't start the IHM because : " + ex);
                    Platform.exit();
                }
            });
        });
    }


    /**
     * @param event
     */
    @FXML
    private void cancel(ActionEvent event) {
        quit();
    }


    public void quit() {
        link.updateUsers(null);
        stage.close();

    }

    public void apply(ActionEvent actionEvent) {
        Users u;

        if (txtfPwd.isVisible()) {
            u = new Users();
            u.setUsername(txtfUsername.getText());
            u.setPassword(txtfPwd.getText());
            boolean admin = cbxPrivilege.getSelectionModel().getSelectedItem().equals("Admin");
            u.setIsAdmin(admin == true ? (short) 1 : (short) 0);
            link.addUsers(u);
            link.log("Added a user : " + u.toString());

        } else {
            String ancien = link.getSelectedUser().toString();
            u = link.getSelectedUser();
            System.out.println(u.getIdUsers());
            u.setUsername(txtfUsername.getText());
            boolean admin = cbxPrivilege.getSelectionModel().getSelectedItem().equals("Admin");
            u.setIsAdmin(admin == true ? (short) 1 : (short) 0);
            link.modifyUser(u);
            link.log(ancien + " changed to : " + u.toString());
        }
        quit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbxPrivilege.getItems().add("User");
        cbxPrivilege.getItems().add("Admin");
    }
}//end IhmUserManagement