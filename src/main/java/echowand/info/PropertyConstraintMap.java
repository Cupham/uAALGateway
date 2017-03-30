package echowand.info;

import java.util.Arrays;

import echowand.common.PropertyMap;
import echowand.util.ConstraintSize;
/*
 * @author HiepNguyen
 */
public class PropertyConstraintMap extends ConstraintSize {
	public PropertyConstraintMap() {
        super(1, 17);
    }
    
    @Override
    public boolean isValid(byte[] data) {
        PropertyMap map = new PropertyMap(data);
        return Arrays.equals(data, map.toBytes());
    }
}
