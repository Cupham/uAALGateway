package mainpackage;

public class Component {
	
	protected static final String CAHRIM = "CAHRIM";
	protected static final String CKB    = "CKB";
	protected static final String CSPEM  = "CSPEM";
	protected static final String AALENVIRONMENT  = "AALENVIRONMENT";
	
	protected static boolean is_Cahrim;
	protected static boolean is_Ckb;
	protected static boolean is_Cspem;
	protected static boolean is_AALEnviroment;
	
	protected static String component_ID;
	
	public Component(){
		
	}
	
	public static void setThisComponentAs(String component){
		
		if (component.equals(CAHRIM)){
			
			is_Cahrim = true;
			is_Ckb    = false;
			is_Cspem  = false;
			is_AALEnviroment = false;
			
			component_ID = "[" + CAHRIM + "]";
			
		} else if (component.equals(CKB)){
			
			is_Cahrim = false;
			is_Ckb    = true;
			is_Cspem  = false;
			is_AALEnviroment = false;
			
			component_ID = "[" + CKB + "   ]";

		} else if (component.equals(CSPEM)){
			
			is_Cahrim = false;
			is_Ckb    = false;
			is_Cspem  = true;
			is_AALEnviroment = false;
			
			component_ID = "[" + CSPEM + " ]";

		} else if(component.equals(AALENVIRONMENT)) {
			is_Cahrim = false;
			is_Ckb    = false;
			is_Cspem  = false;
			is_AALEnviroment = true;
			
			component_ID = "[" + AALENVIRONMENT + " ]";
		}
		
	}

}
