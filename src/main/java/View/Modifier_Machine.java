/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Machine;
import util.FichierMachine;

import java.util.List;

/**
 * Fenêtre JavaFX qui affiche toutes les machines (sans x ni y),
 * permet d’en choisir une dans le tableau, de sélectionner un attribut
 * (Désignation, Type ou Coût) dans un menu déroulant, saisir la nouvelle valeur
 * et cliquer sur Valider pour appliquer la modification en mémoire.
 * Si la liste est vide, affiche "no no".
 */
public class Modifier_Machine {

    private Stage windowStage;
    private TableView<Machine> table;
    private ObservableList<Machine> observableMachines;

    public Modifier_Machine() {
        windowStage = new Stage();
        windowStage.setTitle("Afficher / Modifier Machines");

        // 1) Charger la liste des machines depuis FichierMachine
        List<Machine> liste = FichierMachine.getListeMachines();
        observableMachines = FXCollections.observableArrayList(liste);

        // 2) Construire le TableView (sans colonnes x et y)
        table = new TableView<>();
        table.setItems(observableMachines);
        table.setPrefHeight(250);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        TableColumn<Machine, String> colRef = new TableColumn<>("Référence");
        colRef.setCellValueFactory(new PropertyValueFactory<>("refMachine"));
        colRef.setPrefWidth(100);

        TableColumn<Machine, String> colDesig = new TableColumn<>("Désignation");
        colDesig.setCellValueFactory(new PropertyValueFactory<>("dMachine"));
        colDesig.setPrefWidth(150);

        TableColumn<Machine, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colType.setPrefWidth(100);

        TableColumn<Machine, Float> colCout = new TableColumn<>("Coût");
        colCout.setCellValueFactory(new PropertyValueFactory<>("c"));
        colCout.setPrefWidth(80);

        table.getColumns().addAll(colRef, colDesig, colType, colCout);

        // 3) Si la liste est vide, on affiche "no no" et on arrête la construction
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        if (observableMachines.isEmpty()) {
            root.getChildren().add(new Label("no no"));
            windowStage.setScene(new Scene(root, 450, 100));
            return;
        }

        // 4) Créer le ComboBox des attributs modifiables
        ComboBox<String> attributCombo = new ComboBox<>();
        attributCombo.getItems().addAll("Désignation", "Type", "Coût");
        attributCombo.setPromptText("Choisissez un attribut");

        // 5) Champ pour la nouvelle valeur
        TextField nouvelleValeurField = new TextField();
        nouvelleValeurField.setPromptText("Nouvelle valeur");

        // 6) Bouton Valider qui applique la modification à l’objet sélectionné
        Button validerButton = new Button("Valider");
        validerButton.setDisable(true); // inactif tant que pas de sélection ou pas d'attribut choisi

        // Activation du bouton dès qu’on a une sélection et un attribut
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            validerButton.setDisable(newSel == null || attributCombo.getValue() == null);
        });
        attributCombo.valueProperty().addListener((obs, oldAttr, newAttr) -> {
            validerButton.setDisable(table.getSelectionModel().getSelectedItem() == null || newAttr == null);
        });

        // 7) Gestionnaire du bouton Valider
        validerButton.setOnAction(e -> {
            Machine sel = table.getSelectionModel().getSelectedItem();
            String attribut = attributCombo.getValue();
            String nouvelleTexte = nouvelleValeurField.getText().trim();

            if (sel == null || attribut == null || nouvelleTexte.isEmpty()) {
                // Normalement, on ne doit pas arriver ici si le bouton est bien désactivé.
                return;
            }

            switch (attribut) {
                case "Désignation":
                    sel.setdMachine(nouvelleTexte);
                    break;
                case "Type":
                    sel.setType(nouvelleTexte);
                    break;
                case "Coût":
                    try {
                        float nouveauC = Float.parseFloat(nouvelleTexte);
                        sel.setC(nouveauC);
                    } catch (NumberFormatException ex) {
                        // Afficher un message d’erreur si la valeur n’est pas un float
                        showAlert("Erreur", "Le coût doit être un nombre valide.");
                        return;
                    }
                    break;
                default:
                    return;
            }

            // Rafraîchir le tableau pour afficher la nouvelle valeur
            table.refresh();

            // Facultatif : vider la zone de saisie après validation
            nouvelleValeurField.clear();
        });

        // 8) Disposer les contrôles de modification dans un HBox
        HBox modificationBox = new HBox(10, attributCombo, nouvelleValeurField, validerButton);
        modificationBox.setPadding(new Insets(10, 0, 0, 0));

        // 9) Ajouter le tableau et la zone de modification dans le VBox principal
        root.getChildren().addAll(table, modificationBox);

        windowStage.setScene(new Scene(root, 500, 350));
    }

    /** Affiche la fenêtre. */
    public void show() {
        windowStage.show();
    }

    /** Ferme la fenêtre. */
    public void close() {
        windowStage.close();
    }

    /** Affiche une alerte simple en modal. */
    private void showAlert(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.initOwner(windowStage);
        alert.showAndWait();
    }
}

