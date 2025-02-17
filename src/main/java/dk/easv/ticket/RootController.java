package dk.easv.ticket;

import dk.easv.ticket.be.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RootController {

    @FXML
    private BorderPane root;
    @FXML
    private Label welcomeLabel;
    private final static String IMG_PATH = "src/main/resources/dk/easv/ticket/img/";
    protected final static int ROLE_NONE = 0;
    protected final static int ROLE_ADMIN = 1;
    protected final static int ROLE_ORGANIZER = 2;
    protected final static int ROLE_BOTH = 3;
    private String username;

    private int roles;
    //private TableView<Event> eventTable;

    public void setUsername(String username) {
        this.username = username;
        welcomeLabel.setText(welcomeLabel.getText() + " " + username);
        if (username.equals("admin")) {
            roles = ROLE_ADMIN;
            addAdminButtons();
        } else if (!username.isEmpty()){
            roles = ROLE_ORGANIZER;
            addOrganButtons();
        }
        else {
            roles = ROLE_NONE;
            ArrayList<Button> buttons = new ArrayList<>();
            setMenuBar(buttons);
        }
        /**ArrayList<Button> buttons = new ArrayList<>();
         buttons.addAll(addAdminButtons());
         buttons.addAll(addOrganButtons());
         setMenuBar(buttons);*/
        setCenterMain();
        setBottomPart();
    }

    public int getRoles() {
        return roles;
    }


    public void addAdminButtons() {
        Button usersButton = new Button("Users");
        VBox.setMargin(usersButton, new Insets(0, 0, 0, 10));

        usersButton.setOnAction(e -> {
            try {
                loadUsersPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(usersButton);
        //buttons.add(eventsButton);
        setMenuBar(buttons);
        //return buttons;
    }

    private void addOrganButtons() {


        Button ticketButton = new Button("Tickets");
        VBox.setMargin(ticketButton, new Insets(0, 0, 0, 10));

        ticketButton.setOnAction(e -> {
            try {
                loadTicketsPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });


        ArrayList<Button> buttons = new ArrayList<>();
        //buttons.add(eventsButton);
        buttons.add(ticketButton);
        //buttons.add(profileButton);
        setMenuBar(buttons);
        //return buttons;
    }

    private void setMenuBar(ArrayList<Button> buttons) {
        Label menuLabel = new Label("Menu");
        menuLabel.setId("menuButton");
        menuLabel.getStyleClass().add("luckiest-guy-regular");


        Button logoutButton = new Button("Logout");
        VBox.setMargin(logoutButton, new Insets(0, 10, 15, 10));
        logoutButton.setOnAction(e -> {
            FXMLLoader loader = loadPage("login");
            try {
                Parent root = loader.load();
                //LoginController loginController = loader.getController();
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        //Event button
        Button eventsButton = new Button("Events");
        VBox.setMargin(eventsButton, new Insets(0, 0, 0, 10));

        eventsButton.setOnAction(e -> {
            try {
                loadEventsPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        if (roles == ROLE_NONE)
            eventsButton.setDisable(true);
        Button profileButton = new Button("Profile");
        VBox.setMargin(profileButton, new Insets(0, 0, 0, 10));
        if (roles == ROLE_ORGANIZER || roles == ROLE_NONE)
        profileButton.setOnAction(e -> {
            try {
                loadUsersPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        else {
            profileButton.setOnAction(e -> {
                try {
                    loadAdminProfilePage();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        Region spacer = new Region();
        VBox buttonBox = new VBox(10);
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(menuLabel);
        buttonBox.getChildren().add(eventsButton);
        for (Button button : buttons) {
            buttonBox.getChildren().add(button);
        }
        buttonBox.getChildren().add(profileButton);
        buttonBox.getChildren().addAll(spacer, logoutButton);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        //VBox.setMargin(event, new Insets(0, 10, 0, 0));
        root.setLeft(buttonBox);
    }

    private void loadAdminProfilePage() throws IOException {
        FXMLLoader loader = loadPage("admin_profile");
        VBox adminProfilePage = loader.load();
        adminProfilePage.setAlignment(Pos.TOP_CENTER);
        AdminProfileController usersController = loader.getController();


        root.setCenter(adminProfilePage);
    }

    private void loadTicketsPage() throws IOException {
        FXMLLoader loader = loadPage("tickets");
        Parent ticketPane = loader.load();
        TicketsController ticketsController = loader.getController();
        root.setCenter(ticketPane);
    }

    private void setCenterMain() {
        VBox centerMain = new VBox();
        centerMain.setAlignment(Pos.CENTER);
        centerMain.setSpacing(10);
        ImageView image = new ImageView();
        if (roles == ROLE_ADMIN || roles == ROLE_BOTH) {
            image.setImage(new Image("file:" + IMG_PATH + "SU.png"));
        } else if (roles == ROLE_ORGANIZER || roles == ROLE_BOTH)
            image.setImage(new Image("file:" + IMG_PATH + "organizer.png"));
        image.setFitWidth(400);
        image.setFitHeight(400);
        image.setPreserveRatio(true);
        centerMain.getChildren().add(image);
        root.setCenter(centerMain);
    }

    private FXMLLoader loadPage (String pageName){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RootController.class.getResource(pageName + ".fxml"));
        return loader;
    }

    public void loadEventsPage() throws IOException {
        FXMLLoader loader = loadPage("event");
        VBox eventPage = loader.load();
        eventPage.setAlignment(Pos.TOP_CENTER);
        EventController eventController = loader.getController();
        TableView tableView = eventController.getTableView();

        //Adding the buttons
        HBox assignHbox = new HBox();
        assignHbox.setAlignment(Pos.BOTTOM_LEFT);

        Button assignButton = new Button("Assign to user");
        HBox.setMargin(assignButton, new Insets(0, 250, 15, 0));
        assignButton.setDisable(true);
        assignButton.setOnAction(e -> {

        });
        Button newEventButton = new Button("New event");
        HBox.setMargin(newEventButton, new Insets(0, 10, 15, 0));
        newEventButton.setOnAction(e -> {
            FXMLLoader loaderNew = loadPage("new_event");
            try {
                Parent newEventRoot = loaderNew.load();

                root.setCenter(newEventRoot);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            StackPane stackPane = loaderNew.getRoot();
            NewEventController newEventController = loaderNew.getController();
            newEventButtons(eventPage, newEventController);
        });

        Button editButton = new Button("Edit");
        HBox.setMargin(editButton, new Insets(0, 10, 15, 0));
        editButton.setDisable(true);
        editButton.setOnAction(e -> {
            FXMLLoader loaderNew = loadPage("new_event");
            try {
                Parent newEventRoot = loaderNew.load();

                root.setCenter(newEventRoot);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //StackPane stackPane = loaderNew.getRoot();
            NewEventController newEventController = loaderNew.getController();
            newEventController.setEventToEdit((Event) eventController.getTableView().getSelectionModel().getSelectedItem());
            newEventButtons(eventPage, newEventController);
        });


        Button deleteButton = new Button("Delete");
        HBox.setMargin(deleteButton, new Insets(0, 10, 15, 0));
        deleteButton.setDisable(true);

        deleteButton.setOnAction(e -> {
           Event eventToDelete = (Event) eventController.getTableView().getSelectionModel().getSelectedItem();
           if (eventController.deleteEvent(eventToDelete))
               eventController.getTableView().getSelectionModel().clearSelection();
        });
        if (roles == ROLE_ORGANIZER || roles == ROLE_BOTH) {
            assignHbox.getChildren().addAll(assignButton, newEventButton, editButton, deleteButton);
            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                boolean selected = newSelection != null;
                assignButton.setDisable(!selected);
                deleteButton.setDisable(!selected);
                editButton.setDisable(!selected);
            });
        } else {
            assignHbox.getChildren().addAll(assignButton, deleteButton);
            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                boolean selected = newSelection != null;
                assignButton.setDisable(!selected);
                deleteButton.setDisable(!selected);
            });
        }
        Region spacer = new Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        eventPage.getChildren().addAll(spacer, assignHbox);


        root.setCenter(eventPage);
    }

    private void newEventButtons(VBox eventPage, NewEventController newEventController) {
        Button cancelButton = newEventController.getCancelButton();
        Button saveButton = newEventController.getSaveButton();
        cancelButton.setOnAction(event -> {
            try {
                root.setCenter(eventPage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        saveButton.setOnAction(event -> {
            //Save things and stuff, then load back the event page
            try {
                root.setCenter(eventPage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void loadUsersPage() throws IOException {
        FXMLLoader loader = loadPage("users");
        VBox adminUsersPage = loader.load();
        adminUsersPage.setAlignment(Pos.TOP_CENTER);
        UsersController usersController = loader.getController();
        if (roles != ROLE_ADMIN) {
            usersController.hideAdminButtons();
        }
        else {
            usersController.hidePasswordFields();
        }

        root.setCenter(adminUsersPage);
    }

    private void setBottomPart() {
        HBox bottomBar = new HBox();
        bottomBar.setPrefHeight(15); // Set height
        bottomBar.getStyleClass().add("bottomBar");

        root.setBottom(bottomBar); // Add it to the BorderPane

    }
}
