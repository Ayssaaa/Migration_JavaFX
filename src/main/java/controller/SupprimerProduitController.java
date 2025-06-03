package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.FichierProduit;

import java.io.IOException;

public class SupprimerProduitController {

    @FXML
    private TextField nomField;

    @FXML
    private Label messageLabel;

    @FXML
    private void onSupprimerClicked(ActionEvent event) {
        String nom = nomField.getText();
        if (nom.isEmpty()) {
            messageLabel.setText("Veuillez entrer un nom.");
            return;
        }

        boolean success = FichierProduit.supprimerProduit(nom);
        if (success) {
            messageLabel.setText("Produit supprimé avec succès.");
        } else {
            messageLabel.setText("Produit non trouvé.");
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
            e.printStackTrace();
        }
    }
}
