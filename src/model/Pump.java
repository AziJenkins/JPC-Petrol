package model;

import java.util.Queue;

public class Pump implements Queue{

	public final static double PUMP_CAPACITY = 3;
	public final static double SMALLEST_VEHICLE = 0.75; //this is an awful place to store this
	
	public double spaceUnused;
	private Vehicle[] queue;
	private int head;
	private int tail;
	
	public Pump() {
		this.queue = new Vehicle[(int) Math.ceil(PUMP_CAPACITY * SMALLEST_VEHICLE)];
		this.head = 0;
		this.tail = 0;
		this.spaceUnused = PUMP_CAPACITY;
	}
}
