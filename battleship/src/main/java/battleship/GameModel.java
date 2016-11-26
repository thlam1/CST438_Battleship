package battleship;

import java.util.Arrays;
import java.util.Hashtable;

public class GameModel {
	/* valid game states:
	 * 1 = setup
	 * 2 = in play
	 * 3 = results
	 */
	private int gameState = 1;
	
	/*
	 * point grid (track the state of each point on 10 X 10 game grid
	 */
	private GridPoint[][] points = new GridPoint[10][10];
	private Hashtable player1Ships = new Hashtable();
	private Hashtable player2Ships = new Hashtable();
	
	public GameModel() {
	}

	public GameModel(int gameState) {
		this.gameState = gameState;
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public GridPoint[][] getPoints() {
		return points;
	}

	public void setPoints(GridPoint[][] points) {
		this.points = points;
	}

	public Hashtable getPlayer1Ships() {
		return player1Ships;
	}

	public void setPlayer1Ships(Hashtable player1Ships) {
		this.player1Ships = player1Ships;
	}

	public Hashtable getPlayer2Ships() {
		return player2Ships;
	}

	public void setPlayer2Ships(Hashtable player2Ships) {
		this.player2Ships = player2Ships;
	}

	private void init() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				GridPoint p = new GridPoint();
				points[i][j] = p;
			}
		}
	}
	
	@Override
	public String toString() {
		return "GameModel [gameState=" + gameState + ", points=" + Arrays.toString(points) + "]";
	}
	
	
	
}
