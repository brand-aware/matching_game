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
		if(args.length != 1){
			System.out.println("Please run application in format:\n\n"
					+ "java driver <app dir>");
			System.exit(0);
		}
		Properties properties = new Properties(args[0]);
		Board board = new Board();
		properties.setBoard(board);
		Thread thread = new Thread(new Mover(properties));
		thread.start();
		board.init(properties);
	}
}
