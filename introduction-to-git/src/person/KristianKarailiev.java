package person;

public class KristianKarailiev extends AbstractPerson {
	
	public KristianKarailiev(){
		super("Kristian Karailiev", 21);
	}

	@Override
	public int specialTrick(int a, int b) {
		return a*a + b*b;
	}

}
