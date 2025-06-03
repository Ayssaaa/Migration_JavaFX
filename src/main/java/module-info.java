module com.example.projets2javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports model;
    exports controller;
    exports com.example.projets2javafx;

    opens model to com.google.gson;
    opens controller to javafx.fxml;
    opens com.example.projets2javafx to javafx.graphics;
}
