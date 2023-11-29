package player;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game.GamePanel;
import item.SuperItem;

/**
 * Classe mãe/superclasse para todos os personagens do jogo
 */
public class Character implements LoadImage {
	//Declarando atributos utilizados em cada personagem
	public int xScreen, yScreen, xWorld, yWorld;
	//Velocidade do personagem
	protected final int DEFAULTSPEED = 8;
	protected int currentSpeed = DEFAULTSPEED;
	//Atributo que vai receber a imagem do personagem
	protected BufferedImage image;
	//Respectivos tamanho da imagem do personagem
	protected final int ALTURA = 16, LARGURA = 16;
	//Area que o personagem vai ocupar na tela
	protected Rectangle bounds;
	//Atributo referente ao painel da janela
	protected GamePanel gp;
	//Definindo inventário do personagem
	public List<SuperItem> item = new ArrayList<SuperItem>();
	//Character attributes
	public int characterClass = 1; //Classe referente ao tipo do personagem, lutador, feiticeiro, druída...
	public int maxLife;
	public int currentLife;

	public Character(GamePanel gp) {
		this.gp = gp;
		setPlayerAttibutes();
	}
	
	@Override
	public void load() {		
	}

	/**
	 * Define os atributos do personagem com base na classe escolhida
	 */
	public void setPlayerAttibutes() {
		switch(characterClass) {
		//***Classes***
		//Cavaleiro
		case 0: break;
		//Lenhador
		case 1: 
			maxLife = 12;					
			break;
		//Lançador
		case 2: break;
		//Assassino
		case 3: break;
		//Arqueiro
		case 4: break;
		//Mago
		case 5: break;
		//Mosqueteiro
		case 6: break;
		}
		
		currentLife = maxLife;	
	}
	
	/**
	 * Retorna os limites da imagem do personagem
	 * @return retorna os tamanho da imagem do personagem
	 */
	public Rectangle getBounds() {
		return bounds;
	}

	/**
	 * Define a posição do personagem na tela
	 * @param x coordenada horizontal da tela
	 * @param y coordenada vertical da tela
	 */
	public void setPosition(int x, int y) {
		this.xWorld = x;
		this.yWorld = y;
	}

	/**
	 * Atualiza o posição do atual 'frame' do personagem dentro do JFrame
	 * @param g2 Objeto gráfico para atualizar no JPanel
	 */
	public void draw(Graphics2D g2) {
		g2.drawImage(image, xScreen, yScreen, null);
	}

