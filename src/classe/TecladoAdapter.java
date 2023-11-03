package classe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe utilizada para mover o personagem conforme as teclas são pressionadas
 */
public class TecladoAdapter implements KeyListener{
	Character player;	
	
	public TecladoAdapter(Character player) {
		this.player = player;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		player.pressionarTecla(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.soltarTecla(e);
	}

}
