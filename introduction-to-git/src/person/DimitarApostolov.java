package person;

public class DimitarApostolov extends AbstractPerson{

	public DimitarApostolov() {
		super("Dimitar Apostolov", 23);
	}

	@Override
	public int specialTrick(int a, int b) {
		return Math.subtractExact(a, b);
	}
	
	

}
