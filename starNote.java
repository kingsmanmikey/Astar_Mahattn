package star;

public class starNote implements Comparable<starNote>{
	
	protected int x = -1;
	protected int y = -1;
	public    int G =  0;
	public    int H = -1;
	protected String  status;
	
	protected boolean avilable = true;
	
	private starNote sup;
	public starShape shape;
	
	
	public starNote(int x, int y,  starNote sup) {
		this.x = x;
		this.y = y;
		this.sup = sup;
		this.status = "open";
	}
	
	public starNote(int x, int y,  starNote sup, boolean avilable) {
		this.x = x;
		this.y = y;
		this.sup = sup;
		this.status = "open";
		this.avilable = avilable;
	}
	
	
	public void setStatus(String status) {
		this.status = status;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public boolean isAvilable() {
		return avilable;
	}
	
	public void setAvilable(boolean b) {
		avilable = b;
	}
	
	public int getF() {
		return G+H;
	}
	
	public void setSup(starNote in) {
		this.sup = in;
	}
	public starNote getSup() {
		return sup;
	}
	
	@Override
	public boolean equals(Object o) {
		starNote os = (starNote)o;
		if(this.x == os.x && this.y == os.y) return true;
		return false;
	}
	
	
	@Override
	public int compareTo(starNote o) {
		if(o.getF() > this.getF()) return -1;
		if(o.getF() == this.getF()) return 0;
		if(o.getF() < this.getF()) return 1;
		return o.getF() > this.getF()?-1:
					(o.getF()==this.getF()?0:1);
	}




}
