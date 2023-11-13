package classe;

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

import item.SuperItem;

public class Player extends Character {
	PeripheralAdapter key;
	//Velocidade do personagem
	private final int VELOCIDADE = 1;
	//Imagens de cada posição do personagem(cima, baixo, direita, esquerda)
	private BufferedImage down, down1, down2, up, up1, up2, right, right1, right2, left, left1, left2;
	//Qual direção o personagem está virado
	private char direction = 'd';
	//Contagem para alteração da animação do personagem andando
	private int spriteNum = 1, spriteCounter = 0;
	//Definindo inventário do personagem
	private List<SuperItem> item = new ArrayList<SuperItem>();

	//Construtor
	public Player(PeripheralAdapter key) {
		this.key = key;

		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		solidArea = new Rectangle();
	}

	@Override
	public void load() {		
		//Carreangdo as imagens de todas as direções do player 
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
	}
 
	/**
	 * Faz a atualização das coordenadas do personagem de acordo com a velocidade definida e verificar se é um
	 * caminho de fato e atualiza as 'sprites' referentes a imagem do personagem andando
	 */
	public void update()  {
		int yPlayer = yScreen + altura;
		int xPlayerLeft = xScreen;
		int xPlayerRight = xScreen + largura;
		
		
		if(key.down && Mapa.isWalkable(xPlayerLeft, yPlayer+VELOCIDADE) && Mapa.isWalkable(xPlayerRight, yPlayer+VELOCIDADE)) {
			yScreen+=VELOCIDADE; 
			direction = 'd';
		} 
		if(key.up && Mapa.isWalkable(xPlayerLeft, yPlayer-VELOCIDADE) && Mapa.isWalkable(xPlayerRight, yPlayer-VELOCIDADE)) {
			yScreen-=VELOCIDADE; 
			direction = 'u';
		}		
		if(key.left && Mapa.isWalkable(xPlayerLeft+VELOCIDADE, yPlayer+VELOCIDADE)) {
			xScreen-=VELOCIDADE; 
			direction = 'l';
		}
		if(key.right && Mapa.isWalkable(xPlayerRight-VELOCIDADE, yPlayer)) {
			xScreen+=VELOCIDADE; 
			direction = 'r';
		}
	
		//Váriaveis de atualização das imagens de animação do personagem
		if(key.down || key.up || key.right || key.left) {
			spriteCounter++;
			if(spriteCounter > 5) {
				if(spriteNum == 1) {
					spriteNum = 2;
				} else if(spriteNum == 2) {
					spriteNum = 1;
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
				image = up;
			}
			if(spriteNum == 2) {
				image = up1;
			}
			if(spriteNum == 3) {
				image = up2;
			}
			break;
		case 'd': 
			if(spriteNum == 1) {
				image = down;
			}
			if(spriteNum == 2) {
				image = down1;
			}
			if(spriteNum == 3) {
				image = down2;
			}
			break;
		case 'l': 
			if(spriteNum == 1) {
				image = left;
			}	
			if(spriteNum == 2) {
				image = left1;
			}
			if(spriteNum == 3) {
				image = left2;
			}
			break;
		case 'r':
			if(spriteNum == 1) {
				image = right;
			}
			if(spriteNum == 2) {
				image = right1;
			}
			if(spriteNum == 3) {
				image = right2;
			}
			break;
		}		
		
		g2.drawImage(image, xScreen, yScreen, null);
		showItem(g2);
	}
	
	/**
	 * Mostra os itens dentro do invetário do personagem
	 * @param g2 recebe o componente gráfico do tipo Graphics2D para adicionalo a tela
	 */
	public void showItem(Graphics2D g2) {
		for(SuperItem i : item){
			int wid = i.getImage().getWidth()+2;
			
			i.setPositionX(1);
			i.setPositionY(42);
			

			g2.setColor(Color.gray);
			g2.fillRect(0, 41+i.getImage().getWidth(), wid, wid);
			i.draw(g2);
		}
	}
	
	/**
	 * Adiciona o item que o usuário pegou próximo a ele
	 * @param item item que o usuário pegou
	 */
	public void addItem(SuperItem item) {
		if(this.item.size() != 5) {
			this.item.add(item);	
			return;			
		}

		System.out.println("Inventário cheio");
	}
}
