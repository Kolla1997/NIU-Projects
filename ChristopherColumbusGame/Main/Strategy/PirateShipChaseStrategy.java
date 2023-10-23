package main.strategy;

import main.OceanMap;
import main.ship.pirate.PirateShip;

/**
 * This class represents chase strategy for pirate ship who observes and chases
 * the columbus ship.
 * 
 * @author player1
 *
 */
public class PirateShipChaseStrategy implements PirateShipStrategy {

	private PirateShipStrategyDirection pirateShipStrategyDirection;

	/**
	 * Default constructor to initialize and set east as default chase strategy
	 * direction.
	 */
	public PirateShipChaseStrategy() {
		pirateShipStrategyDirection = PirateShipStrategyDirection.EAST;
	}

	@Override
	public void sail(PirateShip pirateShip) {
		OceanMap oceanMap = OceanMap.getGrid();
		int shipX = pirateShip.getColumbusShipLocation().x;
		int shipY = pirateShip.getColumbusShipLocation().y;
		int pirateX = pirateShip.getPirateShipLocation().x;
		int pirateY = pirateShip.getPirateShipLocation().y;

		// Columbus Ship is east
		if (pirateX < oceanMap.getDimensions() - 1 && pirateX < shipX && pirateY == shipY
				&& oceanMap.isOcean(pirateX + 1, pirateY)) {
			pirateShip.getPirateShipLocation().x++;
		}

		// Columbus Ship is west
		else if (pirateX > 0 && pirateX > shipX && pirateY == shipY && oceanMap.isOcean(pirateX - 1, pirateY)) {
			pirateShip.getPirateShipLocation().x--;
		}

		// Columbus Ship is north
		if (pirateY > 0 && pirateY > shipY && pirateX == shipX && oceanMap.isOcean(pirateX, pirateY - 1)) {
			pirateShip.getPirateShipLocation().y--;
		}

		// Columbus Ship is south
		else if (pirateY < oceanMap.getDimensions() - 1 && pirateY < shipY && pirateX == shipX
				&& oceanMap.isOcean(pirateX, pirateY + 1)) {
			pirateShip.getPirateShipLocation().y++;
		}

		// Columbus Ship is north west
		if (shipX < pirateX && shipY < pirateY) {
			if (pirateX - 1 > 0 && pirateY - 1 > 0 && oceanMap.isOcean(pirateX - 1, pirateY - 1)) {
				pirateShip.getPirateShipLocation().x--;
				pirateShip.getPirateShipLocation().y--;
			}
		}

		// Columbus Ship is north east
		else if (shipX > pirateX && shipY < pirateY) {
			if (pirateX + 1 < oceanMap.getDimensions() && pirateY - 1 > 0
					&& oceanMap.isOcean(pirateX + 1, pirateY - 1)) {
				pirateShip.getPirateShipLocation().x++;
				pirateShip.getPirateShipLocation().y--;
			}
		}

		// Columbus Ship is south west
		if (shipX < pirateX && shipY > pirateY) {
			if (pirateX - 1 > 0 && pirateY + 1 < oceanMap.getDimensions()
					&& oceanMap.isOcean(pirateX - 1, pirateY + 1)) {
				pirateShip.getPirateShipLocation().x--;
				pirateShip.getPirateShipLocation().y++;
			}
		}

		// Columbus Ship is south east
		else if (shipX > pirateX && shipY > pirateY) {
			if (pirateX + 1 < oceanMap.getDimensions() && pirateY + 1 < oceanMap.getDimensions()
					&& oceanMap.isOcean(pirateX + 1, pirateY + 1)) {
				pirateShip.getPirateShipLocation().x++;
				pirateShip.getPirateShipLocation().y++;
			}
		}
	}
}
