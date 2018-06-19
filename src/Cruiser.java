
public class Cruiser extends Ship {

	public Cruiser() {
		this.setLength(6);
		boolean[] hit = new boolean[this.getLength()];
		for (int i = 0; i < this.getLength(); i++) {
			hit[i] = false;
		}
		this.setHit(hit);
	}
	
	@Override
	public String getShipType() {
		// TODO Auto-generated method stub
		return "cruiser";
	}

}
