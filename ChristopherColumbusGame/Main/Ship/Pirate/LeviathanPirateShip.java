package main.ship.pirate;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.OceanMap;
import main.ship.ColumbusShip;
import main.ship.Ship;

/**
 * This class represents a leviathan pirate ship which observes the columbus
 * ship movement.
 * 
 * @author player1
 *
 */
public class LeviathanPirateShip extends PirateShip implements Observer {

	private OceanMap oceanMap;
	private Image leviathanPirateShipImage;
	private ImageView leviathanPirateShipImageView;

	/**
	 * Default constructor to initialize leviathan pirate ship's image and location.
	 */
	public LeviathanPirateShip() {
		super(PirateShipType.LEVIATHAN);
		oceanMap = OceanMap.getGrid();
		leviathanPirateShipImage = new Image("blackpearl.png", SCALING_FACTOR, SCALING_FACTOR, true, true);
		leviathanPirateShipImageView = new ImageView(leviathanPirateShipImage);
		setPirateShipLocation(oceanMap.placeShip());
		this.draw();
	}

	@Override
	public void sail() {
		getPirateShipStrategy().sail(this);
		draw();
	}

	@Override
	public void addToOcean(Pane ocean) {
		ocean.getChildren().add(leviathanPirateShipImageView);
	}

	@Override
	public void draw() {
		leviathanPirateShipImageView.setX(getPirateShipLocation().x * SCALING_FACTOR);
		leviathanPirateShipImageView.setY(getPirateShipLocation().y * SCALING_FACTOR);
	}

	@Override
	public void update(Observable columbusShip, Object arg) {
		if (columbusShip instanceof Ship) {
			Point columbusShipLocation = ((ColumbusShip) columbusShip).getColumbusShipLocation();
			setColumbusShipLocation(columbusShipLocation);
			sail();
		}
	}
}
