package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;

public class BuffDamage extends SuperItem {
	
	public BuffDamage(GamePanel gp) {
		this.gp = gp;
		
		name = "Damage Increase";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/itens/buffDamage.png"));
		} catch (IOException e) {
			
		}
	}
}
