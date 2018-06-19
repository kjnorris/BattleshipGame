
public class BattleShip extends Ship {

	public BattleShip() {
		this.setLength(8);
		boolean[] hit = new boolean[this.getLength()];
		for (int i = 0; i < this.getLength(); i++) {
			hit[i] = false;
		}
		this.setHit(hit);
	}

	@Override
	public String getShipType() {
		// TODO Auto-generated method stub
		return "battleship";
	}

}
