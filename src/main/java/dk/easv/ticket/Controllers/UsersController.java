package dk.easv.ticket.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable {


    @FXML private VBox vbPassword;
    @FXML private Label lblUserTitle;
    @FXML private  HBox hbTop;
    @FXML private  VBox vboxUserList;
    @FXML private  TextField txtSearch;
    @FXML private  ListView listUsers;
    @FXML private  VBox vboxForm;
    @FXML private  TextField txtFirstName;
    @FXML private  TextField txtSurname;
    @FXML private  TextField txtEmail;
    @FXML private  TextField txtUsername;
    @FXML private  TextField txtPhoneNo;
    @FXML private  CheckBox chkAdmin;
    @FXML private  CheckBox chkOrganiser;
    @FXML private  CheckBox chkPassword;
    @FXML private  HBox hbBottom;
    @FXML private  Button btnNewUser;
    @FXML private  Button btnDeleteUser;
    @FXML private  Button btnSaveUser;
    @FXML private HBox hboxRoles;
    @FXML private HBox hboxResetPassAdmin;
    @FXML private TextField txtOldPass;
    @FXML private TextField txtNewPass1;
    @FXML private TextField txtNewPass2;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML private void btnSaveClicked(ActionEvent event) {
        System.out.println("btnSaveClicked");
    }

    @FXML private void btnDeleteClicked(ActionEvent event) {
        System.out.println("btnDeleteClicked");
        listUsers.getSelectionModel().clearSelection();
        clearFields();
    }

    @FXML private void btnNewUserClicked(ActionEvent event) {
        System.out.println("btnNewUserClicked");
        listUsers.getSelectionModel().clearSelection();
        clearFields();
    }

    private void clearFields() {
        txtFirstName.clear();
        txtSurname.clear();
        txtEmail.clear();
        txtUsername.clear();
        txtPhoneNo.clear();
        chkAdmin.setSelected(false);
        chkOrganiser.setSelected(false);
        chkPassword.setSelected(false);
    }

    public void hideAdminButtons() {
        lblUserTitle.setText("Profile");
        btnNewUser.setVisible(false);
        btnDeleteUser.setVisible(false);
        vboxUserList.setVisible(false);
        vboxUserList.setDisable(true);
        vboxForm.setVisible(true);
        txtFirstName.setDisable(true);
        txtSurname.setDisable(true);
        txtUsername.setDisable(true);
        hboxRoles.setVisible(false);
        hboxRoles.setManaged(false);
        hboxResetPassAdmin.setVisible(false);
        hboxResetPassAdmin.setManaged(false);
    }

    public void hidePasswordFields() {
        vbPassword.setVisible(false);
        vbPassword.setManaged(false);
    }
}
