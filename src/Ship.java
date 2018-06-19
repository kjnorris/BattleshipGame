
public abstract class Ship {
	private int bowRow;
	private int bowColumn;   
	private int length;
	private boolean horizontal;
	private boolean[] hit;
	
	abstract String getShipType();
	
	public int getBowRow() {
		return bowRow;
	}

	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}

	public int getBowColumn() {
		return bowColumn;
	}

	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public boolean[] getHit() {
		return hit;
	}

	public void setHit(boolean[] hit) {
		this.hit = hit;
	}

	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		int minCol = 0;
		int maxCol = 20;
		int minRow = 0;
		int maxRow = 20;
		
		if ((row < 0) || (row > 19) || (column < 0) || (column > 19))  {
			return false;
		}
		
		if (horizontal) {
			if (column + this.getLength() - 1 > 19) {
				return false;
			} else if (column == 0) {
				minCol = 0;
				maxCol = this.getLength();
			} else if (column + this.getLength() == 20) {
				minCol = column - 1;
				maxCol = 19;
			} else {
				minCol = column - 1;
				maxCol = column + this.getLength();
			}
			
			if (row == 0) {
				minRow = 0;
				maxRow = 1;
			} else if (row == 19) {
				minRow = 18;
				maxRow = 19;
			} else {
				minRow = row - 1;
				maxRow = row + 1;
			}
		} else {
			if (row + this.getLength() - 1 > 19) {
				return false;
			} else if (row == 0) {
				minRow = 0;
				maxRow = this.getLength();
			} else if (row + this.getLength() == 20) {
				minRow = row - 1;
				maxRow = 19;
			} else {
				minRow = row - 1;
				maxRow = row + this.getLength();
			}
			
			if (column == 0) {
				minCol = 0;
				maxCol = 1;
			} else if (column == 19) {
				minCol = 18;
				maxCol = 19;
			} else {
				minCol = column - 1;
				maxCol = column + 1;
			}
		}
		
		for (int i = minRow; i <= maxRow; i++) {
			for (int j = minCol; j <= maxCol; j++) {
				if (ocean.isOccupied(i, j)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);

		if (horizontal) {
			for (int i = column; i < column + this.getLength(); i++) {
				ocean.getShipArray()[row][i] = this;
			}
		} else {
			for (int i = row; i < row + this.getLength(); i++) {
				ocean.getShipArray()[i][column] = this;
			}
		}
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	boolean shootAt(int row, int column) {
		if (!this.isSunk()) {
			boolean[] hit = this.getHit();
			if (this.isHorizontal() && (row == this.getBowRow())) {
				hit[column - this.getBowColumn()] = true;
				this.setHit(hit);
				return true;
			} else if(column == this.getBowColumn()) {
				hit[row - this.getBowRow()] = true;
				this.setHit(hit);
				return true;
			}
		}
		return false;
	}
	
	boolean isSunk() {
		boolean sunkShip = true;
		for (int i = 0; i < this.getHit().length; i++) {
			if (this.getHit()[i] == false) {
				sunkShip = false;
			}
		}
		return sunkShip;
	}
	
	public String toString() {
		if (this.isSunk()) {
			return "x";
		} else {
			return "S";
		}
	}
	
	public boolean euqals(Object obj) {
		Ship checkShip = (Ship) obj;
		return this.getShipType() == checkShip.getShipType();
	}
	
	public boolean compareHit(boolean[] hit) {
		if (this.getLength() != hit.length) {
			return false;
		} else {
			for (int i = 0; i < this.getLength(); i++) {
				if (this.getHit()[i] != hit[i]) {
					return false;
				}
			}
			return true;
		}
	}
	
	public boolean isShotAt(int row, int column) {
		if (this.getShipType() == "empty") {
			return this.getHit()[0];
		}
		
		if (this.isHorizontal()) {
			return this.getHit()[column - this.getBowColumn()];
		} else {
			return this.getHit()[row - this.getBowRow()];
		}
	}
	
	
}
