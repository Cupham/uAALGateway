package echowand.logic;

import java.util.LinkedList;
import java.util.logging.Logger;

import echowand.common.Data;
import echowand.common.EPC;
import echowand.common.ESV;
import echowand.net.Property;
import echowand.net.StandardPayload;
import echowand.util.Pair;

public class SetGetTransactionConfig extends TransactionConfig {
    private static final Logger logger = Logger.getLogger(SetGetTransactionConfig.class.getName());
    private static final String className = SetGetTransactionConfig.class.getName();
    
    private LinkedList<Pair<EPC, Data>> setProperties;
    private LinkedList<EPC> getProperties;
    private boolean responseRequired;
    private boolean announcePreffered;

    @Override
    public ESV getESV() {
        if (setProperties.isEmpty()) {
            if (announcePreffered) {
                return ESV.INF_REQ;
            } else {
                return ESV.Get;
            }
        }
        
        if (getProperties.isEmpty()) {
            if (responseRequired) {
                return ESV.SetC;
            } else {
                return ESV.SetI;
            }
        }
        
        return ESV.SetGet;
    }

    @Override
    public int getCountPayloads() {
        return 1;
    }

    @Override
    public void addPayloadProperties(int index, StandardPayload payload) {
        logger.entering(className, "addPayloadProperties", new Object[]{index, payload});
        
        if (setProperties.isEmpty()) {
            for (EPC epc : getProperties) {
                payload.addFirstProperty(new Property(epc));
            }
        } else {
            for (Pair<EPC, Data> prop : setProperties) {
                payload.addFirstProperty(new Property(prop.first, prop.second));
            }
            for (EPC epc : getProperties) {
                payload.addSecondProperty(new Property(epc));
            }
        }
        
        logger.exiting(className, "addPayloadProperties");
    }

    public SetGetTransactionConfig() {
        logger.entering(className, "SetGetTransactionConfig");
        
        this.setProperties = new LinkedList<Pair<EPC, Data>>();
        this.getProperties = new LinkedList<EPC>();
        responseRequired = true;
        
        logger.exiting(className, "SetGetTransactionConfig");
    }

    public void setResponseRequired(boolean responseRequired) {
        logger.entering(className, "setResponseRequired", responseRequired);
        
        this.responseRequired = responseRequired;
        
        logger.exiting(className, "setResponseRequired");
    }

    public boolean isResponseRequired() {
        return responseRequired;
    }

    public boolean isAnnouncePreferred() {
        return announcePreffered;
    }

    public void setAnnouncePreferred(boolean announcePreffered) {
        logger.entering(className, "setAnnouncePreferred", announcePreffered);
        
        this.announcePreffered = announcePreffered;
        
        logger.exiting(className, "setAnnouncePreferred");
    }

    public void addSet(EPC epc, Data data) {
        logger.entering(className, "addSet", new Object[]{epc, data});
        
        setProperties.add(new Pair<EPC, Data>(epc, data));
        
        logger.exiting(className, "addSet");
    }

    public void addGet(EPC epc) {
        logger.entering(className, "addGet", epc);
        
        getProperties.add(epc);
        
        logger.exiting(className, "addGet");
    }
}
