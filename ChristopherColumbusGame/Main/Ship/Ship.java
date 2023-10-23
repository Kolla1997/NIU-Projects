package main.ship;

import javafx.scene.layout.Pane;

/**
 * This interface provides common feature methods for any ship.
 * 
 * @author player1
 *
 */
public interface Ship {

	static final int SCALING_FACTOR = 50;

	/**
	 * This method calculates and sails the ship to calculated location.
	 */
	public void sail();

	/**
	 * This method adds the ship to JavaFX pane ocean.
	 * 
	 * @param ocean JavaFX Pane
	 */
	public void addToOcean(Pane ocean);

	/**
	 * This method draws or displays the ship to JavaFX pane ocean.
	 */
	public void draw();
}
