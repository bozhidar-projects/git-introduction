/* class Airplane
 *
 * Version: 1.0
 *
 * Date: 13.04.2016
 *
 * This file is licensed under MIT
 */
package com.softacad.snippets.code.conventions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author bobi
 *
 *         Contains list of {@link Passenger}
 *
 */
public class Airplane {
	/**
	 * European type of airplane
	 */
	public static final String AIRBUS_TYPE = "Airbus";

	/**
	 * American type of airplane
	 */
	public static final String BOEING_TYPE = "Boeing";

	/**
	 * How many years at max the airplane can be used
	 */
	private static final int YEARS_OF_EXPLOATATION = 35;

	private static final double DEFAULT_CRUISE_FLIGHT_HEIGHT_KM = 12;

	private Pilot firstPilot;
	private Pilot secondPilot;

	private Passenger[] passangers;
	private double crouiseFlightHeight;
	private String planeType;

	private int seatsCapacity;

	/**
	 * Full constructor for the Airplane class
	 *
	 * @param seatsCapacity
	 * @param planeType
	 * @param crouiseFlightHeight
	 * @param passangers
	 *
	 * @throws @{@link
	 * 			IllegalArgumentException} if passangers
	 */

	public Airplane(int seatsCapacity, String planeType, double crouiseFlightHeight, Passenger[] passangers) {
		this.seatsCapacity = seatsCapacity;
		if (passangers.length > seatsCapacity) {
			throw new IllegalArgumentException("Cannot add more passangers then the capacity");
		}
		this.passangers = Arrays.copyOf(passangers, passangers.length);
		this.crouiseFlightHeight = crouiseFlightHeight;
		this.planeType = planeType;
	}

	/**
	 * Initializes an airplane with seats capacity, plane type ane maximum
	 * cruise flight height
	 *
	 * @param seatsCapacity
	 *            the capacity of the plane
	 * @param planeType
	 *            the plane type
	 * @param crouiseFlightHeight
	 *            maximum cruise flight;
	 */
	public Airplane(int seatsCapacity, String planeType, double crouiseFlightHeight) {
		this(seatsCapacity, planeType, crouiseFlightHeight, new Passenger[seatsCapacity]);
	}

	/**
	 * @param seatsCapacity
	 * @param planeType
	 */
	public Airplane(int seatsCapacity, String planeType) {
		this(seatsCapacity, planeType, DEFAULT_CRUISE_FLIGHT_HEIGHT_KM);
	}

	/**
	 * Take offs the plane !
	 *
	 * @throws AirplaneException
	 */
	void takeOff() throws AirplaneException {

		boolean thereIsAutoPilot = false;
		if (firstPilot == null || secondPilot == null || !thereIsAutoPilot) {
			throw new AirplaneException("A pilot is missing");
		}

		if (!firstPilot.readQRH()) {
			throw new AirplaneException("There is a technical issue");
		}
		System.out.println("QRH checklist ok!");

		turnsEnginesOn();
	}

	/**
	 * @return
	 * 	all passengers in the plane
	 */
	public Set<Passenger> getPassengers() {

		//This is the result of the method
		Set<Passenger> result = new HashSet<>();
		for (int i = 0; i < passangers.length; i++) {
			if(passangers[i] != null) {
				result.add(passangers[i]);
			}
		}

		return result;
	}

	public String getInfo() {
		String planeInfo = "Plane Type:" + planeType + "\nCapacity: " + seatsCapacity;
		return planeInfo;
	}

	/**
	 * turns the airplane engines on
	 */
	private void turnsEnginesOn() {
		System.out.println("Turning the engines on");
	}

}
