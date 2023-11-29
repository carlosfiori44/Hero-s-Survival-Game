package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;

public class ART_Heart extends SuperItem {
	
	public ART_Heart(GamePanel gp) {
		this.gp = gp;
		
		name = "Damage Increase";
		
		try {
			image = ImageIO.read(getClass().getResource("/player/heart.png"));
			image1 = ImageIO.read(getClass().getResource("/player/halfHeart.png"));
			image2 = ImageIO.read(getClass().getResource("/player/emptyHeart.png"));
		} catch (IOException e) {
			
		}
	}
}
