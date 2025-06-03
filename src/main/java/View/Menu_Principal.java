package View;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class Menu_Principal extends AnchorPane {

    private final TextArea messageArea;

    public Menu_Principal() {
        
        Label titleLabel = new Label("Gestion de l'atelier");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");

        
        Button btnCreerMachine     = new Button("Créer");
        Button btnAfficherMachines = new Button("Afficher");
        Button btnModifierMachine  = new Button("Modifier");
        Button btnSupprimerMachine = new Button("Supprimer");

        btnCreerMachine.setPrefWidth(140);
        btnAfficherMachines.setPrefWidth(140);
        btnModifierMachine.setPrefWidth(140);
        btnSupprimerMachine.setPrefWidth(140);

        btnCreerMachine.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnAfficherMachines.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        btnModifierMachine.setStyle("-fx-background-color: #FFC107; -fx-text-fill: white; -fx-font-weight: bold;");
        btnSupprimerMachine.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox hboxMachines = new HBox(15,
            btnCreerMachine,
            btnAfficherMachines,
            btnModifierMachine,
            btnSupprimerMachine
        );
        hboxMachines.setAlignment(Pos.CENTER);

        
        Button btnDessiner = new Button("Dessiner");
        Button btnCalculer = new Button("Calculer");

        btnDessiner.setPrefWidth(120);
        btnCalculer.setPrefWidth(120);

        btnDessiner.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-font-weight: bold;");
        btnCalculer.setStyle("-fx-background-color: #009688; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox hboxAutres = new HBox(15, btnDessiner, btnCalculer);
        hboxAutres.setAlignment(Pos.CENTER);

        
        messageArea = new TextArea();
        messageArea.setPrefHeight(200);
        messageArea.setWrapText(true);
        messageArea.setEditable(false);
        messageArea.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-border-color: #ccc; " +
            "-fx-border-radius: 5; " +
            "-fx-background-radius: 5;"
        );

        
        VBox vbox = new VBox(20,
            titleLabel,
            hboxMachines,
            hboxAutres,
            messageArea
        );
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));

       
        this.getChildren().add(vbox);
        this.setStyle("-fx-background-color: #f0f4f8;");
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);

        
        btnCreerMachine.setOnAction((ActionEvent e) -> {
    try {
        new Creer_Machine().show();
    } catch (Exception ex) {
        messageArea.setText("Impossible d'ouvrir la fenêtre « Créer Machine » : ");
        ex.printStackTrace();
    }
});
        btnAfficherMachines.setOnAction((ActionEvent e) -> {
    try {
        new Afficher_Machine().show();
    } catch (Exception ex) {
        messageArea.setText("Impossible d'ouvrir la fenêtre « Afficher Machine » : " );
        ex.printStackTrace();
    }
});
        btnModifierMachine.setOnAction((ActionEvent e) -> {
    try {
        new Modifier_Machine().show();
    } catch (Exception ex) {
        messageArea.setText("Impossible d'ouvrir la fenêtre « Modifier Machine » : ");
        ex.printStackTrace();
    }
});
        btnSupprimerMachine.setOnAction((ActionEvent e) -> {
    try {
        new supprimer_machine().show();
    } catch (Exception ex) {
        messageArea.setText("Impossible d'ouvrir la fenêtre « Supprimer Machine » : ");
        ex.printStackTrace();
    }
});
        btnDessiner.setOnAction((ActionEvent e) -> {
    try {
        new Dessiner().show();
    } catch (Exception ex) {
        messageArea.setText("Impossible d'ouvrir la fenêtre « Dessiner » : ");
        ex.printStackTrace();
    }
});
        
               
    }

    
    private void ouvrirFenetre(String cheminFXML, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titre);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            messageArea.setText("Erreur : impossible de charger \"" + titre + "\"\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
