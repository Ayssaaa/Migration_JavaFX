/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Machine;
import util.FichierMachine;

import java.util.List;

/**
 * Fenêtre JavaFX pour afficher toutes les machines (sans abscisse ni ordonnée).
 * Si aucune machine n'est en mémoire, affiche "no no".
 */
public class Afficher_Machine {

    private Stage windowStage;

    public Afficher_Machine() {
        windowStage = new Stage();
        windowStage.setTitle("Afficher les Machines");

        // Récupérer la liste des machines stockées
        List<Machine> liste = FichierMachine.getListeMachines();

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        if (liste.isEmpty()) {
            // Si pas de machines, on affiche "no no"
            Label emptyLabel = new Label("no no");
            root.getChildren().add(emptyLabel);
        } else {
            // Sinon, on crée un TableView sans colonnes x et y
            TableView<Machine> table = new TableView<>();

            // Colonne Référence
            TableColumn<Machine, String> colRef = new TableColumn<>("Référence");
            colRef.setCellValueFactory(new PropertyValueFactory<>("refMachine"));
            colRef.setPrefWidth(100);

            // Colonne Désignation (dMachine)
            TableColumn<Machine, String> colDesig = new TableColumn<>("Désignation");
            colDesig.setCellValueFactory(new PropertyValueFactory<>("dMachine"));
            colDesig.setPrefWidth(150);

            // Colonne Type
            TableColumn<Machine, String> colType = new TableColumn<>("Type");
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colType.setPrefWidth(100);

            // Colonne Coût (c)
            TableColumn<Machine, Float> colCout = new TableColumn<>("Coût");
            colCout.setCellValueFactory(new PropertyValueFactory<>("c"));
            colCout.setPrefWidth(80);

            table.getColumns().addAll(colRef, colDesig, colType, colCout);

            // Remplir le TableView avec la liste des machines
            ObservableList<Machine> data = FXCollections.observableArrayList(liste);
            table.setItems(data);

            root.getChildren().add(table);
        }

        Scene scene = new Scene(root, 500, 300);
        windowStage.setScene(scene);
    }

    /** Affiche la fenêtre. */
    public void show() {
        windowStage.show();
    }

    /** Ferme la fenêtre. */
    public void close() {
        windowStage.close();
    }
}
