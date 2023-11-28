package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import item.SetItem;
import item.SuperItem;
import map.MapController;
import player.PeripheralAdapter;
import player.Player;

public class GamePanel extends JPanel implements Runnable {  
	//Atributos do tamanho da tela e blocos (tiles)
	public final int DEFAULTTILESIZE = 16, SCALE = 5;
	public final int TILESIZE = DEFAULTTILESIZE * SCALE;
	public final int MAXSCREENCOL = 12, MAXSCREENROW = 8;
	public final int SCREENHEIGHT = TILESIZE * MAXSCREENROW, SCREENWIDTH = TILESIZE * MAXSCREENCOL;
	//Classe que lê as teclas pressionadas
	private PeripheralAdapter peripheral = new PeripheralAdapter(this);
	//Criando objeto do tipo Character
	public Player player = new Player(peripheral, this); 
	//Classe que projeta o background
	public MapController map = new MapController(this);
	//Definindo a Thread
	public Thread gameThread;
	//Definindo a váriavel do tipo Graphics
	Graphics2D graphics;
	//Frames por segundo
	private final int FPS = 60;
	//Estado atual do jogo
	public final int TITLESCREEN = 0;
	public final int PLAYSCREEN = 1;		
	public final int PAUSESCREEN = 2;
	public int gameState = TITLESCREEN;
	//Definindo classe de itens, 'inventário de itens' e localização de itens
	public SetItem setItem = new SetItem(this);
	public SuperItem item[] = new SuperItem[4];	
	//Classe referente as mensagens e menus que vão aparecer na tela
	public UserInterface ui = new UserInterface(this);

	/**
	 * Método construtor da clase Fase, utilizada quando a mesma é instanciada
	 * @param background recebe o diretorio da imagem de fundo da tela
	 */
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		this.setLayout(null);	
		this.addKeyListener(peripheral);	

		player.load();			
		map.load();		
	}

	/**
	 * Inicializa as primeira configurações do jogo
	 */
	public void setupGame() {
		setItem.setItem();
	}

	/**
	 * Método que cria e inicia a thread responsável por executar o JPanel
	 */
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();	
	}

	//É executado assim que a thread se inicia
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while(gameThread != null) { 			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if(delta >= 1) {	
				update();
				repaint();				
				delta--;
			}
		}
	}

	/**
	 * Atualiza a localização dos objetos na tela
	 */
	public void update() {
		if(gameState == PLAYSCREEN) {
			player.update();				
		} 					
	}

	/**
	 * Coloca as imagem dos objetos dentro da tela, por meio do objeto do tipo Graphics
	 */ 
	public void paintComponent(Graphics g) {
		graphics = (Graphics2D) g;	

		//Verifica se o estado do jogo é o menu inicial
		if(gameState == TITLESCREEN) {			
			ui.drawMenu(graphics);
		} 

		//Verifica se o estado do jogo é no modo jogável
		if(gameState == PLAYSCREEN) {		
			map.draw(graphics);			

			for(int i = 0; i < item.length; i++) {
				if(item[i] != null) {
					item[i].draw(graphics);
				}				
			}
			
			player.draw(graphics);	
			ui.draw(graphics);
		}

		//Verifica se o jogo está pausado
		if(gameState == PAUSESCREEN) {
			ui.drawPause(graphics);
		}
		
		g.dispose();
	}
}
