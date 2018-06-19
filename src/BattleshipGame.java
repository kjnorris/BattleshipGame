import java.util.Scanner;

public class BattleshipGame {
	private Ocean gameBoard;
	
	public BattleshipGame() {
		// TODO Auto-generated constructor stub
		gameBoard = new Ocean();
	}
	
	public void print() {
		this.gameBoard.print();
		System.out.format("%13s %d %n%13s %d %n%13s %d / 13%n", 
				"Shots Fired:", this.gameBoard.getShotsFired(), 
				"Hits:", this.gameBoard.getHitCount(),
				"Ships Sunk:", this.gameBoard.getShipsSunk());
	}
	
	public void printAll() {
		this.gameBoard.printAll();
		System.out.format("%13s %d %n%13s %d %n%13s %d / 13%n", 
				"Shots Fired:", this.gameBoard.getShotsFired(), 
				"Hits:", this.gameBoard.getHitCount(),
				"Ships Sunk:", this.gameBoard.getShipsSunk());
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\\D\\s*");
		String[] splitArray;
		String[] splitLine;
		int row, column;
		
		BattleshipGame game = new BattleshipGame();
		
		game.gameBoard.placeAllShipsRandomly();
		game.printAll();
		
		System.out.println("Enter five moves as row, column separated by semicolons");
		
		while (!game.gameBoard.isGameOver()) {
			
			System.out.print("Enter the coordinates of your next shot: ");
			splitLine = sc.nextLine().split("\\s*(;)\\s*");
			
			if (splitLine.length != 5) {
				if (splitLine.length == 1) {
					row = Integer.parseInt(splitLine[0].split("\\s*(,|\\s)\\s*")[0]);
					column = Integer.parseInt(splitLine[0].split("\\s*(,|\\s)\\s*")[1]);
					
					if ((row == -1) && (column == -1)) {
						game.gameBoard.setShipsSunk(13);
						game.printAll();
						System.out.println("\n\nThank you for playing!");
					}
				} else {
					System.out.println("Enter five moves as row, column separated by semicolons");
				}
				
			} else {
				for (int i = 0; i < splitLine.length; i++) {
					row = Integer.parseInt(splitLine[i].split("\\s*(,|\\s)\\s*")[0]);
					column = Integer.parseInt(splitLine[i].split("\\s*(,|\\s)\\s*")[1]);
					
					game.gameBoard.shootAt(row, column);
				}
				game.print();
			}
		}

	}
}

