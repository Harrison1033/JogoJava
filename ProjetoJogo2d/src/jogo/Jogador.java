package jogo;

import java.awt.*;
import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Jogador extends Ator {
	static int energia = 3000; // Energia do personagem
	ControleTiros tiros = new ControleTiros();
	Font f = new Font("arial", Font.BOLD, 15);

	public Jogador(int x, int y) {
		super(URL.sprite("jogador.png"), 20); // 20 = número de frames
		this.x = x; // Posição inicial do personagem
		this.y = y;
		this.setTotalDuration(2000); // Tempo de animação em milissegundos
	}

	public void atirar(Window janela, Scene cena, Keyboard teclado, Ator inimigo) {
		if (teclado.keyDown(Keyboard.A_KEY)) { // Corrigido de MenuKeyEvent.VK_A para Keyboard.A_KEY
			tiros.adicionaTiro(x + 5, y + 8, direcao, cena); // Posição do tiro relativa ao jogador
		}
		tiros.run(inimigo);
	}

	public void mover(Window janela, Keyboard teclado) {
		if (teclado.keyDown(Keyboard.LEFT_KEY)) {
			if (this.x > 0) this.x -= velocidade;
			if (direcao != 1) {
				setSequence(4, 8); // Sprites para esquerda
				direcao = 1;
			}
			movendo = true;
		}
		else if (teclado.keyDown(Keyboard.RIGHT_KEY)) {
			if (this.x < janela.getWidth() - 60) this.x += velocidade;
			if (direcao != 2) {
				setSequence(8, 12); // Sprites para direita
				direcao = 2;
			}
			movendo = true;
		}
		else if (teclado.keyDown(Keyboard.UP_KEY)) {
			if (this.y > 0) this.y -= velocidade;
			if (direcao != 4) {
				setSequence(12, 16); // Sprites para cima
				direcao = 4;
			}
			movendo = true;
		}
		else if (teclado.keyDown(Keyboard.DOWN_KEY)) {
			if (this.y < janela.getHeight() - 60) this.y += velocidade;
			if (direcao != 5) {
				setSequence(0, 4); // Sprites para baixo
				direcao = 5;
			}
			movendo = true;
		}

		if (movendo) {
			update();
			movendo = false;
		}
	}

	public void energia(Window janela) {
		Color cor;
		if (energia >= 500) {
			cor = Color.GREEN;
		} else if (energia >= 100) {
			cor = Color.BLUE;
		} else {
			cor = Color.RED;
		}
		janela.drawText("HP: " + energia, 30, 30, cor, f);
	}
}