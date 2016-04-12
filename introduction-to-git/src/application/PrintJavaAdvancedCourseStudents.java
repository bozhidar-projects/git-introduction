package application;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import person.Person;
import person.PetyrGeorgiev;

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

	private static void addPeopleInClass(Set<Person> peopleInCourse) {
		peopleInCourse.add(new PetyrGeorgiev());
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
