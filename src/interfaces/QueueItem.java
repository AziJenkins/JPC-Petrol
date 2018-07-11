package interfaces;

public interface QueueItem {

	public default double getSize() {
		return 1;
	}
}
