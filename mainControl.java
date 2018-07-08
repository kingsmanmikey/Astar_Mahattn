package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import star.starNote;
import star.starShape;

public class mainControl implements Initializable{
	
	public     static starShape tentativeP   = null;
	
	public     static starShape desShape     = new starShape(new starNote(800,250,null));
	
	public     static starShape startPoint   = new starShape(new starNote(200,250,null));
	
	protected  starShape wallShape01  = new starShape(new starNote(400,150,null));
	protected  starShape wallShape02  = new starShape(new starNote(400,200,null));
	protected  starShape wallShape03  = new starShape(new starNote(400,250,null));
	protected  starShape wallShape04  = new starShape(new starNote(400,300,null));	
	protected  starShape wallShape05  = new starShape(new starNote(400,350,null));
	protected  starShape wallShape06  = new starShape(new starNote(500,300,null));	
	protected  starShape wallShape07  = new starShape(new starNote(500,350,null));
	protected  starShape wallShape08  = new starShape(new starNote(500,150,null));
	protected  starShape wallShape09  = new starShape(new starNote(500,100,null));
	
	public     static ArrayList<starShape> allShape  = new ArrayList<starShape>();
	public     static ArrayList<starShape> wallShape = new ArrayList<starShape>();
	
	protected  static ArrayList<starShape> openList  = new ArrayList<starShape>();
	protected  static ArrayList<starShape> closeList = new ArrayList<starShape>();
	
	protected  static ArrayList<String> keyList = new ArrayList<String>();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		new keyMonitor().start();
		new Monitor().start();
	}
	public static void Paint(Shape shape) {
		   Platform.runLater(new Runnable() {
			   public void run() {
				   Main.pane.getChildren().add(shape);
			   }
		   });
		   
	}
	public static void Remove(Shape shape) {
		Platform.runLater(new Runnable() {
			   public void run() {
				   Main.pane.getChildren().remove(shape);
			   }
		   });
	}
	public static void colorUpdate(Shape shape, Color color) {
		Platform.runLater(new Runnable() {
			   public void run() {
				  shape.setFill(color);
			   }
		   });
	}
	class Monitor extends Thread{
		private long sleepTime = 20L;
		public void run() {
			/**
			 * Initialize
			 */
			/**
			 * wall
			 */
			wallShape.add(wallShape01);
			wallShape.add(wallShape02);
			wallShape.add(wallShape03);
			wallShape.add(wallShape04);
			wallShape.add(wallShape05);
			wallShape.add(wallShape06);
			wallShape.add(wallShape07);
			wallShape.add(wallShape08);
			wallShape.add(wallShape09);
			for(starShape ss : wallShape) {
				ss.getStarNote().setAvilable(false);
				ss.update();
				Paint(ss);
			}
			
			allShape.addAll(wallShape);
			/**
			 * destination
			 */
			desShape.setFill(Color.RED);		
			Paint(desShape);
			allShape.add(desShape);
			/**
			 * startPoint
			 */
			startPoint.setFill(Color.BLUE);
			Paint(startPoint);
			allShape.add(startPoint);
			
			tentativeP = startPoint;
			/**
			 * Path
			 */
			for(int x = 0 ; x <= 950 ; x+=50) {
				for(int y = 0 ; y <=450; y+=50) {
					//检查该区域是否有方块
					if(x==desShape.getStarNote().getX() && y==desShape.getStarNote().getY()) continue;
					boolean isOK = true;
					for(starShape ss : allShape) {
						if(ss.getStarNote().getX()==x && ss.getStarNote().getY()==y) {
							isOK = false;
							break;
						}
					}
					if(!isOK) continue;
					//生成方块
					starNote  sn = new starNote(x, y, null);
					starShape ss = new starShape(sn);							
					allShape.add(ss);
					Paint(ss);
				}
			}
			
			while(true) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					//ignore
				}
				
				//if(!keyList.isEmpty())System.out.println(keyList);

			}
		}
	}
	
	   public void input(Event ev) { 
		   if(ev.getEventType() == KeyEvent.KEY_RELEASED) {
			   while(keyList.contains(((KeyEvent)ev).getCode().toString()) && !keyList.isEmpty()) {
				   keyList.remove(((KeyEvent)ev).getCode().toString());
			   }
		   }
		   if(ev.getEventType() == KeyEvent.KEY_PRESSED && !keyList.contains(((KeyEvent)ev).getCode().toString())){
			   keyList.add(((KeyEvent)ev).getCode().toString());
		   }
	   }

}
