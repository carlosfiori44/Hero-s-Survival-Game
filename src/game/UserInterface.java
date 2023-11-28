package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import map.MapController;

public class UserInterface {
	public GamePanel gp;
	public Graphics2D g2;
	public Font defaultFont, gameFont;
	private String message = "";
	private boolean messageVisible = false;
	public boolean gameEnd = false; 
	private int messageTimer = 0;
	private double timer = 0; 
	private DecimalFormat timerFormat = new DecimalFormat("#0");
	public BufferedImage menuInitial, btDefault, selectionDagger; 
	private ImageIcon imagePlay;
	private JButton btPlay;
	private Rectangle btPlayBounds;
	public int option;
	public Color color;

	/**
	 * Constutor da classe UserInterface
	 * @param gp Recebe o JPanel principal
	 */
	public UserInterface(GamePanel gp) {
		this.gp = gp;
		option = 0;
		
		InputStream inputStream = getClass().getResourceAsStream("/font/Dwarf Fat Regular.otf");
		try {
			gameFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(28F);
			
			inputStream = getClass().getResourceAsStream("/font/minecraft_font.ttf");
			defaultFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(40F);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Carrega as imagens referentes a menu, seja pausa ou menu principal
		try {
			menuInitial = ImageIO.read(getClass().getResource("/startScreen/title.png"));
			imagePlay = new ImageIcon("res//startScreen//btJogar.png");
			btDefault = ImageIO.read(getClass().getResource("/objects/button.png"));
			selectionDagger = ImageIO.read(getClass().getResource("/itens/dagger.png"));
			
			btPlayBounds = new Rectangle(gp.SCREENWIDTH/2, gp.SCREENHEIGHT/2, imagePlay.getImage().getWidth(null), imagePlay.getImage().getHeight(null));	
			
			
			btPlay = new JButton(imagePlay);
			btPlay.setBounds(btPlayBounds);		
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		this.g2 = g2;
		
		if(gameEnd) {
			//Tela de fim de jogo
			g2.setColor(Color.black);
			g2.fillRect(0, 0, gp.SCREENWIDTH, gp.SCREENHEIGHT);

			String endGame = "Fim de jogo!";
			String thanks = "Obrigado por jogar!";

			g2.setFont(defaultFont);
			g2.setColor(Color.ORANGE);
			g2.drawString(endGame, (gp.SCREENWIDTH)/2 - (int) g2.getFontMetrics().getStringBounds(endGame, g2).getWidth()/2, gp.TILESIZE*2);

			g2.setFont(defaultFont);
			g2.drawString(thanks, (gp.SCREENWIDTH)/2 - (int) g2.getFontMetrics().getStringBounds(thanks, g2).getWidth()/2, gp.TILESIZE*3);

			gp.player.setPosition(gp.SCREENWIDTH/2 - gp.player.getBounds().width, gp.TILESIZE*4);

			gp.player.draw(g2);

			gp.gameThread = null;

		} else if(gp.gameState == gp.PLAYSCREEN) {
			//Informação sobre a quantidade de itens do jogador
			g2.setFont(defaultFont);
			g2.setColor(Color.white);
			g2.drawString("Itens: " + gp.player.item.size(), 0, 40);

			//Temporizador do jogo
			timer += (double) 1/60;
			g2.drawString("Tempo: " + timerFormat.format(timer), (int) (gp.SCREENWIDTH - g2.getFontMetrics().getStringBounds("Tempo: " + timerFormat.format(timer), g2).getWidth()), 40);

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
		} else if(gp.gameState == gp.PAUSESCREEN) {
			drawPause();
		} else if(gp.gameState == gp.TITLESCREEN) {
			drawMenu();
		}
	}

	/**
	 * Método que mostra o menu inicial do jogo
	 */
	public void drawMenu() {	
		String text;
		int x = gp.SCREENWIDTH/2;
		int y = gp.SCREENHEIGHT/2 - btDefault.getHeight();
		int xText = x + (int) (gp.TILESIZE*0.7);
		int yText;
		
		g2.setColor(Color.black);
		//Definindo todo backgroud preto
		g2.fillRect(0, 0, gp.SCREENWIDTH, gp.SCREENHEIGHT);
		//Colocando a imagem do menu inicial
		g2.drawImage(menuInitial, gp.SCREENWIDTH/2 - menuInitial.getWidth()/2, gp.SCREENHEIGHT/2 - menuInitial.getHeight()/2, null);
		
		g2.setColor(Color.WHITE);
		g2.setFont(gameFont);
		
		//Botão de jogar
		text = "Jogar";
		y += gp.TILESIZE;
		g2.drawImage(btDefault, x, y, null);
		yText = y + (int) (g2.getFontMetrics().getStringBounds(text, g2).getHeight()*1.5);
		//Desenhando a opção de retomar o jogo
		g2.drawString(text, xText, yText);
		//Colocando a seta seletora para cada opção
		if(option == 0) {
			g2.drawImage(selectionDagger, x - gp.TILESIZE, yText-gp.TILESIZE/2, gp.TILESIZE, gp.TILESIZE, null);
		}
		
		//Colocando os botões do menu inicial
		text = "Carregar";
		y += gp.TILESIZE;
		g2.drawImage(btDefault, x, y, null);
		yText = y + (int) (g2.getFontMetrics().getStringBounds(text, g2).getHeight()*1.5);
		//Desenhando a opção de retomar o jogo
		g2.drawString(text, xText, yText);
		//Colocando a seta seletora para cada opção
		if(option == 1) {
			g2.drawImage(selectionDagger, x - gp.TILESIZE, yText-gp.TILESIZE/2, gp.TILESIZE, gp.TILESIZE, null);
		}

		//Colocando os botões do menu inicial
		text = "Sair";
		y += gp.TILESIZE;
		g2.drawImage(btDefault, x, y, null);
		yText = y + (int) (g2.getFontMetrics().getStringBounds(text, g2).getHeight()*1.5);
		//Desenhando a opção de retomar o jogo
		g2.drawString(text, xText, yText);
		//Colocando a seta seletora para cada opção
		if(option == 2) {
			g2.drawImage(selectionDagger, x - gp.TILESIZE, yText-gp.TILESIZE/2, gp.TILESIZE, gp.TILESIZE, null);
		}		
	}

	/**
	 * Mostra a tela de pausa com opções de saida, retomada e salvamento do jogo
	 */
	public void drawPause() {
		String text;

		int x, y;

		color = new Color(0, 0, 0, 150);
		
		x = gp.SCREENWIDTH/4;
		y = gp.TILESIZE;
		//Definindo preenchimento do menu de pausa
		g2.setColor(color);
		g2.fillRoundRect(x, y, gp.SCREENWIDTH/2, gp.SCREENHEIGHT - gp.TILESIZE*2, 20, 20);

		color = new Color(75,0,130);

		//Desenhando a borda do menu de pausa
		g2.setColor(color);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x, y, gp.SCREENWIDTH/2, gp.SCREENHEIGHT - gp.TILESIZE*2, 20, 20);

		g2.setColor(Color.WHITE);
		g2.setFont(defaultFont);

		//Desenhando o texto de pause
		text = "Pausado";
		x = getCenterXText(text);
		y += gp.TILESIZE;
		g2.drawString(text, x,y);

		//Defindo fonte para opções
		g2.setFont(g2.getFont().deriveFont(40f));

		text = "Retomar";
		x = getCenterXText(text);
		y += gp.TILESIZE*2;
		//Desenhando a opção de retomar o jogo
		g2.drawString(text, x, y);
		//Colocando a seta seletora para cada opção
		if(option == 0) {
			g2.drawString(">", x - gp.TILESIZE, y);
		}

		text = "Salvar";
		x = getCenterXText(text);
		y += gp.TILESIZE;		
		//Desenhando a opção de salvar jogo		
		g2.drawString(text, x, y);
		//Seta de seleção para segunda opção
		if(option == 1) {
			g2.drawString(">", x - gp.TILESIZE, y);		
		}

		text = "Sair";
		x = getCenterXText(text);
		y += gp.TILESIZE;
		//Desenhando a opção de sair do jogo		
		g2.drawString(text, x, y);
		//Colocando seta de seleção para a terceira opção
		if(option == 2) {
			g2.drawString(">", x - gp.TILESIZE, y);
		}
	}

	/**
	 * Método que faz o cálculo da posição horizontal que um determinado texto deve ficar para estar centralizado
	 * @param Text Texto do tipo String a ser centralizado na tela
	 * @param G2 Objeto gráfico que faz a inserção no JPanel
	 * @return Retorna o valor da posição horizontal (X) centralziado
	 */
	public int getCenterXText(String text) {
		int widthTextScreen = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;

		return (gp.SCREENWIDTH/2) - widthTextScreen;
	}
	
	
}
