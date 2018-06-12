package model;

public class Till implements Queue {
	
	public final static int MAX_QUEUE_SIZE = 5; //completely random choice - needs rethink

	private Customer[] queue;
	private int head;
	private int tail;
	
	public Till() {
		this.queue = new Customer[MAX_QUEUE_SIZE];
		this.head = 0;
		this.tail = 0;
	}
}
