package jogo;
//projeto atualmente só roda no eclipse, futuras atualizações
import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

public class Main {
    public static void main(String[] args) {
        Window janela = new Window(800, 600);
        GameImage plano = new GameImage(URL.sprite("menu.png"));
        Keyboard teclado = janela.getKeyboard();

        while (true) {
            plano.draw();
            janela.update();

            if (teclado.keyDown(Keyboard.ENTER_KEY)) {
                Cenario1 cenario = new Cenario1(janela);
                Thread thread = new Thread(cenario);
                thread.start();
                break; // sair do loop do menu
            }
        }
    }
}
