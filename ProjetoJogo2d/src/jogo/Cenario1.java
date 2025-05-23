package jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.Window;

public class Cenario1 extends Cenario implements Runnable {
	private Window janela;
	private Keyboard teclado;
	private Scene cena;
	private Jogador jogador;
	private Zumbi[] zumbi = new Zumbi[5];
	private Zumbi00[] zumbi00 = new Zumbi00[3];

	public Cenario1(Window janela) {
		this.janela = janela;
		this.teclado = janela.getKeyboard();
		this.jogador = new Jogador(390, 1060); // Posição inicial do jogador
		this.cena = new Scene();
		this.cena.loadFromFile("recursos/scn/Cenario1.scn");

	}

	private void mudarCenario() {
		// Lógica para mudar o cenário, se houver
	}

	@Override
	public void run() {
		// Inicializar os zumbis
		for (int i = 0; i < zumbi.length; i++) {
			zumbi[i] = new Zumbi(370 * i, 270 * i);
		}
		for (int j = 0; j < zumbi00.length; j++) {
			zumbi00[j] = new Zumbi00(100 * j, 400 * j);
		}

		while (true) {
			cena.draw(); // Desenha o cenário primeiro

			mudarCenario();

			jogador.mover(janela, teclado);
			jogador.caminho(cena);
			cena.moveScene(jogador);

			jogador.x += cena.getXOffset();
			jogador.y += cena.getYOffset();

			// Atualiza e desenha zumbis do tipo Zumbi
			for (int i = 0; i < zumbi.length; i++) {
				zumbi[i].caminho(cena);
				zumbi[i].perseguir(jogador.x, jogador.y);
				jogador.atirar(janela, cena, teclado, zumbi[i]);
				zumbi[i].morrer();
				zumbi[i].ataque(jogador);
				zumbi[i].x += cena.getXOffset();
				zumbi[i].y += cena.getYOffset();
				zumbi[i].draw();
			}

			// Atualiza e desenha zumbis do tipo Zumbi00
			for (int j = 0; j < zumbi00.length; j++) {
				zumbi00[j].caminho(cena);
				zumbi00[j].perseguir(jogador.x, jogador.y);
				zumbi00[j].morrer();
				zumbi00[j].ataque(jogador);
				zumbi00[j].x += cena.getXOffset();
				zumbi00[j].y += cena.getYOffset();
				zumbi00[j].draw();
				jogador.atirar(janela, cena, teclado, zumbi00[j]);
			}

			jogador.draw();
			jogador.energia(janela);
			janela.update();
		}
	}
}
