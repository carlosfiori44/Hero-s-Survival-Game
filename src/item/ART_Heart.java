package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import screen.GamePanel;

/**
 * Classe que generaliza de SuperItem e referencia a vida do jogador
 */
public class ART_Heart extends SuperItem {
	
	public ART_Heart(GamePanel gp) {
		this.gp = gp;
		
		name = "Damage Increase";
		
		try {
			image = ImageIO.read(getClass().getResource("/playerAttributes/heart.png"));
			image1 = ImageIO.read(getClass().getResource("/playerAttributes/halfHeart.png"));
			image2 = ImageIO.read(getClass().getResource("/playerAttributes/emptyHeart.png"));
		} catch (IOException e) {
			
		}
	}
}
