module com.example.selforderingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.selforderingsystem to javafx.fxml;
    exports com.example.selforderingsystem;
}