
public class Orbital extends Location {

	protected int Radius;
	protected OrbitalRecord OrbitalClass;
	protected StarRecord ParentStar;
	protected TradePort port;

	/*
	 * public Orbital(int radius) { super(); }
	 */
	public Orbital(int radius, byte oclass, int parent) {
		Radius = radius;
		setOrbitalClass(oclass);
		ParentStar = Ops.getStarDetails(parent);
		if (rand.nextInt(TradePort.CHANCE) >= OrbitalClass.GetHazard()) {
			port = new TradePort(this);
		}
	}

	public OrbitalRecord getOrbitalClass() {
		return OrbitalClass;
	}

	public int getRadius() {
		return Radius;
	}

	public String getOrbital() {
		switch (OrbitalClass.GetType()) {
		case 0:
			return "Planet";
		case 1:
			return "Gas Giant";
		case 2:
			return "Ring";
		case 3:
			return "Belt";
		default:
			return "";
		}
	}

	public void setOrbitalClass(byte oclass) {
		OrbitalClass = Ops.getOrbitalClasses(oclass);
	}

	public TradePort getTradePort() {
		return port;
	}

	public String GetDetails() {
		String details = String.format("Class: %s - %s %s",
				OrbitalClass.GetID(), OrbitalClass.GetClassification(),
				getOrbital());
		if (port == null) {
			return details;
		} else {
			return details + " - Trade Port";
		}
	}
}
