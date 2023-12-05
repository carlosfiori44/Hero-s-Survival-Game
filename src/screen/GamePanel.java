package screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import character.ActionController;
import character.Character;
import character.Enemy_Goblin;
import character.PeripheralAdapter;
import character.Player;
import item.SetItem;
import item.SuperItem;
import map.MapController;

/**
 * Classe que é uma generalização do JPanel e faz toda a junção das outras classes do jogo
 */
public class GamePanel extends JPanel implements Runnable {  
	//Atributos do tamanho da tela e blocos (tiles)
	public final int DEFAULTTILESIZE = 16, SCALE = 5;
	public final int TILESIZE = DEFAULTTILESIZE * SCALE;
	public final int MAXSCREENCOL = 12, MAXSCREENROW = 8;
	public final int SCREENHEIGHT = TILESIZE * MAXSCREENROW, SCREENWIDTH = TILESIZE * MAXSCREENCOL;
	//Frames por segundo
	private final int FPS = 60;
	//Estado atual do jogo
	public final int TITLESCREEN = 0;
	public final int PLAYSCREEN = 1;		
	public final int PAUSESCREEN = 2;
	public int gameState = TITLESCREEN;

	//Definindo a Thread que vai tornar o jogo continuo
	public Thread gameThread;	

	//Classe que lê as teclas pressionadas
	private PeripheralAdapter peripheral = new PeripheralAdapter(this);
	//Criando objeto do tipo Character
	public Player player = new Player(peripheral, this); 
	//Classe referente as mensagens e menus que vão aparecer na tela
	public UserInterface ui = new UserInterface(this);
	//Instanciando o gerenciador de eventos
	public ActionController ac = new ActionController(this);	
	
	//Classe que projeta o background
	public MapController map = new MapController(this);

	//Definindo classe de itens, 'inventário de itens' e localização de itens
	public SetItem setItem = new SetItem(this);
	public SuperItem item[] = new SuperItem[4];	
	
	//Declarando os inimigos
	public Character enemy[];
	public int wave = 0; 	

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

		setupGame();
		startGameThread();
	}

	/**
	 * Inicializa as primeira configurações do jogo
	 */
	public void setupGame() {
		setItem.setItem();
		player.load();		
		map.load();	
		setWave();
	}

	/**
	 * Método que cria e inicia a thread responsável por executar o JPanel
	 */
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();	
	}
	
	/**
	 * Define os inimigos de acordo com a wave de inimigos atual
	 */
	public void setWave() {
		
		if(wave == 0) {
			enemy = new Enemy_Goblin[4];
		} else if(wave == 1) {
			enemy = new Enemy_Goblin[8];
		} else {
			enemy = new Enemy_Goblin[12];
		}
		
		for(int i = 0; i < enemy.length; i++) {
			enemy[i] = new Enemy_Goblin(this);
			enemy[i].load();	
		}		
		
		enemy[0].xWorld = 25* TILESIZE;
		enemy[0].yWorld = 9* TILESIZE;	
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
			enemy[0].update();
		} 					
	}

	/**
	 * Coloca as imagem dos objetos dentro da tela, por meio do objeto do tipo Graphics
	 */ 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;	

		map.draw(graphics);			

		for(int i = 0; i < item.length; i++) {
			if(item[i] != null) {
				item[i].draw(graphics);
			}				
		}

		enemy[0].draw(graphics);	
		player.draw(graphics);
		
		ui.draw(graphics);

		g.dispose();
	}
}
