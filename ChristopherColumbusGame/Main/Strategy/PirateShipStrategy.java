package main.strategy;

import main.ship.pirate.PirateShip;

/**
 * This interface provides common feature method for pirate ship sailing
 * strategy.
 * 
 * @author player1
 *
 */
public interface PirateShipStrategy {

	/**
	 * This method calculates and sails the given pirate ship.
	 * 
	 * @param pirateShip
	 */
	public void sail(PirateShip pirateShip);
}
