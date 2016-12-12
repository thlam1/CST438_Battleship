package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.Random;
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

		// Create a new game
		UUID newGameId = UUID.randomUUID();
		GameModel newGame = new GameModel(1);

		/* note the 1990 Milton Bradley version of the rules specifies the following ships */

		// Create player 1's ships
		Hashtable<String, Ship> p1 = new Hashtable<String, Ship>();		
		p1.put("Carrier", new Ship("Carrier", 5));
		p1.put("Battleship", new Ship("Battleship", 4));
		p1.put("Cruiser", new Ship("Cruiser", 3));
		p1.put("Submarine", new Ship("Submarine", 3));
		p1.put("Destroyer", new Ship("Destroyer", 2));

		// Create player 2's ships
		Hashtable<String, Ship> p2 = new Hashtable<String, Ship>();
		p2.put("Carrier", new Ship("Carrier", 5));
		p2.put("Battleship", new Ship("Battleship", 4));
		p2.put("Cruiser", new Ship("Cruiser", 3));
		p2.put("Submarine", new Ship("Submarine", 3));
		p2.put("Destroyer", new Ship("Destroyer", 2));

		// Add the ship tables to the game object
		newGame.setPlayer1Ships(p1);
		newGame.setPlayer2Ships(p2);
		
		// Automatically select ship locations
		placeShips(newGame,1);
		placeShips(newGame,2);

		// Store the game in the games Hashtable
		games.put(newGameId, newGame);
		
		//Test returning ship
		String playerShips = "{\"Ships\": [{\"ship\": \"Submarine\",\"hits\": \"1\",\"location\": [{\"x\": 1,\"y\": 1,\"hit\": false}, {\"x\": 1,\"y\": 2,\"hit\": true}, {\"x\": 1,\"y\": 3,\"hit\": false}]},{\"ship\":\"Destroyer\",\"hits\": \"1\",\"location\": [{\"x\": 3,\"y\": 1,\"hit\": false}, {\"x\": 4,\"y\": 1,\"hit\": true}]}]}";
	    String opponentShips = "{\"Ships\": [{\"ship\": \"Submarine\",\"hits\": \"1\",\"location\": [{\"x\": 1,\"y\": 1,\"hit\": false}, {\"x\": 1,\"y\": 2,\"hit\": true}, {\"x\": 1,\"y\": 3,\"hit\": false}]},{\"ship\":\"Destroyer\",\"hits\": \"1\",\"location\": [{\"x\": 3,\"y\": 1,\"hit\": false}, {\"x\": 4,\"y\": 1,\"hit\": true}]}]}";
		JSONObject json = new JSONObject();
		json.put("game_id", newGameId.toString());
		json.put("player_fleet", playerShips);
		json.put("opponent_fleet", opponentShips);
		
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
	
	private boolean placeShips(GameModel game,int playerNumber) {
		// Get the player's table of ships and game grid
		Hashtable<String,Ship> ships;
		if (playerNumber == 1) {
			ships = game.getPlayer1Ships();
		} else if (playerNumber == 2) {
			ships = game.getPlayer2Ships();
		} else {
			// Return false if an invalid player number is provided
			return false;
		}

		// Iterate through the Hashtable of ships to place them
		Set<String> keys = ships.keySet();
		for(String key : keys){
			Ship currentShip = ships.get(key);

			int length = currentShip.getHits();

			// Select a random number 0-1 to determine orientation of ship
			Random rand = new Random();
			int orientation = rand.nextInt(2);

			// Loop picking random locations until we find one that is not already occupied
			while (true) {
				Point[] currentPoints = new Point[length];

				if (orientation == 0) {
					// Horizontal orientation
					// Select a random starting point
					int x = rand.nextInt(10 - length); // subtract the length so it won't overflow the board
					int y = rand.nextInt(10);					
					
					// Create points
					for (int i = 0; i < length; i++) {
						currentPoints[i] = new Point(x+i,y,false);
					}
				} else {
					// Vertical orientation
					// Select a random starting point
					int x = rand.nextInt(10);
					int y = rand.nextInt(10 - length); // subtract the length so it won't overflow the board
					
					// Create points
					for (int i = 0; i < length; i++) {
						currentPoints[i] = new Point(x,y+i,false);
					}
				}

				// Check if there is already a ship in this location
				boolean shipOverlaps = false;
				for (String k : keys) {
					if (shipOverlaps) break; // stop checking if we already know one overlaps
					
					// Must check each point of each of the other ships
					Ship otherShip = ships.get(k);			
					if (otherShip.getLocation() != null) {
						// Get the points in the other ship
						Point[] otherPoints = otherShip.getLocation().getPoints();
						
						// Check each point
						for (Point cp : currentPoints) {
							for (Point op : otherPoints) {
								if (cp.getX() == op.getX() && cp.getY() == op.getY()) {
									shipOverlaps = true;
								}
							}
						}
					}	
				}

				// If no ships overlap, set the location for this ship
				// Otherwise, while loop will restart
				if (shipOverlaps == false) {
					currentShip.setLocation(new Location(currentPoints));
				}
			}
	}
	return true;
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
