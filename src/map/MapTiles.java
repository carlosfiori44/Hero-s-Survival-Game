package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game.GamePanel;
import player.Player;

public class MapTiles {
	//Definindo atributos
	private GamePanel gp;
	public Tile[] mapTile, constructionTile;
	public int numTile = 300;
	private BufferedImage allTileImage, allConstructionImage;
	public int mapTilePosition[][];
	public int construcTilePosition[][];
	private int maxWorldCol = 0;
	private int maxWorldRow = 0;
	//private int maxWorldWidth, maxWorldHeight;

	public MapTiles(GamePanel gp){
		this.gp = gp;

		try {
			allTileImage = ImageIO.read(getClass().getResourceAsStream("/tile/tileSetWorld.png"));
			allConstructionImage = ImageIO.read(getClass().getResourceAsStream("/tile/tileConstruction.png"));

			mapTile = new Tile[numTile];
			constructionTile = new Tile[numTile];

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
		
		//Alterando colisão dos blocos de construção, para aqueles que são passáveis
		constructionTile[148].collision = false;
		constructionTile[70].collision = false;
	}


	/**
	 * Carrega a construção do mapa, que se baseia em números que representam o número de um bloco (tile) que então é interpretado
	 * e substituido por uma imagem do bloco
	 */
	public void loadMap() {
		try {
			InputStream arrayMap = getClass().getResourceAsStream("/background/tileWorldMap.txt");
			InputStream arrayConstr = getClass().getResourceAsStream("/construction/constructionWorldMap.txt");

			BufferedReader brMap = new BufferedReader(new InputStreamReader(arrayMap));
			BufferedReader brConstr = new BufferedReader(new InputStreamReader(arrayConstr));

			List<String[]> listNumM = new ArrayList<String[]>();
			List<String[]> listNumC = new ArrayList<String[]>();

			while(true) {				
				String lineM;
				String lineC;

				lineM = brMap.readLine();
				lineC = brConstr.readLine();

				if(lineM != null && lineC != null) {
					listNumM.add(lineM.split(","));		
					listNumC.add(lineC.split(","));
				} else {
					break;
				}
			}

			mapTilePosition = new int[listNumM.get(0).length][listNumM.size()];
			construcTilePosition = new int[listNumC.get(0).length][listNumC.size()];

			//Aqui é passado toda matriz do arquivo txt para nosso array do java, como são duas matriz(De construção e do fundo do mapa) ambas tem
			//de ter o mesmo tamanho, por isso aproveitamos os mesmo fluxos de for para ambas matrizes.
			for(int i = 0; i < listNumM.get(0).length; i++) {
				for(int j = 0; j < listNumM.size(); j++) {
					mapTilePosition[i][j] = Integer.parseInt(listNumM.get(j)[i]);
					construcTilePosition[i][j] = Integer.parseInt(listNumC.get(j)[i]);
				}				
			}

			maxWorldCol = listNumM.get(0).length;
			maxWorldRow = listNumM.size();

			brMap.close();
			brConstr.close();
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
			int tileNumC = construcTilePosition[col][row];

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
