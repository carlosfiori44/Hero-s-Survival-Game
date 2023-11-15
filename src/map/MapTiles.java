package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Game.GamePanel;
import classe.Player;

public class MapTiles {
	//Definindo atributos
	private Player player;
	private Tile[] tile;
	private int numTile = 100;
	private BufferedImage allTileImage;
	private int mapTilePosition[][];
	private int maxWorldCol = 100;
	private int maxWorldRow = 100;
	private int maxWorldWidth = maxWorldCol * GamePanel.TILESIZE, maxWorldHeight = maxWorldRow * GamePanel.TILESIZE;
	
	public MapTiles(Player player){
		this.player = player;
		
		try {
			allTileImage = ImageIO.read(getClass().getResourceAsStream("/tile/tileSetWorld.png"));
			tile = new Tile[numTile];
			mapTilePosition = new int[maxWorldWidth][maxWorldHeight];
			getTiles();		
			loadMap();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Classe do "pisos" que representam um bloco de imagem 16x16 pixels do mapa
	 */
	private class Tile{
		public BufferedImage image;
		public boolean collision = false;
	}
	
	/**
	 * Cria e carrega o array do tipo Tile para cada quadrado de imagem que constroe o mapa
	 */
	public void getTiles() {		
		for(int i = 0; i < numTile; i++) {
			tile[i] = new Tile();
		}
		
		int numImage = 0;
		
		for(int row = 0; row < allTileImage.getHeight(); row+=16) {			
			for(int col = 0; col < allTileImage.getWidth(); col+=16) {
				tile[numImage].image = allTileImage.getSubimage(col, row, 16, 16);
				numImage++;
			}			
		}
	}
	
	
	/**
	 * Carrega a construção do mapa, que se baseia em números que representam o número de um bloco (tile) que então é interpretado
	 * e substituido por uma imagem do bloco
	 */
	public void loadMap() {
		try {
			InputStream array = getClass().getResourceAsStream("/background/testTileMap.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(array));

			int col = 0;
			int row = 0;

			while(col < maxWorldCol && row < maxWorldRow) {
				String line;

				line = br.readLine();

				while(col < maxWorldCol) {
					String numbers[] = line.split(",");
					int num = Integer.parseInt(numbers[col]);
					mapTilePosition[col][row] = num;
					col++;
				}
				if(col == maxWorldCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Método que faz o 'desenho' na tela
	 * @param g2 Componente gráfico que coloca gráficos na tela
	 */
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		
		while(col < maxWorldCol && row < maxWorldRow) {			
			int tileNum = mapTilePosition[col][row];

			int worldX = col * GamePanel.TILESIZE;
			int worldY = row * GamePanel.TILESIZE;
			int	screenX = worldX - player.xWorld + player.xScreen;
			int	screenY = worldY - player.yWorld + player.yScreen;
			
			g2.drawImage(tile[tileNum].image, screenX, screenY, GamePanel.TILESIZE, GamePanel.TILESIZE, null);			
			col++;

			if(col == maxWorldCol) {				
				col = 0;
				row++;
			}
		}
	}


}
