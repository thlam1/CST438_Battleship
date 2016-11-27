package battleship;

import java.util.Hashtable;
import java.util.UUID;

public class GameModel {
	/* valid game states:
	 * 1 = setup
	 * 2 = in play
	 * 3 = results
	 */
	private int gameState = 1;
	
	/*
	 * who's turn is it.
	 */
	private int playerTurn = 1;
	
	/*
	 * player 1 setup complete
	 */
	private boolean player1Setup = false;
	
	/*
	 * player 2 setup complete
	 */
	private boolean player2Setup = false;

	
	/*
	 * Players point grids (track the state of each point on 10 X 10 game grid
	 */
	private GridPoint[][] player1Grid = new GridPoint[10][10];
	private GridPoint[][] player2Grid = new GridPoint[10][10];
	
	/*
	 * Players ships
	 */
	private Hashtable<UUID, Ship> player1Ships = new Hashtable<UUID, Ship>();
	private Hashtable<UUID, Ship> player2Ships = new Hashtable<UUID, Ship>();
	
	public GameModel() {
		this.gameState = 1;
		init();
	}

	public GameModel(int gameState) {
		this.gameState = gameState;
		init();
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}



	public Hashtable<UUID, Ship> getPlayer1Ships() {
		return player1Ships;
	}

	public void setPlayer1Ships(Hashtable<UUID, Ship> player1Ships) {
		this.player1Ships = player1Ships;
	}

	public Hashtable<UUID, Ship> getPlayer2Ships() {
		return player2Ships;
	}

	public void setPlayer2Ships(Hashtable<UUID, Ship> player2Ships) {
		this.player2Ships = player2Ships;
	}

	private void init() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				GridPoint p = new GridPoint();
				player1Grid[i][j] = p;
				player2Grid[i][j] = p;
			}
		}
	}


	public GridPoint[][] getPlayer1Grid() {
		return player1Grid;
	}

	public void setPlayer1Grid(GridPoint[][] player1Grid) {
		this.player1Grid = player1Grid;
	}

	public GridPoint[][] getPlayer2Grid() {
		return player2Grid;
	}

	public void setPlayer2Grid(GridPoint[][] player2Grid) {
		this.player2Grid = player2Grid;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	public boolean isPlayer1Setup() {
		return player1Setup;
	}

	public void setPlayer1Setup(boolean player1Setup) {
		this.player1Setup = player1Setup;
	}

	public boolean isPlayer2Setup() {
		return player2Setup;
	}

	public void setPlayer2Setup(boolean player2Setup) {
		this.player2Setup = player2Setup;
	}
	
}
