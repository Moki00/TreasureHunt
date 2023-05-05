package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.Obj_Key;

/**
 * 
 * in game display
 * 
 * @author Moki_21_10
 *
 */
public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage keyIcon;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter;
	public boolean gameFinished = false;

	double playTime;
	DecimalFormat decFormat = new DecimalFormat("#0.0");

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		Obj_Key key = new Obj_Key(gp);
		keyIcon = key.image;
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		if (gameFinished) {

			String text;
			int textLength, x, y;

			// Display found treasure
			text = "You found the treasure!";
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 - (gp.tileSize * 3);
			g2.drawString(text, x, y);

			// Finished Time
			text = "Your time was: " + decFormat.format(playTime) + " seconds.";
//			g2.setFont(arial_40);
//			g2.setColor(Color.black);
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 + (gp.tileSize * 4);
			g2.drawString(text, x, y);

			// Display Congratulations
			text = "You won!";
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 + (gp.tileSize * 2);
			g2.drawString(text, x, y);

			// stop the game
			gp.gameThread = null;
		} else {

			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyIcon, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasKey, 74, 65);

			// Time
			playTime += (double) 1 / 60;
			g2.drawString("Time: " + decFormat.format(playTime), gp.tileSize * 11, 65);

			// temporarily view messages for items and such
			if (messageOn) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

				messageCounter++;
				if (messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
	}

	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;

		g2.drawString(text, x, y);
	}

	public int getXforCenteredText(String text) {
		int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - textLength / 2;
		return x;
	}

}
