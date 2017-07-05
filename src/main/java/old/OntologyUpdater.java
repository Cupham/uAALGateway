package old;

import echonet.objects.EchonetLiteDevice;

public class OntologyUpdater {
	public OntologyUpdater() {
		
	}
	
	public static void updateOntology (String ontologyID, Object data) {
		
		switch (ontologyID) {
			case Constants.TEMPERATURE_EVENT:
				//Activator.temperatureSensor.setData((EchonetLiteDevice) data);
				break;
			default:
				break;
			}	
	}

}
