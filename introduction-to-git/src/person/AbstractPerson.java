package person;

public abstract class AbstractPerson implements Person {
	private String name;
	private int age;
	
	public AbstractPerson(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public String getInfo() {
		return "Name: " + getName() + "\nAge: " + getAge();
	}
}
