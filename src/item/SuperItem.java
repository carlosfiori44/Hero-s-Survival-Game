package item;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import screen.GamePanel;

public class SuperItem {
	public BufferedImage image, image1, image2;
	protected String name;
	protected boolean collision = false;
	protected int worldX, worldY;
	protected GamePanel gp;
	
	public void draw(Graphics2D g2) {	
		//Pega as coordenadas atuais da janela, que é onde o player está no mapa
		int	screenX = worldX - gp.player.xWorld + gp.player.xScreen;
		int	screenY = worldY - gp.player.yWorld + gp.player.yScreen;

		//Verifica se o bloco esta dentro dos limites da janela para que ele não renderize o mapa inteiro e economize desempenho
		if(worldX + gp	.TILESIZE > gp.player.xWorld - gp.player.xScreen && worldX - gp.TILESIZE < gp.player.xWorld + gp.player.xScreen &&
				worldY + gp.TILESIZE > gp.player.yWorld - gp.player.yScreen && worldY - gp.TILESIZE < gp.player.yWorld + gp.player.yScreen) {
			g2.drawImage(image, screenX, screenY, gp.TILESIZE/2, gp.TILESIZE/2, null);
		}
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
		return new Rectangle(worldX, worldY, gp.TILESIZE/2, gp.TILESIZE/2);
	}
	
	//Getters e setters
	public void setPositionX(int positionX) {
		this.worldX = positionX;
	}

	public void setPositionY(int positionY) {
		this.worldY = positionY;
	}
}
