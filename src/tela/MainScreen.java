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
		GamePanel game = new GamePanel();	

		//Definindo estado da janela
		setTitle("Hero's Quest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//Adicionando o imagem de fundo da tela (JPanel)
		add(game);			
		//Definindo primeiras configurações do jogo inicio do jogo
		game.setupGame();		
		//Iniciando thread que vai rodar o o JFrame do jogo
		game.startGameThread();
		
		pack();		
		setVisible(true);		
		setLocationRelativeTo(null);
	}
}
