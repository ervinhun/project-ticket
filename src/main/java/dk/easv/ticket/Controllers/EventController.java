package dk.easv.ticket.Controllers;

import dk.easv.ticket.be.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dk.easv.ticket.be.Event;
import javafx.scene.layout.VBox;

public class EventController implements Initializable {


    @FXML private TableView tableView;
    @FXML private TableColumn<Event, String> imageColumn;
    @FXML private TableColumn<Event, String> titleColumn;
    @FXML private TableColumn<Event, String> startDateColumn;
    @FXML private TableColumn<Event, String> activeColumn;
    @FXML private TableColumn<Event, Integer> typeColumn;
    @FXML private TableColumn<Event, String> locationColumn;
    @FXML private VBox vbPopUp;
    @FXML private ListView lstUsers;
    private ObservableList<Event> event;
    private final static String DEFAULT_IMAGES_SRC = "src/main/resources/dk/easv/ticket/img/no_img.jpg";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        event = getDummyData();
        loadEvents();
    }

    public void loadEvents() {
        // Custom cell factory for the image column
        imageColumn.setCellValueFactory(cellData -> cellData.getValue().imageSrcProperty());
        imageColumn.setCellFactory(column -> new TableCell<Event, String>() {
            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(30);  // Set preferred size
                imageView.setFitHeight(30);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);
                if (empty || imagePath == null || imagePath.isEmpty()) {
                    imageView.setImage(new Image("file:" + DEFAULT_IMAGES_SRC));
                    //imageView.setImage(new Image(getClass().getResourceAsStream("/Resources/img/no_img.jpg")));

                } else {
                    imageView.setImage(new Image("file:" + imagePath)); // Load from file system
                }

                setGraphic(imageView);
            }
        });
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        startDateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
        //activeColumn.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeOfEventProperty().asObject());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

        //event = getDummyData();
        tableView.setItems(event);
    }

    private ObservableList<Event> getDummyData() {
        ObservableList<Event> list = FXCollections.observableArrayList();
        list.add(new Event("Enable turn-key technologies", "2025-02-24", "Grover", 17, null));
        list.add(new Event("Streamline efficient interfaces", "2025-01-13", "Del Mar", 69, null));
        list.add(new Event("Seize collaborative vortals", "2024-09-21", "Dottie", 8, "sat.jpeg"));
        list.add(new Event("Incentivize transparent metrics", "2024-10-21", "Moland", 28, null));
        list.add(new Event("Incentivize seamless infomediaries", "2024-11-17", "Towne", 23, null));
        list.add(new Event("Envisioneer sexy initiatives", "2024-09-25", "3rd", 3, null));
        list.add(new Event("Optimize revolutionary markets", "2024-05-11", "Susan", 100, "club.jpg"));
        list.add(new Event("Strategize impactful deliverables", "2025-06-11", "Russell", 51, null));
        list.add(new Event("Matrix dynamic experiences", "2024-04-09", "Lerdahl", 33, null));
        list.add(new Event("Whiteboard seamless e-tailers", "2025-05-09", "8th", 86, null));
        list.add(new Event("Reintermediate extensible e-tailers", "2024-06-12", "Judy", 55, null));
        list.add(new Event("Disintermediate value-added web services", "2024-12-31", "Grayhawk", 1, null));
        list.add(new Event("Exploit seamless partnerships", "2025-05-10", "Bobwhite", 44, null));
        list.add(new Event("Streamline cutting-edge paradigms", "2024-02-27", "Springview", 5, null));
        list.add(new Event("Extend extensible metrics", "2024-04-15", "Columbus", 100, null));
        list.add(new Event("Enable integrated methodologies", "2024-10-22", "Mallory", 13, null));
        return list;
    }

    public ArrayList<User> getDummyUserList() {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1, "John", "Black", "j.black", "j@a.com", "1111"));
        list.add(new User(2,"May","Greep","mgreep1","mgreep1@thetimes.co.uk","$2a$04$u5wXnWzq/S4aqjt0zDybK.H4sqFzp5c7Pqv.mrmay6bk7notLfblO"));
        list.add(new User(3,"Devlin","Jacombs","djacombs2","djacombs2@desdev.cn","$2a$04$hC3/zDe2uba6Upa.h2Shee7R8L0ytg/3nyIueK1P/zB3QfFGDZQOu"));
        list.add(new User(4,"Cletis","Wimpey","cwimpey3","cwimpey3@patch.com","$2a$04$XJWWoiqH/ypC4iI32AYmX.i6R7m2TnG6kl66FTU19bMvsRNqppzWa"));
        list.add(new User(5,"Angelita","Kollatsch","akollatsch4","akollatsch4@omniture.com","$2a$04"));
        return list;
    }

    public ListView getUsersListView() { return lstUsers; }

    public VBox getVbPopUp() { return vbPopUp; }

    public TableView getTableView() {
        return tableView;
    }

    public ObservableList<Event> getEvent() {
        return event;
    }

    public void setEvent(ObservableList<Event> event) {
        this.event = event;
    }

    public boolean deleteEvent (Event event) {
        if (!this.event.isEmpty()) {
            try {
                this.event.remove(event);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public void assignCancelClicked(ActionEvent event) {
        vbPopUp.setVisible(false);
    }

    @FXML private void assignAssignClicked(ActionEvent event) {
        //Saving
        ArrayList<User> users = getDummyUserList();
        ArrayList<CheckBox> cbs = new ArrayList<CheckBox>();
        cbs.addAll(lstUsers.getItems());
        for (User user : users) {String fullName = user.getFirstName() + " " + user.getLastName();
            for (CheckBox cb : cbs) {
                if (cb.getText().equals(fullName) && cb.isSelected()) {
                    System.out.println(fullName);
                }
            }
        }
        vbPopUp.setVisible(false);
    }
}
