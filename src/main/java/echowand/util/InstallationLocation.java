package echowand.util;

public  enum InstallationLocation {

	Invalid(0x00)	,
	LivingRoom(0x80),	
	DiningRoom(0x10),
	Kitchen(0x18)	,
	Bathroom(0x20)	,
	Lavatory(0x28)	,	
	Washroom(0x30)	;
	
	//private byte code;
	private InstallationLocation(int code) {
		//this.code = (byte)code;
	}
	
}
