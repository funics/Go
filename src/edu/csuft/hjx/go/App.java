package edu.csuft.hjx.go;

/**
 * сно╥хК©з
 * @author hjx
 *
 */
public class App {
	public static void main(String[] args) {
		GameModel model = new GameModel(9);
//		model.show();
		
		GameFrame frame = new GameFrame(model);
	}
}
