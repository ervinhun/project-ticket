package dk.easv.ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private Label welcomeText;

    public void loginClicked(ActionEvent event) {
        System.out.println("Login clicked");
        try {
            System.out.println("Login clicked");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
            Parent root = loader.load();
            RootController rootController = loader.getController();
            //rootController.setUsername("admin");
            rootController.setUsername("Ervin");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forgotPass(MouseEvent mouseEvent) {
        System.out.println("This stupid forgot its password");
    }
}