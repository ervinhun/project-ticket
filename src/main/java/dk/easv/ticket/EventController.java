package dk.easv.ticket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import dk.easv.ticket.be.Event;

public class EventController implements Initializable {

    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Event, String> imageColumn;
    @FXML
    private TableColumn<Event, String> titleColumn;
    @FXML
    private TableColumn<Event, String> startDateColumn;
    @FXML
    private TableColumn<Event, String> activeColumn;
    @FXML
    private TableColumn<Event, Integer> typeColumn;
    @FXML
    private TableColumn<Event, String> locationColumn;


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
        activeColumn.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
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
}
