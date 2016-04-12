package person;

public class EmilStanimirov extends AbstractPerson {

	public EmilStanimirov() {
		super("EmilStanimirov", 26);
	}

	@Override
	public int specialTrick(int a, int b) {
		return a + b;
	}

}