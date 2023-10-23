package main.strategy;

import main.OceanMap;
import main.ship.pirate.PirateShip;

/**
 * This class represents vertical patrol strategy for pirate ship which sails
 * between north and south.
 * 
 * @author player1
 *
 */
public class PirateShipVerticalPatrolStrategy implements PirateShipPatrolStrategy {

	private PirateShipStrategyDirection pirateShipStrategyDirection;

	/**
	 * Default constructor to initialize and set south as default vertical patrol
	 * strategy direction.
	 */
	public PirateShipVerticalPatrolStrategy() {
		pirateShipStrategyDirection = PirateShipStrategyDirection.SOUTH;
	}

	@Override
	public void sail(PirateShip pirateShip) {
		OceanMap oceanMap = OceanMap.getGrid();
		int x = pirateShip.getPirateShipLocation().x;
		int y = pirateShip.getPirateShipLocation().y;
		if (pirateShipStrategyDirection == PirateShipStrategyDirection.SOUTH) {
			if (y == oceanMap.getDimensions() - 1 || oceanMap.isIsland(x, y + 1)) {
				y--;
				pirateShipStrategyDirection = PirateShipStrategyDirection.NORTH;
			} else {
				y++;
			}
		} else if (pirateShipStrategyDirection == PirateShipStrategyDirection.NORTH) {
			if (y == 0 || oceanMap.isIsland(x, y - 1)) {
				y++;
				pirateShipStrategyDirection = PirateShipStrategyDirection.SOUTH;
			} else {
				y--;
			}
		}
		pirateShip.getPirateShipLocation().y = y;
	}
}