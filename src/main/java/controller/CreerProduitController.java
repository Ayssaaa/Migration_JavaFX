package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Produit;
import util.FichierProduit;

import java.io.IOException;

public class CreerProduitController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prixField;

    @FXML
    private Label messageLabel;

    @FXML
    private void onValiderClicked(ActionEvent event) {
        String nom = nomField.getText();
        double prix;
        try {
            prix = Double.parseDouble(prixField.getText());
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
