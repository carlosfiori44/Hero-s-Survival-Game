package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Item_Sword extends SuperItem {
	
	public Item_Sword() {
		name = "Sword";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/itens/wood_sword.png"));
		} catch (IOException e) {
			
		}
	}
}
