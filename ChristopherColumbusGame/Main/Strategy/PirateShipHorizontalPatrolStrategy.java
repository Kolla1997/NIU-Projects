package main.strategy;

import main.OceanMap;
import main.ship.pirate.PirateShip;

/**
 * This class represents horizontal patrol strategy for pirate ship which sails
 * between east and west.
 * 
 * @author player1
 *
 */
public class PirateShipHorizontalPatrolStrategy implements PirateShipPatrolStrategy {

	private PirateShipStrategyDirection pirateShipStrategyDirection;

	/**
	 * Default constructor to initialize and set east as default horizontal patrol
	 * strategy direction.
	 */
	public PirateShipHorizontalPatrolStrategy() {
		pirateShipStrategyDirection = PirateShipStrategyDirection.EAST;
	}

	@Override
	public void sail(PirateShip pirateShip) {
		OceanMap oceanMap = OceanMap.getGrid();
		int x = pirateShip.getPirateShipLocation().x;
		int y = pirateShip.getPirateShipLocation().y;
		if (pirateShipStrategyDirection == PirateShipStrategyDirection.EAST) {
			if (x == oceanMap.getDimensions() - 1 || oceanMap.isIsland(x + 1, y)) {
				x--;
				pirateShipStrategyDirection = PirateShipStrategyDirection.WEST;
			} else {
				x++;
			}
		} else if (pirateShipStrategyDirection == PirateShipStrategyDirection.WEST) {
			if (x == 0 || oceanMap.isIsland(x - 1, y)) {
				x++;
				pirateShipStrategyDirection = PirateShipStrategyDirection.EAST;
			} else {
				x--;
			}
		}
		pirateShip.getPirateShipLocation().x = x;
	}
}