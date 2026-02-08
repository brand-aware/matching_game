/**
 * @author mike802
 * 
 * product of - ???
 * 2017
 */
package core;

import java.io.IOException;

public class Mover implements Runnable{
	
	Properties properties;
	
	public Mover(Properties prop){
		properties = prop;
	}

	@Override
	public void run() {
		while(true){
			
			if(properties.getBoard().initialized()){
				try {
					properties.getBoard().doMove();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(77);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
