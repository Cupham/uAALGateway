package echowand.object;

import java.util.logging.Logger;

import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.logic.AnnounceTransactionConfig;
import echowand.logic.Transaction;
import echowand.logic.TransactionManager;
import echowand.net.Subnet;
import echowand.net.SubnetException;

public class LocalObjectNotifyDelegate extends LocalObjectDefaultDelegate {
    private static final Logger logger = Logger.getLogger(LocalObjectNotifyDelegate.class.getName());
    private static final String className = LocalObjectNotifyDelegate.class.getName();
    
    private Subnet subnet;
    private TransactionManager transactionManager;

    public LocalObjectNotifyDelegate(Subnet subnet, TransactionManager transactionManager) {
        logger.entering(className, "LocalObjectNotifyDelegate", new Object[]{subnet, transactionManager});
        
        this.subnet = subnet;
        this.transactionManager = transactionManager;
        
        logger.exiting(className, "LocalObjectNotifyDelegate");
    }

    @Override
    public void notifyDataChanged(NotifyState result, LocalObject object, EPC epc, ObjectData curData, ObjectData oldData) {
        logger.entering(className, "notifyDataChanged", new Object[]{result, object, epc, curData, oldData});
        
        if (object.isObservable(epc) && !curData.equals(oldData)) {
            try {
                AnnounceTransactionConfig transactionConfig = new AnnounceTransactionConfig();
                transactionConfig.addAnnounce(epc, curData.getData());
                transactionConfig.setResponseRequired(false);
                transactionConfig.setSenderNode(subnet.getLocalNode());
                transactionConfig.setReceiverNode(subnet.getGroupNode());
                transactionConfig.setSourceEOJ(object.getEOJ());
                transactionConfig.setDestinationEOJ(new EOJ("0EF001"));
                Transaction transaction = transactionManager.createTransaction(transactionConfig);
                transaction.execute();
            } catch (SubnetException e) {
                e.printStackTrace();
                result.setFail();
            }
        }
        
        result.setDone();
        
        logger.exiting(className, "notifyDataChanged");
    }
}
