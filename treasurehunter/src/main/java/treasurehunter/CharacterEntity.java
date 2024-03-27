package treasurehunter;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class CharacterEntity {
	public int worldX, worldY;
	public int speed;
	boolean isBuffed, isDebuffed;
	public HashMap<String, BufferedImage[]> animatorMap = new HashMap<String, BufferedImage[]>(); // left, right, up, down, upLeft, upRight, downLeft, downRight;
	// public String[] directions = {"idle", "walkF", "walkFL", "walkFR", "walkL", "walkR", "walkB", "walkBL", "walkBR"};
	public String[] directions = {"idle", "walkF", "walkL", "walkB", "walkR"};
	public int animatorFrames = 3;
	public String currentDirection = directions[0];
	public String characterType = "Player";
	public int characterMode = 0;
	boolean containsIdle = true;
	
	public CharacterEntity(String characterType, int animationFrames, boolean containsIdle, int characterMode) {
		this.animatorFrames = animationFrames;
		this.characterMode = characterMode;
		this.characterType = characterType;
		this.containsIdle = containsIdle;
		loadAnimatorImages();
	}
	
	public void loadAnimatorImages() {
		for (int i = 0; i < directions.length; i++) {
			if (directions[i].equals("idle")) {
				if (!containsIdle) continue;
				for (int j = 0; j < 3; j++) {
					// String imagePath = "res\\images\\characters\\" + characterType + " " + characterMode + "\\Idle\\" + directions[i] + j + ".png"; 
					String imagePath = "resources/images/characters/" + characterType + " " + characterMode + "/Idle/" + directions[i] + j + ".png";
					if (!animatorMap.containsKey(directions[i])) {
						BufferedImage[] newImageArr = new BufferedImage[3];
						try {
							newImageArr[j] = ImageIO.read(getClass().getResourceAsStream(imagePath));
						} catch (IOException e) {
							System.out.println("Error loading image: " + imagePath);
						}
						animatorMap.put(directions[i], newImageArr);
					} else {
						try {
							BufferedImage[] temp = animatorMap.get(directions[i]);
							temp[j] = ImageIO.read(getClass().getResourceAsStream(imagePath));
						} catch (IOException e) {
							System.out.println("Error loading image: " + imagePath);
						}
					}
				}
			} else {
				currentDirection = directions[1];
				for (int j = 0; j < animatorFrames; j++) {
					String imagePath = "resources/images/characters/" + characterType + " " + characterMode + "/Walk/" + directions[i] + j + ".png";
					// String imagePath = "resources/images/characters/" + "Player 1/Walk/" + directions[i] + j + ".png";
					// System.out.println("imagePath: " + imagePath);
					if (!animatorMap.containsKey(directions[i])) {
						BufferedImage[] newImageArr = new BufferedImage[animatorFrames];
						try {
							newImageArr[j] = ImageIO.read(getClass().getResourceAsStream(imagePath));
						} catch (IOException e) {
							System.out.println("Error loading image: " + imagePath);
						}
						animatorMap.put(directions[i], newImageArr);
					} else {
						try {
							BufferedImage[] temp = animatorMap.get(directions[i]);
							temp[j] = ImageIO.read(getClass().getResourceAsStream(imagePath));
						} catch (IOException e) {
							System.out.println("Error loading image: " + imagePath);
						}
					}
				}
			}
		}
		System.out.println("Successfully loaded animatorMap: " + animatorMap);
	}
	
}
