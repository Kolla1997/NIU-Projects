package main.threat;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.layout.Pane;
import main.OceanMap;

/**
 * This class represents a composite threat group for multiple threats.
 * 
 * @author player1
 *
 */
public class ThreatGroup implements Threat {

	private OceanMap oceanMap;
	private List<Threat> threats;
	private int x1, y1, x2, y2;
	private Random random = new Random();

	/**
	 * Default constructor to initialize boundary for list of threats.
	 */
	public ThreatGroup() {
		oceanMap = OceanMap.getGrid();
		threats = new ArrayList<>();

		x1 = random.nextInt(oceanMap.getDimensions() - 10);
		y1 = random.nextInt(oceanMap.getDimensions() - 10);
		x2 = x1 + 10;
		y2 = y1 + 10;
	}

	@Override
	public void move() {
		for (Threat threat : threats) {
			threat.move();
		}
	}

	@Override
	public void setBoundary(int x1, int y1, int x2, int y2) {
	}

	@Override
	public Point getThreatLocation() {
		return null;
	}

	@Override
	public void addToOcean(Pane ocean) {
		for (Threat threat : threats) {
			threat.addToOcean(ocean);
		}
	}

	@Override
	public void draw() {
		for (Threat threat : threats) {
			threat.draw();
		}
	}

	@Override
	public void add(Threat threat) {
		threat.setBoundary(x1, y1, x2, y2);
		threats.add(threat);
	}

	@Override
	public void remove(Threat threat) {
		threats.remove(threat);
	}

	@Override
	public List<Threat> list() {
		return threats;
	}

	@Override
	public void run() {
		for (Threat threat : threats) {
			Thread threatThread = new Thread(threat);
			/*
			 * Set threat thread as daemon, so that when closing main JavaFX thread will
			 * stop threat thread as well.
			 */
			threatThread.setDaemon(true);
			threatThread.start();
		}
	}
}
