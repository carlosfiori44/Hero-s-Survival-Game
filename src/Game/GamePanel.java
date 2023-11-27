package game;

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
	public final int MAXSCREENCOL = 9, MAXSCREENROW = 7;
	public final int SCREENHEIGHT = TILESIZE * MAXSCREENROW, SCRRENWIDTH = TILESIZE * MAXSCREENCOL;
	//Classe que lê as teclas pressionadas
	private PeripheralAdapter peripheral = new PeripheralAdapter(this);
	//Criando objeto do tipo Character
	public Player player = new Player(peripheral, this); 
	//Classe que projeta o background
	public MapController map = new MapController(this);
	//Definindo a Thread
	private Thread gameThread;	
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
	//Classe que faz o controle entre menu, itens, personagens e mapas
	private GameStateControl gameC = new GameStateControl(map, this); 	
	//Classe referente as mensagens que vão aparecer na tela
	UserInterface ui = new UserInterface(this);

	/**
	 * Método construtor da clase Fase, utilizada quando a mesma é instanciada
	 * @param background recebe o diretorio da imagem de fundo da tela
	 */
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCRRENWIDTH, SCREENHEIGHT));
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
		Graphics2D graficos = (Graphics2D) g;	
		

		//Verifica se o estado do jogo é o menu inicial
		if(gameState == TITLESCREEN) {			
			graficos.drawImage(gameC.menuInitial, 0, 0, null);
		} 

		//Verifica se o estado do jogo é no modo jogável
		if(gameState == PLAYSCREEN) {		
			map.draw(graficos);			

			for(int i = 0; i < item.length; i++) {
				if(item[i] != null) {
					item[i].draw(graficos);
				}				
			}
			
			player.draw(graficos);	
			ui.draw(graficos);
			
			g.dispose();
		}

		//Verifica se o jogo está pausado
		if(gameState == PAUSESCREEN) {
			gameC.drawPause(graficos);
		}
	}
}
