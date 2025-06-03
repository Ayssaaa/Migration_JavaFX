package controller;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

/**
 * MenuPrincipalController devient désormais un composant JavaFX (hérite d’AnchorPane),
 * construit 100% en code (pas de FXML). On peut l’utiliser comme racine d’une scène.
 */
public class MenuPrincipalController extends AnchorPane {

    private final TextArea messageArea;

    public MenuPrincipalController() {
        // --- 1) Création du Label Titre ---
        Label titleLabel = new Label("Gestion des Machines");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");

        // --- 2) Boutons pour Machine ---
        Button btnCreerMachine     = new Button("Créer Machine");
        Button btnAfficherMachines = new Button("Afficher Machines");
        Button btnModifierMachine  = new Button("Modifier Machine");
        Button btnSupprimerMachine = new Button("Supprimer Machine");

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

        // --- 3) Boutons Dessiner / Calculer (inchangés) ---
        Button btnDessiner = new Button("Dessiner");
        Button btnCalculer = new Button("Calculer");

        btnDessiner.setPrefWidth(120);
        btnCalculer.setPrefWidth(120);

        btnDessiner.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-font-weight: bold;");
        btnCalculer.setStyle("-fx-background-color: #009688; -fx-text-fill: white; -fx-font-weight: bold;");

        HBox hboxAutres = new HBox(15, btnDessiner, btnCalculer);
        hboxAutres.setAlignment(Pos.CENTER);

        // --- 4) TextArea pour messages ---
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

        // --- 5) Assemblage dans un VBox ---
        VBox vbox = new VBox(20,
            titleLabel,
            hboxMachines,
            hboxAutres,
            messageArea
        );
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));

        // --- 6) Ajout du VBox dans cet AnchorPane ---
        this.getChildren().add(vbox);
        this.setStyle("-fx-background-color: #f0f4f8;");
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);

        // --- 7) Handlers pour les boutons Machine ---
        btnCreerMachine.setOnAction((ActionEvent e) -> {
    try {
        new Creer_Machine().show();
    } catch (Exception ex) {
        messageArea.setText("Impossible d'ouvrir la fenêtre « Créer Machine » : " + ex.getMessage());
        ex.printStackTrace();
    }
});
        btnAfficherMachines.setOnAction((ActionEvent e) -> {
    try {
        new Creer_Machine().show();
    } catch (Exception ex) {
        messageArea.setText("Impossible d'ouvrir la fenêtre « Créer Machine » : " + ex.getMessage());
        ex.printStackTrace();
    }
});
        
        btnModifierMachine.setOnAction((ActionEvent e) -> ouvrirFenetre("/view/Modifier_Machine.fxml", "Modifier une machine"));
        btnSupprimerMachine.setOnAction((ActionEvent e) -> ouvrirFenetre("/view/Supprimer_Machine.fxml", "Supprimer une machine"));

        // --- 8) Handlers pour Dessiner / Calculer (inchangés) ---
        btnDessiner.setOnAction((ActionEvent e) -> ouvrirFenetre("/view/dessiner_produit.fxml", "Dessiner un produit"));
        btnCalculer.setOnAction((ActionEvent e) -> ouvrirFenetre("/view/calculer_produit.fxml", "Calculer un produit"));
    }

    /**
     * Charge un FXML et ouvre une nouvelle fenêtre. 
     * Si l’opération échoue, on affiche le message dans messageArea.
     */
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
