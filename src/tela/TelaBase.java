package tela;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fase.Fase;

public class TelaBase extends JFrame {
	
	/**
	 * Construtor da tela de base, chamada quando é instanciada
	 */
	public TelaBase() {		
		setSize(778, 562);
		
		//Adicionando o imagem de fundo da tela (JPanel)
		add(new Fase());
		
		setTitle("Hero's Quest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TelaBase();
	}
}
