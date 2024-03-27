package treasurehunter;

import javax.swing.JFrame;

public class App 
{
    public static void main( String[] args )
    {
        JFrame window = new JFrame();
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setResizable(false);
    	window.setTitle("Treasure Hunter");
    	
    	GamePanel gamePanel = new GamePanel();
    	window.add(gamePanel);
    	window.pack();
    	
    	window.setLocationRelativeTo(null);
    	window.setVisible(true);
    	gamePanel.startGame();
    	
    }
}
