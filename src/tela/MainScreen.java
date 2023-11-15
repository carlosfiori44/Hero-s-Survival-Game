package tela;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Game.GameController;
import Game.GamePanel;

public class MainScreen extends JFrame {
	
	/**
	 * Construtor da tela de base, chamada quando é instanciada
	 */
	public MainScreen() {		
		GamePanel fase = new GamePanel();	

		//Definindo estado da janela
		setTitle("Hero's Quest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);				

		//Adicionando o imagem de fundo da tela (JPanel)
		this.add(fase);			
		pack();
		//Definindo primeiras configurações do jogo inicio do jogo
		fase.setupGame();		
		//Iniciando thread que vai rodar o o JFrame do jogo
		fase.startGameThread();
					
	}
}
