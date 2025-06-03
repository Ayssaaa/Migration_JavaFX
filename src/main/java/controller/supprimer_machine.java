package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.FichierMachine;

public class supprimer_machine {

    private TextField nomField;
    private Label messageLabel;
    private Stage windowStage;

    public supprimer_machine() {
        windowStage = new Stage();
        windowStage.setTitle("Supprimer une Machine");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.TOP_LEFT);

        Label nomLabel = new Label("Référence de la machine :");
        nomField = new TextField();
        nomField.setPrefColumnCount(15);
        grid.add(nomLabel, 0, 0);
        grid.add(nomField, 1, 0);

        messageLabel = new Label();
        grid.add(messageLabel, 0, 1, 2, 1);

        Button supprimerButton = new Button("Supprimer");
        grid.add(supprimerButton, 1, 2);
        GridPane.setMargin(supprimerButton, new Insets(10, 0, 0, 0));

        supprimerButton.setOnAction(event -> {
            String refTexte = nomField.getText().trim();
            if (refTexte.isEmpty()) {
                messageLabel.setText("Veuillez entrer une référence.");
                return;
            }

            boolean success = FichierMachine.supprimerMachine(refTexte);
            if (success) {
                messageLabel.setStyle("-fx-text-fill: green;");
                messageLabel.setText("Machine supprimée avec succès.");
                nomField.clear();
            } else {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Machine non trouvée.");
            }
        });

        Scene scene = new Scene(grid, 400, 200);
        windowStage.setScene(scene);
    }

    public void show() {
        windowStage.show();
    }

    public void close() {
        windowStage.close();
    }
}
