package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	/**
	 * Control the player with this constructor
	 * 
	 * @param gamePanel
	 * @param keyHandler
	 */
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		// solid collision area
		solidArea = new Rectangle();

		// upper left corner of collision
//		solidArea.x = gp.tileSize / 4; // 48/4=12 from the left (mid 50% to collide, a quarter free on both sides)
//		solidArea.y = gp.tileSize / 3; // 48/3=16 from the top (bottom 2/3rds will collide)
		solidArea.x = gp.tileSize / 3; // 48/3=16 from the left (mid 1/3rd to collide, 1/3 free on both sides)
		solidArea.y = gp.tileSize / 2; // 48/2=24 from the top (bottom half will collide)

		// size of collision
		solidArea.width = gp.tileSize - solidArea.x * 2; // 48-32=16 wide
		System.out.println(solidArea.width);
		solidArea.height = gp.tileSize - solidArea.y; // 48-24=24 high

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 22;
		speed = 4;
		direction = "down";
		spriteNum = 1;
	}

	public void getPlayerImage() {
		try {
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/heroR1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/heroR2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/heroLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/heroLeft2.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/heroUp1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/heroUp2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/heroDown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/heroDown2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * update player location
	 */
	public void update() {

		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {

			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}

			// Check Tile Collision
			collisionOn = false;
			gp.collisionChecker.checkTile(this);

			// if collision is false, player can move
			if (collisionOn == false) {

				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;

				default:
					System.out.println("error in collision");
					break;
				}

			}

			// player has 2 movements only
			spriteCounter++;
			if (spriteCounter > 13) {
				if (spriteNum == 1) {
					spriteNum++;
				} else if (spriteNum == 2) {
					spriteNum--;
				}
				spriteCounter = 0;
			}
		}
	}

	/**
	 * drawing the player
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize); // location, then size

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			} else if (spriteNum == 2) {
				image = up2;
			} else {
				image = up1;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			} else if (spriteNum == 2) {
				image = down2;
			} else {
				image = down1;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			} else if (spriteNum == 2) {
				image = right2;
			} else {
				image = right1;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			} else if (spriteNum == 2) {
				image = left2;
			} else {
				image = left1;
			}
			break;
		}

		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

	}

}
