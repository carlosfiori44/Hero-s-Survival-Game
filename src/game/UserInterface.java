package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UserInterface {

	GamePanel gp;
	Font defaultArial;
	private String message = "";
	private boolean messageVisible = false;
	public boolean gameEnd = false; 
	private int messageTimer = 0;
	private double timer = 0; 
	private DecimalFormat timerFormat = new DecimalFormat("#0");


	/**
	 * Constutor da classe UserInterface
	 * @param gp Recebe o JPanel principal
	 */
	public UserInterface(GamePanel gp) {
		this.gp = gp;

		defaultArial = new Font("Arial", Font.PLAIN, 40);
	}

	/**
	 * Define uma mensagem que é notificada na tela
	 * @param message Mensagem a ser notificada
	 */
	public void setMessage(String message) {
		this.message = message;
		messageVisible = true;
	}

	/**
	 * Desenha mensagens dentro do JFrame
	 * @param g2 Objeto do componente gráfico do tipo Graphics2D
	 */
	public void draw(Graphics2D g2) {	
		if(gameEnd) {
			//Tela de fim de jogo
			g2.setColor(Color.black);
			g2.fillRect(0, 0, gp.SCREENWIDTH, gp.SCREENHEIGHT);
			
			String endGame = "Fim de jogo!";
			String thanks = "Obrigado por jogar!";
			
			g2.setFont(defaultArial);
			g2.setColor(Color.ORANGE);
			g2.drawString(endGame, (gp.SCREENWIDTH)/2 - (int) g2.getFontMetrics().getStringBounds(endGame, g2).getWidth()/2, gp.TILESIZE*2);
			
			g2.setFont(defaultArial);
			g2.drawString(thanks, (gp.SCREENWIDTH)/2 - (int) g2.getFontMetrics().getStringBounds(thanks, g2).getWidth()/2, gp.TILESIZE*3);
			
			gp.player.setPosition(gp.SCREENWIDTH/2 - gp.player.getBounds().width, gp.TILESIZE*4);
			
			gp.player.draw(g2);
			
			gp.gameThread = null;
			
		} else {
			//Informação sobre a quantidade de itens do jogador
			g2.setFont(defaultArial);
			g2.setColor(Color.white);
			g2.drawString("Itens: " + gp.player.item.size(), 0, 40);

			//Temporizador do jogo
			timer += (double) 1/60;
			g2.drawString("Tempo: " + timerFormat.format(timer), (int) (gp.SCREENWIDTH - g2.getFontMetrics().getStringBounds("Tempo: " + timerFormat.format(timer), g2).getWidth()*1.5), 40);
			
			//Mesnagem que aparece na tela 
			if(messageVisible) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, (gp.SCREENWIDTH)/2 - (int) g2.getFontMetrics().getStringBounds(message, g2).getWidth()/2, gp.TILESIZE*2);

				messageTimer++;

				if(messageTimer > 120) {
					messageTimer = 0;
					messageVisible = false;
				}
			}			
		}		
	}
}
