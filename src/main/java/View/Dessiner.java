/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author louis
 */
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Machine;
import util.FichierMachine;

import java.util.List;

public class Dessiner {

    private Stage windowStage;

    public Dessiner() {
        windowStage = new Stage();
        windowStage.setTitle("Dessiner les Machines");

        Pane drawingPane = new Pane();
        drawingPane.setPrefSize(800, 600);

        List<Machine> machines = FichierMachine.getListeMachines();
        for (Machine m : machines) {
            double x = m.getX();
            double y = m.getY();
            Circle circle = new Circle(x, y, 10, Color.LIGHTBLUE);
            circle.setStroke(Color.DARKBLUE);
            Text label = new Text(x + 12, y + 4, m.getRefMachine());
            drawingPane.getChildren().addAll(circle, label);
        }

        ScrollPane scrollPane = new ScrollPane(drawingPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane);
        windowStage.setScene(scene);
    }

    public void show() {
        windowStage.show();
    }

    public void close() {
        windowStage.close();
    }
}

