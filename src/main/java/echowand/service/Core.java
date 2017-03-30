package echowand.service;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import echowand.logic.Listener;
import echowand.logic.MainLoop;
import echowand.logic.RequestDispatcher;
import echowand.logic.TooManyObjectsException;
import echowand.logic.TransactionManager;
import echowand.net.Inet4Subnet;
import echowand.net.Subnet;
import echowand.net.SubnetException;
import echowand.object.AnnounceRequestProcessor;
import echowand.object.LocalObject;
import echowand.object.LocalObjectManager;
import echowand.object.RemoteObjectManager;
import echowand.object.SetGetRequestProcessor;

/**
 * @author HIEP NGUYEN
 */
public class Core {
    private static final Logger LOGGER = Logger.getLogger(Core.class.getName());
    private static final String CLASS_NAME = Core.class.getName();
    
    private Subnet subnet;
    private TransactionManager transactionManager;
    private RemoteObjectManager remoteManager;
    private LocalObjectManager localManager;
    private LocalObject nodeProfileObject;
    private RequestDispatcher requestDispatcher;
    private MainLoop mainLoop;
    private SetGetRequestProcessor setGetRequestProcessor;
    private AnnounceRequestProcessor announceRequestProcessor;
    private ObserveResultProcessor observeResultProcessor;
    private CaptureResultObserver captureResultObserver;
    
    private NodeProfileObjectConfig nodeProfileObjectConfig;
    private LinkedList<LocalObjectConfig> localObjectConfigs;
    private LinkedList<LocalObjectUpdater> localObjectUpdaters;
    
    private boolean initialized = false;
    private boolean inService = false;
    private boolean captureEnabled = false;
    
    /**
     * @throws SubnetException Inet4Subnet
     */
    public Core() throws SubnetException {
        LOGGER.entering(CLASS_NAME, "Core");
        
        this.subnet = new CaptureSubnet(Inet4Subnet.startSubnet());
        nodeProfileObjectConfig = new NodeProfileObjectConfig();
        localObjectConfigs = new LinkedList<LocalObjectConfig>();
        localObjectUpdaters = new LinkedList<LocalObjectUpdater>();
        
        LOGGER.exiting(CLASS_NAME, "Core");
    }
    
    /**
     * 
     */
    public Core(Subnet subnet) {
        LOGGER.entering(CLASS_NAME, "Core", subnet);
        
        this.subnet = subnet;
        nodeProfileObjectConfig = new NodeProfileObjectConfig();
        localObjectConfigs = new LinkedList<LocalObjectConfig>();
        localObjectUpdaters = new LinkedList<LocalObjectUpdater>();
        
        LOGGER.exiting(CLASS_NAME, "Core");
    }
    
    /**
     * 
     */
    public NodeProfileObjectConfig getNodeProfileObjectConfig() {
        return nodeProfileObjectConfig;
    }
    
    /**
     * 
     */
    public boolean addLocalObjectConfig(LocalObjectConfig config) {
        LOGGER.entering(CLASS_NAME, "addLocalObjectConfig", config);
        
        boolean result = localObjectConfigs.add(config);
        
        LOGGER.exiting(CLASS_NAME, "addLocalObjectConfig", result);
        return result;
    }
    
    /**
     * æŒ‡å®šã�•ã‚Œã�ŸLocalObjectConfigã‚’æŠ¹æ¶ˆã�™ã‚‹ã€‚
     * @param config æŠ¹æ¶ˆã�™ã‚‹LocalObjectConfig
     * @return æŠ¹æ¶ˆã�«æˆ�åŠŸã�—ã�Ÿã‚‰trueã€�ã��ã�†ã�§ã�ªã�‘ã‚Œã�°false
     */
    public boolean removeLocalObjectConfig(LocalObjectConfig config) {
        LOGGER.entering(CLASS_NAME, "removeLocalObjectConfig", config);
        
        boolean result = localObjectConfigs.remove(config);
        
        LOGGER.exiting(CLASS_NAME, "removeLocalObjectConfig", result);
        return result;
    }
    
    /**
     * åˆ©ç”¨ã�—ã�¦ã�„ã‚‹Subnetã‚’è¿”ã�™
     * @return åˆ©ç”¨ã�—ã�¦ã�„ã‚‹Subnet
     */
    public Subnet getSubnet() {
        return subnet;
    }
    
