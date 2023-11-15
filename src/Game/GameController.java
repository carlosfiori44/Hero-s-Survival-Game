package Game;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import classe.Player;
import item.SuperItem;
import map.MapController;
import map.MapTiles;

public class GameController {
	//Definindo o atributos das imagens de fundo
	private BufferedImage menuInitial; 
	private ImageIcon imagePlay, imagePlaySelected;
	private JButton btPlay;
	private Rectangle btPlayBounds;
	private Player player;
	private MapController map;
	private GamePanel gp;
	private SuperItem item[];
	private boolean dispose = false;
	private MapTiles tile;

	public GameController(MapController map, Player player, SuperItem item[], GamePanel gp) {
		this.map = map;
		this.player = player;
		this.gp = gp;
		this.item = item;
		tile = new MapTiles(player);
				
		//Carrega as imagens referentes a menu, seja pausa ou menu principal
		try {
			menuInitial = ImageIO.read(getClass().getResource("/startScreen/title.png"));
			imagePlay = new ImageIcon("res//startScreen//btJogar.png");
			imagePlaySelected = new ImageIcon("res//startScreen//btJogar.png");
			btPlayBounds = new Rectangle(350, 280, imagePlay.getImage().getWidth(null), imagePlay.getImage().getHeight(null));	

			btPlay = new JButton(imagePlay);
			btPlay.setBounds(btPlayBounds);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que mostra o menu inicial do jogo
	 */
	private void menu() {	
		btPlay.addActionListener(new ActionListener() {					
			@Override
			public void actionPerformed(ActionEvent e) {
				map.map = map.MAP_ISLAND;
				gp.gameState = gp.PLAYSCREEN;
				gp.remove(btPlay);				
			}
		});		
		gp.add(btPlay);
	}

	/**
	 * Mostra a tela de pause quando o jogador está jogando
	 */
	private void drawPause(Graphics2D g2) {
		String text = "PAUSED";
		g2.setColor(Color.BLACK);
		g2.setFont(new Font(null, Font.BOLD, 80));
		g2.drawString("PAUSE", (gp.getWidth() - (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth())/2, 
				(gp.getHeight() - (int)g2.getFontMetrics().getStringBounds(text, g2).getHeight())/2);		
	}

	/**
	 * Coloca no JFrame o mapa atual em que o personagem está
	 * @param g2 Objeto gráfico para atualizar no JPanel
	 */
	public boolean draw(Graphics2D g2) {
		//Verifica se o estado do jogo é o menu inicial
		if(gp.gameState == gp.TITLESCREEN) {			
			g2.drawImage(menuInitial, 0, 0, null);
			menu();
			dispose = false;			
		} 
		
		//Verifica se o estado do jogo é no modo jogável
		if(gp.gameState == gp.PLAYSCREEN) {		
			//map.draw(g2);
			tile.draw(g2);			
			
			/*for(int i = 0; i < item.length; i++) {
				if(item[i] != null) {
					item[i].draw(g2);
				}
			}*/
			
			player.draw(g2);			
			
			dispose = true;
		}

		//Verifica se o jogo está pausado
		if(gp.gameState == gp.PAUSESCREEN) {
			drawPause(g2);
			dispose = false;				
		}

		return dispose;
	}
}


