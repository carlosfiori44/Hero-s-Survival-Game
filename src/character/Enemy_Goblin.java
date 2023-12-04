package character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import screen.GamePanel;

public class Enemy_Goblin extends Character {

	public Enemy_Goblin(GamePanel gp) {
		super(gp);

		//Definindo a quantidade de imagens de movimentação
		moviment = new BufferedImage[4][5];
		attackMoviment = new BufferedImage[4][3];

		bounds = new Rectangle(5*gp.SCALE, 5*gp.SCALE, 6*gp.SCALE, 11*gp.SCALE);	
		
		maxLife = 16;

		//Definindo a imagem do personagem
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/enemy/ClubGoblin.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void draw(Graphics2D g2) {

		if(direction == 'd') {
			if(checkCollision()) {
				yWorld+=currentSpeed/2; 			
			}
		}

		super.draw(g2);

		//Pega as coordenadas atuais da janela, que é onde o player está no mapa
		int	screenX = xWorld - gp.player.xWorld + gp.player.xScreen;
		int	screenY = yWorld - gp.player.yWorld + gp.player.yScreen;

		//Verifica se o bloco esta dentro dos limites da janela para que ele não renderize o mapa inteiro e economize desempenho
		if(xWorld + gp.TILESIZE > gp.player.xWorld - gp.player.xScreen && xWorld - gp.TILESIZE < gp.player.xWorld + gp.player.xScreen &&
				yWorld + gp.TILESIZE > gp.player.yWorld - gp.player.yScreen && yWorld - gp.TILESIZE < gp.player.yWorld + gp.player.yScreen) {
			g2.drawImage(currentImage, screenX, screenY, gp.TILESIZE, gp.TILESIZE, null);
		}
	}

	@Override
	public void update() {		
		super.update();
		moveNPC();
	}
}
