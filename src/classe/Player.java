package classe;

import java.awt.Graphics2D;

public class Player extends Character {
	//private int dx, dy;
	TecladoAdapter key;
	private int velocidade = 1;

	public Player(int atualX, int atualY, TecladoAdapter key) {
		super(atualX, atualY, "images\\character\\protagonista.png");
		this.key = key;
	}

	/**
	 * Faz com que o personagem se mova pela tela com base na tecla que está sendo pressionada
	 */
	public void update() {
		int yPlayer = y + altura;
		int xPlayerLeft = x;
		int xPlayerRight = x + largura;
		
				
		if(key.baixo && Mapa.isWalkable(xPlayerLeft, yPlayer+velocidade) && Mapa.isWalkable(xPlayerRight, yPlayer+velocidade)) {
			y+=velocidade; 
		} 
		if(key.cima && Mapa.isWalkable(xPlayerLeft, yPlayer-velocidade) && Mapa.isWalkable(xPlayerRight, yPlayer-velocidade)) {
			y-=velocidade; 
		}		
		if(key.esquerda && Mapa.isWalkable(xPlayerLeft+velocidade, yPlayer+velocidade)) {
			x-=velocidade; 
		}
		if(key.direita && Mapa.isWalkable(xPlayerRight-velocidade, yPlayer)) {
			x+=velocidade; 
		}
	}

	/**
	 * Atualiza o posição do atual 'frame' do personagem dentro do JFrame
	 * @param g2 Objeto gráfico para atualizar no JPanel
	 */
	public void draw(Graphics2D g2) {
		g2.drawImage(imagem, x, y, null);
	}

}
