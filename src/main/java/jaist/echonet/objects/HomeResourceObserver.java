package jaist.echonet.objects;


import org.universAAL.ontology.echonetontology.airconditionerRelatedDevices.HomeAirConditioner;
import org.universAAL.ontology.echonetontology.housingFacilitiesRelatedDevices.GeneralLighting;
import org.universAAL.ontology.echonetontology.managementOperationRelatedDevices.Switch;
import org.universAAL.ontology.echonetontology.sensorRelatedDevices.TemperatureSensor;

import jaist.mainpackage.Activator;
import jaist.mainpackage.CaressesOntology;
import jaist.utils.OntologyHelper;

public class HomeResourceObserver extends DataChangeObserver{
	public HomeResourceObserver(EchonetLiteDevice eDevice) {
		eDevice.getProfileObj().attach(this);
		for(eDataObject dataObj :eDevice.getDataObjList()) {
			dataObj.attach(this);
		}
	}

	@Override
	public void dataUpdated(Object obj, String property) {
		if(obj.getClass().equals(eTemperatureSensor.class)) {
			eTemperatureSensor sensor = (eTemperatureSensor) obj;
			String uriSuffix =  sensor.getNode().getNodeInfo().toString() + "_"+sensor.getInstanceCode(); 
			String URI = CaressesOntology.NAMESPACE +"I_TemperatureSensor"+uriSuffix;
			if(Activator.temperatureSensorOntologies.containsKey(URI)) {
				Activator.i_TemperatureSensor = Activator.temperatureSensorOntologies.get(URI);
				OntologyHelper.updateTemperatureOntologyProperty(sensor, Activator.i_TemperatureSensor, property);

			} else {
				Activator.i_TemperatureSensor = new TemperatureSensor(URI);
				OntologyHelper.initTemperatureSensorOntology(sensor, Activator.i_TemperatureSensor);
			}
			Activator.temperatureSensorOntologies.put(URI, Activator.i_TemperatureSensor);
			Activator.changedOntologies.add(URI);
			
		} else if(obj.getClass().equals(eAirConditioner.class)) {
			eAirConditioner aircon = (eAirConditioner) obj;
			String uriSuffix =  aircon.getNode().getNodeInfo().toString() + "_"+aircon.getInstanceCode(); 
			String URI = CaressesOntology.NAMESPACE +"I_Airconditioner"+uriSuffix;
			if(Activator.homeAirconditionerOntologies.containsKey(URI)) {
				Activator.i_HomeAirConditoner = Activator.homeAirconditionerOntologies.get(URI);
				OntologyHelper.updateAirconditionerOntologyProperty(aircon, Activator.i_HomeAirConditoner, property);
			} else {
				Activator.i_HomeAirConditoner = new HomeAirConditioner(URI);
				OntologyHelper.initHomeAirconditionerOntology(aircon, Activator.i_HomeAirConditoner);
			}
			Activator.homeAirconditionerOntologies.put(URI, Activator.i_HomeAirConditoner);
			Activator.changedOntologies.add(URI);
			
		}else if(obj.getClass().equals(eLighting.class)) {
			eLighting lighting = (eLighting) obj;
			String uriSuffix =  lighting.getNode().getNodeInfo().toString() + "_"+lighting.getInstanceCode(); 
			String URI = CaressesOntology.NAMESPACE +"I_Lighting"+uriSuffix;
			if(Activator.lightingOntologies.containsKey(URI)) {
				Activator.i_Lighting = Activator.lightingOntologies.get(URI);
				OntologyHelper.updateLightingOntologyProperty(lighting, Activator.i_Lighting, property);

			} else {
				Activator.i_Lighting = new GeneralLighting(URI);
				OntologyHelper.initLightingOntology(lighting, Activator.i_Lighting);
			}
			Activator.lightingOntologies.put(URI, Activator.i_Lighting);
			Activator.changedOntologies.add(URI);
		} else if(obj.getClass().equals(eElectricConsent.class)) {
			eElectricConsent consent = (eElectricConsent) obj;
			String uriSuffix =  consent.getNode().getNodeInfo().toString() + "_"+consent.getInstanceCode(); 
			String URI = CaressesOntology.NAMESPACE +"I_Consent"+uriSuffix;
			if(Activator.consentOntologies.containsKey(URI)) {
				Activator.i_Consent = Activator.consentOntologies.get(URI);
				OntologyHelper.updateConsentOntologyProperty(consent, Activator.i_Consent, property);
			} else {
				Activator.i_Consent = new Switch(URI);
				OntologyHelper.initConsentntology(consent, Activator.i_Consent);
			}
			Activator.consentOntologies.put(URI, Activator.i_Consent);
			Activator.changedOntologies.add(URI);
		}
		
		
	}

	@Override
	public void dataObjectUpdated() {
		// TODO Auto-generated method stub
		
	}
	

}
