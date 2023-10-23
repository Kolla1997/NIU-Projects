package main.threat;

/**
 * This class represents a concrete decorator tornado for whirlpool which has
 * ability to drag any ship along with him once captured.
 * 
 * @author player1
 *
 */
import main.ship.ColumbusShip;
import main.OceanMap;

import java.awt.Point;
import java.util.Random;
public class Tornado extends TornadoDecorator {

	private Threat whirlpool;
	private Random random = new Random();

	/**
	 * Constructor to initialize tornado from given whirlpool.
	 * 
	 * @param whirlpool
	 */
	public Tornado(Threat whirlpool) {
		this.whirlpool = whirlpool;

	}

	@Override
	public void drag(ColumbusShip columbusShip, OceanMap oceanMap) {
		boolean placed = false;
		while(!placed)
		{
			int x = random.nextInt(20);
            int y = random.nextInt(20);
            if(oceanMap.isOcean(x, y))
            {
            	Point p = new Point(x, y);
            	columbusShip.setColumbusShipLocation(p);
            	placed = true;
            }
		}
		
	}
}