package treasurehunter;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import services.ReadINIFile;
import tiles.TileManager;



public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	final int originalTileSize = 16;
	public int scaleFactor;

	public int tileSize;
	public final int maxScreenCol = 22;
	public final int maxScreenRow = 15;
	int screenWidth;
	int screenHeight;

	// Game FPS
	int FPS = 60;

	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	Player player = new Player(this, keyH, 4, false, 1);
	TileManager tManager = new TileManager(this);
	ReadINIFile iniReader;

	public GamePanel() {
		loadINIData();
		this.tileSize = originalTileSize * scaleFactor;

		screenWidth = maxScreenCol * tileSize;
		screenHeight = maxScreenRow * tileSize;

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	void loadINIData() {
		iniReader = new ReadINIFile();
		if (iniReader != null) {
			this.scaleFactor = Integer.parseInt(iniReader.getData("scaleFactor"));
			tileSize = Integer.parseInt(iniReader.getData("tileSize"));
			System.out.println("scaleFactor : " + scaleFactor + " tileSize : " + tileSize);
		}
	}

	public void startGame() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawThreshold = 1000000000 / FPS;
		double delta = 0;
		long lastDrawTime = System.nanoTime();
		long currentDrawTime;

		while (gameThread != null) {
			currentDrawTime = System.nanoTime();
			delta += (currentDrawTime - lastDrawTime) / drawThreshold;

			lastDrawTime = currentDrawTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}

		}
	}

	public void update() {
		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		tManager.draw(g2);
		player.draw(g2);
		g2.dispose();
	}

}
