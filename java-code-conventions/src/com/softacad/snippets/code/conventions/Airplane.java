/* class Airplane
 * 
 * Version: 1.0
 * 
 * Date: 13.04.2016
 * 
 * This file is licensed under MIT
 */
package com.softacad.snippets.code.conventions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author bobi
 * 
 * Contains list of {@link Passenger}
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
	
	public Airplane(int seatsCapacity, String planeType, double crouiseFlightHeight, Passenger[] passangers) {
		this.passangers = Arrays.copyOf(passangers, passangers.length);
		this.crouiseFlightHeight = crouiseFlightHeight;
		this.planeType = planeType;
	}
	
	public Airplane(int seatsCapacity, String planeType, double crouiseFlightHeight) {
		this(seatsCapacity, planeType, crouiseFlightHeight, new Passenger[seatsCapacity]);
	}
	
	public Airplane(int seatsCapacity, String planeType) {
		this(seatsCapacity, planeType, DEFAULT_CRUISE_FLIGHT_HEIGHT_KM);
	}
	
}


