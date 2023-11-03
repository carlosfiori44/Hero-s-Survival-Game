package classe;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Character {
	//Declarando atributos utilizados em cada personagem
	private int x, y, dx, dy;
	private final int VELOCIDADE = 1;
	//Atributo que vai receber a imagem do personagem
	private Image imagem;
	//Respectivos tamanho da imagem do personagem
	private int altura, largura;
	
	/**
	 * Método construtor utilizado na instanciação da classe
	 * @param atualX coordenada X onde o personagem vai aparecer
	 * @param atualY coordenada Y onde o personagem vai aparecer
	 */
	public Character(int atualX, int atualY) {
		this.x = atualX;
		this.y = atualY;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
	
	/**
	 * Método utilizado para definir a imagem do personagem
	 */
	public void load() {
		ImageIcon referencia = new ImageIcon("images\\character\\protagonista.png");
		imagem = referencia.getImage();
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	/**
	 * Método que atualiza a posição do personagem na tela
	 */
	public void update() {
		x += dx;
		y += dy;
	}
	
	/**
	 * Pega os limites de tamanho da imagem do personagem
	 * @return retorna os tamnhos e localização da imagem do personagem no tipo Retangulo
	 */
	public Rectangle limiteForma() {
		return new Rectangle(x, y, largura, altura);
	}
	
	/**
	 * Faz com que o personagem se mova pela tela com base na tecla que está sendo pressionada
	 * @param tecla recebe a tecla que está sendo apertada no momento
	 */
	public void pressionarTecla(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		switch (codigo) {
		case KeyEvent.VK_W: dy=-VELOCIDADE; break;
		case KeyEvent.VK_S: dy=VELOCIDADE; break;
		case KeyEvent.VK_A: dx=-VELOCIDADE; break;
		case KeyEvent.VK_D: dx=VELOCIDADE; break;
		}
	}
	
	/**
	 * Redefine os atributos de movimentação do jogador para 0, para que ele não se mexa após soltar a tecla
	 * @param tecla recebe a tecla que está sendo apertada no momento
	 */
	public void soltarTecla(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		switch (codigo) {
		case KeyEvent.VK_W: dy=0; break;
		case KeyEvent.VK_S: dy=0; break;
		case KeyEvent.VK_A: dx=0; break;
		case KeyEvent.VK_D: dx=0; break;
		}
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
