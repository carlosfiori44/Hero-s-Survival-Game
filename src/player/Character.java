package player;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;

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

		int tile1, tile2;
		
		boolean collision = true;
		
		switch(direction) {
		case 'u': 
			topRowY = (topWorldY - VELOCIDADE)/gp.TILESIZE;
			tile1 = gp.map.mapTile.mapTilePosition[leftColX][topRowY];
			tile2 = gp.map.mapTile.mapTilePosition[rightColX][topRowY];
			
			if(gp.map.mapTile.mapTile[tile1].collision == true || gp.map.mapTile.mapTile[tile2].collision == true) {
				collision = false;
			}			
			break;
		case 'd':
			bottomRowY = (bottomWorldY + VELOCIDADE)/gp.TILESIZE;
			tile1 = gp.map.mapTile.mapTilePosition[leftColX][bottomRowY];
			tile2 = gp.map.mapTile.mapTilePosition[rightColX][bottomRowY];
			
			if(gp.map.mapTile.mapTile[tile1].collision == true || gp.map.mapTile.mapTile[tile2].collision == true) {
				collision = false;
			}	
			break;
		case 'l':
			leftColX = (leftWorldX - VELOCIDADE)/gp.TILESIZE;
			tile1 = gp.map.mapTile.mapTilePosition[leftColX][topRowY];
			tile2 = gp.map.mapTile.mapTilePosition[leftColX][bottomRowY];
			
			if(gp.map.mapTile.mapTile[tile1].collision == true || gp.map.mapTile.mapTile[tile2].collision == true) {
				collision = false;
			}	
			break;
		case 'r':
			rightColX = (rightWorldX + VELOCIDADE)/gp.TILESIZE;
			tile1 = gp.map.mapTile.mapTilePosition[rightColX][topRowY];
			tile2 = gp.map.mapTile.mapTilePosition[rightColX][bottomRowY];
			
			if(gp.map.mapTile.mapTile[tile1].collision == true || gp.map.mapTile.mapTile[tile2].collision == true) {
				collision = false;
			}	
			break;
		}

		return collision;
	}
}
