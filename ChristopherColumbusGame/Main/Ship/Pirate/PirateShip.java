package main.ship.pirate;

import java.awt.Point;

import main.ship.Ship;
import main.strategy.PirateShipStrategy;

/**
 * This abstract class provides common features for any type of pirate ship.
 * 
 * @author player1
 *
 */
public abstract class PirateShip implements Ship {

	private PirateShipType pirateShipType;
	private PirateShipStrategy pirateShipStrategy;
	private Point columbusShipLocation;
	private Point pirateShipLocation;

	/**
	 * Constructor to initialize pirate ship with given type.
	 * 
	 * @param pirateShipType Type of pirate ship
	 */
	public PirateShip(PirateShipType pirateShipType) {
		setPirateShipType(pirateShipType);
	}

	/**
	 * @return the pirateShipType
	 */
	public PirateShipType getPirateShipType() {
		return pirateShipType;
	}

	/**
	 * @param pirateShipType the pirateShipType to set
	 */
	public void setPirateShipType(PirateShipType pirateShipType) {
		this.pirateShipType = pirateShipType;
	}

	/**
	 * @return the pirateShipStrategy
	 */
	public PirateShipStrategy getPirateShipStrategy() {
		return pirateShipStrategy;
	}

	/**
	 * @param pirateShipStrategy the pirateShipStrategy to set
	 */
	public void setPirateShipStrategy(PirateShipStrategy pirateShipStrategy) {
		this.pirateShipStrategy = pirateShipStrategy;
	}

	/**
	 * @return the columbusShipLocation
	 */
	public Point getColumbusShipLocation() {
		return columbusShipLocation;
	}

	/**
	 * @param columbusShipLocation the columbusShipLocation to set
	 */
	public void setColumbusShipLocation(Point columbusShipLocation) {
		this.columbusShipLocation = columbusShipLocation;
	}

	/**
	 * @return the pirateShipLocation
	 */
	public Point getPirateShipLocation() {
		return pirateShipLocation;
	}

	/**
	 * @param pirateShipLocation the pirateShipLocation to set
	 */
	public void setPirateShipLocation(Point pirateShipLocation) {
		this.pirateShipLocation = pirateShipLocation;
	}
}
