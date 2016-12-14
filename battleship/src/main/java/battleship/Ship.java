package battleship;

public class Ship {
	
	String type = null;
	int hits = 0;
	Location location = null;
	
	public Ship() {
		
	}

	public Ship(String type, int hits) {		
		this.type = type;
		this.hits = hits;
		this.location = new Location();
	}
	
	public Ship(String type, int hits, Location location) {
		this.type = type;
		this.hits = hits;
		this.location = location;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	public boolean decrementHits() {
		int h = this.getHits();
		
		if(h > 0) {
			this.setHits(h - 1);
			return true;
		}
		
		return false;
		
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "{\"ship\":\"" + type + "\", \"hits\":\"" + hits + "\", \"location\":" + this.location.toString() + "}";
	}
	
	
}
