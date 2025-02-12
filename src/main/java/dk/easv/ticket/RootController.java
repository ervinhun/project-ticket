package dk.easv.ticket;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class RootController {

    @FXML private BorderPane root;
    @FXML private Label welcomeLabel;
    private String username;
    private final static String IMG_PATH = "src/main/resources/dk/easv/ticket/img/";

    public void setUsername(String username) {
        this.username = username;
        //usernameLabel.setText("Welcome, " + username);
        System.out.println("Username: " + username);
        welcomeLabel.setText(welcomeLabel.getText() + " " + username);
        if (username.equals("admin"))
            addAdminButtons();
        else
            addOrganButtons();
        /**ArrayList<Button> buttons = new ArrayList<>();
        buttons.addAll(addAdminButtons());
        buttons.addAll(addOrganButtons());
        setMenuBar(buttons);*/
        setCenterMain();
    }



    public void addAdminButtons() {
        Button usersButton = new Button("Users");
        VBox.setMargin(usersButton, new Insets(0, 0, 0, 10));

        usersButton.setOnAction(e -> {
            //getUsersPage();
            System.out.println("Users");
        });

        Button eventsButton = new Button("Events");
        VBox.setMargin(eventsButton, new Insets(0, 0, 0, 10));

        eventsButton.setOnAction(e -> {
            System.out.println("Events");
        });
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(usersButton);
        buttons.add(eventsButton);
        setMenuBar(buttons);
        //return buttons;
    }

    private void addOrganButtons() {
        Button eventsButton = new Button("Events");
        VBox.setMargin(eventsButton, new Insets(0, 0, 0, 10));

        eventsButton.setOnAction(e -> {
            //getUsersPage();
            System.out.println("Events");
        });

        Button ticketButton = new Button("Tickets");
        VBox.setMargin(ticketButton, new Insets(0, 0, 0, 10));

        ticketButton.setOnAction(e -> {
            System.out.println("Tickets");
        });

        Button profileButton = new Button("Profile");
        VBox.setMargin(profileButton, new Insets(0, 0, 0, 10));
        profileButton.setOnAction(e -> {
            System.out.println("Profile");
        });
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(eventsButton);
        buttons.add(ticketButton);
        buttons.add(profileButton);
        setMenuBar(buttons);
        //return buttons;
    }

    private void setMenuBar(ArrayList<Button> buttons) {
        Label menuLabel = new Label("Menu");
        menuLabel.setId("menuButton");
        menuLabel.getStyleClass().add("luckiest-guy-regular");
        System.out.println(menuLabel.getStyleClass());

        Button logoutButton = new Button("Logout");
        VBox.setMargin(logoutButton, new Insets(0, 0, 15, 10));
        logoutButton.setOnAction(e -> {
            System.out.println("Logout");
        });

        Region spacer = new Region();
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(menuLabel);
        for (Button button : buttons) {
            buttonBox.getChildren().add(button);
        }
        buttonBox.getChildren().addAll(spacer, logoutButton);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        root.setLeft(buttonBox);
    }

    private void setCenterMain() {
        VBox centerMain = new VBox();
        centerMain.setAlignment(Pos.CENTER);
        centerMain.setSpacing(10);
        ImageView image = new ImageView();
        image.setImage(new Image("file:" + IMG_PATH + "organizer.png"));
        image.setFitWidth(400);
        image.setFitHeight(400);
        image.setPreserveRatio(true);
        centerMain.getChildren().add(image);
        root.setCenter(centerMain);
    }
}
