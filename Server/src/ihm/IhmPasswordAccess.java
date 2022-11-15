package ihm;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import app.helpers.JfxPopup;

import javax.swing.*;
import java.io.IOException;

/**
 * @author raposoesilvac
 * @version 1.0
 * @created 11-nov.-2022 11:38:45
 */
public class IhmPasswordAccess {
    private final String password = "Emf12345";
    private final String fxml = "/ihm/PasswordAccess.fxml";
    @FXML
    private TextField txtfPassword;
    private Stage stage;
    private Ihm link;

    public IhmPasswordAccess(Ihm ref) {
        link = ref;
    }

    public void start() {
        IhmPasswordAccess myself = this;
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

    public IhmPasswordAccess() {

    }

    /**
     * @param event
     */
    @FXML
    private void submit(ActionEvent event) {
       // if (password.equals(txtfPassword.getText())) {
            link.showMainScreen();
       // } else {
         //   JfxPopup.displayError("Erreur", "Mauvais mot de passe", "Veuillez réssayer.");

       // }

    }

    public void quit() {
        stage.close();
    }
}//end IhmPasswordAccess