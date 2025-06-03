package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Machine;
import util.FichierMachine;

import java.util.ArrayList;
import java.util.List;

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
            List<Machine> tempList = new ArrayList<>(FichierMachine.getListeMachines());
            boolean found = false;
            for (Machine m : tempList) {
                if (m.getRefMachine().equalsIgnoreCase(refTexte)) {
                    tempList.remove(m);
                    found = true;
                    break;
                }
            }
            if (!found) {
                messageLabel.setStyle("-fx-text-fill: red;");
                messageLabel.setText("Machine non trouvée.");
                return;
            }
            FichierMachine.clearListeMachines();
            for (Machine m : tempList) {
                FichierMachine.ajouterMachine(m);
            }
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Machine supprimée avec succès.");
            nomField.clear();
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
