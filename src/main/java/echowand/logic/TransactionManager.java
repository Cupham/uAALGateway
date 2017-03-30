package echowand.logic;

import java.util.LinkedList;
import java.util.logging.Logger;

import echowand.net.Frame;
import echowand.net.Subnet;

public class TransactionManager implements Listener {
    private static final Logger logger = Logger.getLogger(TransactionManager.class.getName());
    private static final String className = TransactionManager.class.getName();
    
    private Subnet subnet;
    private LinkedList<Transaction> transactions;
    
    public TransactionManager(Subnet subnet) {
        logger.entering(className, "TransactionManager", subnet);
        
        this.subnet = subnet;
        transactions = new LinkedList<Transaction>();
        
        logger.exiting(className, "TransactionManager");
    }
    
    private synchronized LinkedList<Transaction> cloneTransactions() {
        return new LinkedList<Transaction>(transactions);
    }
    
    protected synchronized void addTransaction(Transaction t) {
        logger.entering(className, "addTransaction", t);
        
        transactions.add(t);
        
        logger.exiting(className, "addTransaction");
    }
    
    protected synchronized void removeTransaction(Transaction t) {
        logger.entering(className, "removeTransaction", t);
        
        transactions.remove(t);
        
        logger.exiting(className, "removeTransaction");
    }
    
    public synchronized int countActiveTransactions() {
        return transactions.size();
    }
    
    @Override
    public boolean process(Subnet subnet, Frame frame, boolean processed) {
        logger.entering(className, "process", new Object[]{subnet, frame, processed});
        
        boolean ret = false;
        
        if (processed) {
            logger.exiting(className, "process", ret);
            return ret;
        }
        
        for (Transaction transaction : cloneTransactions()) {
            if (frame.getCommonFrame().getTID() == transaction.getTID()) {
                ret |= transaction.receiveResponse(frame);
            }
        }
        
        logger.exiting(className, "process", ret);
        return ret;
    }

    public Transaction createTransaction(TransactionConfig transactionConfig) {
        logger.entering(className, "createTransaction", transactionConfig);
        
        Transaction newTransaction = new Transaction(subnet, this, transactionConfig);
        
        logger.exiting(className, "createTransaction");
        
        return newTransaction;
    }
}
