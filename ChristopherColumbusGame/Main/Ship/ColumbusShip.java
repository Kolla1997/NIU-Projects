package main.ship;

import java.awt.Point;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.OceanMap;
import main.ship.pirate.PirateShip;

/**
 * This class represents the columbus ship whose movements is observed by
 * multiple pirate ships.
 * 
 * @author player1
 *
 */
public class ColumbusShip extends Observable implements Ship {

	private OceanMap oceanMap;
	private Point columbusShipLocation;
	private Image columbusShipImage;
	private ImageView columbusShipImageView;

	/**
	 * Default constructor to initialize columbus ship's image and location.
	 */
	public ColumbusShip() {
		oceanMap = OceanMap.getGrid();
		columbusShipImage = new Image("ship.png", SCALING_FACTOR, SCALING_FACTOR, true, true);
		columbusShipImageView = new ImageView(columbusShipImage);
		columbusShipLocation = oceanMap.placeShip();
		this.draw();
	}

	/**
	 * This methods adds multiple pirates ships observing columbus ship.
	 * 
	 * @param pirateShips List of pirate ships
	 */
	public void addPirateShipObservers(List<PirateShip> pirateShips) {
		for (PirateShip pirateShip : pirateShips) {
			this.addObserver((Observer) pirateShip);
		}
	}

	@Override
	public void addToOcean(Pane ocean) {
		ocean.getChildren().add(columbusShipImageView);
	}

	@Override
	public void draw() {
		columbusShipImageView.setX(columbusShipLocation.x * SCALING_FACTOR);
		columbusShipImageView.setY(columbusShipLocation.y * SCALING_FACTOR);
	}

	/**
	 * This method sails the columbus ship towards east.
	 */
	public void goEast() {
		if (columbusShipLocation.x < oceanMap.getDimensions() - 1
				&& oceanMap.isOcean(columbusShipLocation.x + 1, columbusShipLocation.y)) {
			columbusShipLocation.x++;
			sail();
		}
	}

	/**
	 * This method sails the columbus ship towards west.
	 */
	public void goWest() {
		if (columbusShipLocation.x > 0 && oceanMap.isOcean(columbusShipLocation.x - 1, columbusShipLocation.y)) {
			columbusShipLocation.x--;
			sail();
		}
	}

	/**
	 * This method sails the columbus ship towards north.
	 */
	public void goNorth() {
		if (columbusShipLocation.y > 0 && oceanMap.isOcean(columbusShipLocation.x, columbusShipLocation.y - 1)) {
			columbusShipLocation.y--;
			sail();
		}
	}

	/**
	 * This method sails the columbus ship towards south.
	 */
	public void goSouth() {
		if (columbusShipLocation.y < oceanMap.getDimensions() - 1
				&& oceanMap.isOcean(columbusShipLocation.x, columbusShipLocation.y + 1)) {
			columbusShipLocation.y++;
			sail();
		}
	}

	@Override
	public void sail() {
		draw();

		// Notify all the pirate ship observers about columbus ship movement.
		setChanged();
		notifyObservers();
	}

	/**
	 * This method returns the current location of columbus ship.
	 * 
	 * @return Point
	 */
	public Point getColumbusShipLocation() {
		return columbusShipLocation;
	}
	
	public void setColumbusShipLocation(Point p) {
		columbusShipLocation = p;
		
	}
}