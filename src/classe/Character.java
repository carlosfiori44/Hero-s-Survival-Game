package classe;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character {
	//Declarando atributos utilizados em cada personagem
	public int xScreen, yScreen, xWorld, yWorld;
	//Atributo que vai receber a imagem do personagem
	protected BufferedImage image;
	//Respectivos tamanho da imagem do personagem
	protected int altura, largura;
	//Area que o personagem vai ocupar na tela
	protected Rectangle solidArea;
	
	/**
	 * Método utilizado para definir todas as imagens do personagem
	 */
	public void load() {	
	}
		
	/**
	 * Pega os limites de tamanho da imagem do personagem
	 * @return retorna os tamnhos e localização da imagem do personagem no tipo Retangulo
	 */
	public Rectangle limiteForma() {
		return new Rectangle(xScreen, yScreen, largura, altura);
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
}
