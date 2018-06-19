import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sun.invoke.empty.Empty;

class ShipTest {

	@Test
	void getLengthTest() {
		BattleShip myBShip = new BattleShip();
		BattleCruiser myBCruiser = new BattleCruiser();
		Cruiser myCruiser = new Cruiser();
		LightCruiser myLCruiser = new LightCruiser();
		Destroyer myDestroyer = new Destroyer();
		Submarine mySubmarine = new Submarine();
		EmptySea myEmpty = new EmptySea();
		
		assertEquals(myBShip.getLength(), 8);
		assertEquals(myBCruiser.getLength(), 7);
		assertEquals(myCruiser.getLength(), 6);
		assertEquals(myLCruiser.getLength(), 5);
		assertEquals(myDestroyer.getLength(), 4);
		assertEquals(mySubmarine.getLength(), 3);
		assertEquals(myEmpty.getLength(), 1);
	}
	
	@Test
	void getTypeTest() {
		BattleShip myBShip = new BattleShip();
		BattleCruiser myBCruiser = new BattleCruiser();
		Cruiser myCruiser = new Cruiser();
		LightCruiser myLCruiser = new LightCruiser();
		Destroyer myDestroyer = new Destroyer();
		Submarine mySubmarine = new Submarine();
		EmptySea myEmpty = new EmptySea();
		
		assertEquals(myBShip.getShipType(), "battleship");
		assertEquals(myBCruiser.getShipType(), "battlecruiser");
		assertEquals(myCruiser.getShipType(), "cruiser");
		assertEquals(myLCruiser.getShipType(), "light cruiser");
		assertEquals(myDestroyer.getShipType(), "destroyer");
		assertEquals(mySubmarine.getShipType(), "submarine");
		assertEquals(myEmpty.getShipType(), "empty");
	}

}
