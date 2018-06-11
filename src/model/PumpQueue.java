package model;

public class PumpQueue {

	private Pump[] pumps;
	
	public PumpQueue(int noPumps) {
		pumps = new Pump[noPumps];
		for (int i = 0; i < noPumps; i++) {
			pumps[i] = new Pump();
		}
	}
}
