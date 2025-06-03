package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DessinerProduitController {

    @FXML
    private Canvas canvas;

    @FXML
    public void initialize() {
        dessinerProduit();
    }

    private void dessinerProduit() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.BLUE);
        gc.fillOval(100, 75, 200, 150); // exemple d’un produit dessiné (ellipse)
    }
}
