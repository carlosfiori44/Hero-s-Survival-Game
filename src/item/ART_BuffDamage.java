package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import screen.GamePanel;

/**
 * Classe que generaliza de SuperItem e é um item que aumenta o dano do jogador
 */
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
