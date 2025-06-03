/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Machine;
import util.FichierMachine;

/**
 * Fenêtre JavaFX « pure » (sans FXML) pour créer une Machine.
 * On ne définit pas de main() ici : on instancie simplement cette classe
 * depuis le menu principal et on appelle la méthode show().
 */
public class Creer_Machine {

    // Référence aux champs de saisie
    private TextField refField;
    private TextField designationField;
    private TextField typeField;
    private TextField abscisseField;
    private TextField ordonneeField;
    private TextField coutField;

    // Label pour afficher un message d'erreur ou de succès
    private Label messageLabel;

    // La Stage sur laquelle on bâtit la fenêtre
    private Stage windowStage;

    /**
     * Constructeur : on prépare tout le contenu de la fenêtre ici.
     * Il ne sera pas affiché automatiquement ; pour cela, appelez show().
     */
    public Creer_Machine() {
        // 1) Créer le Stage
        windowStage = new Stage();
        windowStage.setTitle("Créer une Machine");

        // 2) Construire le GridPane qui contient tous les labels/champs/boutons
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.TOP_LEFT);

        // 3) Ligne 0 : champ Référence
        Label refLabel = new Label("Référence :");
        refField = new TextField();
        refField.setPrefColumnCount(15);
        grid.add(refLabel, 0, 0);
        grid.add(refField, 1, 0);

        // 4) Ligne 1 : champ Désignation (dMachine)
        Label designationLabel = new Label("Désignation :");
        designationField = new TextField();
        designationField.setPrefColumnCount(20);
        grid.add(designationLabel, 0, 1);
        grid.add(designationField, 1, 1);

        // 5) Ligne 2 : champ Type
        Label typeLabel = new Label("Type :");
        typeField = new TextField();
        typeField.setPrefColumnCount(20);
        grid.add(typeLabel, 0, 2);
        grid.add(typeField, 1, 2);

        // 6) Ligne 3 : champ Abscisse (x)
        Label abscisseLabel = new Label("Abscisse (x) :");
        abscisseField = new TextField();
        abscisseField.setPrefColumnCount(10);
        grid.add(abscisseLabel, 0, 3);
        grid.add(abscisseField, 1, 3);

        // 7) Ligne 4 : champ Ordonnée (y)
        Label ordonneeLabel = new Label("Ordonnée (y) :");
        ordonneeField = new TextField();
        ordonneeField.setPrefColumnCount(10);
        grid.add(ordonneeLabel, 0, 4);
        grid.add(ordonneeField, 1, 4);

        // 8) Ligne 5 : champ Coût (c)
        Label coutLabel = new Label("Coût (c) :");
        coutField = new TextField();
        coutField.setPrefColumnCount(10);
        grid.add(coutLabel, 0, 5);
        grid.add(coutField, 1, 5);

        // 9) Ligne 6 : Label pour message d’erreur / succès (span sur 2 colonnes)
        messageLabel = new Label();
        grid.add(messageLabel, 0, 6, 2, 1);

        // 10) Ligne 7 : bouton Valider
        Button validerButton = new Button("Valider");
        grid.add(validerButton, 1, 7);
        GridPane.setMargin(validerButton, new Insets(10, 0, 0, 0));

        // Gestionnaire du clic sur Valider :
        validerButton.setOnAction(event -> {
            // 1. Récupérer et nettoyer le texte des champs
            String refTexte        = refField.getText().trim();
            String desigTexte      = designationField.getText().trim();
            String typeTexte       = typeField.getText().trim();
            String abscisseTexte   = abscisseField.getText().trim();
            String ordonneeTexte   = ordonneeField.getText().trim();
            String coutTexte       = coutField.getText().trim();

            // 2. Vérifier que rien n'est vide
            if (refTexte.isEmpty()
             || desigTexte.isEmpty()
             || typeTexte.isEmpty()
             || abscisseTexte.isEmpty()
             || ordonneeTexte.isEmpty()
             || coutTexte.isEmpty()) 
            {
                messageLabel.setText("Tous les champs doivent être remplis.");
                return;
            }

            // 3. Essayer de parser abscisse, ordonnée et coût
            float x, y, c;
            try {
                x = Float.parseFloat(abscisseTexte);
                y = Float.parseFloat(ordonneeTexte);
                c = Float.parseFloat(coutTexte);
            } catch (NumberFormatException e) {
                messageLabel.setText("Abscisse, ordonnée et coût doivent être des nombres valides.");
                return;
            }

            // 4. Construire l'objet Machine avec le constructeur fourni
            //    public Machine(String refMachine, String dMachine, String type, float x, float y, float c)
            Machine machine = new Machine(
                refTexte,
                desigTexte,
                typeTexte,
                x,
                y,
                c
            );

            // 5. Appeler FichierMachine.ajouterMachine(machine)
            //    - Si la méthode existe déjà dans util.FichierMachine
            //    - On récupère true si l'ajout s'est bien passé, false sinon
            boolean ajoutOK = FichierMachine.ajouterMachine(machine);
            if (ajoutOK) {
                messageLabel.setText("Machine ajoutée avec succès.");
                // Vider tous les champs
                refField.clear();
                designationField.clear();
                typeField.clear();
                abscisseField.clear();
                ordonneeField.clear();
                coutField.clear();
            } else {
                messageLabel.setText("Erreur : cette référence existe peut-être déjà.");
            }
        });

        // 11) Construire la Scene et associer au Stage
        Scene scene = new Scene(grid, 450, 350);
        windowStage.setScene(scene);
    }

    /**
     * Affiche la fenêtre. À appeler depuis votre menu principal
     * ou depuis un autre contrôleur JavaFX.
     */
    public void show() {
        windowStage.show();
    }

    /**
     * Si vous souhaitez fermer cette fenêtre depuis l'extérieur,
     * vous pouvez exposer une méthode close().
     */
    public void close() {
        windowStage.close();
    }
}