package map;

import java.awt.image.BufferedImage;

/**
 * Classe do "pisos" que representam um bloco de imagem 16x16 pixels do mapa
 */
public class Tile {
	public BufferedImage image;
	public boolean collision = false;
}
