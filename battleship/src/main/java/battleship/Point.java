package battleship;

public class Point {

	private int x;
	private int y;
	private boolean hit = false;

	public Point() {
	}

	
	public Point(int x, int y, boolean hit) {
		this.x = x;
		this.y = y;
		this.hit = hit;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}	
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	public boolean equals(Point p) {
		return (p.getX() == x && p.getY() == y);
	}
	
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", hit=" + hit + "]";
	}
}
