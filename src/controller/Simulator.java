package controller;

import java.util.Random;

import view.TextBasedInterface;

import model.PetrolStation;
import model.Vehicle;

public class Simulator {

	public static final double INITIAL_T = 0.02;
	private PetrolStation station;
	private final double p;
	private final double q;
	private double t;
	private boolean trucksAllowed;
	public static Random rand;
	public TextBasedInterface view;
	
	public Simulator() {
		this.view = new TextBasedInterface();
		view.start();
		this.p = view.getP();
		this.q = view.getQ();
		if (view.getTrucksAllowed()) {
			t = INITIAL_T;
		}
		int numPumps = view.getNumPumps();
		int numTills = view.getNumTills();
		this.station = new PetrolStation(numPumps, numTills);
	}

	public void tick() {
		
		
	}
	
	public Vehicle rollForVehicle() {
		return null;
	}
	
	public void pushVehicle(Vehicle v) {
		
	}
	
	public PetrolStation getStation() {
		return this.station;
	}
	
}
