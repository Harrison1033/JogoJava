package jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;
//melhorar a classe Zumbi
public class Cenario1 extends Cenario {
	private Window janela;//criar classe janela
	private Scene cena;//criar a classe cena 
	private Jogador jogador;
	private Keyboard teclado;//implementa o teclado no cenario
	private Zumbi zumbi[];//por serem multiplos zumbis, tem que ser um array
	private Zumbi00 zumbi00[];
		
	
	
	public Cenario1 (Window window) {//construtor com o par�metro Window da classe main
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("Cenario1.scn"));//carrega o arquivo de cen�rio1
		jogador = new Jogador(390, 1060);;//TENTAR UM IF PARA RETORNO DE MULTIPLAS TELAS
		teclado = janela.getKeyboard();//parte do Keyboard teclado
		zumbi = new Zumbi[6];//array de 5 zumbis no cen�rio
		zumbi00 = new Zumbi00[4];
		
		//Som.play("obscuro.wav");
		run();//o m�todo run cont�m o loop infinito como na classe main
	}
	public void run() {
		 for(int i = 0; i < zumbi.length; i ++) {
			 zumbi[i] = new Zumbi(370 * i, 270 * i);
			 
		 }
		 for(int j = 0; j < zumbi00.length;j ++) {
			 zumbi00[j] = new Zumbi00(100 * j, 400 * j); 
		 }
		while (true) {
			//cena.draw();//Pintando a cena que no caso n�o segue o jogador
			mudarCenario();
			janela.update();
			jogador.mover(janela,teclado);//mover jogador no cenario atrav�s do mover da classe Jogador
			jogador.caminho(cena);
			cena.moveScene(jogador);//c�mera segue o jogadeor  
			jogador.x += cena.getXOffset();//adicionando o posicionamento para seguir mais suavemente
			jogador.y += cena.getYOffset();//adicionando o posicionamento
			for(int i = 0; i < zumbi.length; i ++) {
			zumbi[i].caminho(cena);
			zumbi[i].perseguir(jogador.x, jogador.y);
			jogador.atirar(janela, cena, teclado, zumbi[i]);
			zumbi[i].morrer();//M�todo morrer(sem parametros) da classe zumbi
			zumbi[i].ataque(jogador);//M�todo da classe zumbi(atacar jogador)
			zumbi[i].x += cena.getXOffset();//usado para o zumbi n�o ser arrastado quando o jogador andar na tela
			zumbi[i].y += cena.getYOffset();//o mesmo de cima
			zumbi[i].draw();
			}
			for(int j = 0; j < zumbi00.length; j ++) {
				zumbi00[j].caminho(cena);
				zumbi00[j].perseguir(jogador.x, jogador.y);
				zumbi00[j].morrer();
				zumbi00[j].ataque(jogador);
				zumbi00[j].x += cena.getXOffset();
				zumbi00[j].y += cena.getYOffset();
				zumbi00[j].draw();
				jogador.atirar(janela, cena, teclado, zumbi00[j]);
				
			}
			
			jogador.draw();//pintando o jogador
			jogador.energia(janela);//referente ao life do jogador
		}
	}
	private void mudarCenario() {
		if (tileCollision(05, jogador, cena)== true) {//05 � o n�mero do tile que colide para outro cen�rio
			new Cenario2(janela);//Abre a janela do cenario 2
		}
	
	if (tileCollision(06, jogador, cena)== true) {//06 � o n�mero do tile que colide para outro cen�rio
		new Cenario3(janela);//Abre a janela do cenario 3
	}
}
}
