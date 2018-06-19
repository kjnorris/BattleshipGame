
public class Submarine extends Ship {

	public Submarine() {
		this.setLength(3);
		boolean[] hit = new boolean[this.getLength()];
		for (int i = 0; i < this.getLength(); i++) {
			hit[i] = false;
		}
		this.setHit(hit);
	}
	
	@Override
	public String getShipType() {
		// TODO Auto-generated method stub
		return "submarine";
	}

}
