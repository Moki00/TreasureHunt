package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	// Screen Settings
	final int originalTileSize = 16;
	final int scale = 3; // scale for large monitor
	final int tileSize = originalTileSize * scale; // 48x48 tiles

	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	Thread gameThread;

	public GamePanel() {
//		this.setPreferredSize(getPreferredSize());
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

	}
}
