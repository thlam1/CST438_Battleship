package battleship;

public class GameModel {
	/* valid game states:
	 * 1 = setup
	 * 2 = in play
	 * 3 = results
	 */
	private int gameState = 1;
	
	/*
	 * Whose turn is it? (1 or 2)
	 */
	private int playerTurn = 1;
	
	/*
	 * Players
	 */
	private Player p1;
	private Player p2;
	
	public GameModel() {
		this.gameState = 1;
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
	
	public Player getPlayer1() {
		return p1;
	}
	
	public void addPlayer1(Player p) {
		p1 = p;
	}
	
	public Player getPlayer2() {
		return p2;
	}
	
	public void addPlayer2(Player p) {
		p2 = p;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}	
}