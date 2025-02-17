package dk.easv.ticket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AdminProfileController {
    @FXML private TextField txtOldPass;
    @FXML private TextField txtNewPass1;
    @FXML private TextField txtNewPass2;

    @FXML private void btnSaveNewPassClicked(ActionEvent event) {

        //Check for correct old password and matching new passwords
        txtOldPass.clear();
        txtNewPass1.clear();
        txtNewPass2.clear();
    }
}
