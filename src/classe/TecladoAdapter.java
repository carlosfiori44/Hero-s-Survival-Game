package classe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe utilizada para mover o personagem conforme as teclas são pressionadas
 */
public class TecladoAdapter implements KeyListener{
	//Player player;	
	public boolean cima, baixo, esquerda, direita;
	
	public TecladoAdapter() {
		//this.player = player;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//player.pressionarTecla(e);
			
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: 
			cima = true; 
			break;
		case KeyEvent.VK_S: 
			baixo = true;
			break;
		case KeyEvent.VK_A: 
			esquerda = true;
			break;
		case KeyEvent.VK_D: 
			direita = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//player.soltarTecla(e);
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: 
			cima = false; 
			break;
		case KeyEvent.VK_S: 
			baixo = false;
			break;
		case KeyEvent.VK_A: 
			esquerda = false;
			break;
		case KeyEvent.VK_D: 
			direita = false;
			break;
		}		
	}
}
