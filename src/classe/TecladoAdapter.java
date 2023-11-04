package classe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe utilizada para mover o personagem conforme as teclas são pressionadas
 */
public class TecladoAdapter implements KeyListener{
	//Declarando atributos relacionados a movimentação do player
	public boolean up, down, left, right;
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//player.pressionarTecla(e);
			
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
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//player.soltarTecla(e);
		
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
		}		
	}
}
