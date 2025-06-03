module com.example.projets2javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports model;
    exports View;
    exports com.example.projets2javafx;

    opens model to com.google.gson;
    opens View to javafx.fxml;
    opens com.example.projets2javafx to javafx.graphics;
}
