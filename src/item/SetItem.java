package item;

import fase.GamePanel;

public class SetItem {
	private GamePanel gp;
	
	public SetItem(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setItem(){
		gp.item[0] = new Item_Sword();
		gp.item[0].positionX = 250;
		gp.item[0].positionY = 280;
	}
}
