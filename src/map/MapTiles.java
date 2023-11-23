package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Game.GamePanel;
import player.Player;

public class MapTiles {
	//Definindo atributos
	private GamePanel gp;
	public Tile[] mapTile, constructionTile;
	private int numTile = 300;
	private BufferedImage allTileImage, allConstructionImage;
	public int mapTilePosition[][];
	public int mapConstructionPosition[][];
	private int maxWorldCol = 50;
	private int maxWorldRow = 50;
	private int maxWorldWidth, maxWorldHeight;

	public MapTiles(GamePanel gp){
		this.gp = gp;
		
		maxWorldWidth = maxWorldCol * gp.TILESIZE;
		maxWorldHeight = maxWorldRow * gp.TILESIZE;

		try {
			allTileImage = ImageIO.read(getClass().getResourceAsStream("/tile/tileSetWorld.png"));
			allConstructionImage = ImageIO.read(getClass().getResourceAsStream("/tile/tileConstruction.png"));
			
			mapTile = new Tile[numTile];
			constructionTile = new Tile[numTile];
			
			mapTilePosition = new int[maxWorldWidth][maxWorldHeight];
			mapConstructionPosition = new int[maxWorldWidth][maxWorldHeight];
			
			getTiles();		
			loadMap();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Cria e carrega o array do tipo Tile para cada quadrado de imagem que constroe o mapa
	 */
	public void getTiles() {		
		//Instanciando cada posição do array para que possa ser definido uma imagem
		for(int i = 0; i < numTile; i++) {
			mapTile[i] = new Tile();
			
			constructionTile[i] = new Tile();
			//Todas as construções tem colisões
			constructionTile[i].collision = true;
		}

		//Número da posição do array do tile
		int numImage = 0;

		//Inserindo cada tile da imagem principal em uma posição do array
		for(int row = 0; row < allTileImage.getHeight(); row+=16) {			
			for(int col = 0; col < allTileImage.getWidth(); col+=16) {
				mapTile[numImage].image = allTileImage.getSubimage(col, row, 16, 16);
				numImage++;
			}			
		}
		
		numImage = 0;
		//Inserindo cada tile da imagem principal das construções em uma posição do array
		for(int row = 0; row < allConstructionImage.getHeight(); row+=16) {			
			for(int col = 6; col < allConstructionImage.getWidth(); col+=16) {
				constructionTile[numImage].image = allConstructionImage.getSubimage(col, row, 16, 16);
				numImage++;
			}			
		}		
		
		//Definindo os blocos que não são possíveis de atravessar das imagens do fundo do mapa
		mapTile[0].collision = true;
		mapTile[9].collision = true;
		mapTile[10].collision = true;
		mapTile[11].collision = true;
		mapTile[12].collision = true;
		mapTile[16].collision = true;
		mapTile[17].collision = true;
		mapTile[18].collision = true;
		
	}


	/**
	 * Carrega a construção do mapa, que se baseia em números que representam o número de um bloco (tile) que então é interpretado
	 * e substituido por uma imagem do bloco
	 */
	public void loadMap() {
		try {
			InputStream arrayMap = getClass().getResourceAsStream("/background/tileWorldMap.txt");
			InputStream arrayConstr = getClass().getResourceAsStream("/construction/constructionWorldMap");
			
			BufferedReader brMap = new BufferedReader(new InputStreamReader(arrayMap));
			BufferedReader brConstr = new BufferedReader(new InputStreamReader(arrayConstr));


			int col = 0;
			int row = 0;

			while(col < maxWorldCol && row < maxWorldRow) {
				String lineM;
				String lineC;

				lineM = brMap.readLine();
				lineC = brConstr.readLine();

				while(col < maxWorldCol) {
					String numbersC[] = lineM.split(",");
					String numbersM[] = lineC.split(",");
					
					int numM = Integer.parseInt(numbersC[col]);
					int numC = Integer.parseInt(numbersM[col]);
					
					mapTilePosition[col][row] = numM;
					mapConstructionPosition[col][row] = numC;
					
					col++;
				}
				if(col == maxWorldCol) {
					col = 0;
					row++;
				}
			}

			brMap.close();
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
			int tileNumM = mapTilePosition[col][row];
			int tileNumC = mapConstructionPosition[col][row];

			//Pega as coordenas atuais do mudo, em relação a coluna e linha
			int worldX = col * gp.TILESIZE;
			int worldY = row * gp.TILESIZE;
			//Pega as coordenadas atuais da janela, que é onde o player está no mapa
			int	screenX = worldX - gp.player.xWorld + gp.player.xScreen;
			int	screenY = worldY - gp.player.yWorld + gp.player.yScreen;

			//Verifica se o bloco esta dentro dos limites da janela para que ele não renderize o mapa inteiro e economize desempenho
			if(worldX + gp.TILESIZE > gp.player.xWorld - gp.player.xScreen && worldX - gp.TILESIZE < gp.player.xWorld + gp.player.xScreen &&
					worldY + gp.TILESIZE > gp.player.yWorld - gp.player.yScreen && worldY - gp.TILESIZE < gp.player.yWorld + gp.player.yScreen) {
				
				g2.drawImage(mapTile[tileNumM].image, screenX, screenY, gp.TILESIZE, gp.TILESIZE, null);
				
				//Desconsiderando espaços vazios
				if(tileNumC >= 0 && tileNumC < numTile) {
					g2.drawImage(constructionTile[tileNumC].image, screenX, screenY, gp.TILESIZE, gp.TILESIZE, null);
				}
			}

			col++;

			if(col == maxWorldCol) {				
				col = 0;
				row++;
			}
			
			if(gp.player.xWorld + 10 > maxWorldCol * gp.TILESIZE || gp.player.yWorld + 10 > maxWorldRow * gp.TILESIZE ||
			   gp.player.xWorld - 10 < 0 || gp.player.yWorld - 10 < 0) {
				System.out.println(gp.player.xWorld + " | " + gp.player.yWorld);
				System.out.println(maxWorldCol * gp.TILESIZE + " | " + maxWorldRow * gp.TILESIZE);
			}
		}
	}


}
