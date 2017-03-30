package echowand.info;

import echowand.common.ClassEOJ;
import echowand.common.EPC;
/*
 * @author HiepNguyen
 */
public interface ObjectInfo {
	public ClassEOJ getClassEOJ();
	public PropertyInfo get(EPC epc);
	public PropertyInfo getAtIndex(int index);
	public int size();
}
