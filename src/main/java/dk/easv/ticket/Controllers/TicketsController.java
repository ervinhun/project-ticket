package dk.easv.ticket.Controllers;

import dk.easv.ticket.be.TicketType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TicketsController implements Initializable {
    @FXML private VBox vbChangeTicket;
    @FXML private FlowPane flowType;
    @FXML private ChoiceBox cbEvents;
    @FXML private TextField txtSearch;
    @FXML private ListView lstTickets;
    @FXML private TextField txtTicketIdentifier;
    @FXML private TextField txtName;
    @FXML private TextField txtEmail;

    private ArrayList<TicketType> ticketTypes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Loads the events to the choicebox
        NewEventController newEventController = new NewEventController();
        ticketTypes = new ArrayList<>();
        ticketTypes.addAll(newEventController.getDummyTicketTypes());
    }

    @FXML private void btnShowTicketsClicked(ActionEvent event) {
    }

    @FXML private void btnChangeClicked1(ActionEvent event) {
        showPopUp();
    }

    @FXML private void btnSeeClicked1(ActionEvent event) {
        System.out.println("Showing ticket");
    }

    @FXML private void btnChangeClicked2(ActionEvent event) {
        showPopUp();
    }



    @FXML private void btnSeeClicked2(ActionEvent event) {
        System.out.println("Showing ticket");
    }


    @FXML private void btnChangeTicketClicked(ActionEvent event) {
        System.out.println("Saving the new type");
        hidePopUp();
    }

    @FXML private void btnChangeTicketCancelClicked(ActionEvent event) {
        hidePopUp();
    }



    private void showPopUp() {
        vbChangeTicket.setVisible(true);
        flowType.getChildren().clear();
        for (TicketType ticketType : ticketTypes) {
            if (!ticketType.getSpecial()) {
                CheckBox cb = new CheckBox();
                cb.setText(ticketType.getName());
                flowType.getChildren().add(cb);
            }
        }
        double flowTicketHeight = flowType.getHeight();
        vbChangeTicket.setPrefHeight(flowTicketHeight+10);
    }

    private void setTicketTypesHeight(FlowPane flowType) {
    }

    private void hidePopUp() {
        vbChangeTicket.setVisible(false);
    }
}
