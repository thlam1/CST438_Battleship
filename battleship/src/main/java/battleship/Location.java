package battleship;

public class Location {
	private int xBow;
	private int yBow;
	private int xStern;
	private int yStern;
	
	public Location() {
	}
	
	public Location(int xBow, int yBow, int xStern, int yStern) {
		this.xBow = xBow;
		this.yBow = yBow;
		this.xStern = xStern;
		this.yStern = yStern;
	}
	
	public int getxBow() {
		return xBow;
	}
	public void setxBow(int xBow) {
		this.xBow = xBow;
	}
	public int getyBow() {
		return yBow;
	}
	public void setyBow(int yBow) {
		this.yBow = yBow;
	}
	public int getxStern() {
		return xStern;
	}
	public void setxStern(int xStern) {
		this.xStern = xStern;
	}
	public int getyStern() {
		return yStern;
	}
	public void setyStern(int yStern) {
		this.yStern = yStern;
	}
	
		
}
