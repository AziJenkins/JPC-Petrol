package controller;

import java.util.Random;

import interfaces.Tickable;
import model.PetrolStation;

public class Simulator {

	
	private PetrolStation station;
	private final double p;
	private final double q;
	private double t;
	public static Random rand;
	
	public Simulator(double p, double q, double t, int numPumps, int numTills) {
		this.p = p;
		this.q = q;
		this.t = t;
		this.station = new PetrolStation(numPumps, numTills);
	}

	public void tick() {
		
		
	}
}
