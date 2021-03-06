package application;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import person.BozhidarDimitrov;
import person.DimitarApostolov;
import person.Person;

public class PrintJavaAdvancedCourseStudents {

	private static final int SPECIAL_TRICK_FIRST_OPERAND = 10;
	private static final int SPECIAL_TRICK_SECOND_OPERAND = 15;

	private static class ByNameAlphabeticallyAsc implements Comparator<Person> {

		@Override
		public int compare(Person person1, Person person2) {
			return person1.getName().compareTo(person2.getName());
		}
		
	}
	
	public static void main(String[] args) {
		Set<Person> peopleInCourse = new TreeSet<>(new ByNameAlphabeticallyAsc());
		addPeopleInClass(peopleInCourse);
		printPeople(peopleInCourse);
	}

	//Another change
	private static void addPeopleInClass(Set<Person> peopleInCourse) {
		//Add your elements here
		peopleInCourse.add(new DimitarApostolov());
		peopleInCourse.add(new BozhidarDimitrov());
	}
	
	private static void printPeople(Set<Person> people) {
		for (Person person : people) {
			System.out.println("-----------------------------");
			System.out.println(person.getInfo());
			System.out.println("Trick: " + person.specialTrick(SPECIAL_TRICK_FIRST_OPERAND, SPECIAL_TRICK_SECOND_OPERAND));
			System.out.println("-----------------------------");
		}
	}
}