package dk.easv.ticket;

import dk.easv.ticket.be.TicketType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewEventController implements Initializable {
    @FXML private CheckBox chkSpecialInNewTicketType;
    @FXML private GridPane gridPaneForm;
    @FXML private Label lblTitle;
    @FXML private TextField txtTitle;
    @FXML private TextField txtStartDate;
    @FXML private TextField txtEndDate;
    @FXML private TextField txtLocation;
    @FXML private TextArea txtaLocation;
    @FXML private ChoiceBox dropEventType;
    @FXML private TextField txtNewEventType;
    @FXML private Button btnEventType;
    @FXML private TextField txtFileName;
    @FXML private Button btnImage;
    @FXML private FlowPane flowTicketTypes;
    @FXML private FlowPane flowSpecialTickets;
    @FXML private TextArea txtaDescription;
    @FXML private Button btnAddTicketType;
    @FXML private Button btnCancel;
    @FXML private Button btnSave;
    @FXML private VBox vboxShader;
    @FXML private VBox vboxNewTicketType;
    @FXML private TextField txtNewTicketType;
    @FXML private Button btnNewTicketCancel;
    @FXML private Button btnNewTicketSave;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDummyTicketTypes();
        Platform.runLater(this::setTicketTypesHeight);
    }

    @FXML private void btnAddTicketTypeClicked(ActionEvent event) {
        showNewTicketPopup();
    }

    @FXML private void btnCancelClicked(ActionEvent event) {

    }

    @FXML private void btnSaveClicked(ActionEvent event) {
    }

    @FXML private void btnNewTicketCancelClicked(ActionEvent event) {
        closeNewTicketType();
    }

    @FXML private void btnNewTicketSaveClicked(ActionEvent event) {
        //Do some stuff here
        closeNewTicketType();
    }

    public Button getCancelButton() {
        return btnCancel;
    }

    public Button getSaveButton() {
        return btnSave;
    }


    private void showNewTicketPopup() {
        vboxShader.setVisible(true);
        vboxNewTicketType.setVisible(true);
    }

    private void closeNewTicketType() {
        txtNewTicketType.setText("");
        vboxNewTicketType.setVisible(false);
        vboxShader.setVisible(false);
    }

    private void setDummyTicketTypes() {
        ArrayList<TicketType> ticketTypes = new ArrayList<>();
        ticketTypes.add(new TicketType(1, "Normal", false));
        ticketTypes.add(new TicketType(2, "VIP", false));
        ticketTypes.add(new TicketType(3, "Guest", false));
        ticketTypes.add(new TicketType(4, "Fast track", false));
        ticketTypes.add(new TicketType(5, "Participant", false));
        ticketTypes.add(new TicketType(6, "Free beer", true));
        ticketTypes.add(new TicketType(7, "Free pizza", true));
        ticketTypes.add(new TicketType(8, "Free taxi", true));
        ticketTypes.add(new TicketType(9, "Chuck Noris", false));
        ticketTypes.add(new TicketType(10, "Madonna", false));
        ticketTypes.add(new TicketType(11, "Some very important person, who is famous", false));
        ticketTypes.add(new TicketType(12, "Steve Jobs", false));


        for (TicketType ticketType : ticketTypes) {
            CheckBox cb = new CheckBox();
            cb.setText(ticketType.getName());
            cb.setId("cb_" + ticketType.getId());
            System.out.println(cb.getId());

            if (ticketType.getSpecial()) {
                flowSpecialTickets.getChildren().add(cb);
            } else {
                flowTicketTypes.getChildren().add(cb);
            }
        }
    }

    private void setTicketTypesHeight() {
        RowConstraints ticketRow = gridPaneForm.getRowConstraints().get(7);
        RowConstraints ticketRow2 = gridPaneForm.getRowConstraints().get(8);
        double flowTicketHeight = flowTicketTypes.getHeight();
        double flowSpecialTicketHeight = flowSpecialTickets.getHeight();
        ticketRow.setPrefHeight(flowTicketHeight+5);
        ticketRow2.setPrefHeight(flowSpecialTicketHeight);
    }


}
