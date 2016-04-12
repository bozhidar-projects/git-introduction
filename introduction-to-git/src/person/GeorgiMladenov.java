package person;

public class GeorgiMladenov extends AbstractPerson {

	public GeorgiMladenov() {
		super("Georgi Mladenov", 22);
	}

	@Override
	public int specialTrick(int a, int b) {
		return 2*a+5*b;
	}

}
