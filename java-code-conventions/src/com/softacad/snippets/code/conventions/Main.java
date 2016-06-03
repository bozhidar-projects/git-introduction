package com.softacad.snippets.code.conventions;

public class Main {

	private static final int AIRBUS_PRICE = 10000;
	private static final int AIR_JET_PRICE = 50000;

	public static void main(String[] args) {
		Airplane airplane = new Airplane(180, Airplane.AIRBUS_TYPE);

		Airplane su37 = new Airplane(2, Airplane.AIR_JET_TYPE, 13.2);

		printPrice(airplane);
		printPrice(su37);
	}

	private static void printPrice(Airplane airplane) {
		String airplaneType = airplane.getPlaneType();
		switch (airplaneType) {
		case Airplane.AIR_JET_TYPE:
			System.out.println(AIR_JET_PRICE);
			break;
		case Airplane.AIRBUS_TYPE:
			System.out.println(AIRBUS_PRICE);
			break;
		case Airplane.BOEING_TYPE:
			System.out.println(134000);
			break;
		default:
			throw new IllegalArgumentException("Unknown airplane type");
		}
	}

}
