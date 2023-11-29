package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;

public class ART_BuffDamage extends SuperItem {
	
	public ART_BuffDamage(GamePanel gp) {
		this.gp = gp;
		
		name = "Damage Increase";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/itens/buffDamage.png"));
		} catch (IOException e) {
			
		}
	}
}
