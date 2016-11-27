package battleship;

public class GridPoint {
	/*
	 * 0 = no state
	 * 1 = miss
	 * 2 = hit
	 */
	private int state = 0;

	public GridPoint() {
	}
	
	public GridPoint(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
