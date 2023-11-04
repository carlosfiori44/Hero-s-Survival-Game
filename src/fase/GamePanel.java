package fase;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import classe.Player;
import classe.TecladoAdapter;

public class GamePanel extends JPanel implements Runnable {
	//Definindo os atributos das imagens de fundo
	private Image background, backgroundAllow;
	//Variavel que guarda o fundo atual
	public static String currentBackground;
	//Criando objeto do tipo Character
	private Player player;   
	//Classe que adapta as teclas pressionadas
	private TecladoAdapter tecladoA;
	//Definindo a Thread
	private Thread gameThread;
	//Frames por segundo
	private final int FPS = 30;

	/**
	 * Método construtor da clase Fase, utilizada quando a mesma é instanciada
	 * @param background recebe o diretorio da imagem de fundo da tela
	 */
	public GamePanel() {
		this.setFocusable(true);
		this.setDoubleBuffered(true);

		ImageIcon back = new ImageIcon("res\\background\\mapa_ilha.png");
		ImageIcon backAllow = new ImageIcon("res\\background\\mapa_ilha_allow.png");
		
		background = back.getImage();
		backgroundAllow = backAllow.getImage();

		tecladoA = new TecladoAdapter();
		this.addKeyListener(tecladoA);	

		player = new Player(220, 240, tecladoA);
		player.loadPlayerImage();						

		//timer = new Timer(1000/60, this);
		//timer.start();
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
			currentBackground = "res\\background\\mapa_ilha_allow.png";
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if(delta >= 1) {
				player.update();
				repaint();
				delta--;
			}
		}
	}

	/**
	 * Coloca as imagem dos objetos dentro da tela, como por exemplo o personagem
	 */ 
	public void paintComponent(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;		

		graficos.drawImage(background, 0, 0, null);
		graficos.drawImage(backgroundAllow, 0, 0, null);

		player.draw(graficos);

		g.dispose();
	}
}