	/**
	 * Faz a checagem para cada bloco de desenhado se este é 'passável'
	 * @param direction Direção que o player está indo
	 * @return retorna verdadeiro caso o bloco seja passável e falso caso não seja
	 */
	public boolean checkCollision(char direction) {
		int leftWorldX = xWorld + bounds.x;
		int rightWorldX = xWorld + bounds.x + bounds.width;
		
		int topWorldY = yWorld + bounds.y;
		int bottomWorldY = yWorld + bounds.y + bounds.height;

		int leftColX = leftWorldX/gp.TILESIZE;
		int rightColX = rightWorldX/gp.TILESIZE;
		int topRowY = topWorldY/gp.TILESIZE;
		int bottomRowY = bottomWorldY/gp.TILESIZE;

		int tileM1, tileM2, tileC1, tileC2;
		
		boolean collision = true;
		
		switch(direction) {
		case 'u': 
			topRowY = (topWorldY - currentSpeed)/gp.TILESIZE;
			
			tileM1 = gp.map.mapTile.mapTilePosition[leftColX][topRowY];
			tileM2 = gp.map.mapTile.mapTilePosition[rightColX][topRowY];
			
			tileC1 = gp.map.mapTile.construcTilePosition[leftColX][topRowY];
			tileC2 = gp.map.mapTile.construcTilePosition[rightColX][topRowY];
			
			if(gp.map.mapTile.mapTile[tileM1].collision || gp.map.mapTile.mapTile[tileM2].collision) {
				collision = false;
			}			
			
			if((tileC1 >= 0 && tileC1 < gp.map.mapTile.numTile)) {
				if(gp.map.mapTile.constructionTile[tileC1].collision) {
					collision = false;	
				}	
			} 
			
			if(tileC2 >= 0 && tileC2 < gp.map.mapTile.numTile) {
				if(gp.map.mapTile.constructionTile[tileC2].collision) {
					collision = false;	
				}						
			}
			
			break;
			
		case 'd':
			bottomRowY = (bottomWorldY + currentSpeed)/gp.TILESIZE;
			
			tileM1 = gp.map.mapTile.mapTilePosition[leftColX][bottomRowY];
			tileM2 = gp.map.mapTile.mapTilePosition[rightColX][bottomRowY];
			
			tileC1 = gp.map.mapTile.construcTilePosition[leftColX][bottomRowY];
			tileC2 = gp.map.mapTile.construcTilePosition[rightColX][bottomRowY];
			
			if(gp.map.mapTile.mapTile[tileM1].collision || gp.map.mapTile.mapTile[tileM2].collision) {
				collision = false;
			}	
			
			if((tileC1 >= 0 && tileC1 < gp.map.mapTile.numTile)) {
				if(gp.map.mapTile.constructionTile[tileC1].collision) {
					collision = false;	
				}	
			} 
			
			if(tileC2 >= 0 && tileC2 < gp.map.mapTile.numTile) {
				if(gp.map.mapTile.constructionTile[tileC2].collision) {
					collision = false;	
				}			
			}	
			
			break;
			
		case 'l':
			leftColX = (leftWorldX - currentSpeed)/gp.TILESIZE;
			
			tileM1 = gp.map.mapTile.mapTilePosition[leftColX][topRowY];
			tileM2 = gp.map.mapTile.mapTilePosition[leftColX][bottomRowY];
			
			tileC1 = gp.map.mapTile.construcTilePosition[leftColX][topRowY];
			tileC2 = gp.map.mapTile.construcTilePosition[leftColX][bottomRowY];
			
			if(gp.map.mapTile.mapTile[tileM1].collision || gp.map.mapTile.mapTile[tileM2].collision) {
				collision = false;
			}	
			
			if((tileC1 >= 0 && tileC1 < gp.map.mapTile.numTile)) {
				if(gp.map.mapTile.constructionTile[tileC1].collision) {
					collision = false;	
				}	
			} 
			
			if(tileC2 >= 0 && tileC2 < gp.map.mapTile.numTile) {
				if(gp.map.mapTile.constructionTile[tileC2].collision) {
					collision = false;	
				}						
			}
			
			break;
			
		case 'r':
			rightColX = (rightWorldX + currentSpeed)/gp.TILESIZE;
			
			tileM1 = gp.map.mapTile.mapTilePosition[rightColX][topRowY];
			tileM2 = gp.map.mapTile.mapTilePosition[rightColX][bottomRowY];
			
			tileC1 = gp.map.mapTile.construcTilePosition[rightColX][topRowY];
			tileC2 = gp.map.mapTile.construcTilePosition[rightColX][bottomRowY];
			
			if(gp.map.mapTile.mapTile[tileM1].collision || gp.map.mapTile.mapTile[tileM2].collision) {
				collision = false;	
			} 
			
			if((tileC1 >= 0 && tileC1 < gp.map.mapTile.numTile)) {
				if(gp.map.mapTile.constructionTile[tileC1].collision) {
					collision = false;	
				}	
			} 
			
			if(tileC2 >= 0 && tileC2 < gp.map.mapTile.numTile) {
				if(gp.map.mapTile.constructionTile[tileC2].collision) {
					collision = false;	
				}						
			}
			
			break;
		}
		
		return collision;
	}
	
	/**
	 * Verifica se o personagem para por cima de um item de buff e adiciona ao status do personagem
	 */
	public void checkObjectCollision() {
		for(int i = 0; i < gp.item.length; i++) {
			if(gp.item[i] != null && gp.item[i].findItem(new Rectangle(xWorld, yWorld, gp.TILESIZE, gp.TILESIZE))) {
				if(addItem(gp.item[i])) {
					gp.item[i] = null;
					gp.ui.setMessage("Voce pegou um item!");
				}
			}
		}		
		
		int leftWorldX = xWorld + bounds.x;
		int rightWorldX = xWorld + bounds.x + bounds.width;
		
		int bottomWorldY = yWorld + bounds.y + bounds.height;
		
		int leftColX = leftWorldX/gp.TILESIZE;
		int rightColX = rightWorldX/gp.TILESIZE;
		int bottomRowY = bottomWorldY/gp.TILESIZE;
		
		//Verifica se o player está em um arbusto
		if(gp.map.mapTile.construcTilePosition[leftColX][bottomRowY] == 70 || gp.map.mapTile.construcTilePosition[rightColX][bottomRowY] == 70) {
			currentSpeed = DEFAULTSPEED/4;
		} else {
			currentSpeed = DEFAULTSPEED;
		}
	}
	
	/**
	 * Mostra os itens dentro do invetário do personagem 
	 * @param g2 recebe o componente gráfico do tipo Graphics2D para adicionalo a tela
	 */
	public void showItem(Graphics2D g2) {
		int positionY = 50;
		
		for(SuperItem i : item){
			i.setPositionX(1);
			i.setPositionY(42);

			g2.drawImage(i.image, 0, positionY, gp.TILESIZE/2, gp.TILESIZE/2, null);
			
			positionY += gp.TILESIZE + 4;
		}
	}

	/**
	 * Adiciona o item que o usuário pegou próximo a ele
	 * @param item item que o usuário pegou
	 */	
	public boolean addItem(SuperItem item) {
		if(this.item.size() < 2) {
			this.item.add(item);	
			return true;			
		}

		return false;
	}
}
