/**
 * @author wontzer
 * 
 * product of: brand-aware
 * 2017
 */
package core;

import java.io.IOException;

/**
 * Thread class to handle all gameplay animation
 * - flipping cards randomly
 * - flipping/unflipping selected cards
 * - timer updates
 */
public class Mover implements Runnable{
	
	Properties properties;
	
	public Mover(Properties prop){
		properties = prop;
	}

	@Override
	public void run() {
		
		// already ready for game motion
		while(true){
			
			if(properties.getBoard().initialized()){
				try {
					// calls main action of program
					properties.getBoard().doMove();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try {
				// simulated "animation"
				Thread.sleep(77);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
