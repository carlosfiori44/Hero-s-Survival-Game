package player;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Game.GamePanel;

/**
 * Classe utilizada para mover o personagem conforme as teclas são pressionadas
 */
public class PeripheralAdapter implements KeyListener {
	//Declarando atributos relacionados a movimentação do player
	public boolean up, down, left, right, action, esc;	
	//Váriavel do painel que executa o jogo
	private GamePanel gp;
	
	public PeripheralAdapter(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {			
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: 
			up = true; 
			break;
		case KeyEvent.VK_S: 
			down = true;
			break;
		case KeyEvent.VK_A: 
			left = true;
			break;
		case KeyEvent.VK_D: 
			right = true;
			break;
		case KeyEvent.VK_E:
			action = true;
			break;
		case KeyEvent.VK_ESCAPE:
			if(gp.gameState == gp.PLAYSCREEN) {
				gp.gameState = gp.PAUSESCREEN;
			} else if(gp.gameState == gp.PAUSESCREEN){
				gp.gameState = gp.PLAYSCREEN;
			}			
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: 
			up = false; 
			break;
		case KeyEvent.VK_S: 
			down = false;
			break;
		case KeyEvent.VK_A: 
			left = false;
			break;
		case KeyEvent.VK_D: 
			right = false;
			break;
		case KeyEvent.VK_E:
			action = false;
			break;
		case KeyEvent.VK_ESCAPE:
			esc = false;
			break;
		}		
	}
}
