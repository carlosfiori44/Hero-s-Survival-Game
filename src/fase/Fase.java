package fase;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import classe.Character;
import classe.TecladoAdapter;

public class Fase extends JPanel implements ActionListener {
	//Definindo os atributos
	private Image fundo, fundoAllow;
	private Rectangle f1, f2;
	//Criando objeto do tipo Character
	private Character player;   
	//Definindo o timer que será utilizado para animar a tela
	private Timer timer;
	//private Thread gameThread;
		
	/**
	 * Método construtor da clase Fase, utilizada quando a mesma é instanciada
	 * @param background recebe o diretorio da imagem de fundo da tela
	 */
	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon back = new ImageIcon("images\\background\\mapa_ilha.png");
		ImageIcon backAllow = new ImageIcon("images\\background\\mapa_ilha_allow.png");
		
		fundo = back.getImage();
		fundoAllow = backAllow.getImage();
				
		f1 = new Rectangle(fundo.getWidth(null), fundo.getHeight(null));
		f2 = new Rectangle(fundo.getWidth(null), fundoAllow.getHeight(null));
				
		player = new Character(208, 223);
		player.load();
		
		this.addKeyListener(new TecladoAdapter(player));
		
		double drawInterval = 1000000000/60;
		double nextDrawTime = System.nanoTime() + drawInterval;
		double remainingTime = nextDrawTime - System.nanoTime();
		
		
		timer = new Timer(1000/60, this);
		timer.start();
	}
	
	/**
	 * Coloca as imagem dos objetos dentro da tela, como por exemplo o personagem
	 */ 
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;		

		graficos.drawImage(fundo, 0, 0, null);
		graficos.drawImage(fundoAllow, 0, 0, null);
		graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
		graficos.setStroke(new BasicStroke(5));
		
		g.dispose();
	}

	/**	
	 * Faz algo sempre que uma ação ocorre
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		checarColisoes();
		repaint();
	}
	
	public void checarColisoes() {
		if(player.getBounds().intersects(f1) && player.getBounds().intersects(f2)) {
			System.out.println("ola");
		}		
	}
}
