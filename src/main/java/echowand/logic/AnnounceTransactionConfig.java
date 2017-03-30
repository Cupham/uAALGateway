package echowand.logic;

import java.util.LinkedList;
import java.util.logging.Logger;

import echowand.common.Data;
import echowand.common.EPC;
import echowand.common.ESV;
import echowand.net.Property;
import echowand.net.StandardPayload;
import echowand.util.Pair;

public class AnnounceTransactionConfig extends TransactionConfig {
    private static final Logger logger = Logger.getLogger(AnnounceTransactionConfig.class.getName());
    private static final String className = AnnounceTransactionConfig.class.getName();
    
    private LinkedList<Pair<EPC, Data>> annoProperties;
    private boolean responseRequired;

    public AnnounceTransactionConfig() {
        logger.entering(className, "AnnounceTransactionConfig");
        
        this.annoProperties = new LinkedList<Pair<EPC, Data>>();
        responseRequired = true;
        
        logger.exiting(className, "AnnounceTransactionConfig");
    }

    @Override
    public ESV getESV() {
        logger.entering(className, "getESV");
        
        if (responseRequired) {
            logger.exiting(className, "getESV", ESV.INFC);
            return ESV.INFC;
        } else {
            logger.exiting(className, "getESV", ESV.INF);
            return ESV.INF;
        }
    }

    @Override
    public int getCountPayloads() {
        return 1;
    }

    @Override
    public void addPayloadProperties(int index, StandardPayload payload) {
        logger.entering(className, "addPayloadProperties", new Object[]{index, payload});
        
        for (Pair<EPC, Data> prop : annoProperties) {
            payload.addFirstProperty(new Property(prop.first, prop.second));
        }
        
        logger.exiting(className, "addPayloadProperties");
    }

    public void addAnnounce(EPC epc, Data data) {
        logger.entering(className, "addAnnounce", new Object[]{epc, data});
        
        annoProperties.add(new Pair<EPC, Data>(epc, data));
        
        logger.exiting(className, "addAnnounce");
    }

    public void setResponseRequired(boolean responseRequired) {
        logger.entering(className, "setResponseRequired", responseRequired);
        
        this.responseRequired = responseRequired;
        
        logger.exiting(className, "setResponseRequired");
    }

    public boolean isResponseRequired() {
        return responseRequired;
    }
}
