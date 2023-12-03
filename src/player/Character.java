package player;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import item.SuperItem;
import screen.GamePanel;

/**
 * Superclasse para todos os personagens do jogo
 */
public class Character {
	//Atributo que vai receber a imagem do personagem
	public BufferedImage currentImage, image;
	//Imagens de cada posição do personagem(cima, baixo, direita, esquerda
	//A coluna da matrix representa a direção, 0 = down, 1 = up, 2 = left, 3 = right
	public BufferedImage[][] moviment = new BufferedImage[4][5];
	public BufferedImage[][] atackMoviment = new BufferedImage[4][6];
	//Contagem para alteração da animação do personagem andando
	public int spriteNum = 1, spriteAttNum = 1, spriteCounter = 0, attackCounter = 0;
	public boolean attack = false;

	//Respectivos tamanho da imagem do personagem
	protected final int ALTURA = 16, LARGURA = 16;
	//Declarando atributos utilizados em cada personagem
	public int xScreen, yScreen, xWorld, yWorld;

	//Qual direção o personagem está virado
	public char direction;

	//Area que o personagem vai ocupar na tela
	protected Rectangle bounds;
	//Atributo referente ao painel da janela
	protected GamePanel gp;

	//Definindo inventário do personagem
	public int invetorySize = 4;
	public List<SuperItem> item = new ArrayList<SuperItem>();

	//Character attributes
	public int characterClass = 1; //Classe referente ao tipo do personagem, lutador, feiticeiro, druída...
	public int maxLife;
	public int currentLife;
	//Velocidade do personagem
	protected final int DEFAULTSPEED = 8;
	protected int currentSpeed = DEFAULTSPEED;

