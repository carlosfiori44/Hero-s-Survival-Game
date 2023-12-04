package character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import item.SuperItem;
import screen.GamePanel;

/**
 * Classe generalizada de Character que representa o jogador principal 
 */
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

		//Definindo a quantidade de imagens de movimentação
		moviment = new BufferedImage[4][5];
		attackMoviment = new BufferedImage[4][6];		

		xScreen = (gp.SCREENWIDTH/2) - (gp.TILESIZE/2);
		yScreen = (gp.SCREENHEIGHT/2) - (gp.TILESIZE/2);

		xWorld = 25*gp.TILESIZE;
		yWorld = 5*gp.TILESIZE;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/Borg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void draw(Graphics2D g2) {		
		super.draw(g2);
		g2.drawImage(currentImage, xScreen, yScreen, gp.TILESIZE, gp.TILESIZE, null);
	}

	@Override
	public void update() {		
		//Verifica se objetos especificos estão na posição do personagem
		checkObjectCollision();		
				
		if(key.down) {
			direction = 'd';
			if(checkCollision() && checkCharacterCollision()) {
				yWorld+=currentSpeed; 				
			}
		}

		if(key.up) {
			direction = 'u';
			if(checkCollision() && checkCharacterCollision()) {
				yWorld-=currentSpeed;		
			}
		}

		if(key.left) {
			direction = 'l';
			if(checkCollision() && checkCharacterCollision()) {
				xWorld-=currentSpeed; 	
			}
		}

		if(key.right) {
			direction = 'r';
			if(checkCollision() && checkCharacterCollision()) {
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
		
		//Atualização da movimentação
		if(!attack && key.down || key.up || key.right || key.left) {
			moving = true;
		} else {
			moving = false;
		}
	
		super.update();			
	}
	
	@Override
	public void checkObjectCollision() {
		for(int i = 0; i < gp.item.length; i++) {
			if(gp.item[i] != null && gp.item[i].findItem(new Rectangle(xWorld, yWorld, gp.TILESIZE, gp.TILESIZE))) {
				if(addItem(gp.item[i])) {
					gp.item[i] = null;
					gp.ui.setMessage("Voce pegou um item!");
				} else {
					gp.ui.setMessage("Inventario cheio!");
				}
			}
		}	
		
		super.checkObjectCollision();
	}
	
	/**
	 * Adiciona o item que o usuário pegou próximo a ele
	 * @param item item que o usuário pegou
	 */	
	public boolean addItem(SuperItem item) {
		//Verifica se o inventário do personagem está cheio
		if(this.item.size() < invetorySize) {
			this.item.add(item);	
			return true;			
		}

		return false;
	}
}
