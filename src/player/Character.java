package player;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;

/**
 * Classe mãe/superclasse para todos os personagens do jogo
 */
public class Character {
	//Declarando atributos utilizados em cada personagem
	public int xScreen, yScreen, xWorld, yWorld;
	//Velocidade do personagem
	protected final int VELOCIDADE = 8;
	//Atributo que vai receber a imagem do personagem
	protected BufferedImage image;
	//Respectivos tamanho da imagem do personagem
	protected final int ALTURA = 16, LARGURA = 16;
	//Area que o personagem vai ocupar na tela
	protected Rectangle bounds;
	//Atributo referente ao painel da janela
	protected GamePanel gp;

	/**
	 * Método utilizado para definir todas as imagens do personagem
	 */
	public void load() {	
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
		this.xScreen = x;
		this.yScreen = y;
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
			topRowY = (topWorldY - VELOCIDADE)/gp.TILESIZE;
			
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
			bottomRowY = (bottomWorldY + VELOCIDADE)/gp.TILESIZE;
			
			tileM1 = gp.map.mapTile.mapTilePosition[leftColX][bottomRowY];
			tileM2 = gp.map.mapTile.mapTilePosition[rightColX][bottomRowY];
			
			tileC1 = gp.map.mapTile.construcTilePosition[leftColX][bottomRowY];
			tileC2 = gp.map.mapTile.construcTilePosition[rightColX][bottomRowY];
			
			if(gp.map.mapTile.mapTile[tileM1].collision || gp.map.mapTile.mapTile[tileM2].collision) {
				collision = false;
			}	
			
			if((tileC1 >= 0 && tileC1 < gp.map.mapTile.numTile)) {
				System.out.println("1: " + gp.map.mapTile.constructionTile[tileC1].collision);
				if(gp.map.mapTile.constructionTile[tileC1].collision) {
					collision = false;	
				}	
			} 
			
			if(tileC2 >= 0 && tileC2 < gp.map.mapTile.numTile) {
				System.out.println("2: " + gp.map.mapTile.constructionTile[tileC2].collision);
				if(gp.map.mapTile.constructionTile[tileC2].collision) {
					collision = false;	
				}						
				
			}	
			
			break;
			
		case 'l':
			leftColX = (leftWorldX - VELOCIDADE)/gp.TILESIZE;
			
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
			rightColX = (rightWorldX + VELOCIDADE)/gp.TILESIZE;
			
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
}
