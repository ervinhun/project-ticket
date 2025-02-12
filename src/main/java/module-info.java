module dk.easv.ticket {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens dk.easv.ticket to javafx.fxml;
    exports dk.easv.ticket;
}