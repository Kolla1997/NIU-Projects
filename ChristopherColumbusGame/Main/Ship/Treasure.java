package main;

import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * This class represents a treasure.
 *
 * @author player1
 *
 */
public class Treasure {

    private static final int SCALING_FACTOR = 50;

    private Point treasureLocation;
    private Image treasureImage;
    private ImageView treasureImageView;

    /**
     * Default constructor to initialize treasure image and location.
     */
    public Treasure() {
        treasureImage = new Image("treasure.png", SCALING_FACTOR, SCALING_FACTOR, true, true);
        treasureImageView = new ImageView(treasureImage);
        treasureLocation = OceanMap.getGrid().placeTreasure();

        treasureImageView.setX(treasureLocation.x * SCALING_FACTOR);
        treasureImageView.setY(treasureLocation.y * SCALING_FACTOR);
    }

    /**
     * This method adds the treasure to JavaFX pane ocean.
     *
     * @param ocean JavaFX Pane
     */
    public void addToOcean(Pane ocean) {
        ocean.getChildren().add(treasureImageView);
    }

    /**
     * This method returns the location of treasure.
     *
     * @return Point
     */
    public Point getTreasureLocation() {
        return treasureLocation;
    }
}

