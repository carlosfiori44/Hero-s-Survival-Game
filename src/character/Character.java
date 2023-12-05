package character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
	public BufferedImage[][] moviment;
	public BufferedImage[][] attackMoviment;
	//Contagem para alteração da animação do personagem andando
	public int spriteNum = 1, spriteAttNum = 1, spriteCounter = 0, attackCounter = 0;
	public boolean attack = false;
	public boolean moving = true;
	public boolean characterCollision = false;

	//Declarando atributos utilizados em cada personagem
	public int xScreen, yScreen, xWorld, yWorld;

	//Qual direção o personagem está virado
	public char direction = 'd';

	//Area que o personagem vai ocupar na tela
	public Rectangle bounds;
	
	//Atributo referente ao painel da janela
	public GamePanel gp;

	//Definindo inventário do personagem
	public int invetorySize = 4;
	public List<SuperItem> item = new ArrayList<SuperItem>();

	//Character attributes
	public int characterClass = 1; //Classe referente ao tipo do personagem, lutador, feiticeiro, druída...
	public int maxLife;
	public int currentLife;
	//Velocidade do personagem
	public final int DEFAULTSPEED = 6;
	public int currentSpeed = DEFAULTSPEED;

	public Character(GamePanel gp) {
		this.gp = gp;


		//Definindo valor padrão do tamanho do personagem
		bounds = new Rectangle(4*gp.SCALE, 4*gp.SCALE, 8*gp.SCALE, 11*gp.SCALE);		

		setPlayerAttributes();
	}

	/**
	 * Define os atributos do personagem com base na classe escolhida
	 */
	public void setPlayerAttributes() {
		switch(characterClass) {
		//***Classes***
		//Cavaleiro
		case 0: break;

		//Lenhador
		case 1: maxLife = 12; break;

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

		for (int i = 0; i < attackMoviment.length; i++) {
			for (int j = 0; j < attackMoviment[i].length; j++) {
				attackMoviment[i][j] = image.getSubimage(j*16, i*16+moviment.length*gp.DEFAULTTILESIZE, gp.DEFAULTTILESIZE, gp.DEFAULTTILESIZE);
			}
		}
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
					currentImage = attackMoviment[1][0];
				}
				if(spriteAttNum == 2) {
					currentImage = attackMoviment[1][1];
				}
				if(spriteAttNum == 3) {
					currentImage = attackMoviment[1][2];
				}
				if(spriteAttNum == 4) {
					currentImage = attackMoviment[1][3];
				}
				if(spriteAttNum == 5) {
					currentImage = attackMoviment[1][4];
				}
				if(spriteAttNum == 6) {
					currentImage = attackMoviment[1][4];
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
					currentImage = attackMoviment[0][0];
				}
				if(spriteAttNum == 2) {
					currentImage = attackMoviment[0][1];
				}
				if(spriteAttNum == 3) {
					currentImage = attackMoviment[0][2];
				}
				if(spriteAttNum == 4) {
					currentImage = attackMoviment[0][3];
				}
				if(spriteAttNum == 5) {
					currentImage = attackMoviment[0][4];
				}
				if(spriteAttNum == 6) {
					currentImage = attackMoviment[0][4];
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
					currentImage = attackMoviment[2][0];
				}
				if(spriteAttNum == 2) {
					currentImage = attackMoviment[2][1];
				}
				if(spriteAttNum == 3) {
					currentImage = attackMoviment[2][2];
				}
				if(spriteAttNum == 4) {
					currentImage = attackMoviment[2][3];
				}
				if(spriteAttNum == 5) {
					currentImage = attackMoviment[2][4];
				}
				if(spriteAttNum == 6) {
					currentImage = attackMoviment[2][4];
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
					currentImage = attackMoviment[3][0];
				}
				if(spriteAttNum == 2) {
					currentImage = attackMoviment[3][1];
				}
				if(spriteAttNum == 3) {
					currentImage = attackMoviment[3][2];
				}
				if(spriteAttNum == 4) {
					currentImage = attackMoviment[3][3];
				}
				if(spriteAttNum == 5) {
					currentImage = attackMoviment[3][4];
				}
				if(spriteAttNum == 6) {
					currentImage = attackMoviment[3][4];
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
	}

	/**
	 * Faz a atualização das coordenadas do personagem de acordo com a velocidade definida e verificar se é um
	 * caminho de fato e atualiza as 'sprites' referentes a imagem do personagem andando
	 */
	public void update() {				
		//Váriaveis de atualização das imagens de animação do personagem
		//Atualização do ataque
		if(attack) {
			attackCounter++;

			if(attackCounter > 4) {
				if(spriteAttNum == 1) {
					spriteAttNum = 2;
				} else if(spriteAttNum == 2) {
					spriteAttNum = 3;
				} else if(spriteAttNum == 3) {
					spriteAttNum = 4;
				}else if(spriteAttNum == 4) {
					spriteAttNum = 5;
				}else if(spriteAttNum == 5) {
					spriteAttNum = 6;
				} else if(spriteAttNum == 6) {
					spriteAttNum = 1;
				}
				attackCounter = 0;
			}
		} else {
			spriteAttNum = 1;
		}

		//Atualização da movimentação
		if(moving) {
			spriteCounter++;
			if(spriteCounter > 8) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if(spriteNum == 2) {
					spriteNum = 3;
				} else if(spriteNum == 3) {
					spriteNum = 4;
				}else if(spriteNum == 4) {
					spriteNum = 5;
				}else if(spriteNum == 5) {
					spriteNum = 2;
				}				
				spriteCounter = 0;
			}
		} else {
			spriteNum = 1;
		}
	}

	/**
	 * Realiza a movimentação do NPC
	 */
	public void moveNPC() {
		//Rectangle position = bounds;


		//if(getBounds().intersects(bounds)) {

		//}
	}

	public boolean checkCharacterCollision(){
		int leftWorldX = xWorld + bounds.x;
		int topWorldY = yWorld + bounds.y;
		
		int leftColX = leftWorldX/gp.TILESIZE;
		int topRowY = topWorldY/gp.TILESIZE;;

		Rectangle worldBound = new Rectangle(leftWorldX, topWorldY, bounds.width, bounds.height);

		boolean passable = true;

		switch(direction) {
		case 'u': 
			worldBound.y -= currentSpeed;
			//Verificando se há outro personagem
			for(int i = 0; i < gp.enemy.length; i++) {
			 	if(worldBound.intersects(gp.enemy[i].xWorld + gp.enemy[i].bounds.x, gp.enemy[i].yWorld+gp.enemy[i].bounds.y, gp.enemy[i].bounds.width, gp.enemy[i].bounds.height)) {
			 		passable = false;
			 	}
			}

			break;

		case 'd':
			worldBound.y += currentSpeed;
			//Verificando se há outro personagem
			for(int i = 0; i < gp.enemy.length; i++) {
			 	if(worldBound.intersects(gp.enemy[i].xWorld + gp.enemy[i].bounds.x, gp.enemy[i].yWorld+gp.enemy[i].bounds.y, gp.enemy[i].bounds.width, gp.enemy[i].bounds.height)) {
			 		passable = false;
			 	}
			}
			break;

		case 'l':
			worldBound.x -= currentSpeed;
			//Verificando se há outro personagem
			for(int i = 0; i < gp.enemy.length; i++) {		 	
			 	if(worldBound.intersects(gp.enemy[i].xWorld + gp.enemy[i].bounds.x, gp.enemy[i].yWorld+gp.enemy[i].bounds.y, gp.enemy[i].bounds.width, gp.enemy[i].bounds.height)) {
			 		passable = false;
			 	}
			}
			break;

		case 'r':
			worldBound.x += currentSpeed;
			//Verificando se há outro personagem
			for(int i = 0; i < gp.enemy.length; i++) {			 	
			 	if(worldBound.intersects(gp.enemy[i].xWorld + gp.enemy[i].bounds.x, gp.enemy[i].yWorld+gp.enemy[i].bounds.y, gp.enemy[i].bounds.width, gp.enemy[i].bounds.height)) {
			 		passable = false;
			 	}
			}
			break;
		}
		
		return passable;
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

		boolean passable = true;

		switch(direction) {
		case 'u': 
			topRowY = (topWorldY - currentSpeed)/gp.TILESIZE;

			tileM1 = gp.map.mapTile.mapTilePosition[leftColX][topRowY];
			tileM2 = gp.map.mapTile.mapTilePosition[rightColX][topRowY];

			tileC1 = gp.map.mapTile.construcTilePosition[leftColX][topRowY];
			tileC2 = gp.map.mapTile.construcTilePosition[rightColX][topRowY];
			
			//Verificação do backgroud mapa
			if(gp.map.mapTile.mapTile[tileM1].collision || gp.map.mapTile.mapTile[tileM2].collision) {
				passable = false;
			}			

			//*****Verificação dos blocos de construção*****
			if((tileC1 >= 0 && tileC1 < gp.map.mapTile.numTile)) {
				if(gp.map.mapTile.constructionTile[tileC1].collision) {
					passable = false;	
				}	
			} 

			if(tileC2 >= 0 && tileC2 < gp.map.mapTile.numTile) {
				if(gp.map.mapTile.constructionTile[tileC2].collision) {
					passable = false;	
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
				passable = false;
			}	

			if((tileC1 >= 0 && tileC1 < gp.map.mapTile.numTile)) {
				if(gp.map.mapTile.constructionTile[tileC1].collision) {
					passable = false;	
				}	
			} 

			if(tileC2 >= 0 && tileC2 < gp.map.mapTile.numTile) {
				if(gp.map.mapTile.constructionTile[tileC2].collision) {
					passable = false;	
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
				passable = false;
			}	

			if((tileC1 >= 0 && tileC1 < gp.map.mapTile.numTile)) {
				if(gp.map.mapTile.constructionTile[tileC1].collision) {
					passable = false;	
				}	
			} 

			if(tileC2 >= 0 && tileC2 < gp.map.mapTile.numTile) {
				if(gp.map.mapTile.constructionTile[tileC2].collision) {
					passable = false;	
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
				passable = false;	
			} 

			if((tileC1 >= 0 && tileC1 < gp.map.mapTile.numTile)) {
				if(gp.map.mapTile.constructionTile[tileC1].collision) {
					passable = false;	
				}	
			} 

			if(tileC2 >= 0 && tileC2 < gp.map.mapTile.numTile) {
				if(gp.map.mapTile.constructionTile[tileC2].collision) {
					passable = false;	
				}						
			}

			break;
		}

		return passable;
	}

	/**
	 * Verifica se o personagem está em cima de um item ou bloco que altere seus atributos
	 */
	public void checkObjectCollision() {		
		int leftWorldX = xWorld + bounds.x;
		int rightWorldX = xWorld + bounds.x + bounds.width;
		int bottomWorldY = yWorld + bounds.y + bounds.height;

		int leftColX = leftWorldX/gp.TILESIZE;
		int rightColX = rightWorldX/gp.TILESIZE;
		int bottomRowY = bottomWorldY/gp.TILESIZE;

		//Verifica se o personagem está em um arbusto
		if(gp.map.mapTile.construcTilePosition[leftColX][bottomRowY] == 70 || gp.map.mapTile.construcTilePosition[rightColX][bottomRowY] == 70) {
			currentSpeed = DEFAULTSPEED/4;
		} else {
			currentSpeed = DEFAULTSPEED;
		}
	}
}
