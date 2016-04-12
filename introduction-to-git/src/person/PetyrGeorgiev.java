package person;

public class PetyrGeorgiev extends AbstractPerson {

	public PetyrGeorgiev() {
		super("Petyr Georgiev", 21);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int specialTrick(int a, int b) {
		
		return (a*b + (a+b)*b )/a;
	}

}
