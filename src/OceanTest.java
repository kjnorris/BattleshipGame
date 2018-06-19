import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OceanTest {

	@Test
	void placeBattleshipTest() {
		Ocean gameBoard = new Ocean();
		BattleShip shipToPlace = new BattleShip();
		
		// Place the BattleShip at the upper left of the game board
		assertTrue(shipToPlace.okToPlaceShipAt(0, 0, true, gameBoard));
		shipToPlace.placeShipAt(0, 0, true, gameBoard);
		
		// Try to place another ship in the same spot
		assertFalse(shipToPlace.okToPlaceShipAt(0, 0, true, gameBoard));
		assertFalse(shipToPlace.okToPlaceShipAt(1, 0, true, gameBoard));
		assertFalse(shipToPlace.okToPlaceShipAt(1, 8, true, gameBoard));
		
		// Try to place the ship past the edges of the board
		assertFalse(shipToPlace.okToPlaceShipAt(0, 18, true, gameBoard));
		assertFalse(shipToPlace.okToPlaceShipAt(-12, 0, true, gameBoard));
	}
	
	@Test
	void getShipArrayTest() {
		Ocean gameBoard = new Ocean();
		BattleShip shipToPlace = new BattleShip();
		Cruiser unplacedShip = new Cruiser();
		shipToPlace.placeShipAt(0, 0, true, gameBoard);
		assertTrue(gameBoard.getShipArray()[0][0].equals(shipToPlace));
		assertFalse(gameBoard.getShipArray()[0][0].equals(unplacedShip));
	}
	
	@Test 
	void isOccupiedTest() {
		Ocean gameBoard = new Ocean();
		BattleShip shipToPlace = new BattleShip();
		shipToPlace.placeShipAt(0, 0, true, gameBoard);
		assertTrue(gameBoard.isOccupied(0, 0));
		assertTrue(gameBoard.isOccupied(0, 5));
		assertFalse(gameBoard.isOccupied(5, 5));
	}
	
	@Test
	void shootAtTest() {
		Ocean gameBoard = new Ocean();
		BattleShip shipToPlace = new BattleShip();
		shipToPlace.placeShipAt(0, 0, true, gameBoard);
		assertTrue(gameBoard.shootAt(0, 0));
		assertFalse(gameBoard.shootAt(5, 5));
		
		// Sink the BattleShip
		boolean[] hit = shipToPlace.getHit();
		for (int i = 0; i < hit.length; i++) {
			hit[i] = true;
		}
		shipToPlace.setHit(hit);
		assertFalse(gameBoard.shootAt(0, 0));
	}
	
	@Test
	void hitTest() {
		Ocean gameBoard = new Ocean();
		BattleShip shipToPlace = new BattleShip();
		
		boolean[] hit = new boolean[shipToPlace.getLength()];
		for (int i = 0; i < hit.length; i++) {
			hit[i] = false;
		}
		hit[0] = true;
		
		shipToPlace.placeShipAt(0, 0, true, gameBoard);
		gameBoard.shootAt(0, 0);
		assertTrue(shipToPlace.compareHit(hit));

		hit[1] = true;
		assertFalse(shipToPlace.compareHit(hit));
	}
	
	@Test
	void checkHitTest() {
		Ocean gameBoard = new Ocean();
		BattleShip shipToPlace = new BattleShip();
		shipToPlace.placeShipAt(0, 0, true, gameBoard);
		gameBoard.shootAt(0, 0);
		assertTrue(shipToPlace.isShotAt(0, 0));
		assertFalse(shipToPlace.isShotAt(0, 1));
		
		gameBoard.shootAt(5, 5);
		assertTrue(gameBoard.getShipArray()[5][5].isShotAt(5, 5));
		assertFalse(gameBoard.getShipArray()[8][8].isShotAt(8, 8));
	}
	
	@Test
	void gameBoardTest() {
		Ocean gameBoard = new Ocean();
		BattleShip shipToPlace = new BattleShip();
		shipToPlace.placeShipAt(0, 0, true, gameBoard);
		// gameBoard.print();
		
		gameBoard.shootAt(0, 0);
		// gameBoard.print();
		
		gameBoard.shootAt(5, 5);
		assertTrue(gameBoard.getShipArray()[5][5].isShotAt(5, 5));
		// gameBoard.print();
		
		// Sink the BattleShip
		for (int i = 1; i < shipToPlace.getLength(); i++) {
			gameBoard.shootAt(0, i);
		}
		gameBoard.print();
	}

}
