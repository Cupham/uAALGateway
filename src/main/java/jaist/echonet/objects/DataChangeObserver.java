package jaist.echonet.objects;

public abstract class DataChangeObserver {
	public abstract void dataUpdated(Object obj, String property);
	public abstract void dataObjectUpdated();
}
