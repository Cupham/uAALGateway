package mainpackage;

public class OntologyUpdater {
	
	public OntologyUpdater(){
		
	}

	public static void updateOntology(String message_type_ID, String message_content){
		
		switch (message_type_ID){
			case "D1.1":
				Activator.i_D1_1.setMessage(message_content);
				break;
			case "D1.2":
				Activator.i_D1_2.setMessage(message_content);
				break;
			case "D2.1":
				Activator.i_D2_1.setMessage(message_content);
				break;
			case "D2.2":
				Activator.i_D2_2.setMessage(message_content);
				break;
			case "D2.3":
				Activator.i_D2_3.setMessage(message_content);
				break;
			case "D2.4":
				Activator.i_D2_4.setMessage(message_content);
				break;
			case "D3.1":
				Activator.i_D3_1.setMessage(message_content);
				break;
			case "D3.2":
				Activator.i_D3_2.setMessage(message_content);
				break;
			case "D4.1":
				Activator.i_D4_1.setMessage(message_content);
				break;
			case "D4.2":
				Activator.i_D4_2.setMessage(message_content);
				break;
			case "D5.1":
				Activator.i_D5_1.setMessage(message_content);
				break;
			case "D5.2":
				Activator.i_D5_2.setMessage(message_content);
				break;
			case "D6.1":
				Activator.i_D6_1.setMessage(message_content);
				break;
			case "D6.2":
				Activator.i_D6_2.setMessage(message_content);
				break;
			case "D6.3":
				Activator.i_D6_3.setMessage(message_content);
				break;
			case "D7.1":
				Activator.i_D7_1.setMessage(message_content);
				break;
			case "D7.2":
				Activator.i_D7_2.setMessage(message_content);
				break;
			case "D8.1":
				Activator.i_D8_1.setMessage(message_content);
				break;
			case "D8.2":
				Activator.i_D8_2.setMessage(message_content);
				break;
			case "D11.1":
				Activator.i_D11_1.setMessage(message_content);
				break;
			case "D11.2":
				Activator.i_D11_2.setMessage(message_content);
				break;
			case "D11.3":
				Activator.i_D11_3.setMessage(message_content);
				break;
			case "D11.4":
				Activator.i_D11_4.setMessage(message_content);
				break;
			case "TemperatureSensor":
				Activator.i_TemperatureSensor.setMessage(message_content);
				break;
		}
		
	}
	
	
}
