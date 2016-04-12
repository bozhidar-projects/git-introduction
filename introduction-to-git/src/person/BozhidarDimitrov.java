package person;

public class BozhidarDimitrov extends AbstractPerson {

	public BozhidarDimitrov() {
		super("Bozhidar Dimitrov", 26);
	}

	@Override
	public int specialTrick(int a, int b) {
		return a + b;
	}

}
