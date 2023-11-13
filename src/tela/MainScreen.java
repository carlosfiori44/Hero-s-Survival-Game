package tela;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import classe.GameController;
import fase.GamePanel;

public class MainScreen extends JFrame {
	
	/**
	 * Construtor da tela de base, chamada quando é instanciada
	 */
	public MainScreen() {		
		GamePanel fase = new GamePanel();	

		//Tamanho padrão da tela
		setSize(778, 562);
		//Definindo estado da janela
		setTitle("Hero's Quest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);		
		

		//Adicionando o imagem de fundo da tela (JPanel)
		this.add(fase);			
		//Definindo primeiras configurações do jogo inicio do jogo
		fase.setupGame();		
		//Iniciando thread que vai rodar o o JFrame do jogo
		fase.startGameThread();
					
	}
}
