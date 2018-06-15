package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

import utils.CircularArrayQueue;

public class Till {
	
	public final static int MAX_QUEUE_SIZE = 5; //completely random choice - needs rethink

	private CircularArrayQueue<Customer> queue;
	
	public Till() {
		queue = new CircularArrayQueue<Customer>();
	}
	
	public Payment collectPayment() {
		return null;
	}
	
	public void reduceTimers() {
		
	}

	public CircularArrayQueue<Customer> getQueue() {
		return this.queue;
	}
}
