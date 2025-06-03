package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculerProduitController {

    @FXML
    private TextField prixField;

    @FXML
    private TextField quantiteField;

    @FXML
    private Label resultatLabel;

    @FXML
    private void onCalculerClicked() {
        try {
            double prix = Double.parseDouble(prixField.getText());
            int quantite = Integer.parseInt(quantiteField.getText());
            double totalHT = prix * quantite;
            double totalTTC = totalHT * 1.2; // TVA 20%

            resultatLabel.setText(String.format("Résultat TTC : %.2f €", totalTTC));
        } catch (NumberFormatException e) {
            resultatLabel.setText("Erreur : saisir des nombres valides.");
        }
    }
}
