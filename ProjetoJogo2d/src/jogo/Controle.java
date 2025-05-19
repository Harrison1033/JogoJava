package jogo;

import jplay.GameObject;
import jplay.TileInfo;

public class Controle {
	public boolean Colisao(GameObject obj, TileInfo tile) {//m�todo boolean colis�o
		if ((tile.id >= 20 )&& obj.collided(tile)) {//decide objetos que podem ou n�o serem atravessados
			return true;
		}
		return false;
	}

}
