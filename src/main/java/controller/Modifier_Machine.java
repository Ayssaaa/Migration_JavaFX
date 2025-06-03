/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Machine;
import util.FichierMachine;
import java.util.List;
/**
 *
 * @author louis
 */
public class Modifier_Machine {
    
    @FXML
    private ListView<String> Liste_Machines;

    @FXML
    private ComboBox<String> Choix_Atribut;

    @FXML
    private TextField Zone_Attribut;

    @FXML
    private Label statusLabel;

    private boolean listeChargee = false;

    @FXML
    public void initialize() {
        // Initialisation du ComboBox pour correspondre aux paramètres de FichierMachine.modifierMachine(...)
        Choix_Atribut.getItems().addAll(
            "Attribut",
            "Designation",
            "Type",
            "X",
            "Y",
            "Coût"
        );
        Choix_Atribut.getSelectionModel().selectFirst();
    }

    @FXML
    private void onAfficher() {
        List<Machine> machines = FichierMachine.lireMachines();
        Liste_Machines.getItems().clear();
        for (Machine m : machines) {
            Liste_Machines.getItems().add(m.getRefMachine());
        }
        listeChargee = true;
        statusLabel.setText("Liste des machines chargée (" + machines.size() + " machine(s)).");
    }

    @FXML
    private void onModifier() {
        if (!listeChargee) {
            statusLabel.setText("Veuillez d’abord cliquer sur « Afficher » avant de modifier une machine.");
            return;
        }

        String refSelectionnee = Liste_Machines.getSelectionModel().getSelectedItem();
        if (refSelectionnee == null) {
            statusLabel.setText("Veuillez sélectionner une machine dans la liste.");
            return;
        }

        String attribut = Choix_Atribut.getValue();
        if (attribut == null || attribut.equals("Attribut")) {
            statusLabel.setText("Veuillez sélectionner un attribut à modifier.");
            return;
        }

        String nouvelleValeur = Zone_Attribut.getText().trim();
        if (nouvelleValeur.isEmpty()) {
            statusLabel.setText("Veuillez saisir la nouvelle valeur pour l’attribut.");
            return;
        }

        // On récupère la machine existante pour extraire ses valeurs actuelles
        List<Machine> machines = FichierMachine.lireMachines();
        Machine cible = null;
        for (Machine m : machines) {
            if (m.getRefMachine().equalsIgnoreCase(refSelectionnee)) {
                cible = m;
                break;
            }
        }
        if (cible == null) {
            statusLabel.setText("Machine introuvable.");
            return;
        }

        // Valeurs par défaut (extraites de la machine courante) :
        String designationActuelle = cible.getdMachine();
        String typeActuel        = cible.getType();
        float xActuelle          = cible.getX();
        float yActuelle          = cible.getY();
        float coutActuel         = cible.getC();

        // On décide de quels paramètres passer à modifierMachine(...)
        String nouvelleDesignation = designationActuelle;
        String nouveauType         = typeActuel;
        float nouveauX             = xActuelle;
        float nouveauY             = yActuelle;
        float nouveauC             = coutActuel;

        try {
            switch (attribut) {
                case "Designation":
                    nouvelleDesignation = nouvelleValeur;
                    break;

                case "Type":
                    nouveauType = nouvelleValeur;
                    break;

                case "X":
                    nouveauX = Float.parseFloat(nouvelleValeur);
                    break;

                case "Y":
                    nouveauY = Float.parseFloat(nouvelleValeur);
                    break;

                case "Coût":
                    nouveauC = Float.parseFloat(nouvelleValeur);
                    break;

                default:
                    statusLabel.setText("Attribut inconnu.");
                    return;
            }
        } catch (NumberFormatException e) {
            statusLabel.setText(attribut + " invalide. Veuillez saisir un nombre.");
            return;
        }

        boolean succes = FichierMachine.modifierMachine(
            refSelectionnee,
            nouvelleDesignation,
            nouveauType,
            nouveauX,
            nouveauY,
            nouveauC
        );

        if (succes) {
            statusLabel.setText("Machine « " + refSelectionnee + " » modifiée avec succès.");
            // Optionnel : fermer la fenêtre automatiquement
            // Stage stage = (Stage) Zone_Attribut.getScene().getWindow();
            // stage.close();
        } else {
            statusLabel.setText("Échec de la modification. Vérifiez que le fichier est accessible.");
        }
    }

    @FXML
    private void onRetour() {
        Stage stage = (Stage) Zone_Attribut.getScene().getWindow();
        stage.close();
    }
}