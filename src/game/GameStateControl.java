package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import map.MapController;

public class GameStateControl {
	//Definindo o atributos das imagens de fundo
	public BufferedImage menuInitial; 
	private ImageIcon imagePlay;
	private JButton btPlay;
	private Rectangle btPlayBounds;
	private MapController map;
	private GamePanel gp;

	public GameStateControl(MapController map, GamePanel gp) {
		this.map = map;
		this.gp = gp;
				
		//Carrega as imagens referentes a menu, seja pausa ou menu principal
		try {
			menuInitial = ImageIO.read(getClass().getResource("/startScreen/title.png"));
			imagePlay = new ImageIcon("res//startScreen//btJogar.png");
			btPlayBounds = new Rectangle(gp.SCREENWIDTH/2, gp.SCREENHEIGHT/2, imagePlay.getImage().getWidth(null), imagePlay.getImage().getHeight(null));	

			btPlay = new JButton(imagePlay);
			btPlay.setBounds(btPlayBounds);
			
			//Chama o menu inicial
			menu();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que mostra o menu inicial do jogo
	 */
	public void menu() {	
		btPlay.addActionListener(new ActionListener() {					
			@Override
			public void actionPerformed(ActionEvent e) {
				map.map = map.MAP_ISLAND;
				gp.gameState = gp.PLAYSCREEN;
				gp.removeAll();				
			}
		});		
		gp.add(btPlay);
	}

	/**
	 * Mostra a tela de pause quando o jogador está jogando
	 */
	public void drawPause(Graphics2D g2) {
		String text = "PAUSED";
		g2.setColor(Color.BLACK);
		g2.setFont(new Font(null, Font.BOLD, 80));
		g2.drawString("PAUSE", (gp.getWidth() - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth())/2, 
				(gp.getHeight() - (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight())/2);		
	}
}


