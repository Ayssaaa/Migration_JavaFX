package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.FichierProduit;

public class ModifierProduitController {

    @FXML
    private TextField ancienNomField;

    @FXML
    private TextField nouveauNomField;

    @FXML
    private TextField nouveauPrixField;

    @FXML
    private Label statusLabel;

    @FXML
    private void onModifierProduit() {
        String ancienNom = ancienNomField.getText();
        String nouveauNom = nouveauNomField.getText();
        double nouveauPrix;
        try {
            nouveauPrix = Double.parseDouble(nouveauPrixField.getText());
        } catch (NumberFormatException e) {
            statusLabel.setText("Prix invalide.");
            return;
        }

        if (FichierProduit.modifierProduit(ancienNom, nouveauNom, nouveauPrix)) {
            statusLabel.setText("Produit modifié avec succès.");
        } else {
            statusLabel.setText("Échec de la modification.");
        }
    }
}
