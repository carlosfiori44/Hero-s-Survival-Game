package player;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import screen.GamePanel;

public class Player extends Character {
	PeripheralAdapter key;

	/**
	 * Construtor da classe Player
	 * @param key Classe que faz a leitura das teclas pressionadas para que o seja feita alguma ação
	 * @param gp Classe JPanel que todo o jogo é executado
	 */
	public Player(PeripheralAdapter key, GamePanel gp) {
		super(gp);
		this.key = key;


		xScreen = (gp.SCREENWIDTH/2) - (gp.TILESIZE/2);
		yScreen = (gp.SCREENHEIGHT/2) - (gp.TILESIZE/2);

		xWorld = 25*gp.TILESIZE;
		yWorld = 5*gp.TILESIZE;

		direction = 'd';

		bounds = new Rectangle();		
		bounds.height = 11*gp.SCALE;
		bounds.width = 8*gp.SCALE;
		bounds.x = 4*gp.SCALE;
		bounds.y = 4*gp.SCALE;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/Borg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}


	@Override
	public void update() {		
		//Verifica se objetos especificos estão na posição do personagem
		checkObjectCollision();

		if(key.down) {
			direction = 'd';
			if(checkCollision()) {
				yWorld+=currentSpeed; 
			}
		}

		if(key.up) {
			direction = 'u';
			if(checkCollision()) {
				yWorld-=currentSpeed;		
			}
		}

		if(key.left) {
			direction = 'l';

			if(checkCollision()) {
				xWorld-=currentSpeed; 	
			}
		}

		if(key.right) {
			direction = 'r';

			if(checkCollision()) {
				xWorld+=currentSpeed; 	
			}
		}

		if(key.action) {
			for(int i = 0; i < gp.item.length; i++) {
				if(gp.item[i] != null && gp.item[i].findItem(new Rectangle(xWorld, yWorld, gp.TILESIZE, gp.TILESIZE))) {
					if(addItem(gp.item[i])) {
						gp.item[i] = null;
					}
				}
			}
		}

		//Váriaveis de atualização das imagens de animação do personagem
		if(key.down || key.up || key.right || key.left) {
			spriteCounter++;
			if(spriteCounter > 8) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if(spriteNum == 2) {
					spriteNum = 3;
				} else if(spriteNum == 3) {
					spriteNum = 4;
				}else if(spriteNum == 4) {
					spriteNum = 5;
				}else if(spriteNum == 5) {
					spriteNum = 2;
				}				
				spriteCounter = 0;
			}
		}
	}
}
