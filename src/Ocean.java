import java.util.Random;

public class Ocean {
	public Ship[][] ships;
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
	public Ocean() {
		// TODO Auto-generated constructor stub
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
		this.ships = new Ship[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				this.ships[i][j] = new EmptySea();
			}
		}
	}
	

	public int getShotsFired() {
		return shotsFired;
	}

	public int getHitCount() {
		return hitCount;
	}

	public int getShipsSunk() {
		return shipsSunk;
	}
	
	public void setShipsSunk(int sunkShips) {
		this.shipsSunk = sunkShips;
	}

	public boolean isGameOver() {
		return this.getShipsSunk() == 13;
	}

	public void print() {
		System.out.print("   ");
		for (int i = 0; i < 20; i++) {
			System.out.format("%3d", i);
		}
		System.out.print("\n");
		
		for (int i = 0; i < 20; i++) {
			System.out.format("%3d",  i);
			for (int j = 0; j < 20; j++) {
				if (ships[i][j].isShotAt(i, j)) {
					System.out.format("%3s", ships[i][j]);
				} else {
					System.out.format("%3s", ".");
				}
			}
			System.out.print("\n");;
		}
		System.out.println("\n\n");
	}
	
	public Ship[][] getShipArray() {
		return this.ships;
	}
	
	public boolean isOccupied(int row, int column) {
		return this.getShipArray()[row][column].getShipType() != "empty";
	}
	
	public void placeAShip(Ship currShip) {
		int row;
		int col;
		boolean canPlace = false;
		boolean placeHorizontal;
		Random rand = new Random();
		
		while (!canPlace) {
			row = rand.nextInt(20);
			col = rand.nextInt(20);
			if (rand.nextInt(2) == 1) {
				placeHorizontal = true;
			} else {
				placeHorizontal = false;
			}
			if (currShip.okToPlaceShipAt(row, col, placeHorizontal, this)) {
				currShip.placeShipAt(row,  col, placeHorizontal, this);
				canPlace = true;
			}
		}
	}
	
	public void placeAllShipsRandomly() {
		Ship myShip;
		
		// Place a battleship
		myShip = new BattleShip();
		this.placeAShip(myShip);

		// Place a battlecruiser
		myShip = new BattleCruiser();
		this.placeAShip(myShip);

		// Place two cruiser
		for (int i = 1; i <= 2; i ++) {
			myShip = new Cruiser();
			this.placeAShip(myShip);
		}
		
		// Place two lightcruiser
		for (int i = 1; i <= 2; i ++) {
			myShip = new LightCruiser();
			this.placeAShip(myShip);
		}
		
		// Place three destroyer
		for (int i = 1; i <= 3; i ++) {
			myShip = new Destroyer();
			this.placeAShip(myShip);
		}
		
		// Place four submarine
		for (int i = 1; i <= 3; i ++) {
			myShip = new Submarine();
			this.placeAShip(myShip);
		}

	}	

	public boolean shootAt(int row, int column) {
		boolean sunkYet = this.getShipArray()[row][column].isSunk();
		this.shotsFired++;
		
		if (this.getShipArray()[row][column].getShipType() == "empty") {
			this.getShipArray()[row][column].shootAt(row, column);
			return false;
		} else {
			this.hitCount++;
			boolean hitShip = this.getShipArray()[row][column].shootAt(row, column);
			if (!sunkYet && this.getShipArray()[row][column].isSunk()) {
				this.shipsSunk++;
				System.out.format("You sank my %s! %n%n", this.getShipArray()[row][column].getShipType());
			}
			return hitShip;
		}
	}
	
	public void printAll() {
		System.out.print("   ");
		for (int i = 0; i < 20; i++) {
			System.out.format("%3d", i);
		}
		System.out.print("\n");
		
		for (int i = 0; i < 20; i++) {
			System.out.format("%3d",  i);
			for (int j = 0; j < 20; j++) {
				System.out.format("%3s", ships[i][j]);
			}
			System.out.print("\n");;
		}
		System.out.println("\n\n");
	}
}

