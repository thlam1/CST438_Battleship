package battleship;

import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


/**
 * Servlet implementation class GameController
 */
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Hashtable games = new Hashtable();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameController() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UUID newGameId = UUID.randomUUID();
		GameModel newGame = new GameModel(1);
		
		/*
		 * The 1990 Milton Bradley version of the rules specify the following ships:
		 */
		UUID tmp = UUID.randomUUID();
		
		Hashtable p1 = new Hashtable();
		
		p1.put(tmp, new Ship("Carrier", 5, tmp));
		tmp = UUID.randomUUID();
		p1.put(tmp, new Ship("Battleship", 4, tmp));
		tmp = UUID.randomUUID();
		p1.put(tmp, new Ship("Cruiser", 3, tmp));
		tmp = UUID.randomUUID();
		p1.put(tmp, new Ship("Submarine", 3, tmp));
		tmp = UUID.randomUUID();
		p1.put(tmp, new Ship("Destroyer", 2, tmp));
		
		Hashtable p2 = new Hashtable();
		tmp = UUID.randomUUID();
		p2.put(tmp, new Ship("Carrier", 5, tmp));
		tmp = UUID.randomUUID();
		p2.put(tmp, new Ship("Battleship", 4, tmp));
		tmp = UUID.randomUUID();
		p2.put(tmp, new Ship("Cruiser", 3, tmp));
		tmp = UUID.randomUUID();
		p2.put(tmp, new Ship("Submarine", 3, tmp));
		tmp = UUID.randomUUID();
		p2.put(tmp, new Ship("Destroyer", 2, tmp));
		
		newGame.setPlayer1Ships(p1);
		newGame.setPlayer2Ships(p2);
		
		games.put(newGameId, newGame);
		
		JSONObject json = new JSONObject();
		json.put("game_id", newGameId);
		json.put("player1_ships", newGame.getPlayer1Ships().toString());
		json.put("player2_ships", newGame.getPlayer2Ships().toString());
		json.put("grid", newGame.getPoints().toString());
		
		response.getWriter().append(json.toString());
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
