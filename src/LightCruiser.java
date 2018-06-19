
public class LightCruiser extends Ship {

	public LightCruiser() {
		this.setLength(5);
		boolean[] hit = new boolean[this.getLength()];
		for (int i = 0; i < this.getLength(); i++) {
			hit[i] = false;
		}
		this.setHit(hit);
	}
	
	@Override
	public String getShipType() {
		// TODO Auto-generated method stub
		return "lightcruiser";
	}

}
