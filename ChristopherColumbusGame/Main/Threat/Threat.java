package main.threat;

import java.awt.Point;
import java.util.List;

import javafx.scene.layout.Pane;

/**
 * This interface represents a threat in an ocean and has ability to move itself
 * in defined area.
 * 
 * @author player1
 *
 */
public interface Threat extends Runnable {

	static final int SCALING_FACTOR = 50;

	/**
	 * This method adds the threat to JavaFX pane ocean.
	 * 
	 * @param ocean JavaFX Pane
	 */
	public void addToOcean(Pane ocean);

	/**
	 * This method draws or displays the threat to JavaFX pane ocean.
	 */
	public void draw();

	/**
	 * This method calculates and moves the threat to calculated location.
	 */
	public void move();

	/**
	 * This method sets the rectangular area and boundary for a threat.
	 * 
	 * @param x1 Starting x location for threat
	 * @param y1 Starting y location for threat
	 * @param x2 Ending x location for threat
	 * @param y2 Ending y location for threat
	 */
	public void setBoundary(int x1, int y1, int x2, int y2);

	/**
	 * This method returns the current location of threat.
	 * 
	 * @return Point
	 */
	public Point getThreatLocation();

	/**
	 * This method adds the multiple threats to threat group.
	 * 
	 * @param threat Threat to be added
	 */
	public void add(Threat threat);

	/**
	 * This method removed the given threat from threat group.
	 * 
	 * @param threat Threat to be removed
	 */
	public void remove(Threat threat);

	/**
	 * This method returns the list of threats from threat group.
	 * 
	 * @return List<Threat>
	 */
	public List<Threat> list();
}