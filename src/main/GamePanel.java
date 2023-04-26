package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	/**
	 * auto generated
	 */
	private static final long serialVersionUID = -4611689532662755630L;

	// Screen Settings
	final int originalTileSize = 16;
	final int scale = 3; // scale for large monitor
	final int tileSize = originalTileSize * scale; // 48x48 tiles

	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	// FPS
	final int Fps = 60;

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;

	// set players default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;

	public GamePanel() {
//		this.setPreferredSize(getPreferredSize());
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1_000_000_000 / Fps; // update every 0.0167 seconds
		double delta = 0;
		double lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update(); // Update info as position
				repaint(); // Draw/paint the screen
				delta--;
				drawCount++;
			}

			if (timer >= 1_000_000_000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

//	public void run() {
//		double drawInterval = 1_000_000_000 / Fps; // 0.016667 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		while (gameThread != null) {
//			long currentTime = System.nanoTime();
//			update(); // Update info as position
//			repaint(); // Draw/paint the screen
//
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime /= 1_000_000; // from nano to long
//				if (remainingTime < 0) {
//					remainingTime = 0;
//				}
//				Thread.sleep((long) remainingTime); // possible error here?
//				nextDrawTime += drawInterval;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

	/**
	 * 
	 */
	public void update() {
		if (keyH.upPressed == true) {
			playerY -= playerSpeed;
		} else if (keyH.downPressed == true) {
			playerY += playerSpeed;
		} else if (keyH.leftPressed == true) {
			playerX -= playerSpeed;
		} else if (keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
	}

	/**
	 * Repaint method comes from here
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);

		// x, y location, then x,y size
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose();
	}
}
