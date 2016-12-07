package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class GameController
 */
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Hashtable<UUID, GameModel> games = new Hashtable<UUID, GameModel>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GameController() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RestRequest resourceValues = new RestRequest(request.getPathInfo());

		UUID gameId = resourceValues.getId();

		if (gameId.equals(null)) {
			throw new IllegalArgumentException("A valid game id is required");
		}
		
		GameModel game = games.get(gameId);
		
		JSONObject json = new JSONObject();
		json.put("game_id", gameId.toString());
		json.put("game_state", game.getGameState());
		json.put("player1_ships", game.getPlayer1Ships());
		json.put("player2_ships", game.getPlayer2Ships());

		response.addHeader("Content-Type", "application/json");
		
		response.getWriter().append(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UUID newGameId = UUID.randomUUID();
		GameModel newGame = new GameModel(1);

		/*
		 * The 1990 Milton Bradley version of the rules specify the following
		 * ships:
		 */

		Hashtable<String, Ship> p1 = new Hashtable<String, Ship>();

		p1.put("Carrier", new Ship("Carrier", 5));
		p1.put("Battleship", new Ship("Battleship", 4));
		p1.put("Cruiser", new Ship("Cruiser", 3));
		p1.put("Submarine", new Ship("Submarine", 3));
		p1.put("Destroyer", new Ship("Destroyer", 2));

		Hashtable<String, Ship> p2 = new Hashtable<String, Ship>();
		p2.put("Carrier", new Ship("Carrier", 5));
		p2.put("Battleship", new Ship("Battleship", 4));
		p2.put("Cruiser", new Ship("Cruiser", 3));
		p2.put("Submarine", new Ship("Submarine", 3));
		p2.put("Destroyer", new Ship("Destroyer", 2));

		newGame.setPlayer1Ships(p1);
		newGame.setPlayer2Ships(p2);

		games.put(newGameId, newGame);

		JSONObject json = new JSONObject();
		json.put("game_id", newGameId.toString());

		response.addHeader("Content-Type", "application/json");

		response.getWriter().append(json.toString());
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RestRequest resourceValues = new RestRequest(request.getPathInfo());

		UUID gameId = resourceValues.getId();

		if (gameId.equals(null)) {
			throw new IllegalArgumentException("A valid game id is required");
		}
		
		GameModel game = games.get(gameId);

		StringBuffer body = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				body.append(line);
		} catch (Exception e) {
			throw new IOException("Error processing request string");
		}

		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(body.toString());
			
			switch(game.getGameState()) {
				// In game setup
				case 1:
//					if(jsonObject.get("player1_ships") != null) {
//						JSONObject p1_s = (JSONObject) jsonObject.get("player1_ships");
//						Hashtable<String, Ship> p1Ships = game.getPlayer1Ships();
//						Set<String> keys = p1Ships.keySet();
//						for(String key: keys){
//							JSONObject ship = (JSONObject) p1_s.get(key);
//							JSONObject location = (JSONObject) ship.get("location");
//							int x = Integer.parseInt(location.get("x").toString());
//							int y = Integer.parseInt(location.get("y").toString());
//							String o = location.get("orientation").toString();
//							
//							Location loc = new Location(x, y, o);
//							
//							Ship sp = p1Ships.get(key);
//							sp.setLocation(loc);
//						}
//					}
//					
//					if(jsonObject.get("player2_ships") != null) {
//						JSONObject p2_s = (JSONObject) jsonObject.get("player2_ships");
//						Hashtable<String, Ship> p2Ships = game.getPlayer2Ships();
//						Set<String> keys = p2Ships.keySet();
//						for(String key: keys){
//							JSONObject ship = (JSONObject) p2_s.get(key);
//							JSONObject location = (JSONObject) ship.get("location");
//							int x = Integer.parseInt(location.get("x").toString());
//							int y = Integer.parseInt(location.get("y").toString());
//							String o = location.get("orientation").toString();
//							
//							Location loc = new Location(x, y, o);
//							
//							Ship sp = p2Ships.get(key);
//							sp.setLocation(loc);
//						}
//					}

				break;
				// In game play
				case 2:
					// TODO
				break;
				// In game results (we have a winner and loser)
				case 3:
					// TODO
				break;
			}

		} catch (org.json.simple.parser.ParseException e) {
			throw new IOException("Error parsing JSON request string");
		}

		JSONObject json = new JSONObject();
		json.put("game_id", gameId.toString());
		json.put("game_state", game.getGameState());
		json.put("player1_ships", game.getPlayer1Ships());
		json.put("player2_ships", game.getPlayer2Ships());

		response.addHeader("Content-Type", "application/json");

		response.getWriter().append(json.toString());
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private class RestRequest {
		// Accommodate two requests, one for all resources, another for a
		// specific resource
		private Pattern regExAllPattern = Pattern.compile("/b");
		private Pattern regExIdPattern = Pattern
				.compile("/([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})");

		private UUID id;

		public RestRequest(String pathInfo) throws ServletException {
			// regex parse pathInfo
			Matcher matcher;

			// Check for ID case first, since the All pattern would also match
			matcher = regExIdPattern.matcher(pathInfo);
			if (matcher.find()) {
				this.id = UUID.fromString(matcher.group(1));
				return;
			}

			matcher = regExAllPattern.matcher(pathInfo);
			if (matcher.find())
				return;

			throw new ServletException("Invalid URI");
		}

		public UUID getId() {
			return this.id;
		}
	}
}
