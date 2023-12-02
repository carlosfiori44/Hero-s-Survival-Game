package item;

import screen.GamePanel;

/**
 * Classe que tem o objetivo de definir a localização dos itens no mapa
 */
public class SetItem {
	private GamePanel gp;
	
	public SetItem(GamePanel gp) {
		this.gp = gp;
	}
	
	/**
	 * Define a posição do item na tela
	 */
	public void setItem(){
		gp.item[0] = new ART_BuffDamage(gp);
		gp.item[0].worldX = 27*gp.TILESIZE;
		gp.item[0].worldY = 5*gp.TILESIZE;

	}
}
