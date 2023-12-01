package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import screen.GamePanel;

public class MapController {
	//Definindo o atributos das imagens de fundo
	private BufferedImage map_Island_Image;
	//Variavel que guarda o fundo atual somente com as localizações permitidas para movimentação
	private static BufferedImage currentBackgroundAllow;	
	//Variavel que irá indicar o mapa que deverá aparecer
	public int map;
	//Definindo quais os mapas para passagem
	public final int MAP_ISLAND = 0;
	public final int MAP_HOUSE = 1;
	public final int MAP_CASTLE = 2;
	//Definindo atributo referente a classe MapTiles para os mapas gerados
	public MapTiles mapTile;
	GamePanel gp;
	
	public MapController(GamePanel gp) {
		this.gp = gp;
		mapTile = new MapTiles(gp);
	}
	
	/**
	 * Carrega a imagem de todos os mapas
	 */
	public void load() {
		try {
			switch(map) {
			case 0:
				map_Island_Image = ImageIO.read(getClass().getResource("/background/mapa_ilha.png"));
				currentBackgroundAllow = ImageIO.read(getClass().getResource("/background/mapa_ilha_allow.png"));
				break;
			}					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que verifica se a posição em que foi passada é caminhável ou não
	 * @param x coordenada vertical de onde quer verificar
	 * @param y coordenada horizontal de onde quer verificar
	 * @return verdadeiro caso seja caminhável ou falso caso contrario
	 */
	public static boolean isWalkable(int x, int y) {
		int rgb = currentBackgroundAllow.getRGB(x, y);
		
		if(rgb == 0) {
			return false;
		}				
		return true;
	}
	
	/**
	 * Coloca no JFrame o mapa atual em que o personagem está
	 * @param g2 Objeto gráfico para atualizar no JPanel
	 */
	public void draw(Graphics2D g2) {	
		gp.setBackground(Color.black);
		
		if(map == MAP_ISLAND) {
			mapTile.draw(g2);
			//g2.drawImage(map_Island_Image, 0, 0, null);
			//tile.draw(g2);
		}
	}
	
}
