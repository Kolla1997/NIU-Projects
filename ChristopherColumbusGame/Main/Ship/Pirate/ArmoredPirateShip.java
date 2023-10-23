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
 * This class represents a armored pirate ship which observes the columbus ship
 * movement.
 * 
 * @author player1
 *
 */
public class ArmoredPirateShip extends PirateShip implements Observer {

	private OceanMap oceanMap;
	private Image armoredPirateShipImage;
	private ImageView armoredPirateShipImageView;

	/**
	 * Default constructor to initialize armored pirate ship's image and location.
	 */
	public ArmoredPirateShip() {
		super(PirateShipType.ARMORED);
		oceanMap = OceanMap.getGrid();
		armoredPirateShipImage = new Image("pirateShip.png", SCALING_FACTOR, SCALING_FACTOR, true, true);
		armoredPirateShipImageView = new ImageView(armoredPirateShipImage);
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
		ocean.getChildren().add(armoredPirateShipImageView);
	}

	@Override
	public void draw() {
		armoredPirateShipImageView.setX(getPirateShipLocation().x * SCALING_FACTOR);
		armoredPirateShipImageView.setY(getPirateShipLocation().y * SCALING_FACTOR);
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
