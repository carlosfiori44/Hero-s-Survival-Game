package classe;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fase.GamePanel;

public class Mapa {
	
	/**
	 * Método que verifica se a posição em que foi passada é caminhável ou não
	 * @param x coordenada vertical de onde quer verificar
	 * @param y coordenada horizontal de onde quer verificar
	 * @return verdadeiro caso seja caminhável ou falso caso contrario
	 */
	public static boolean isWalkable(int x, int y) {
		BufferedImage walkableAreas = null;
		try {
			walkableAreas = ImageIO.read(new File(GamePanel.currentBackground));
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
