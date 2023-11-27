package player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game.GamePanel;
import item.SuperItem;
import map.MapController;

public class Player extends Character {
	PeripheralAdapter key;
	//Imagens de cada posição do personagem(cima, baixo, direita, esquerda)
	private BufferedImage down0, down1, down2, down3, down4;
	private BufferedImage up0, up1, up2, up3, up4;
	private BufferedImage right0, right1, right2, right3, right4;
	private BufferedImage left0, left1, left2, left3, left4;
	//Qual direção o personagem está virado
	private char direction;
	//Contagem para alteração da animação do personagem andando
	private int spriteNum = 1, spriteCounter = 0;

	/**
	 * Construtor da classe Player
	 * @param key Classe que faz a leitura das teclas pressionadas para que o seja feita alguma ação
	 * @param gp Classe JPanel que todo o jogo é executado
	 */
	public Player(PeripheralAdapter key, GamePanel gp) {
		this.key = key;
		this.gp = gp;

		xScreen = (gp.SCRRENWIDTH/2) - (gp.TILESIZE/2);
		yScreen = (gp.SCREENHEIGHT/2) - (gp.TILESIZE/2);

		xWorld = 25*16*5;
		yWorld = 3*16*5;

		direction = 'd';

		bounds = new Rectangle();		
		bounds.height = 11*gp.SCALE;
		bounds.width = 8*gp.SCALE;
		bounds.x = 4*gp.SCALE;
		bounds.y = 4*gp.SCALE;

		try {
			//this.image = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
			this.image = ImageIO.read(getClass().getResourceAsStream("/player/Borg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void load() {	
		//Carreangdo as imagens de todas as direções do player 
		down0 = image.getSubimage(0, 0, 16, 16);
		down1 = image.getSubimage(16, 0, 16, 16);
		down2 = image.getSubimage(32, 0, 16, 16);	
		down3 = image.getSubimage(48, 0, 16, 16);	
		down4 = image.getSubimage(64, 0, 16, 16);	

		up0 = image.getSubimage(0, 16, 16, 16);
		up1 = image.getSubimage(16, 16, 16, 16);
		up2 = image.getSubimage(32, 16, 16, 16);
		up3 = image.getSubimage(48, 16, 16, 16);
		up4 = image.getSubimage(64, 16, 16, 16);

		left0 = image.getSubimage(0, 32, 16, 16);
		left1 = image.getSubimage(16, 32, 16, 16);
		left2 = image.getSubimage(32, 32, 16, 16);		
		left3 = image.getSubimage(48, 32, 16, 16);	
		left4 = image.getSubimage(64, 32, 16, 16);	

		right0 = image.getSubimage(0, 48, 16, 16);	
		right1 = image.getSubimage(16, 48, 16, 16);	
		right2 = image.getSubimage(32, 48, 16, 16);	
		right3 = image.getSubimage(48, 48, 16, 16);	
		right4 = image.getSubimage(64, 48, 16, 16);	

		//Animações da imagem 'player.png
		/*
		altura = image.getSubimage(91, 131, 10, 16).getHeight();
		largura = image.getSubimage(91, 131, 10, 16).getWidth();

		down = image.getSubimage(91, 131, 10, 16);
		down1 = image.getSubimage(77, 131, 10, 16);
		down2 = image.getSubimage(107, 131, 10, 16);	

		up = image.getSubimage(91, 150, 10, 16);
		up1 = image.getSubimage(78, 150, 10, 16);
		up2 = image.getSubimage(106, 150, 10, 16);

		right = image.getSubimage(92, 169, 10, 16);
		right1 = image.getSubimage(77, 169, 10, 16);
		right2 = image.getSubimage(105, 169, 10, 16);		

		//Classes e métodos que fazem a imagem do personagem indo para a direita inverter
		//e assim animar como se estivesse indo para a esquerda
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-largura, 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        left = op.filter(right, null);
        left1 = op.filter(right1, null);
        left2 = op.filter(right2, null);     
		 */
	}

	/**
	 * Faz a atualização das coordenadas do personagem de acordo com a velocidade definida e verificar se é um
	 * caminho de fato e atualiza as 'sprites' referentes a imagem do personagem andando
	 */
	public void update() {		
		//Verifica se objetos especificos estão na posição do personagem
		checkObjectCollision();
		
		if(key.down) {
			direction = 'd';

			if(checkCollision(direction)) {
				yWorld+=currentSpeed; 
			}
		}

		if(key.up) {
			direction = 'u';

			if(checkCollision(direction)) {
				yWorld-=currentSpeed;		
			}
		}

		if(key.left) {
			direction = 'l';

			if(checkCollision(direction)) {
				xWorld-=currentSpeed; 	
			}
		}

		if(key.right) {
			direction = 'r';

			if(checkCollision(direction)) {
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

	@Override
	public void draw(Graphics2D g2) {
		switch(direction) {
		case 'u':
			if(spriteNum == 1) {
				image = up0;
			}
			if(spriteNum == 2) {
				image = up1;
			}
			if(spriteNum == 3) {
				image = up2;
			}
			if(spriteNum == 4) {
				image = up3;
			}
			if(spriteNum == 5) {
				image = up4;
			}
			break;
		case 'd': 
			if(spriteNum == 1) {
				image = down0;
			}
			if(spriteNum == 2) {
				image = down1;
			}
			if(spriteNum == 3) {
				image = down2;
			}
			if(spriteNum == 4) {
				image = down3;
			}
			if(spriteNum == 5) {
				image = down4;
			}
			break;
		case 'l': 
			if(spriteNum == 1) {
				image = left0;
			}	
			if(spriteNum == 2) {
				image = left1;
			}
			if(spriteNum == 3) {
				image = left2;
			}
			if(spriteNum == 4) {
				image = left3;
			}
			if(spriteNum == 5) {
				image = left4;
			}
			break;
		case 'r':
			if(spriteNum == 1) {
				image = right0;
			}
			if(spriteNum == 2) {
				image = right1;
			}
			if(spriteNum == 3) {
				image = right2;
			}
			if(spriteNum == 4) {
				image = right3;
			}
			if(spriteNum == 5) {
				image = right4;
			}
			break;
		}		

		g2.drawImage(image, xScreen, yScreen, gp.TILESIZE, gp.TILESIZE, null);
		showItem(g2);
	}		
}
