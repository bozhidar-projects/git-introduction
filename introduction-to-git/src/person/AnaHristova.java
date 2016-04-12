package person;

public class AnaHristova extends AbstractPerson {

	public AnaHristova() {
		super("Ana Hristova", 32);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int specialTrick(int a, int b) {
		// TODO Auto-generated method stub
		return ((a+b)*super.getName().hashCode())%super.getAge();
	}

}