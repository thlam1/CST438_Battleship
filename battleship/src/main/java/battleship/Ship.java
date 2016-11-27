package battleship;

import java.util.UUID;

public class Ship {
	
	UUID ship_id = null;
	String type = null;
	int spaces = 0;
	Location location = null;

	public Ship(String type, int spaces, UUID ship_id) {		
		this.type = type;
		this.spaces = spaces;
		this.ship_id = ship_id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSpaces() {
		return spaces;
	}
	public void setSpaces(int spaces) {
		this.spaces = spaces;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "{\"type\":\"" + type + "\", \"spaces\":\"" + spaces + "\"}";
	}
	
	
}
