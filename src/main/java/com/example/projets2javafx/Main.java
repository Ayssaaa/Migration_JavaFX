package com.example.projets2javafx;

import View.Menu_Principal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        
        Menu_Principal root = new Menu_Principal();
        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
