package battleship;

public class Location {
	
	private Point[] points;
	
	public Location() {
	}
	
	public Location(Point[] points) {
		this.points = points;
	}

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}
}
