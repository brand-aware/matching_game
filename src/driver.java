/**
 * 
 * @author mike802
 *
 * brand_aware
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
		String currentDir = System.getProperty("user.dir");
		Properties properties = new Properties(currentDir);
		Board board = new Board();
		properties.setBoard(board);
		Thread thread = new Thread(new Mover(properties));
		thread.start();
		board.init(properties);
	}
}
