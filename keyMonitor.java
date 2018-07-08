package controller;

import javafx.scene.paint.Color;
import star.starShape;

public class keyMonitor extends Thread{
	private long sleepTime = 20L;
	private boolean itCanBeStart = true;
	public void run() {
		while(true) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				//ignore
			}
			if(mainControl.keyList.contains("N")) {
				for(starShape ss :mainControl.tentativeP.getNeighbor()) {
					Color c = (Color) ss.getFill();
					mainControl.colorUpdate(ss, Color.GREEN);
					try {
						Thread.sleep(100L);
					} catch (InterruptedException e) {
						//ignore
					}
					mainControl.colorUpdate(ss, c);
				}
			}
			if(mainControl.keyList.contains("ENTER") && itCanBeStart) {
				itCanBeStart = false;
				new mainProcess().start();
			}
		}
	}
}
