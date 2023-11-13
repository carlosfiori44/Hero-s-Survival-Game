package fase;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import classe.GameController;
import classe.Mapa;
import classe.Player;
import item.SetItem;
import item.SuperItem;
import classe.PeripheralAdapter;

public class GamePanel extends JPanel implements Runnable {  
	//Classe que lê as teclas pressionadas
	private PeripheralAdapter peripheral = new PeripheralAdapter(this);
	//Criando objeto do tipo Character
	private Player player = new Player(peripheral); 
	//Classe que projeta o background
	private Mapa map = new Mapa();
	//Definindo a Thread
	private Thread gameThread;	
	//Frames por segundo
	private final int FPS = 30;
	//Estado atual do jogo
	public final int TITLESCREEN = 0;
	public final int PLAYSCREEN = 1;		
	public final int PAUSESCREEN = 2;
	public int gameState = TITLESCREEN;
	//Definindo classe de itens, 'inventário de itens' e localização de itens
	public SetItem setItem = new SetItem(this);
	public SuperItem item[] = new SuperItem[4];	
	//Classe que faz o controle entre menu, itens, personagens e mapas
	private GameController gameC = new GameController(map, player, item, this); 	
	
	/**
	 * Método construtor da clase Fase, utilizada quando a mesma é instanciada
	 * @param background recebe o diretorio da imagem de fundo da tela
	 */
	public GamePanel() {
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
	 * Atualiza a localização dos objetos
	 */
	public void update() {
		if(gameState == PLAYSCREEN) {
			player.update();	
			
			if(peripheral.action) {
				for(int i = 0; i < item.length; i++) {
					if(item[i] != null && item[i].findItem(player.limiteForma())) {
						player.addItem(item[i]);
						item[i] = null;
					};
				}
			}
		} 					
	}

	/**
	 * Coloca as imagem dos objetos dentro da tela, como por exemplo o personagem
	 */ 
	public void paintComponent(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;	
		if(gameC.draw(graficos)) g.dispose();
	}
}