	public Character(GamePanel gp) {
		this.gp = gp;
		setPlayerAttibutes();
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
	 * Extrai as subimagens da animação do personagem da imagem principal
	 */
	public void load() {
		for (int i = 0; i < moviment.length; i++) {
			for (int j = 0; j < moviment[i].length; j++) {
				moviment[i][j] = image.getSubimage(j*16, i*16, gp.DEFAULTTILESIZE, gp.DEFAULTTILESIZE);
			}
		}

		for (int i = 0; i < atackMoviment.length; i++) {
			for (int j = 0; j < atackMoviment[i].length; j++) {
				atackMoviment[i][j] = image.getSubimage(j*16, i*16+moviment.length*gp.DEFAULTTILESIZE, gp.DEFAULTTILESIZE, gp.DEFAULTTILESIZE);
			}
		}
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
		switch(direction) {
		case 'u':
			if(attack) {
				//Alterando entre os movimentos de ataque
				if(spriteAttNum == 1) {
					currentImage = atackMoviment[1][0];
				}
				if(spriteAttNum == 2) {
					currentImage = atackMoviment[1][1];
				}
				if(spriteAttNum == 3) {
					currentImage = atackMoviment[1][2];
				}
				if(spriteAttNum == 4) {
					currentImage = atackMoviment[1][3];
				}
				if(spriteAttNum == 5) {
					currentImage = atackMoviment[1][4];
				}
				if(spriteAttNum == 6) {
					currentImage = atackMoviment[1][4];
				}
			} else {
				//Alterando entre os movimentos de andar
				if(spriteNum == 1) {
					currentImage = moviment[1][0];
				}
				if(spriteNum == 2) {
					currentImage = moviment[1][1];
				}
				if(spriteNum == 3) {
					currentImage = moviment[1][2];
				}
				if(spriteNum == 4) {
					currentImage = moviment[1][3];
				}
				if(spriteNum == 5) {
					currentImage = moviment[1][4];
				}
			}
			break;
		case 'd': 
			if(attack) {
				//Alterando entre os movimentos de ataque
				if(spriteAttNum == 1) {
					currentImage = atackMoviment[0][0];
				}
				if(spriteAttNum == 2) {
					currentImage = atackMoviment[0][1];
				}
				if(spriteAttNum == 3) {
					currentImage = atackMoviment[0][2];
				}
				if(spriteAttNum == 4) {
					currentImage = atackMoviment[0][3];
				}
				if(spriteAttNum == 5) {
					currentImage = atackMoviment[0][4];
				}
				if(spriteAttNum == 6) {
					currentImage = atackMoviment[0][4];
				}
			} else {
				//Alterando entre os movimentos de andar
				if(spriteNum == 1) {
					currentImage = moviment[0][0];
				}
				if(spriteNum == 2) {
					currentImage = moviment[0][1];
				}
				if(spriteNum == 3) {
					currentImage = moviment[0][2];
				}
				if(spriteNum == 4) {
					currentImage = moviment[0][3];
				}
				if(spriteNum == 5) {
					currentImage = moviment[0][4];
				}
			}
			break;
		case 'l': 
			if(attack) {
				//Alterando entre os movimentos de ataque
				if(spriteAttNum == 1) {
					currentImage = atackMoviment[2][0];
				}
				if(spriteAttNum == 2) {
					currentImage = atackMoviment[2][1];
				}
				if(spriteAttNum == 3) {
					currentImage = atackMoviment[2][2];
				}
				if(spriteAttNum == 4) {
					currentImage = atackMoviment[2][3];
				}
				if(spriteAttNum == 5) {
					currentImage = atackMoviment[2][4];
				}
				if(spriteAttNum == 6) {
					currentImage = atackMoviment[2][4];
				}
			} else {
				//Alterando entre os movimentos de andar
				if(spriteNum == 1) {
					currentImage = moviment[2][0];
				}	
				if(spriteNum == 2) {
					currentImage = moviment[2][1];
				}
				if(spriteNum == 3) {
					currentImage = moviment[2][2];
				}
				if(spriteNum == 4) {
					currentImage = moviment[2][3];
				}
				if(spriteNum == 5) {
					currentImage = moviment[2][4];
				}
			}	
			break;
		case 'r':
			if(attack) {
				//Alterando entre os movimentos de ataque
				if(spriteAttNum == 1) {
					currentImage = atackMoviment[3][0];
				}
				if(spriteAttNum == 2) {
					currentImage = atackMoviment[3][1];
				}
				if(spriteAttNum == 3) {
					currentImage = atackMoviment[3][2];
				}
				if(spriteAttNum == 4) {
					currentImage = atackMoviment[3][3];
				}
				if(spriteAttNum == 5) {
					currentImage = atackMoviment[3][4];
				}
				if(spriteAttNum == 6) {
					currentImage = atackMoviment[3][4];
				}
			} else {
				//Alterando entre os movimentos de andar
				if(spriteNum == 1) {
					currentImage = moviment[3][0];
				}
				if(spriteNum == 2) {
					currentImage = moviment[3][1];
				}
				if(spriteNum == 3) {
					currentImage = moviment[3][2];
				}
				if(spriteNum == 4) {
					currentImage = moviment[3][3];
				}
				if(spriteNum == 5) {
					currentImage = moviment[3][4];
				}
				break;
			}
		}

		g2.drawImage(currentImage, xScreen, yScreen, gp.TILESIZE, gp.TILESIZE, null);
	}

	/**
	 * Faz a atualização das coordenadas do personagem de acordo com a velocidade definida e verificar se é um
	 * caminho de fato e atualiza as 'sprites' referentes a imagem do personagem andando
	 */
	public void update() {		

	}

	/**
	 * Faz a checagem para cada bloco de desenhado se este é 'passável'
	 * @param direction Direção que o player está indo
	 * @return retorna verdadeiro caso o bloco seja passável e falso caso não seja
	 */
	public boolean checkCollision() {
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

			//Verificação do backgroud mapa
			if(gp.map.mapTile.mapTile[tileM1].collision || gp.map.mapTile.mapTile[tileM2].collision) {
				collision = false;
			}			

			//*****Verificação dos blocos de construção*****
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
				} else {
					gp.ui.setMessage("Inventario cheio!");
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
	 * Adiciona o item que o usuário pegou próximo a ele
	 * @param item item que o usuário pegou
	 */	
	public boolean addItem(SuperItem item) {
		//Verifica se o inventário do personagem está cheio
		if(this.item.size() < invetorySize) {
			this.item.add(item);	
			return true;			
		}

		return false;
	}
}
