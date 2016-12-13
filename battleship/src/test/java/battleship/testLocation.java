package battleship;

import junit.framework.TestCase;

public class testLocation extends TestCase {

	public testLocation(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLocation() {
		Location lc = new Location();
		assertThat(lc, isA(Location.class));
	}

	public void testLocationPointArray() {
		fail("Not yet implemented");
	}

	public void testGetPoints() {
		fail("Not yet implemented");
	}

	public void testSetPoints() {
		fail("Not yet implemented");
	}

	public void testGetSinglePoint() {
		fail("Not yet implemented");
	}

}
