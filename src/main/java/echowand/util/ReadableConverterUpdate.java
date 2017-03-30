package echowand.util;

import echowand.object.ObjectData;

/**
 * @author Cu Pham
 *
 */
public abstract class ReadableConverterUpdate extends ReadableConverter {

}

class ReadableConverterMeasuredPower extends ReadableConverter {
	private String unit;
	
	public ReadableConverterMeasuredPower() {
		this("");
	}
	
	public ReadableConverterMeasuredPower(String unit) {
		this.unit = unit;
	}

	public String dataToString(ObjectData data) {
		StringBuilder builder = new StringBuilder();
		int datasize = data.size();
		int power = 0;
		if(datasize != 2 && datasize != 4) {
			return INVALID;
		}
		if(datasize == 2) {
			/**
			 * EPC.x84
			 */
			int b0 = (int)(data.get(0));
			int b1 = (int)(data.get(1));
			power = b0<<8 + b1;
			if(power > 65533) {
				return "Overflow: FFFF";
			} else if (power < 0) {
				return "Underflow: FFFE";
			}
		}
		
		if(datasize ==4) {
			/**
			 * EPC.x85
			 */
			
		}
		
		builder.append(power);
		builder.append("W");
		return builder.toString() + unit;
	}
}