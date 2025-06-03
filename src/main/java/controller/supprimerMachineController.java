/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import util.FichierMachine;

import java.io.IOException;

/**
 *
 * @author cmoinecorneill01
 */
public class supprimerMachineController {
    
    @FXML
    private TextField nomField;

    @FXML
    private Label messageLabel;

    @FXML
    private void onSupprimerClicked(ActionEvent event) {
        String nom = nomField.getText();
        if (nom.trim().isEmpty()) {
            messageLabel.setText("Veuillez entrer un nom valide.");
            return;
            }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, 
            "Supprimer la machine \"" + nom + "\" ?", 
            ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmation");
        alert.showAndWait();

        if (alert.getResult() != ButtonType.YES) {
            return;
        }

        boolean success = FichierMachine.supprimerMachine(nom);
        if (success) {
            messageLabel.setText("Machine supprimée avec succès.");
        } else {
            messageLabel.setText("Machine non trouvée.");
        }
    }
    

    @FXML
    private void onRetourClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu_principal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Menu Principal");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) nomField.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            messageLabel.setText("Erreur lors du chargement du menu.");
            e.printStackTrace(); 
        }
    }
}

