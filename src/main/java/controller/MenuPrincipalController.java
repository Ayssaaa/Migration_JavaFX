package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalController {

    public void onCreerProduitClicked(ActionEvent event) {
        ouvrirFenetre("/view/creer_produit.fxml", "Créer un produit");
    }

    public void onAfficherClicked(ActionEvent event) {
        ouvrirFenetre("/view/afficher_produit.fxml", "Liste des produits");
    }

    public void onModifierClicked(ActionEvent event) {
        ouvrirFenetre("/view/modifier_produit.fxml", "Modifier un produit");
    }

    public void onSupprimerClicked(ActionEvent event) {
        ouvrirFenetre("/view/supprimer_produit.fxml", "Supprimer un produit");
    }

    public void onDessinerClicked(ActionEvent event) {
        ouvrirFenetre("/view/dessiner_produit.fxml", "Dessiner un produit");
    }

    public void onCalculerClicked(ActionEvent event) {
        ouvrirFenetre("/view/calculer_produit.fxml", "Calculer un produit");
    }

    private void ouvrirFenetre(String cheminFXML, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titre);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
