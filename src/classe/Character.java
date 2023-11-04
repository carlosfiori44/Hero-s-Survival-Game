package classe;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Character {
	//Declarando atributos utilizados em cada personagem
	protected int x, y;
	//Atributo que vai receber a imagem do personagem
	protected BufferedImage image;
	//Respectivos tamanho da imagem do personagem
	protected int altura, largura;
	
	/**
	 * Método construtor utilizado na instanciação da classe
	 * @param atualX coordenada X onde o personagem vai aparecer
	 * @param atualY coordenada Y onde o personagem vai aparecer
	 */
	public Character(int atualX, int atualY, String source) {
		this.x = atualX;
		this.y = atualY;
		
		try {
			this.image = ImageIO.read(getClass().getResource(source));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cria um Retangulo que condiz com a localização e tamanho da imagem do Character
	 * @return retorna um retangulo com a localização e tamanho da imagem do character
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
	
	/**
	 * Método utilizado para definir todas as imagens do personagem
	 */
	public void loadPlayerImage() {	
	}
		
	/**
	 * Pega os limites de tamanho da imagem do personagem
	 * @return retorna os tamnhos e localização da imagem do personagem no tipo Retangulo
	 */
	public Rectangle limiteForma() {
		return new Rectangle(x, y, largura, altura);
	}
	
	//Getters e Setters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return image;
	}
	
	
}
