/**
 * 
 * @author wontzer
 *
 * product by: brand_aware
 * ??? - 2019
 * 
 */
import core.Board;
import core.Mover;
import core.Properties;

public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// finds current working directory to help locate game images
		String currentDir = System.getProperty("user.dir");
		Properties properties = new Properties(currentDir);
		Board board = new Board();
		properties.setBoard(board);
		
		// begins game animation
		Thread thread = new Thread(new Mover(properties));
		thread.start();
		
		// starts actual gameplay
		board.init(properties);
	}
}
