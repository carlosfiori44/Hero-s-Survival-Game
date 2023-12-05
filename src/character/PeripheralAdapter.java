package character;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import screen.GamePanel;

/**
 * Classe utilizada para mover o personagem conforme as teclas são pressionadas
 */
public class PeripheralAdapter implements KeyListener {
	//Declarando atributos relacionados a movimentação do player
	public boolean up, down, left, right, action, attack, esc;	
	//Váriavel do painel que executa o jogo
	private GamePanel gp;

	public PeripheralAdapter(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Caso esteja em modo de jogo
		if(gp.gameState == gp.PLAYSCREEN) {
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
			case KeyEvent.VK_F:
				attack = true;
				break;				
			case KeyEvent.VK_ESCAPE:
				gp.gameState = gp.PAUSESCREEN;			
				break;
			}

			//Caso esteja no modo de pause
		} else if(gp.gameState == gp.PAUSESCREEN) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W: 
				if(gp.ui.option != 0){
					gp.ui.option--;
				} else {
					gp.ui.option = 2;
				}
				break;
			case KeyEvent.VK_S: 
				if(gp.ui.option != 2){
					gp.ui.option++;
				} else {
					gp.ui.option = 0;
				}				
				break;			
			case KeyEvent.VK_ESCAPE:
				gp.gameState = gp.PLAYSCREEN;
				gp.ui.option = 0;
				break;
			case KeyEvent.VK_ENTER:
				if(gp.ui.option == 0) {
					//Retomar o jogo
					gp.gameState = gp.PLAYSCREEN;
				} else if(gp.ui.option == 1) {
					//Salvamento do jogo
				} else if(gp.ui.option == 2) {
					//Sair do jogo
					System.exit(0);
				}
				break;
			}			
			//Caso esteja na tela de inicio
		} else if(gp.gameState == gp.TITLESCREEN) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W: 
				if(gp.ui.option != 0){
					gp.ui.option--;
				} else {
					gp.ui.option = 2;
				}
				break;
			case KeyEvent.VK_S: 
				if(gp.ui.option != 2){
					gp.ui.option++;
				} else {
					gp.ui.option = 0;
				}				
				break;			
			case KeyEvent.VK_ENTER:
				if(gp.ui.option == 0) {
					//Inicia o jogo
					gp.gameState = gp.PLAYSCREEN;
				} else if(gp.ui.option == 1) {
					//Carrega um salvamento do jogo
				} else if(gp.ui.option == 2) {
					//Sair do jogo
					System.exit(0);
				}
				break;
			}
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
		case KeyEvent.VK_F:
			attack = false;
			break;
		case KeyEvent.VK_ESCAPE:
			esc = false;
			break;
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}
}
