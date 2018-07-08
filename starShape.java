package star;

import java.util.ArrayList;

import controller.mainControl;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class starShape extends Rectangle{
	  
	
	private starNote s;
	public starNote getStarNote() {
		return s;
	}
	
	public starShape(starNote starNote) {
		this.s = starNote;
		this.s.shape = this;
		setX(starNote.x);
		setY(starNote.y);
		setHeight(50.0);
		setWidth(50.0);
		update();
	}
	
	
	public void update() {
		if(!s.avilable) {
			setFill(Color.BLUEVIOLET);
		}else {
			setFill(Color.rgb(50, 50, 50));
		}		
		if(s.status.equals("open")) {
			setStroke(Color.BLACK);
		}else if(s.status.equals("close")){
			setStroke(Color.AQUA);
		}else if(s.status.equals("start")) {
			setStroke(Color.BLACK);
			setFill(Color.BLUE);
		}
		
		
	}
	
	public ArrayList<starShape> getNeighbor(){
		ArrayList<starShape> re = new ArrayList<starShape>();
		for(starShape ss : mainControl.allShape) {
			for(int x = this.getStarNote().x-50;x<=this.getStarNote().x+50;x+=50) {
				for(int y = this.getStarNote().y-50;y<=this.getStarNote().y+50;y+=50) {
					if(ss.getStarNote().x == x && ss.getStarNote().y ==y && ss!=this && ss.getStarNote().isAvilable()) {
//						ss.getStarNote().G += MahattnUtil.MahattnDistance(ss, mainControl.desShape);
//						if(ss.getStarNote().H<0) 	
//							ss.getStarNote().H = MahattnUtil.Distance(ss,  this);
						re.add(ss);			
					}
				}
			}
		}
		return re;
	}
	
	@Override
	public String toString() {
		return this.getStarNote().x+" "+this.getStarNote().y;
	}
	
	
	
}
