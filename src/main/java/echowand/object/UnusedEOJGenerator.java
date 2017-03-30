package echowand.object;

import java.util.HashMap;
import java.util.HashSet;

import echowand.common.ClassEOJ;
import echowand.common.EOJ;
import echowand.logic.TooManyObjectsException;

public class UnusedEOJGenerator {
    private HashMap<ClassEOJ, Byte> usedEOJMap;
    private HashSet<EOJ> usedEOJSet;

    public UnusedEOJGenerator() {
        usedEOJMap = new HashMap<ClassEOJ, Byte>();
        usedEOJSet = new HashSet<EOJ>();
    }
    
    private boolean isValidInstanceCode(byte instanceCode) {
        return (1 <= instanceCode && instanceCode <= 0x7f);
    }

    public synchronized EOJ generate(ClassEOJ ceoj) throws TooManyObjectsException {
        byte unusedCode = 1;
        Byte b = usedEOJMap.get(ceoj);
        
        if (b != null) {
            unusedCode = (byte)(b + 1);
        }
        
        for (;;) {
            if (!isValidInstanceCode(unusedCode)) {
                throw new TooManyObjectsException("too many generated eojs for " + ceoj);
            }
            
            if (!usedEOJSet.contains(ceoj.getEOJWithInstanceCode(unusedCode))) {
                break;
            }
            
            unusedCode += 1;
        }

        usedEOJMap.put(ceoj, unusedCode);
        
        return ceoj.getEOJWithInstanceCode(unusedCode);
    }

    public synchronized void addUsed(EOJ eoj) {
        usedEOJSet.add(eoj);
    }

    public synchronized boolean isUsed(EOJ eoj) {
        Byte b = usedEOJMap.get(eoj.getClassEOJ());
        if (b != null && (eoj.getInstanceCode() <= b)) {
            return true;
        }
        
        return usedEOJSet.contains(eoj);
    }
}
