package classe;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mapa {
	
	public static boolean isWalkable(int x, int y) {
		BufferedImage walkableAreas = null;
		try {
			walkableAreas = ImageIO.read(new File("images\\background\\mapa_ilha_allow.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rgb = walkableAreas.getRGB(x, y);
		
		if(rgb == 0) {
			return false;
		}		
		
		return true;
	}
	
}
