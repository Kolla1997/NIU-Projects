package main.threat;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.OceanMap;

/**
 * This class represents a Shark monster.
 * 
 * @author player1
 *
 */
public class Shark implements Threat, Runnable {

	private OceanMap oceanMap;
	private Random random = new Random();
	private boolean isRunning;

	private Point sharkLocation;
	private Image sharkImage;
	private ImageView sharkImageView;
	private int x1, y1, x2, y2;

	/**
	 * Default constructor to initialize shark image.
	 */
	public Shark() {
		isRunning = true;
		oceanMap = OceanMap.getGrid();
		sharkImage = new Image("shark.png", SCALING_FACTOR, SCALING_FACTOR, true, true);
		sharkImageView = new ImageView(sharkImage);
	}

	@Override
	public void setBoundary(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.sharkLocation = new Point(x1, y1);
		this.move();
	}

	@Override
	public Point getThreatLocation() {
		return sharkLocation;
	}

	@Override
	public void move() {
		int x = sharkLocation.x + random.nextInt(3) - 1;
		if (x >= x1 && x <= x2 && oceanMap.isOcean(x, sharkLocation.y)) {
			sharkLocation.x = x;
		}
		int y = sharkLocation.y + random.nextInt(3) - 1;
		if (y >= y1 && y <= y2 && oceanMap.isOcean(sharkLocation.x, y)) {
			sharkLocation.y = y;
		}
		draw();
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				move();
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addToOcean(Pane ocean) {
		ocean.getChildren().add(sharkImageView);
	}

	@Override
	public void draw() {
		/*
		 * For updating JavaFX controls from Java thread, use JavaFX thread by
		 * Platform.runLater()
		 */
		Platform.runLater(() -> {
			sharkImageView.setX(sharkLocation.x * SCALING_FACTOR);
			sharkImageView.setY(sharkLocation.y * SCALING_FACTOR);
		});
	}

	@Override
	public void add(Threat threat) {
		System.err.println("The add() not available for leaf node");
	}

	@Override
	public void remove(Threat threat) {
		System.err.println("The remove() not available for leaf node");
	}

	@Override
	public List<Threat> list() {
		System.err.println("The list() not available for leaf node");
		return null;
	}
}
