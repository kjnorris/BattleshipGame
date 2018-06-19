
public class EmptySea extends Ship {
	public EmptySea()  {
		this.setLength(1);
		boolean[] myHit = new boolean[1];
		myHit[0] = false;
		this.setHit(myHit);
	}

	@Override
	public boolean shootAt(int row, int column) {
		boolean[] hit = this.getHit();
		hit[0] = true;
		this.setHit(hit);
		return false;
	}

	@Override 
	public boolean isSunk() {
		return false;
	}

	@Override
	public String toString() {
		if (this.getHit()[0]) {
			return "-";
		} else {
			return ".";
		}
	}

	@Override
	public String getShipType() {
		return "empty";
	}

}
