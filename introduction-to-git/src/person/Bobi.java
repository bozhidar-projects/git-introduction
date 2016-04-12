package person;

public class Bobi extends AbstractPerson {
	
	public Bobi() {
		super("Bobi", 25);
	}

	@Override
	public int specialTrick(int a, int b) {
		return a + b;
	}

}
