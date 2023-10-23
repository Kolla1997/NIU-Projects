package main.ship.pirate;

/**
 * This factory provides a factory method for creating different type pirate
 * ships.
 * 
 * @author player1
 *
 */
public class PirateShipFactory {

	/**
	 * This method creates and returns a pirate ship of given type.
	 * 
	 * @param pirateShipType Type of pirate ship
	 * @return PirateShip
	 */
	public PirateShip createPirateShip(PirateShipType pirateShipType) {
		PirateShip pirateShip = null;
		if (pirateShipType == PirateShipType.ARMORED) {
			pirateShip = new ArmoredPirateShip();
		} else if (pirateShipType == PirateShipType.LEVIATHAN) {
			pirateShip = new LeviathanPirateShip();
		}
		return pirateShip;
	}
}
