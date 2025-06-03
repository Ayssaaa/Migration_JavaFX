/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Machine;
import util.FichierMachine;  

import java.io.IOException;

public class Creer_Machine {

    @FXML
    private TextField refMachineField;

    @FXML
    private TextField designationField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField xField;

    @FXML
    private TextField yField;

    @FXML
    private TextField coutField;

    @FXML
    private Label messageLabel;

    @FXML
    private void onValiderClicked(ActionEvent event) {
        String refMachine = refMachineField.getText().trim();
        String designation = designationField.getText().trim();
        String type = typeField.getText().trim();
        float x, y, cout;

        // Vérification des champs obligatoires (par exemple, on suppose que refMachine et designation sont obligatoires)
        if (refMachine.isEmpty() || designation.isEmpty() || type.isEmpty() ||
            xField.getText().isEmpty() || yField.getText().isEmpty() || coutField.getText().isEmpty()) {
            messageLabel.setText("Tous les champs sont requis.");
            return;
        }

        try {
            x = Float.parseFloat(xField.getText());
            y = Float.parseFloat(yField.getText());
            cout = Float.parseFloat(coutField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Veuillez saisir des valeurs numériques valides pour X, Y et coût.");
            return;
        }

        // Création de l'objet Machine
        Machine machine = new Machine(refMachine, designation, type, x, y, cout);

        // Appel à la méthode d'ajout (à implémenter dans util.FichierMachine)
        boolean ajoute = util.FichierMachine.ajouterMachine(machine);

        if (ajoute) {
            messageLabel.setText("Machine ajoutée avec succès.");
            // Optionnel : vider les champs après ajout
            refMachineField.clear();
            designationField.clear();
            typeField.clear();
            xField.clear();
            yField.clear();
            coutField.clear();
        } else {
            messageLabel.setText("Cette machine existe déjà.");
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

            // Ferme la fenêtre actuelle
            Stage currentStage = (Stage) refMachineField.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
