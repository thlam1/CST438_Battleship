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
	 * who's turn is it. (1 or 2)
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
	 * Players ships
	 */
	private Hashtable<String, Ship> player1Ships = new Hashtable<String, Ship>();
	private Hashtable<String, Ship> player2Ships = new Hashtable<String, Ship>();
	
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



	public Hashtable<String, Ship> getPlayer1Ships() {
		return player1Ships;
	}

	public void setPlayer1Ships(Hashtable<String, Ship> player1Ships) {
		this.player1Ships = player1Ships;
	}

	public Hashtable<String, Ship> getPlayer2Ships() {
		return player2Ships;
	}

	public void setPlayer2Ships(Hashtable<String, Ship> player2Ships) {
		this.player2Ships = player2Ships;
	}

	private void init() {

	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}	
}
