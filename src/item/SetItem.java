package item;

import Game.GamePanel;

public class SetItem {
	private GamePanel gp;
	
	public SetItem(GamePanel gp) {
		this.gp = gp;
	}
	
	/**
	 * Define a posição do item na tela
	 */
	public void setItem(){
		gp.item[0] = new Item_Sword();
		gp.item[0].worldX = 27*16*5;
		gp.item[0].worldY = 2*16*5;

	}
}
