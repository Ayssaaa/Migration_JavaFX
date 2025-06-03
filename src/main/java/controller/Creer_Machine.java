/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Produit;

/**
 *
 * @author Assiya.mdabhi
 */
public class Creer_Machine {
    
    private TextField refMachineField;

    
    private TextField dMachineField ;

    
    private Label messageLabel;

    
    private void Valider(ActionEvent event) {
        String refMachine = refMachineField.getText();
        double cout;
        try {
             = Double.parseDouble(prixField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Prix invalide.");
            return;
        }

        Produit produit = new Produit(nom, prix);
        boolean ajoute = util.FichierProduit.ajouterProduit(produit);
        messageLabel.setText(ajoute ? "Produit ajouté avec succès." : "Ce produit existe déjà.");
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
            e.printStackTrace();
        }
    }
}
}
