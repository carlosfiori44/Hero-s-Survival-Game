package fase;

import java.awt.BasicStroke;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import classe.Mapa;
import classe.Player;
import classe.TecladoAdapter;

public class Fase extends JPanel implements ActionListener, Runnable {
	//Definindo os atributos
	private Image fundo, fundoAllow;
	//Criando objeto do tipo Character
	private Player player;   
	//Definindo o timer que será utilizado para animar a tela
	private Timer timer;
	//Classe mapa
	private Mapa mapa = new Mapa();
	//Classe que adapta as teclas pressionadas
	private TecladoAdapter tecladoA;
	//Definindo a Thread
	private Thread gameThread;
	//Frames por segundo
	private final int FPS = 60;

	/**
	 * Método construtor da clase Fase, utilizada quando a mesma é instanciada
	 * @param background recebe o diretorio da imagem de fundo da tela
	 */
	public Fase() {
		this.setFocusable(true);
		this.setDoubleBuffered(true);

		ImageIcon back = new ImageIcon("images\\background\\mapa_ilha.png");
		ImageIcon backAllow = new ImageIcon("images\\background\\mapa_ilha_allow.png");

		fundo = back.getImage();
		fundoAllow = backAllow.getImage();

		tecladoA = new TecladoAdapter();
		this.addKeyListener(tecladoA);	

		player = new Player(210, 220, tecladoA);
		player.load();						

		//timer = new Timer(1000/60, this);
		//timer.start();
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

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

		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundoAllow, 0, 0, null);

		player.draw(graficos);

		graficos.setStroke(new BasicStroke(5));

		g.dispose();
	}

	/**	
	 * Faz algo sempre que uma ação ocorre
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		repaint();
	}
}
