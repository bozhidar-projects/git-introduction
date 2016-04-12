package person;

public class VentseslavMarinov extends AbstractPerson{

	public VentseslavMarinov() {
		super("Ventseslav Marinov", 36);
	}

	@Override
	public int specialTrick(int a, int b) {
		return a * b + (a - b);
	}

}
