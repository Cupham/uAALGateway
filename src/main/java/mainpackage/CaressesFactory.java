package mainpackage;

import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.rdf.ResourceFactory;

public class CaressesFactory implements ResourceFactory {
	
	public static final int CARESSES_COMPONENT = 0;
	
	public static final int CKB                = 1;
	public static final int CSPEM              = 2;
	public static final int CAHRIM             = 3;
	public static final int AALEnvironment     = 100;
	public static final int DATA_MESSAGE       = 4;
	
	public static final int D1                 = 5;
	public static final int D2                 = 6;
	public static final int D3                 = 7;
	public static final int D4                 = 8;
	public static final int D5                 = 9;
	public static final int D6                 = 10;
	public static final int D7                 = 11;
	public static final int D8                 = 12;
	public static final int D9                 = 13;
	public static final int D10                = 14;
	public static final int D11                = 15;
	public static final int EchonetSensor      = 150;
	
	public static final int TemperatureSensor = 151;
	public static final int D1_1               = 16;
	public static final int D1_2               = 17;
	
	public static final int D2_1               = 18;
	public static final int D2_2               = 19;
	public static final int D2_3               = 20;
	public static final int D2_4               = 21;
	
	public static final int D3_1               = 22;
	public static final int D3_2               = 23;
	
	public static final int D4_1               = 24;
	public static final int D4_2               = 25;
	
	public static final int D5_1               = 26;
	public static final int D5_2               = 27;
	
	public static final int D6_1               = 28;
	public static final int D6_2               = 29;
	public static final int D6_3               = 30;
	
	public static final int D7_1               = 31;
	public static final int D7_2               = 32;
	
	public static final int D8_1               = 33;
	public static final int D8_2               = 34;
	
	public static final int D11_1              = 35;
	public static final int D11_2              = 36;
	public static final int D11_3              = 37;
	public static final int D11_4              = 38;
	
	
	
	public Resource createInstance(String classURI, String instanceURI, int factoryIndex){
		switch(factoryIndex){
			case CARESSES_COMPONENT:
				return new CaressesComponent(instanceURI);
				
			case CKB:
				return new Ckb(instanceURI);
			case CSPEM:
				return new Cspem(instanceURI);
			case CAHRIM:
				return new Cahrim(instanceURI);
			case AALEnvironment:
				return new AALEnvironment(instanceURI);
				
			case DATA_MESSAGE:
				return new DataMessage(instanceURI);
			case D1:
				return new D1(instanceURI);
			case D2:
				return new D2(instanceURI);
			case D3:
				return new D3(instanceURI);
			case D4:
				return new D4(instanceURI);
			case D5:
				return new D5(instanceURI);
			case D6:
				return new D6(instanceURI);
			case D7:
				return new D7(instanceURI);
			case D8:
				return new D8(instanceURI);
			case D9:
				return new D9(instanceURI);
			case D10:
				return new D10(instanceURI);
			case D11:
				return new D11(instanceURI);
			case EchonetSensor:
				return new EchonetSensor(instanceURI);
				
			case TemperatureSensor:
				return new TemperatureSensor(instanceURI);
				
			case D1_1:
				return new D1_1(instanceURI);
			case D1_2:
				return new D1_2(instanceURI);
				
			case D2_1:
				return new D2_1(instanceURI);
			case D2_2:
				return new D2_2(instanceURI);
			case D2_3:
				return new D2_3(instanceURI);
			case D2_4:
				return new D2_4(instanceURI);
				
			case D3_1:
				return new D3_1(instanceURI);
			case D3_2:
				return new D3_2(instanceURI);
				
			case D4_1:
				return new D4_1(instanceURI);
			case D4_2:
				return new D4_2(instanceURI);
				
			case D5_1:
				return new D5_1(instanceURI);
			case D5_2:
				return new D5_2(instanceURI);
				
			case D6_1:
				return new D6_1(instanceURI);
			case D6_2:
				return new D6_2(instanceURI);
			case D6_3:
				return new D6_3(instanceURI);
				
			case D7_1:
				return new D7_1(instanceURI);
			case D7_2:
				return new D7_2(instanceURI);
				
			case D8_1:
				return new D8_1(instanceURI);
			case D8_2:
				return new D8_2(instanceURI);
				
			case D11_1:
				return new D11_1(instanceURI);
			case D11_2:
				return new D11_2(instanceURI);
			case D11_3:
				return new D11_3(instanceURI);
			case D11_4:
				return new D11_4(instanceURI);
		}
		
		return null;
	}

}
