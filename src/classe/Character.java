package classe;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Character {
	//Declarando atributos utilizados em cada personagem
	protected int x, y;
	//Atributo que vai receber a imagem do personagem
	protected Image imagem;
	protected String diretorioImagem;
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
		this.diretorioImagem = source;
	}
	
	/**
	 * Cria um Retangulo que condiz com a localização e tamanho da imagem do Character
	 * @return retorna um retangulo com a localização e tamanho da imagem do character
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
	
	/**
	 * Método utilizado para definir a imagem do personagem
	 */
	public void load() {
		ImageIcon referencia = new ImageIcon(diretorioImagem);
		imagem = referencia.getImage();
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
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
		return imagem;
	}
	
	
}
