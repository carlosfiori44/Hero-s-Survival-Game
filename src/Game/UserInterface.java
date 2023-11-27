package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UserInterface {
	
	GamePanel gp;
	Font defaultArial;
	
	/**
	 * Constutor da classe UserInterface
	 * @param gp Recebe o JPanel principal
	 */
	public UserInterface(GamePanel gp) {
		this.gp = gp;
		
		defaultArial = new Font("Arial", Font.PLAIN, 40);
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setFont(defaultArial);
		g2.setColor(Color.white);
		g2.drawString("Itens: " + gp.player.item.size(), 50, 50);
	}
}
