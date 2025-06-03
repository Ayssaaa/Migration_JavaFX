package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.Produit;
import util.FichierProduit;

import java.util.List;

public class AfficherProduitController {

    @FXML
    private TextArea produitTextArea;

    @FXML
    private void onActualiserClicked() {
        List<Produit> produits = FichierProduit.lireProduits();
        StringBuilder builder = new StringBuilder();

        for (Produit p : produits) {
            builder.append(p.getNom()).append(" - ").append(p.getPrix()).append(" €\n");
        }

        produitTextArea.setText(builder.toString());
    }
}
