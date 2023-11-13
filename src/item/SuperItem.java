package item;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import fase.GamePanel;

public class SuperItem {
	protected BufferedImage image;
	protected String name;
	protected boolean collision = false;
	protected int positionX, positionY;
	
	public void draw(Graphics2D g2) {
		g2.drawImage(image, positionX, positionY, null);
	}
	
	/**
	 * Verifica se o player está próximo ao item
	 * @param player Retangulo do personagem, que sinaliza sua localização e tamanho
	 * @return verdadeiro ou falso caso o personagem esteja próximo ao item
	 */
	public boolean findItem(Rectangle player) {
		boolean retorno;
		
		if(player.intersects(getBounds())) {
			retorno = true;
		} else {
			retorno = false;
		}		
		
		return retorno;
	}
	
	
	/**
	 * Cria um retangulo com a posição e area do item 
	 * @return Retorna um retangulo referente ao item
	 */
	public Rectangle getBounds() {
		return new Rectangle(positionX, positionY, image.getWidth(), image.getHeight());
	}
	
	//Getters e setters
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public BufferedImage getImage() {
		return image;
	}
}
