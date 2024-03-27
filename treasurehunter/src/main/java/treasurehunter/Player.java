package treasurehunter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends CharacterEntity {
	GamePanel gp;
	KeyHandler keyH;
	float currentFrame;
	int keyDirections;
	boolean freeze = false;

	public Player(GamePanel gp, KeyHandler kh, int keyDirections, boolean containsIdle, int characterMode) {
		super("Player", 4, containsIdle, characterMode);
		this.keyDirections = keyDirections;
		this.gp = gp;
		this.keyH = kh;
		setDefault();
	}

	public void setDefault() {
		// worldX = gp.tileSize * 23;
		worldX = 100;
		// worldY = gp.tileSize * 21;
		worldY = 100;
		speed = 2;
	}

	public void update() {
		if (this.keyDirections == 8) {
			if (keyH.upPressed && keyH.leftPressed) {
				worldY -= speed - 1;
				worldX -= speed - 1;
				currentDirection = directions[7];
			} else if (keyH.upPressed && keyH.rightPressed) {
				worldY -= speed - 1;
				worldX += speed - 1;
				currentDirection = directions[8];
			} else if (keyH.downPressed && keyH.leftPressed) {
				worldY += speed - 1;
				worldX -= speed - 1;
				currentDirection = directions[2];
			} else if (keyH.downPressed && keyH.rightPressed) {
				worldY += speed - 1;
				worldX += speed - 1;
				currentDirection = directions[3];
			} else if (keyH.upPressed) {
				worldY -= speed;
				currentDirection = directions[6];
			}

			else if (keyH.downPressed) {
				worldY += speed;
				currentDirection = directions[1];
			}

			else if (keyH.leftPressed) {
				worldX -= speed;
				currentDirection = directions[4];
			}

			else if (keyH.rightPressed) {
				worldX += speed;
				currentDirection = directions[5];
			} else {
				currentDirection = directions[0];
			}
		} else if (this.keyDirections == 4) {
			if (keyH.upPressed) {
				worldY -= speed;
				currentDirection = directions[3];
				this.freeze = false;
			}

			else if (keyH.downPressed) {
				worldY += speed;
				currentDirection = directions[1];
				this.freeze = false;
			}

			else if (keyH.leftPressed) {
				worldX -= speed;
				currentDirection = directions[2];
				this.freeze = false;
			}

			else if (keyH.rightPressed) {
				worldX += speed;
				currentDirection = directions[4];
				this.freeze = false;
			} else {
				if (this.containsIdle) {
					currentDirection = directions[0];
				} else {
					this.freeze = true;
				}
			}
		}
	}

	public void draw(Graphics2D g2) {

		currentFrame += 0.12;
		if (currentFrame > 4)
			currentFrame = 0;

		BufferedImage currentFrameImage = animatorMap.get(currentDirection)[(int) currentFrame];

		if (this.freeze) {
			currentFrameImage = animatorMap.get(currentDirection)[1];
		}

		g2.drawImage(currentFrameImage, worldX, worldY, gp.tileSize * (gp.scaleFactor - 1),
				gp.tileSize * (gp.scaleFactor - 1),
				null);
	}

}
