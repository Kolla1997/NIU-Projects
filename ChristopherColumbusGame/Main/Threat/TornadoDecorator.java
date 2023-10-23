package main.threat;

import main.OceanMap;
import main.ship.ColumbusShip;

/**
 * This abstract class represents a decorator tornado for a whirlpool.
 * 
 * @author player1
 *
 */
public abstract class TornadoDecorator extends Whirlpool {

	/**
	 * This method drags any ship along with tornado.
	 */
	public abstract void drag(ColumbusShip columbusShip, OceanMap oceanMap);
}