    /**
     * åˆ©ç”¨ä¸­ã�®TransactionManagerã‚’è¿”ã�™ã€‚initializeãƒ¡ã‚½ãƒƒãƒ‰ã‚’å‘¼ã�³å‡ºã�™ã�¾ã�§ã�¯nullã‚’è¿”ã�™ã€‚
     * @return åˆ©ç”¨ä¸­ã�®TransactionManager
     */
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }
    
    /**
     * åˆ©ç”¨ä¸­ã�®RemoteObjectManagerã‚’è¿”ã�™ã€‚initializeãƒ¡ã‚½ãƒƒãƒ‰ã‚’å‘¼ã�³å‡ºã�™ã�¾ã�§ã�¯nullã‚’è¿”ã�™ã€‚
     * @return åˆ©ç”¨ä¸­ã�®RemoteObjectManager
     */
    public RemoteObjectManager getRemoteObjectManager() {
        return remoteManager;
    }
    
    /**
     * åˆ©ç”¨ä¸­ã�®LocalObjectManagerã‚’è¿”ã�™ã€‚initializeãƒ¡ã‚½ãƒƒãƒ‰ã‚’å‘¼ã�³å‡ºã�™ã�¾ã�§ã�¯nullã‚’è¿”ã�™ã€‚
     * @return åˆ©ç”¨ä¸­ã�®LocalObjectManager
     */
    public  LocalObjectManager getLocalObjectManager() {
        return localManager;
    }
    
    /**
     * ãƒŽãƒ¼ãƒ‰ãƒ—ãƒ­ãƒ•ã‚¡ã‚¤ãƒ«ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆã�¨ã�—ã�¦åˆ©ç”¨ä¸­ã�®LocalObjectã‚’è¿”ã�™ã€‚
     * initializeãƒ¡ã‚½ãƒƒãƒ‰ã‚’å‘¼ã�³å‡ºã�™ã�¾ã�§ã�¯nullã‚’è¿”ã�™ã€‚
     * @return ãƒŽãƒ¼ãƒ‰ãƒ—ãƒ­ãƒ•ã‚¡ã‚¤ãƒ«ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆã‚’è¡¨ã�™LocalObject
     */
    public LocalObject getNodeProfileObject() {
        return nodeProfileObject;
    }
    
    /**
     * åˆ©ç”¨ä¸­ã�®RequestDispatcherã‚’è¿”ã�™ã€‚initializeãƒ¡ã‚½ãƒƒãƒ‰ã‚’å‘¼ã�³å‡ºã�™ã�¾ã�§ã�¯nullã‚’è¿”ã�™ã€‚
     * @return åˆ©ç”¨ä¸­ã�®RequestDispatcher
     */
    public RequestDispatcher getRequestDispatcher() {
        return requestDispatcher;
    }
    
    /**
     * åˆ©ç”¨ä¸­ã�®MainLoopã‚’è¿”ã�™ã€‚initializeãƒ¡ã‚½ãƒƒãƒ‰ã‚’å‘¼ã�³å‡ºã�™ã�¾ã�§ã�¯nullã‚’è¿”ã�™ã€‚
     * @return åˆ©ç”¨ä¸­ã�®MainLoop
     */
    public MainLoop getMainLoop() {
        return mainLoop;
    }
    
    /**
     * åˆ©ç”¨ä¸­ã�®SetGetRequestProcessorã‚’è¿”ã�™ã€‚initializeãƒ¡ã‚½ãƒƒãƒ‰ã‚’å‘¼ã�³å‡ºã�™ã�¾ã�§ã�¯nullã‚’è¿”ã�™ã€‚
     * @return åˆ©ç”¨ä¸­ã�®SetGetRequestProcessor
     */
    public SetGetRequestProcessor getSetGetRequestProcessor() {
        return setGetRequestProcessor;
    }
    
    /**
     * åˆ©ç”¨ä¸­ã�®AnnounceRequestProcessorã‚’è¿”ã�™ã€‚initializeãƒ¡ã‚½ãƒƒãƒ‰ã‚’å‘¼ã�³å‡ºã�™ã�¾ã�§ã�¯nullã‚’è¿”ã�™ã€‚
     * @return åˆ©ç”¨ä¸­ã�®AnnounceRequestProcessor
     */
    public AnnounceRequestProcessor getAnnounceRequestProcessor() {
        return announceRequestProcessor;
    }
    
    public ObserveResultProcessor getObserveResultProsessor() {
        return observeResultProcessor;
    }
    
    public CaptureResultObserver getCaptureResultObserver() {
        return captureResultObserver;
    }
    
    private TransactionManager createTransactionManager(Subnet subnet) {
        LOGGER.entering(CLASS_NAME, "createTransactionManager", new Object[]{subnet});
        
        TransactionManager transactionManager = new TransactionManager(subnet);
        
        LOGGER.exiting(CLASS_NAME, "createTransactionManager", transactionManager);
        return transactionManager;
    }
    
    private RemoteObjectManager createRemoteObjectManager() {
        LOGGER.entering(CLASS_NAME, "createRemoteObjectManager");
        
        RemoteObjectManager remoteManager = new RemoteObjectManager();
        
        LOGGER.exiting(CLASS_NAME, "createRemoteObjectManager", remoteManager);
        return remoteManager;
    }
    
    private LocalObjectManager createLocalObjectManager() {
        LOGGER.entering(CLASS_NAME, "createLocalObjectManager");
        
        LocalObjectManager localManager = new LocalObjectManager();
        
        LOGGER.exiting(CLASS_NAME, "createLocalObjectManager", localManager);
        return localManager;
    }
    
    private RequestDispatcher createRequestDispatcher() {
        LOGGER.entering(CLASS_NAME, "createRequestDispatcher");
        
        RequestDispatcher requestDispatcher = new RequestDispatcher();
        
        LOGGER.exiting(CLASS_NAME, "createRequestDispatcher", requestDispatcher);
        return requestDispatcher;
    }
    
    private SetGetRequestProcessor createSetGetRequestProcessor(LocalObjectManager localManager) {
        LOGGER.entering(CLASS_NAME, "createSetGetRequestProcessor", new Object[]{localManager});
        
        SetGetRequestProcessor setGetRequestProcessor = new SetGetRequestProcessor(localManager);
        
        LOGGER.exiting(CLASS_NAME, "createSetGetRequestProcessor", setGetRequestProcessor);
        return setGetRequestProcessor;
    }

    private AnnounceRequestProcessor createAnnounceRequestProcessor(LocalObjectManager localManager, RemoteObjectManager remoteManager) {
        LOGGER.entering(CLASS_NAME, "createAnnounceRequestProcessor", new Object[]{localManager, remoteManager});
        
        AnnounceRequestProcessor announceRequestProcessor = new AnnounceRequestProcessor(localManager, remoteManager);
        
        LOGGER.exiting(CLASS_NAME, "createAnnounceRequestProcessor", announceRequestProcessor);
        return announceRequestProcessor;
    }

    private ObserveResultProcessor createObserveResultProcessor() {
        LOGGER.entering(CLASS_NAME, "createObserveResultProcessor");
        
        ObserveResultProcessor observeResultProcessor = new ObserveResultProcessor();
        
        LOGGER.exiting(CLASS_NAME, "createObserveResultProcessor", observeResultProcessor);
        return observeResultProcessor;
    }
    
    private CaptureResultObserver createCaptureResultObserver() {
        LOGGER.entering(CLASS_NAME, "createCaptureResultObserver");
        
        CaptureResultObserver captureResultObserver = new CaptureResultObserver();
        
        LOGGER.exiting(CLASS_NAME, "createCaptureResultObserver", captureResultObserver);
        return captureResultObserver;
    }
    
    private MainLoop createMainLoop(Subnet subnet, Listener... listeners) {
        LOGGER.entering(CLASS_NAME, "createMainLoop", new Object[]{subnet, listeners});
        
        MainLoop mainLoop = new MainLoop();
        mainLoop.setSubnet(subnet);
        for (Listener listener: listeners) {
            mainLoop.addListener(listener);
        }
        
        LOGGER.exiting(CLASS_NAME, "createMainLoop", mainLoop);
        return mainLoop;
    }
    
    /**
     * Coreã�Œåˆ�æœŸåŒ–æ¸ˆã�¿ã�§ã�‚ã‚‹ã�‹è¿”ã�™ã€‚
     * @return åˆ�æœŸåŒ–æ¸ˆã�¿ã�§ã�‚ã‚Œã�°trueã€�åˆ�æœŸåŒ–æ¸ˆã�¿ã�§ã�ªã�‘ã‚Œã�°false
     */
    public boolean isInitialized() {
        return initialized;
    }
    
    /**
     * Coreã�Œå®Ÿè¡Œä¸­ã�§ã�‚ã‚‹ã�‹è¿”ã�™ã€‚
     * @return å®Ÿè¡Œä¸­ã�§ã�‚ã‚Œã�°trueã€�å®Ÿè¡Œä¸­ã�§ã�ªã�‘ã‚Œã�°false
     */
    public boolean isInService() {
        return inService;
    }
    
    public boolean isCaptureEnabled() {
        return captureEnabled;
    }
    
    private void createLocalObjects() throws TooManyObjectsException {
        for (LocalObjectConfig config : localObjectConfigs) {
            LocalObjectCreator creator = new LocalObjectCreator(config);
            LocalObjectCreatorResult creatorResult = creator.create(this);
            if (creatorResult.updater != null) {
                localObjectUpdaters.add(creatorResult.updater);
            }
        }
    }
    
    private CaptureSubnet getCaptureSubnet() {
        if (subnet instanceof ExtendedSubnet) {
            return ((ExtendedSubnet)subnet).getSubnet(CaptureSubnet.class);
        } else {
            return null;
        }
    }

    /**
     * Coreã‚’åˆ�æœŸåŒ–ã�™ã‚‹ã€‚
     * @return åˆ�æœŸåŒ–ã�Œæˆ�åŠŸã�™ã‚Œã�°trueã€�ã�™ã�§ã�«åˆ�æœŸåŒ–æ¸ˆã�¿ã�§ã�‚ã‚Œã�°false
     * @throws TooManyObjectsException ãƒ­ãƒ¼ã‚«ãƒ«ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆã�®æ•°ã�Œå¤šã�™ã�Žã‚‹å ´å�ˆ
     */
    public synchronized boolean initialize() throws TooManyObjectsException {
        LOGGER.entering(CLASS_NAME, "initialize");
        
        if (initialized) {
            LOGGER.exiting(CLASS_NAME, "initialize", false);
            return false;
        }

        transactionManager = createTransactionManager(subnet);
        remoteManager = createRemoteObjectManager();
        localManager = createLocalObjectManager();

        setGetRequestProcessor = createSetGetRequestProcessor(localManager);
        announceRequestProcessor = createAnnounceRequestProcessor(localManager, remoteManager);
        observeResultProcessor = createObserveResultProcessor();

        requestDispatcher = createRequestDispatcher();
        requestDispatcher.addRequestProcessor(setGetRequestProcessor);
        requestDispatcher.addRequestProcessor(announceRequestProcessor);
        requestDispatcher.addRequestProcessor(observeResultProcessor);

        captureResultObserver = createCaptureResultObserver();

        CaptureSubnet captureSubnet = getCaptureSubnet();
        if (captureSubnet != null) {
            captureSubnet.addObserver(captureResultObserver);
            captureEnabled = true;
        }
        
        try {
            LocalObjectCreatorResult creatorResult = new LocalObjectCreator(nodeProfileObjectConfig).create(this);
            nodeProfileObject = creatorResult.object;
            if (creatorResult.updater != null) {
                localObjectUpdaters.add(creatorResult.updater);
            }
        } catch (TooManyObjectsException ex) {
            LOGGER.logp(Level.WARNING, CLASS_NAME, "initialize", "cannot create NodeProfileObject", ex);
        }

        initialized = true;
        
        LOGGER.exiting(CLASS_NAME, "initialize", true);
        return true;
    }
    
    private void startUpdateThreads() {
        for (LocalObjectUpdater updater : localObjectUpdaters) {
            new Thread(updater).start();
        }
    }
    
    private void startMainLoopThread() {
        mainLoop = createMainLoop(subnet, requestDispatcher, transactionManager);
        new Thread(mainLoop).start();
    }
    
    private boolean startThreads() {
        LOGGER.entering(CLASS_NAME, "startThreads");
        
        if (inService) {
            LOGGER.exiting(CLASS_NAME, "startThreads", false);
            return false;
        }

        if (!isInitialized()) {
            LOGGER.exiting(CLASS_NAME, "startThreads", false);
            return false;
        }
        
        startUpdateThreads();
        
        startMainLoopThread();

        inService = true;
        
        LOGGER.exiting(CLASS_NAME, "startThreads", true);
        return true;
    }
    
    /**
     * Coreã‚’å®Ÿè¡Œã�™ã‚‹ã€‚
     * åˆ�æœŸåŒ–ã�•ã‚Œã�¦ã�„ã�ªã�„å ´å�ˆã�«ã�¯åˆ�æœŸåŒ–ã‚’å…ˆã�«è¡Œã�†ã€‚
     * addLocalObjectConfigã�§ç™»éŒ²ã�•ã‚Œã�Ÿãƒ­ãƒ¼ã‚«ãƒ«ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆã�®ç”Ÿæˆ�ã�¨ç™»éŒ²ã‚’è¡Œã�„ã€�å®Ÿè¡Œã�«å¿…è¦�ã�ªã‚¹ãƒ¬ãƒƒãƒ‰ã‚’é–‹å§‹ã�™ã‚‹ã€‚
     * @return å®Ÿè¡Œã�Œæˆ�åŠŸã�™ã‚Œã�°trueã€�ã�™ã�§ã�«å®Ÿè¡Œæ¸ˆã�¿ã�§ã�‚ã‚Œã�°false
     * @throws TooManyObjectsException ãƒ­ãƒ¼ã‚«ãƒ«ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆã�®æ•°ã�Œå¤šã�™ã�Žã‚‹å ´å�ˆ
     */
    public synchronized boolean startService() throws TooManyObjectsException {
        LOGGER.entering(CLASS_NAME, "startService");
        
        if (inService) {
            LOGGER.exiting(CLASS_NAME, "startService", false);
            return false;
        }

        if (!isInitialized()) {
            boolean result = initialize();
            if (!result) {
                LOGGER.exiting(CLASS_NAME, "startService", false);
            }
        }

        createLocalObjects();
        
        startThreads();

        LOGGER.exiting(CLASS_NAME, "startService", true);
        return true;
    }
}
