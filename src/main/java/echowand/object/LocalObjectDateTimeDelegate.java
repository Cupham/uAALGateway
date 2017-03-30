package echowand.object;

import java.util.Calendar;
import java.util.logging.Logger;

import echowand.common.EPC;

public class LocalObjectDateTimeDelegate extends LocalObjectDefaultDelegate {
    private static final Logger logger = Logger.getLogger(LocalObjectDateTimeDelegate.class.getName());
    private static final String className = LocalObjectDateTimeDelegate.class.getName();

    @Override
    public void getData(GetState result, LocalObject object, EPC epc) {
        logger.entering(className, "getData", new Object[]{result, object, epc});
        
        Calendar cal = Calendar.getInstance();
        
        switch (epc) {
            case x97:
                byte hour = (byte)cal.get(Calendar.HOUR_OF_DAY);
                byte minute = (byte)cal.get(Calendar.MINUTE);
                result.setGetData(new ObjectData(hour, minute));
                break;
            case x98:
                int year = cal.get(Calendar.YEAR);
                byte year1 = (byte)(((0x0000ff00) & year) >> 8);
                byte year2 = (byte)(0x000000ff & year);
                byte month = (byte)(cal.get(Calendar.MONTH) + 1);
                byte day = (byte)cal.get(Calendar.DAY_OF_MONTH);
                result.setGetData(new ObjectData(year1, year2, month, day));
                break;
            default :
            	break;
        }
        
        logger.exiting(className, "getData");
    }
}
